/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author informatica
 */
public class Ship {
    
    private int caselle;
    private int x;
    private int y;

    public Ship(int caselle) {
        this.caselle = caselle;
    }

    public int getCaselle() {
        return caselle;
    }

    public void setCaselle(int caselle) {
        this.caselle = caselle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
