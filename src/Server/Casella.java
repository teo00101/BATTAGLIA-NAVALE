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
public class Casella {
    
    private Ship ship;
    private boolean colpita;

    public Casella() {
        ship = null;
        colpita = false;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean getColpita() {
        return colpita;
    }

    public void setColpito(boolean colpita) {
        this.colpita = colpita;
    }
    
    
    
}
