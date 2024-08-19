package DataBase;

import java.sql.*;
//INFORMATII:
// - am creat un singur tabel in care stochez nivelul si calea catre playgroundul specific fiecarui nivel

public class DataBase {
   public Connection c = null;
    public static Statement stmt = null;
    public static ResultSet rs=null;
   public String sql="";

    public void CreateDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            c.setAutoCommit(true);
            stmt = c.createStatement();
            //functii aplicate la prima rulare pentru crearea tabelei
           // createTable();
           // populateTable();
            stmt.close();
           // c.commit();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE TableLevel " +
                "(Level INT PRIMARY KEY NOT NULL," +
                " BG_Path TEXT NOT NULL)";
        stmt.execute(sql);
    }
    public void populateTable() throws SQLException {

        sql = "INSERT or IGNORE  INTO TableLevel (Level,BG_Path) " +
                "VALUES (1, '/PlayGroundLayers/PlaygroundGame/BG_L1.png' );";
        stmt.executeUpdate(sql);

        sql = "INSERT or IGNORE INTO TableLevel (Level,BG_Path) " +
                "VALUES (2, '/PlayGroundLayers/PlaygroundGame/BG_L2.png' );";
        stmt.executeUpdate(sql);


    }
    public  String getBackGround(int level) {   //functie care imi returneaza calea catre backgroundul corect
        try {
            rs = stmt.executeQuery("SELECT * FROM TableLevel where Level=" + level + ";");
            String s=rs.getString("BG_Path");
            return rs.getString("BG_Path");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return  "nu s a citit bine bg";
    }
    public void closeConnectionDB()
    {
        try {
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}

