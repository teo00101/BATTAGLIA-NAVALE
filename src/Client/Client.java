/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


import java.io.BufferedReader;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class Client {
    
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    
    public Client() throws Exception {
        socket = new Socket("127.0.0.1", 59090);
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.play();
    }
    
    private void play() throws Exception{
        String request;
        // scrivo il nome inviato dal server
        System.out.println("Server response: " + input.nextLine());
        // fino a quando non finisce il gioco interpreto tutti i comandi che arrivano dal server
        while(input.hasNextLine()) {
            request = input.nextLine();
            if (request.startsWith("SET_SHIP")) {
                // funzione lettura e invio,  Mando stats barca:
                System.out.println(request.substring(9));
                output.println(readSend("Inserisci il valore dell'ascissa"));
                output.println(readSend("Inserisci il valore dell'ordinata"));
                output.println(readSend("Inserisci l'orientamento"));
            } else if (request.startsWith("MESSAGE")) {
                System.out.println(request.substring(8));
            } else if (request.startsWith("MOVE")) {
                output.println(readSend("Inserisci il valore dell'ascissa dello sparo"));
                output.println(readSend("Inserisci il valore dell'ordinata dello sparo"));
            } else if (request.startsWith("VICTORY")) {
                System.out.println("Complimenti per la vittoria");
                break;
            } else if (request.startsWith("DEFEAT")) {
                System.out.println("Mi dispiace hai perso");
                break;
            }else if (request.startsWith("PLAY")) {
                output.println("MOVE");
            }
        }
        // invio al server la fine della partita
        output.println("QUIT");
        // chiudo il socket a partita finita
        socket.close();
    }
    
    private String readSend(String string) throws Exception{
        System.out.println(string);
        InputStreamReader tastiera = new InputStreamReader(System.in);
        BufferedReader send = new BufferedReader(tastiera);
        return send.readLine();
    }
    
    
}