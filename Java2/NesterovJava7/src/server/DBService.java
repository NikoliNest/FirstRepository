package server;

import com.sun.javafx.binding.StringFormatter;

import java.sql.*;

public class DBService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/db_clients.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNickByLoginAndPassword(String login, String password) {
        String sql = String.format("SELECT nick FROM ClientsDB\n" +
                "WHERE login = '%s'\n" +
                "AND password = '%s'", login, password);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLoginByNick(String nick) {
        String sql = String.format("SELECT login FROM ClientsDB\n" +
                "WHERE nick = '%s'\n", nick);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPasswordByNick(String nick) {
        String sql = String.format("SELECT password FROM ClientsDB\n" +
                "WHERE nick = '%s'\n", nick);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertNickLoginAndPassword(String nick, String login, String password) {
        String sql = String.format("INSERT INTO ClientsDB (\"nick\", \"login\", \"password\")\n" +
                "VALUES('%s', '%s', '%s')", nick, login, password);
        try {
            System.out.println(sql);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveChangedLoginAndPassword(String nick, String login, String password) {
        String sql = String.format("UPDATE ClientsDB SET login='%s' WHERE nick='%s'", login, nick);
        String sq2 = String.format("UPDATE ClientsDB SET password='%s' WHERE nick='%s'", password, nick);
        try {
            System.out.println(sql);
            stmt.execute(sql);
            System.out.println(sq2);
            stmt.execute(sq2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByNick(String nick) {
        String sql = String.format("DELETE FROM ClientsDB\n" +
                "WHERE nick='%s'", nick);
        try {
            System.out.println(sql);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
