/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author informatica
 */
public class Game {
    
    // Campo giocatore A e B
    private Casella[][] boardA = new Casella[21][21];
    private Casella[][] boardB = new Casella[21][21];
    
    private Player currentPlayer;
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public void placeShip(Scanner input, PrintWriter output, String name) {
        // 3da2 2da3 1da4 1da5 creazione navi
        Ship ship2 = new Ship(2);
        Ship ship3 = new Ship(3);
        Ship ship4 = new Ship(4);
        Ship ship5 = new Ship(5);
        
        // creazione array di navi
        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(ship2);ships.add(ship2);ships.add(ship2);
        ships.add(ship3);ships.add(ship3);
        ships.add(ship4);
        ships.add(ship5);
        
        // richiesta posizione nave al'utente
        for (int i = 0; i < ships.size(); i++) {
            output.println("SET_SHIP Barca " + ships.get(i).getCaselle() + " posti");
            System.out.println(name + " " + input.nextLine());
            // implementare controllo coordinate
        }
        
    }
    
}
