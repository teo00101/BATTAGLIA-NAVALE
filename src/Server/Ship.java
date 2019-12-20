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
    
    public Ship(Ship ship) {
        this.numCaselle = ship.getNumCaselle();
        coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < ship.coordinates.size(); i++) {
            this.coordinates.add(new Coordinate(ship.coordinates.get(i).getX(), ship.coordinates.get(i).getY()));
        }
    }

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
       
       // vuoto l'array di coordinate in caso di inserimenti precedenti errati
       if (coordinates.size() != 0) {
           coordinates.clear();
       }
       
       for (int i = 0; i < numCaselle; i++) {
           if (orientation.equals("N")) {
               coordinates.add(new Coordinate(x, y - i));
           } else if (orientation.equals("E")) {
                coordinates.add(new Coordinate(x + i, y));
           } else if (orientation.equals("S")) {
               coordinates.add(new Coordinate(x, y + i));
           } else if (orientation.equals("W")) {
               coordinates.add(new Coordinate(x + i, y));
           }
       }
       
   }
   
   private boolean areCoordinatesInField(int x, int y, Field field) {
           
           boolean areCoordinatesInTheField = true;
           
           if (x < 0 || x >= field.getLength() ) { 
               areCoordinatesInTheField = false;
           }
           
           if (y < 0 || y >= field.getHeight()) {
               areCoordinatesInTheField = false;
           }
           
           return areCoordinatesInTheField;
    }
   
   public void setRange(Field field) {
       
       int x;
       int y;
       
       // per ogni casella della nave imposto le sue confinanti come range
       for (Coordinate coord : coordinates) {
           
           // acquisisco le coordinate per ogni casella della nave
           x = coord.getX();
           y = coord.getY();
           
           // giro in torno ad ogni casella passando dalle confinanti
           x = Left(x, y, field);
           y = Top(x, y, field);
           x = Right(x, y, field);
           x = Right(x, y, field);
           y = Bottom(x, y, field);
           y = Bottom(x, y, field);
           x = Left(x, y, field);
           x = Left(x, y, field);
           
       }

   }
   
   private int Left(int x, int y, Field field) {
       // mi sposto a sinistra
       x--;
       CoordinatesControl(x, y, field);
       return x;
   }
   
   private int Right(int x, int y, Field field) {
       // mi sposto a destra
       x++;
       CoordinatesControl(x, y, field);
       return x;
   }
   
   private int Top(int x, int y, Field field) {
       // mi sposto in alto
       y--;
       CoordinatesControl(x, y, field);
       return y;
   }
   
   private int Bottom(int x, int y, Field field) {
       // mi sposto in basso
       y++;
       CoordinatesControl(x, y, field);
       return y;
   }
   
   private void CoordinatesControl(int x, int y, Field field) {
       // controllo che le coordinate siano interne alla matrice 
       if (areCoordinatesInField(x, y, field)) {
               // se la cella indicata dalle coordinate e' vuota la dichiaro come range di una barca
               if (field.getCaselle()[x][y].getShip() == null) {
                   field.getCaselle()[x][y].setRange(true);
               }
           }
   }
    
}
