/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author informatica
 */
public class Ship {
    
    private int numCaselle;
    private ArrayList<Coordinate> coordinates;

    public Ship(int numCaselle) {
        this.numCaselle = numCaselle;
        coordinates = new ArrayList<Coordinate>();
    }
    
    /*
    // costruttore di copia 
    public Ship(Ship ship) {
        caselle = ship.getCaselle();
    }
    */

    public int getNumCaselle() {
        return numCaselle;
    }

    public void setNumCaselle(int numCaselle) {
        this.numCaselle = numCaselle;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    
   public void shipSizing(String orientation, int x, int y) {
       
       for (int i = 0; i < numCaselle; i++) {
           if (orientation.equals("N")) {
               coordinates.add(new Coordinate(x, y - i));
           } else if (orientation.equals("E")) {
               coordinates.add(new Coordinate(x + 1, y));
           } else if (orientation.equals("S")) {
               coordinates.add(new Coordinate(x, y + i));
           } else if (orientation.equals("W")) {
               coordinates.add(new Coordinate(x + i, y));
           }
       }
       
   }
    
}
