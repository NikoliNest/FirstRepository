package NesterovJava6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


class NewServer {

    int n = 0;
    private Vector<ClientHandler> clients;

    NewServer() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");


            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                n++;
                clients.add(new ClientHandler(this, socket, ("Клиент" + n)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    void deleteClient(ClientHandler client) {
        clients.remove(client);
        broadcastMsg(client.getName() + " покинул чат");
    }

    ClientHandler getClient(String name) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(name)) {
                return o;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        NewServer newServer = new NewServer();
    }

}