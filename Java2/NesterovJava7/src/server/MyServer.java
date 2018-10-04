package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class MyServer {
    private ServerSocket server;
    private Vector<ClientHandler> clients;

    public final int PORT = 8189;

    public MyServer() {

        try {
            DBService.connect();
            server = new ServerSocket(PORT);
            Socket socket = null;
            clients = new Vector<ClientHandler>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Scanner sc = new Scanner(System.in);
                        String nickNamePassword = sc.nextLine();
                        String[] tokens = nickNamePassword.split(" ");
                        System.out.println(Arrays.toString(tokens));
                        if (tokens[0].equals("/ins")) {
                            DBService.insertNickLoginAndPassword(tokens[1], tokens[2], tokens[3]);
                        }
                        if (tokens[0].equals("/del")) {
                            DBService.deleteByNick(tokens[1]);
                        }
                    }
                }
            }).start();
            while (true) {
                System.out.println("Сервер включён.");
                socket = server.accept();
                System.out.println("Клиент подключился.");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DBService.disconnect();
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void broadcastMsg(String msg, String name) {
        for (ClientHandler o : clients) {
            System.out.println("Личное сообщение ");
            if (o.getName().equals(name)) {
                o.sendMsg(msg);
            }
        }
    }

    void subscribe(ClientHandler client) {
        clients.add(client);
    }

    void unsuscribe(ClientHandler client) {
        clients.remove(client);
        broadcastMsg(client.getName() + " покинул чат");
    }

//    public static void main(String[] args) {
//        MyServer myServer = new MyServer();
//    }
}
