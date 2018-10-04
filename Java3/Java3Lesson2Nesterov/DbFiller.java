import java.sql.SQLException;

public class DbFiller {

    static void fillTable(int i, String title, int cost) {
        try {
            String query = ("INSERT INTO 'Magazin'(prodid, title, cost) VALUES (" +
                    i + "," +
                    " '" + title + "', " +
                    cost + ")");
            System.out.println(query);
            boolean res = CreateDrop.stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CreateDrop.connect();
        CreateDrop.createTable();
        CreateDrop.dropTable();
        CreateDrop.createTable();
        for (int i = 1; i <= 10000; i++) {
            String title = ("prod_name" + i);
            int cost = (i * 10);
            fillTable(i, title, cost);
        }
        CreateDrop.disconnect();
    }
}
