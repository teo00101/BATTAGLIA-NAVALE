/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private boolean pronto = false;
    
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
        printRules();
        if (name.equals("A")) {
            game.setCurrentPlayer(this);
            game.placeShip(this);
            pronto = true;
        } else {
            game.getCurrentPlayer().opponent = this;
            opponent = game.getCurrentPlayer();
            game.placeShip(this);
            pronto = true;
        }
        
        // controllo se i due giocatori sono pronti
        if (this != game.getCurrentPlayer()) {// se il giocatore non e' quello di cui e' il turno errore
            output.println("MESSAGE Non e' il tuo turno, attendi....");
        } else if (opponent == null) {// se non c'e ancora l'avversario errore
            output.println("MESSAGE Ancora non c'e un avversario, attendi....");
        } else if (!opponent.pronto) {// se l'avversario non ha ancora piazzato le navi errore
            output.println("MESSAGE L'avversario non e'ancora pronto, attendi....");
        }
        // se uno dei due casi sopra si verifica  aspetto
        while (opponent == null || !opponent.pronto) {
            // aspetto del tempo prima di riprovare a far giocare il giocatore
            try {
                Thread.sleep(2000);
            } catch (Exception f) {
                System.out.println("Errore");
            }
        }
        
        if (name.equals("A")) {
            game.getCurrentPlayer().output.println("PLAY");
        }
        
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
            sparo.setX(Game.checkCoordinates(response));
            // recupero la coordinata y dello sparo
            response = input.nextLine();
            sparo.setY(Game.checkCoordinates(response));
            // se le coordinate sono sbagliate avverto il client
            if (!Ship.areCoordinatesInField(sparo.getX(), sparo.getY(), game.getFieldA())) { // il campo puo essere uno qualunque
                output.println("MESSAGE Le coordinate dello sparo sono sbagliate");
                output.println("MESSAGE Riprovare");
            }
        } while (!Ship.areCoordinatesInField(sparo.getX(), sparo.getY(), game.getFieldA()));// il campo puo essere uno qualunque
        
        try {
            game.move(sparo, this);
            output.println("MESSAGE Mossa valida, perfavore aspettare");
            if (game.hasWinner()) {
                output.println("VICTORY");
                opponent.getOutput().println("DEFEAT");
            } else {
                opponent.getOutput().println("PLAY");
            }
        } catch (Exception e) {
            output.println("MESSAGE " + e.getMessage());
            output.println("PLAY");
        }
        
    }
    
    private void printRules() {
        output.println("MESSAGE -------------------------------------------------------------");
        output.println("MESSAGE Regole di gioco:");
        output.println("MESSAGE la tabella e' 21x21");
        output.println("MESSAGE per posizionare le barche inserire un numero compreso tra 0 e 20 sia come valore d'ascissa che d'ordinata");
        output.println("MESSAGE es. ascissa 0 e ordinata 0 equivalente in alto a sinistra");
        output.println("MESSAGE l'orientamento richiede s=sud, n=nord, e=est, o=ovest");
        output.println("MESSAGE -------------------------------------------------------------");
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

    public boolean getPronto() {
        return pronto;
    }

    public void setPronto(boolean pronto) {
        this.pronto = pronto;
    }
    
}
