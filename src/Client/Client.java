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
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 59090);
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Server response: " + input.nextLine());
        comunication(input, output, socket);
    }
    
    private static void comunication(Scanner input, PrintWriter output, Socket socket) throws Exception{
        String request;
        while(input.hasNextLine()) {
            request = input.nextLine();
            if (request.startsWith("SET_SHIP")) {
                // funzione lettura e invio,  Mando stats barca:
                System.out.println(request.substring(9));
                output.println(readSend("Inserisci il valore dell'ascissa"));
                output.println(readSend("Inserisci il valore dell'ordinata"));
                output.println(readSend("Inserisci l'orientametno"));
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
                output.println(readSend("Se vuoi iniziare la partita digita MOVE"));
            }
        }
        // invio al server la fine della partita
        output.println("QUIT");
        // chiudo il socket a partita finita
        socket.close();
    }
    
    private static String readSend(String string) throws Exception{
        System.out.println(string);
        InputStreamReader tastiera = new InputStreamReader(System.in);
        BufferedReader send = new BufferedReader(tastiera);
        return send.readLine();
    }
    
    
}