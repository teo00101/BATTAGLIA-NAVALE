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
    Field fieldA = new Field(21, 21);
    Field fieldB = new Field(21, 21);
    
    private Player currentPlayer;
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public void placeShip(Player player) {
        // 3da2 2da3 1da4 1da5 creazione navi
        Ship ship2 = new Ship(2);
        Ship ship3 = new Ship(3);
        Ship ship4 = new Ship(4);
        Ship ship5 = new Ship(5);
        
        // creazione array di navi per A
        ArrayList<Ship> shipsA = new ArrayList<>();
        shipsA.add(new Ship(ship2));shipsA.add(new Ship(ship2));shipsA.add(new Ship(ship2));
        shipsA.add(new Ship(ship3));shipsA.add(new Ship(ship3));
        shipsA.add(new Ship(ship4));
        shipsA.add(new Ship(ship5));
        
        // creazione array di navi per B
        ArrayList<Ship> shipsB = new ArrayList<>();
        shipsB.add(new Ship(ship2));shipsB.add(new Ship(ship2));shipsB.add(new Ship(ship2));
        shipsB.add(new Ship(ship3));shipsB.add(new Ship(ship3));
        shipsB.add(new Ship(ship4));
        shipsB.add(new Ship(ship5));
        
        String response;
        String orientation;
        int x;
        int y;
        
        // richiesta posizione nave al'utente
        for (int i = 0; i < shipsA.size(); i++) {
            player.getOutput().println("SET_SHIP " + (i + 1) + " Barca " + shipsA.get(i).getNumCaselle() + " posti");
            response = player.getInput().nextLine();
            x = Integer.parseInt(response);
            response = player.getInput().nextLine();
            y = Integer.parseInt(response);
            response = player.getInput().nextLine();
            orientation = response;
            
            // controllo parametri della risposta
            if (checkParameters(orientation, x , y)) {
                
                System.out.println(player.getName() + " " + x + "," + y + "," + orientation);
                
                // utilizzo il campo A
                //affidamento coordinate alla nave
                shipsA.get(i).shipSizing(orientation, x, y);
                if (fieldA.isInTheField(shipsA.get(i)) && !fieldA.isOverlap(shipsA.get(i))) {// aggiungere controllo raggio nave
                    for (int j = 0; j < shipsA.get(i).getNumCaselle(); j++) {
                        fieldA.getCaselle()[shipsA.get(i).getCoordinates().get(j).getX()][shipsA.get(i).getCoordinates().get(j).getY()].setShip(shipsA.get(i));
                    }
                } else {
                    player.getOutput().println("MESSAGE Le navi non si possono sovrapporre");
                    i--;
                }
                
                
            } else {
                player.getOutput().println("MESSAGE Errore inserimento barca");
                player.getOutput().println("MESSAGE Riprovare");
                i--;
            }
           
        }
        
    }
    
    private boolean checkParameters(String orientation, int x, int y) {
        
        boolean correct = false;
        boolean xCorrect = false;
        boolean yCorrect = false;
        boolean orCorrect = false;
        
        // x deve andare da 0 a lunghezza massima tabella
        
        for (int i = 0; i < fieldA.getLength(); i++) {
            if (x == i) {
                xCorrect = true;
            }
        }
        
        // y deve andare da 0 a altezza massima tabella 
        
        for (int i = 0; i < fieldA.getHeight(); i++) {
            if (y == i) {
                yCorrect = true;
            }
        }
         
        // deve essere uguale ad una lattera che rappresenta un segno cardinale
        orientation = orientation.toUpperCase();
        
        if (orientation.equals("N")) {
          orCorrect = true;  
        } else if (orientation.equals("E")) {
            orCorrect = true;
        } else if (orientation.equals("S")) {
            orCorrect = true;
        } else if (orientation.equals("W")) {
            orCorrect = true;
        }
        
        if (xCorrect && yCorrect && orCorrect) {
            correct = true;
        }
        
        return correct;
    }
        
    
}
