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
    Field fieldA;
    Field fieldB;
    ArrayList<Ship> shipsA;
    ArrayList<Ship> shipsB;
    
    public Game() {
        fieldA = new Field(21, 21);
        fieldB = new Field(21, 21);
        shipsA = new ArrayList<>();
        shipsB = new ArrayList<>();
        // inserisco nell'array di navi dei due giocatori un preset di base
        AddShip(shipsA);
        AddShip(shipsB);
    }
    
    private Player currentPlayer;
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public void placeShip(Player player) {
        
        // inserimento navi del player A o B nel corrispettivo campo
        if (player.getName().startsWith("A")) {
            shipPositioning(player, shipsA, fieldA);
        } else if (player.getName().startsWith("B")) {
            shipPositioning(player, shipsB, fieldB);
        }
        
    }
    
    private boolean isDirectionRight(String orientation) {
        
        boolean isDirectionRight = false;
        
        // controllo che i valori di orientamento siano corretti
        if (orientation.equals("N")) {
          isDirectionRight = true;  
        } else if (orientation.equals("E")) {
            isDirectionRight = true;
        } else if (orientation.equals("S")) {
            isDirectionRight = true;
        } else if (orientation.equals("W")) {
            isDirectionRight = true;
        }
        
        return isDirectionRight;
    }
    
    private void AddShip(ArrayList<Ship> ships) {
        // creo il preset di navi per il game
        ships.add(new Ship(2));shipsA.add(new Ship(2));shipsA.add(new Ship(2));
        ships.add(new Ship(3));shipsA.add(new Ship(3));
        ships.add(new Ship(4));
        ships.add(new Ship(5));
    }
        
    private void shipPositioning(Player player, ArrayList<Ship> ships, Field field) {
        
        String response;
        String orientation;
        int x;
        int y;
        
        // richiesta posizione nave al'utente
        for (int i = 0; i < ships.size(); i++) {
            
            // invio richiecsta inserimento nave al client
            player.getOutput().println("SET_SHIP " + (i + 1) + " Barca " + shipsA.get(i).getNumCaselle() + " posti");
            // lettura valore ascissa dal client
            response = player.getInput().nextLine();
            x = Integer.parseInt(response);
            // lettura valore ordinata dal client
            response = player.getInput().nextLine();
            y = Integer.parseInt(response);
            // lettura orientamento dal client
            response = player.getInput().nextLine();
            orientation = response.toUpperCase(); // rendo maiuscola la stringa di ritorno cosi i controlli sul valore saranno piu facili
        
            // controllo parametri della risposta
            if (isDirectionRight(orientation)) {
                
                // se corretti stampo i parametri ricevuti a video
                System.out.println(player.getName() + " " + x + "," + y + "," + orientation);

                //affidamento coordinate alla nave
                ships.get(i).shipSizing(orientation, x, y);
                
                // controllo parametri nave (rientra nel campo, non e' sovrapposta, non e' nel range di altre barche)
                if (field.isInTheField(ships.get(i)) && field.isNotOverlap(ships.get(i)) && field.isNotInRange(ships.get(i))) {
                    // inserimento nave nel campo
                    for (int j = 0; j < ships.get(i).getNumCaselle(); j++) {
                        field.getCaselle()[ships.get(i).getCoordinates().get(j).getX()][ships.get(i).getCoordinates().get(j).getY()].setShip(ships.get(i));
                    }
                    // dichiaro il campo intorno alla nave
                    ships.get(i).setRange(field);
                    // print field
                    field.printField();
                } else {
                    // se c'e un errore nel posizionamento della nave, sovrapposta o fuoricampo
                    player.getOutput().println("MESSAGE Le navi non si possono sovrapporre o essere nello stesso range");
                    i--;
                }

            } else {
                // se i parametri ricevuti non sono corretti
                player.getOutput().println("MESSAGE Errore inserimento barca");
                player.getOutput().println("MESSAGE Riprovare");
                i--;
            }
        }
    }
    
}
