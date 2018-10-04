import java.sql.*;
import java.util.Scanner;

public class DbSearch {

    private static String findByName(String title) {

        try {
            ResultSet rs = CreateDrop.stmt.executeQuery("SELECT * FROM 'Magazin' WHERE title = '" + title + "'");
            return rs.getString(4);
        } catch (SQLException e) {
            //e.printStackTrace();
            return "Товар не обнаружен в базе.";
        }
    }

    private static void changePrice(String name, String price) {
        try {
            int rs = CreateDrop.stmt.executeUpdate("UPDATE 'Magazin' SET cost = " + price + " WHERE title = '" + name + "'");
            //System.out.println(rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CreateDrop.connect();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            String[] tokens = command.split(" ");

            if (command.startsWith("/цена")) {
                System.out.println("Цена " + tokens[1] + " = " + findByName(tokens[1]));
            }

            if (command.startsWith("/сменитьцену")) {
                changePrice(tokens[1] , tokens[2]);
                System.out.println("Цена " + tokens[1] + " = " + findByName( tokens[1]));
            }

            if (command.startsWith("/товарыпоцене")) {
                int one = Integer.parseInt(tokens[1]);
                int two = Integer.parseInt(tokens[2]);
                try {
                    ResultSet rs = CreateDrop.stmt.executeQuery("SELECT * FROM 'Magazin' WHERE (cost > " + one + " AND cost < " + two + ")");
                    while (rs.next()) {
                        System.out.println(rs.getString(4));
                    }
                    ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (command.startsWith("/выход")) break;
        }
        CreateDrop.disconnect();

    }


}
