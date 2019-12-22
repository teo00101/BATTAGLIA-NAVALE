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
            game.placeShip(this);
            output.println("MESSAGE In attesa dell'avversario");
        } else {
            game.getCurrentPlayer().opponent = this;
            opponent = game.getCurrentPlayer();
            game.placeShip(this);
            output.println("MESSAGE E' il tuo turno");
            
        }
        output.println("PLAY");
        processCommands();
        
    }
    
    private void processCommands() {
        
        String command;
        
        while (input.hasNextLine()) {
            command = input.nextLine();
            if (command.startsWith("QUIT")) {
                return;
            } else if (command.startsWith("MOVE")) {
                processMoveCommand();
            }
        }
    }
    
    private void processMoveCommand() {
        Coordinate sparo = new Coordinate(0, 0);
        String response;
        
        // controllo che i valori di coordinata ricevuti siano giusti
        do {
            output.println("MOVE");
            // recupero la coordinata x dello sparo
            response = input.nextLine();
            sparo.setX(Integer.parseInt(response));
            // recupero la coordinata y dello sparo
            response = input.nextLine();
            sparo.setY(Integer.parseInt(response));
            // se le coordinate sono sbagliate avverto il client
            if (!Ship.areCoordinatesInField(sparo.getX(), sparo.getY(), game.getFieldA())) { // il campo puo essere uno qualunque
                output.println("MESSAGE Le coordinate dello sparo sono sbagliate");
                output.println("MESSAGE Riprovare");
            }
        } while (!Ship.areCoordinatesInField(sparo.getX(), sparo.getY(), game.getFieldA()));// il campo puo essere uno qualunque
        
        try {
            game.move(sparo, this);
            output.println("MESSAGE Mossa valida");
            if (game.hasWinner()) {
                output.println("VICTORY");
                opponent.getOutput().println("DEFEAT");
            }
        } catch (Exception e) {
            output.println("MESSAGE " + e.getMessage());
            output.println("PLAY");
        }
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
    
}
