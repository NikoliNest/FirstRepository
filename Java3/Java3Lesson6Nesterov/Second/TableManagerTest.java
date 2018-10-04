package Second;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static Second.StudentsDB.*;

public class TableManagerTest {
    static Connection conn = null;
    static Statement st = null;

    @Before
    public void startTest() {
        connect();
        System.out.println("Connection set.");
    }

    @Test
    public void testNewUpdDel() {
        newStudent("TestStudent", "3");
        System.out.println("new student written");
        updStudent("TestStudent", "4");
        System.out.println("student updated");
        delStudent("TestStudent");
        System.out.println("student deleted");

//        try {
//            st.close();                                   //Почему он не хочет закрывать БД?
//           System.out.println("statement closed");
//           conn.close();
//            System.out.println("connection closed");
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        }
//    }
    }
}
