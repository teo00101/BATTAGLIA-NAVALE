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
        comunication(input, output);
    }
    
    private static void comunication(Scanner input, PrintWriter output) throws Exception{
        String request;
        while(input.hasNextLine()) {
            request = input.nextLine();
            if (request.startsWith("SET_SHIP")) {
                // funzione lettura e invio,  Mando stats barca:
                System.out.println(request.substring(9));
                output.println(readsend("Inserisci il valore dell'ascissa"));
                output.println(readsend("Inserisci il valore dell'ordinata"));
                output.println(readsend("Inserisci l'orientametno"));
            } else if (request.startsWith("MESSAGE")) {
                System.out.println(request.substring(8));
            }
        }
    }
    
    private static String readsend(String string) throws Exception{
        System.out.println(string);
        InputStreamReader tastiera = new InputStreamReader(System.in);
        BufferedReader send = new BufferedReader(tastiera);
        return send.readLine();
    }
    
    
}