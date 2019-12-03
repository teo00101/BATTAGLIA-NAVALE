/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrice;

/**
 *
 * @author informatica
 */
public class Casella {
    private boolean pieno;
    private boolean colpito;

    public Casella() {
        pieno=false;
        colpito=false;
    }

    public boolean isPieno() {
        return pieno;
    }

    public void setPieno(boolean pieno) {
        this.pieno = pieno;
    }

    public boolean isColpito() {
        return colpito;
    }

    public void setColpito(boolean colpito) {
        this.colpito = colpito;
    }
    
    
    
}
