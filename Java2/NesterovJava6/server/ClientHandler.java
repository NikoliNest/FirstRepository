package NesterovJava6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private NewServer newServer;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;

    ClientHandler(NewServer newServer, Socket socket, String name) {
        try {
            setName(name);
            this.socket = socket;
            this.newServer = newServer;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str =  in.readUTF();
                            String str1 = (name +  " : " + str);
                            if(str.equals("/end")) {
                                newServer.deleteClient(newServer.getClient(name));
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            newServer.broadcastMsg(str1);
                          //  System.out.println(str);
                         //   out.writeUTF(str);
                           // newServer
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
