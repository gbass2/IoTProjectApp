package com.example.a49ersense;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientSocket {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        OutputStream outToServer = clientSocket.getOutputStream();
        InputStream inFromServer = clientSocket.getInputStream();
        out = new PrintWriter(outToServer, true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


    }
    public void sendMessage(String msg) throws IOException {
        Log.d("Client Msg", "sendMessage: " + msg);
        out.println(msg);

//        String resp = in.readUTF();
//        Log.d("Server resp", "resp: " + resp);
//        return resp;
    }
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
