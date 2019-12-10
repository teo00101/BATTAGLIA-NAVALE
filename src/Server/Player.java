/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author informatica
 */
public class Player implements Runnable{
    
    private String name;
    private Player opponent;
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private Game game;
    
    public Player(Game game, Socket socket, String name) throws Exception {
        this.name = name;
        this.socket = socket;
        this.game = game;
        input = new  Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        output.println("Welcome " + name);
        if (name.equals("A")) {
            game.setCurrentPlayer(this);
            output.println("Attendendo giocatore B");
            System.out.println("Creato giocatore A");
        } else {
            game.getCurrentPlayer().opponent = this;
            opponent = game.getCurrentPlayer();
            System.out.println("Inizio partita");
            System.out.println("Creato giocatore B");
        }
    }
    
    
    
}
