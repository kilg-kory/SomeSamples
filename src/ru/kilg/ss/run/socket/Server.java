package ru.kilg.ss.run.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SomeSamples
 * Server - [Just receive message and answer Ok]
 *
 * @author KIlG
 * @version 0.1
 * Create 21.05.19
 */
public class Server extends Thread {
    private final Socket s;
    private static AtomicInteger counter = new AtomicInteger();

    private Server(Socket s) {
        this.s = s;
        counter.incrementAndGet();
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(3456, 0, InetAddress.getByName("localhost"))) {
            System.out.println("Server started");

            serverSocket.setSoTimeout(100);

            Thread t = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        new Server(serverSocket.accept());
                    } catch (SocketTimeoutException e) {
                        //nothing
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();

            if (new Scanner(System.in).next().equals("exit")) {
                t.interrupt();
            }
            t.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            byte[] buf = new byte[64 * 1024];

            String inputMessage = "";
            while (!inputMessage.equals("exit")) {
                int r = is.read(buf);

                if (r > 0) {
                    inputMessage = new String(buf, 0, r);
                    System.out.println(counter.get() + " -> " + inputMessage);
                    os.write("Ok".getBytes());

                }
            }

            s.close();
            counter.decrementAndGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
