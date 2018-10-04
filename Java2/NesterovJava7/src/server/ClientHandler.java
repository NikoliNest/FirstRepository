package server;

import client.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private MyServer myServer;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;
    private boolean isAuthorized;
    final long TIMEOUT = 120000;

    ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.name = getName();
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            long timeClient0 = System.currentTimeMillis();           //1
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        setAuthorized(false);                                   //4
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                System.out.println(str);
                                String[] tokens = str.split(" ");
                                String login = tokens[1];
                                String password = tokens[2];
                                String nick = new DBService().getNickByLoginAndPassword(login, password);
                                if (!(nick == null)) {
                                    if (!myServer.isNickBusy(nick)) {
                                        System.out.println("/authok " + nick);
                                        sendMsg("/authok");
                                        myServer.subscribe(ClientHandler.this);
                                        ClientHandler.this.name = nick;
                                        setAuthorized(true);                      // 2
                                        break;
                                    } else {
                                        sendMsg("Учетная запись уже используется.");
                                        throw new RuntimeException("Учетная запись уже используется.");
                                    }
                                } else {
                                    sendMsg("Неверные логин/пароль.");
                                    throw new RuntimeException("Неверные логин/пароль.");
                                }
                            }
                        }
                        while (true) {
                            String str = in.readUTF();
                            String str1 = (name + " : " + str);

                            if (str.startsWith("/w")) {
                                String[] tokens = str.split(" ");
                                String str2 = (name + " : " + tokens[2]);
                                for (int i = 3; i < tokens.length; i++) {
                                    str2 = (str2 + " " + tokens[i]);
                                }
                                ClientHandler.this.myServer.broadcastMsg(str2, tokens[1]);
                            } else if (str.equals("/end")) {
                                myServer.unsuscribe(ClientHandler.this);
                                out.writeUTF("/serverClosed");
                                break;
                            } else ClientHandler.this.myServer.broadcastMsg(str1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            Thread t2 = new Thread(new Runnable() {                                          // 5         Таймер
                @Override
                public void run() {
                    long timeClient;
                    while (true) {
                        long currentTime = System.currentTimeMillis();
                        timeClient = currentTime - timeClient0;
                        if (timeClient >= TIMEOUT && !isAuthorized) {
                            System.out.println(timeClient);
                            System.out.println("Закрываю сервером клиента");
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAuthorized(boolean b) {                                                  //3
        this.isAuthorized = b;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }
}
