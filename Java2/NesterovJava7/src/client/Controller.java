package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    HBox topPanel;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    HBox bottomPanel;

//    @FXML
//    public Button closeButton;
//
//    @FXML
//    public void handleCloseButtonAction(ActionEvent event) {
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
//    }

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    boolean isAuthorized;

    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // connect();

                        while (true) {
                            String str = " ";
                            if (socket != null && !socket.isClosed()) {
                                try {
                                    str = in.readUTF();
                                } catch (EOFException e) {
                                    // e.printStackTrace();
                                }
                            }
                            if (str.equals("/authok")) {
                                setAuthorized(true);
                                break;
                            }
                        }
                        while (true) {
                            String str = in.readUTF();
                            textArea.appendText(str + "\n");
                            if (str.equals("/serverClosed")) {
                                setAuthorized(false);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            //System.out.println("Сюда провал?");
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void connect() {
        try {
            System.out.println("Connecting");
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void auth() {
        try {
            if (socket == null || socket.isClosed()) connect();
            String str = ("/auth " + loginField.getText() + " " + passwordField.getText());
            System.out.println(str);
            out.writeUTF(str);
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if (isAuthorized == false) {
            topPanel.setManaged(true);
            topPanel.setVisible(true);
            bottomPanel.setManaged(false);
            bottomPanel.setVisible(false);
        } else {
            topPanel.setManaged(false);
            topPanel.setVisible(false);
            bottomPanel.setManaged(true);
            bottomPanel.setVisible(true);
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            // System.out.println("Послано сообщение: " + textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
