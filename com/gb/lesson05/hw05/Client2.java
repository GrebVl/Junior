package com.gb.lesson05.hw05;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client2 {
    private static final int SERVER_PORT = Server.PORT;
    private static final String URL = "localhost";

    public static void main(String[] args){

        try(Socket client = new Socket(URL, SERVER_PORT)){
            new Thread(() -> {outputMessage(client);}).start();

            InputMessage(client);

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }


    private static void InputMessage(Socket client){
        try (Scanner input = new Scanner(client.getInputStream())) {
            while (!client.isClosed()) {
                if(input.hasNext()){
                    System.out.println(input.nextLine());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void outputMessage(Socket client){
        try (PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
            Scanner consoleScanner = new Scanner(System.in);
            while (true) {
                String consoleInput = consoleScanner.nextLine();
                output.println(consoleInput);
                if (Objects.equals("q", consoleInput)) {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}