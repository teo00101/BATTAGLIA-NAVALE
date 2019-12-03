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
    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(59090)) {
            System.out.println("The server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(2);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(listener.accept(), "Player A"));
                
                pool.execute(game.new Player(listener.accept(), "Player B"));
            }
        }
    }
}

class Game {

    // Board cells numbered 0-8, top to bottom, left to right; null if empty
    private Player[] board = new Player[9];

    Player currentPlayer;

    public boolean hasWinner(){return true;} //momentaneo


    /**
     * For communication with the client the player has a socket and associated
     * Scanner and PrintWriter.
     */
    class Player implements Runnable {
        String name;
        Player opponent;
        Socket socket;
        Scanner input;
        PrintWriter output;

        public Player(Socket socket, String mark) {
            this.socket = socket;
            this.name = mark;
        }

        @Override
        public void run() {
            try {
                setup();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (opponent != null && opponent.output != null) {
                    opponent.output.println("OTHER_PLAYER_LEFT");
                }
                try {socket.close();} catch (IOException e) {}
            }
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Welcome " + name);
            if (name.equals("Player A")) {
                currentPlayer = this;
                output.println("MESSAGE Waiting for opponent to connect");
            } else {
                opponent = currentPlayer;
                opponent.opponent = this;
                opponent.output.println("MESSAGE Your move");
            }
        }

    }
}