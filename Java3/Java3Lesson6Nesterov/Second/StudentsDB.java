package Second;

import java.sql.*;
import java.util.Scanner;


public class StudentsDB {
    static String tableName = "students";
    static String query = null;
    static Scanner sc = new Scanner(System.in);
    static boolean end = false;


    static Connection conn = null;
    static Statement st = null;

    public static void main(String[] args) {

        connect();
        createTable();
        while (!end) {
            System.out.println("\n Введите запрос в базу данных:");
            System.out.println("/new фамилия_студента балл - создать новую запись.");
            System.out.println("/del фамилия_студента - удалить запись.");
            System.out.println("/upd фамилия_студента балл - исправить запись.");
            System.out.println("/get фамилия_студента - вывести запись.");
            System.out.println("/all - вывести все запись");
            System.out.println("/end - выход");

            String rawQuery = sc.nextLine();
            String[] tokens = rawQuery.split(" ");
            switch (tokens[0]) {
                case "/new":
                   newStudent(tokens[1], tokens[2]);       // Подлежит тестированию
                    break;
                case "/del":
                   delStudent(tokens[1]);                  // Подлежит тестированию
                    break;
                case "/upd":
                    updStudent(tokens[1], tokens[2]);       // Подлежит тестированию
                    break;
                case "/get":
                    getStudent(tokens[1]);                  // Подлежит тестированию
                    break;

                case "/all":
                    query = String.format("SELECT id, surname, ball FROM %s", tableName);
                    try {
                        ResultSet rs = StudentsDB.st.executeQuery(query);
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id") + "\t"
                                    + "Фамилия: " + rs.getString("surname") + "\t"
                                    + "Балл: " + rs.getInt("ball"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/end":
                    end = true;
                    break;
            }
        }

        try {
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");       // У меня без этой строчки не работает :(   Почему?
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS students (\n" +
                " id integer PRIMARY KEY AUTOINCREMENT,\n" +
                " surname text NOT NULL,\n" +
                " ball integer NOT NULL)";
        try {
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void newStudent(String name, String stringBall) {
        int ball = Integer.parseInt(stringBall);
        query = String.format("INSERT INTO %s ('surname', 'ball') VALUES('%s', '%d')", tableName, name, ball);
        try {
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void delStudent(String name) {
        try {
            int id = findID(name, tableName);
            query = String.format("DELETE FROM %s WHERE id = %d", tableName, id);
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updStudent(String name, String stringBall) {
        int ball1 = Integer.parseInt(stringBall);
        int id = findID(name, tableName);
        query = String.format("UPDATE %s SET 'ball' = %d WHERE id = %d", tableName, ball1, id);
        try {
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void getStudent(String name) {
        query = String.format("SELECT id, surname, ball FROM %s WHERE surname = '%s'", tableName, name);
        try {
            ResultSet rs1 = st.executeQuery(query);
            System.out.println("ID: " + rs1.getInt("id") + "\t"
                    + "Фамилия: " + rs1.getString("surname") + "\t"
                    + "Балл: " + rs1.getInt("ball"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static int findID(String surname, String tableName) {                  //  Используется два раза, поэтому вынесен в отдельный метод.
        int id = 0;                                                        // ID нужен, т.к. не по первичному ключу в таблицу не пишет.
        try {
            query = String.format("SELECT (id) FROM %s WHERE surname = '%s'", tableName, surname);
            ResultSet rs2 = st.executeQuery(query);
            id = rs2.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
