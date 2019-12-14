/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author morel
 */
public class Field {
    
    private int length;
    private int height;
    private Casella[][] caselle;

    public Field(int length, int height) {
        this.length = length;
        this.height = height;
        caselle = new Casella[length][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                caselle[j][i] = new Casella();
            }
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Casella[][] getCaselle() {
        return caselle;
    }

    public void setCaselle(Casella[][] caselle) {
        this.caselle = caselle;
    }
    
    public boolean isInTheField(Ship ship) {
       
        ArrayList<Coordinate> coordinates = ship.getCoordinates();
       boolean inTheField = true;
       
       if (coordinates.get(0).getX() < 0 || coordinates.get(0).getX() > length) {
           inTheField = false;
       }
       if (coordinates.get(0).getY() < 0 || coordinates.get(0).getY() > height) {
           inTheField = false;
       } 
       if (coordinates.get(coordinates.size() - 1).getX() < 0 || coordinates.get(coordinates.size() - 1).getX() > length) {
           inTheField = false;
       }
       if (coordinates.get(coordinates.size() - 1).getY() < 0 || coordinates.get(coordinates.size() - 1).getY() > height) {
           inTheField = false;
       }
       
       return inTheField;
   }
    
    public boolean isOverlap(Ship ship) {
        
        boolean isOverlap = false;
        int x, y;
        
        for (int i = 0; i < ship.getNumCaselle(); i++) {
            
            x = ship.getCoordinates().get(i).getX();
            y = ship.getCoordinates().get(i).getY();
            
            if (caselle[x][y].getShip() != null) {
                isOverlap = true;
            }
        }
        
        return isOverlap;
    }
    
}
