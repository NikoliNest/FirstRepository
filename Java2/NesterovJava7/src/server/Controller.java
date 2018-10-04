package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField nickField;

    @FXML
    public TextField loginField;

    @FXML
    public TextField passField;

    @FXML
    public TextField find;

    @FXML
    public TextField nickField2;

    @FXML
    public TextField loginField2;

    @FXML
    public TextField passField2;

    @FXML
    public Button btn0;

    @FXML
    public Button btn1;

    @FXML
    public Button btn3;

    @FXML
    public HBox topPanel;

    @FXML
    public HBox bottomPanel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyServer myServer = new MyServer();
            }
        }).start();

        topPanel.setManaged(true);
        topPanel.setVisible(true);
        bottomPanel.setManaged(true);
        bottomPanel.setVisible(true);

    }

    public void add(ActionEvent actionEvent) {
        String nick = nickField.getText();
        String login = loginField.getText();
        String pass = passField.getText();

        DBService.insertNickLoginAndPassword(nick, login, pass);

        nickField.clear();
        loginField.clear();
        passField.clear();
    }

    public void del(ActionEvent actionEvent) {
        String nick = nickField.getText();
        DBService.deleteByNick(nick);
    }

    public void save(ActionEvent actionEvent) {
        String nick = nickField2.getText();
        String login = loginField2.getText();
        String password = passField2.getText();
        DBService.saveChangedLoginAndPassword(nick, login, password);

    }

    public void find(ActionEvent actionEvent) {
        nickField2.clear();
        loginField2.clear();
        passField2.clear();
        String nick = find.getText();
        nickField2.appendText(find.getText());
        find.clear();
        loginField2.appendText(DBService.getLoginByNick(nick));
        passField2.appendText(DBService.getPasswordByNick(nick));
    }
}
