import java.sql.*;

public class CreateDrop {

    static Connection connection;
    static Statement stmt;

    static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/test.db");
            //connection.setAutoCommit(false);
            stmt = connection.createStatement();
            System.out.println("Connected.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void disconnect() {
        try {
            stmt.close();
            connection.close();
            System.out.println("Disconnected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createTable() {
        try {
            int res = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Magazin " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "prodid INTEGER NOT NULL, " +
                    "title TEXT NOT NULL, " +
                    "cost INTEGER NOT NULL)");

            System.out.println("Table created : " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void dropTable() {
        try {
            int res = stmt.executeUpdate("DROP TABLE Magazin");
            System.out.println("Table dropped :" + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}


