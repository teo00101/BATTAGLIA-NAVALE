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
    
    
}
