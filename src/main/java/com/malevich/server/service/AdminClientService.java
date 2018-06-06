package com.malevich.server.service;

import com.malevich.server.http.response.status.exception.CanNotConnectException;
import org.springframework.util.CollectionUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminClientService implements Runnable {

    private List<Socket> clients = Collections.synchronizedList(new ArrayList<>());
    private ServerSocket serverSocket;

    public AdminClientService(int serverPort) {
        try {
            serverSocket = new ServerSocket(serverPort);
            new Thread(this).start();
        } catch (IOException e) {
            throw new CanNotConnectException();
        }
    }

    public void run() {
        while (true) {
            waitClient();
        }
    }

    private void waitClient() {
        try {
            clients.add(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String jsonObject) {
        if (!CollectionUtils.isEmpty(clients)) {
                for (Socket client : clients) {
                    try {
                        DataOutputStream out = new DataOutputStream(client.getOutputStream());
                        out.writeUTF(jsonObject);
                        out.flush();
                    } catch (Exception e) {
                    }
                }
        }
    }
}