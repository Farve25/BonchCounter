import java.sql.*;
import java.util.ArrayList;

public class StudentDB {

    private static Connection connection = null;
    private static Statement stmt;
    private static ResultSet resSet = null;
    private static PreparedStatement pstAdd;
    private static PreparedStatement pstUpdate;
    private static PreparedStatement pstSearch;

    public static void add(String id, String name, String group){
        try {
            pstAdd.setString(1, id);
            pstAdd.setString(2, name);
            pstAdd.setString(3, group);
            pstAdd.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось добавить запись! " + e.getMessage());
        }
    }

    public static void update(String id, String name, String group){
            try {
                pstUpdate.setString(1, name);
                pstUpdate.setString(2, group);
                pstUpdate.setString(3, id);
                pstUpdate.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Не удалось обновить запись!");
            }

    }

    static ArrayList<String> search(String id) {
        ArrayList<String> list = new ArrayList();
        try {
            pstSearch.setString(1, id);
            resSet = pstSearch.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString("name");
                String group = resSet.getString("team");
                list.add(name);
                list.add(group);
            }
        } catch (SQLException e) {
            System.out.println("Поиск не удался: " + e.getMessage());
        }

        return list;
    }

    public static void setConnection() throws ClassNotFoundException, SQLException
    {
        try {
            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Student.db");

            pstAdd = connection.prepareStatement(
                    "Insert into Student (id, name, team) values (?, ?, ?);");

            pstUpdate = connection.prepareStatement("Update Student set name = ?, team = ? where id = ?;");

            pstSearch = connection
                    .prepareStatement("Select name, team from Student where id = ?;");

            System.out.println("База Подключена!");
        } catch (Exception e) {
            System.out.println("хуй");
        }
    }

    public static void fullDB() throws ClassNotFoundException, SQLException
    {
        stmt = connection.createStatement();
        resSet = stmt.executeQuery("SELECT * FROM Student");

        while(resSet.next())
        {
            String id = resSet.getString("id");
            String name = resSet.getString("name");
            String group = resSet.getString("team");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "group = " + group );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    public static void closeDB() throws ClassNotFoundException, SQLException
    {
        if (connection != null) {
            pstAdd.close();
            pstUpdate.close();
            pstSearch.close();
            connection.close();
            if (resSet != null)
                resSet.close();
        }

        System.out.println("Соединения закрыты");
    }
}
