/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(59090)) {
            System.out.println("The server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(2);
            while (true) {
                Game game = new Game();
                pool.execute(new Player(game, listener.accept(), "A"));
                pool.execute(new Player(game, listener.accept(), "B"));
            }
        }
    }
}