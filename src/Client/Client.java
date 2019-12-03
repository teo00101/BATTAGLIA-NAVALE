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

/**
 * A command line client for the date server. Requires the IP address of
 * the server as the sole argument. Exits after printing the response.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 59090);
        InputStreamReader messaggio=new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(messaggio);
        System.out.println("Server response: " + bf.readLine());
        System.out.println(bf.readLine());
    }
}
