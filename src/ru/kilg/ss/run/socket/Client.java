package ru.kilg.ss.run.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * SomeSamples
 * Client - [Just send message.]
 *
 * @author KIlG
 * @version 0.1
 * Create 21.05.19
 */
public class Client {

    public static void main(String[] args) {


        try (Socket s = new Socket("localhost", 34543)) {
            System.out.println(s.getInetAddress().toString() + ":" + s.getPort() + " connected.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            String message = "";
            while (!message.equals("exit")) {

                message = "";
                while (message.length() == 0) {
                    System.out.print("Type message:(or exit to quit)> ");
                    message = br.readLine();
                }

                s.getOutputStream().write(message.getBytes());

                byte[] buf = new byte[64 * 1024];
                int countBytes = s.getInputStream().read(buf);
                if (countBytes == -1) {
                    break;
                }

                System.out.println("<Server: " + new String(buf, 0, countBytes));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
