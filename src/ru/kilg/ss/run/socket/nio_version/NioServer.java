package ru.kilg.ss.run.socket.nio_version;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * SomeSamples
 * NioServer - Version via Channels and Buffers with non blocking.
 * Add log file. Also NIO
 *
 * @author KIlG
 * @version 0.1
 * Create 22.05.19
 */
public class NioServer {
    private static Spinner spinner = new Spinner();


    public static void main(String[] args) {
        openNonBlockSocketServer();
    }


    private static void openNonBlockSocketServer() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(34543));
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);




            var condition = new Object() {
                boolean b = true;
            };



            while (condition.b) {
                selector.select(key -> {
                            if (key.isAcceptable()) {
                                try {
                                    SocketChannel client = serverSocketChannel.accept();
                                    client.configureBlocking(false);
                                    client.register(selector, SelectionKey.OP_READ);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (key.isReadable()) {
                                try {
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                                    SocketChannel client = (SocketChannel) key.channel();
                                    client.read(byteBuffer);
                                    String message = new String(byteBuffer.array()).trim();

                                    if (message.equals("shutdown")) {
                                        condition.b = false;
                                        printMessage("close socket");
                                        client.close();
                                        printMessage("close socket server");
                                        serverSocketChannel.close();
                                    } else if (message.equals("close")) {
                                        printMessage("close socket");
                                        client.close();
                                    } else {
                                        printMessage(message);

                                        byteBuffer.flip();
                                        client.write(byteBuffer);
                                        byteBuffer.clear();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

//                            if(key.isWritable()){
//                              log to file?
//                           }


                        }, 400
                );

                spinner.printNext();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMessage(String s) {
        spinner.clear();
        System.out.println(s);
    }

    private static void logfile() {
        try {
            var logFile = new RandomAccessFile("chat-log.txt", "rw");
            var inChannel = logFile.getChannel();

            var buffer = ByteBuffer.allocate(48);
            var bytesRead = inChannel.read(buffer);

            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead + " bytes");
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                bytesRead = inChannel.read(buffer);
            }
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
