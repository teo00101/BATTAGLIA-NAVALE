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

/**
 * A command line client for the date server. Requires the IP address of
 * the server as the sole argument. Exits after printing the response.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 59090);
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Server response: " + input.nextLine());
        comunication(input, output);
    }
    
//    private static void comunication(Scanner input){
//        String request;
//        while(input.hasNextLine()) {
//            request = input.nextLine();
//            switch (request){
//                case "SET_SHIP":
//                    System.out.println("yamme");
//                    break;
//            }
//        }
//    }
    
    private static void comunication(Scanner input, PrintWriter output) throws IOException{
        String request;
        while(input.hasNextLine()) {
            request = input.nextLine();
            if (request.startsWith("SET_SHIP")) {
                // funzione lettura e invio,  Mando stats barca:
                output.println(readsend(request.substring(9)));
                System.out.println("Piazzo le navi");
            } else if (request.startsWith("MESSAGE")) {
                System.out.println(request.substring(8));
            }
        }
    }
    
    private static String readsend(String string) throws IOException{
        System.out.println(string);
        InputStreamReader tastiera = new InputStreamReader(System.in);
        BufferedReader send = new BufferedReader(tastiera);
        return send.readLine();
    }
    
    
}