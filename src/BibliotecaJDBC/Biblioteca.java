package BibliotecaJDBC;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    //private static String dbURL = "jdbc:derby:biblioteca;create=true";
    //private static String dbURL = "jdbc:derby:biblioteca;create=true;server=MeDerby;password=MeDerby";
    // Pentru situatia in care serverul Derby ruleaza pe o alta masina, respectiv
    // intr-o alta Java VM
    private static String dbURL = "jdbc:derby://127.0.0.1:1527/biblioteca;create=true";
    private static String dbUser = "APP";
    private static String dbPassword = "APP";
    private static String dbName = "biblioteca2";
    private static String tableName = "carte2";

    // pentru interactiunea cu JDBC
    private static Connection conn;
    private static Statement sqlStmt;

        /*createConnection();
        dropTable();
        createTable();
        insertCarte(c1);
        insertCarte(c2);
        insertCarte(c3);
        updateCarte(c1);
        deleteCarte(c2);
        selectCarti();
        shutDown();*/


    public static void createConnection() {
        try {
            //conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            conn = DriverManager.getConnection(dbURL);
            System.out.println("Conexiune realizata cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            sqlStmt = conn.createStatement();
            sqlStmt.execute("create table " + tableName +
                    " (cota varchar(16) primary key, titlu varchar(64), autori varchar(64), an int, exemplare int)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        try {
            sqlStmt = conn.createStatement();
            sqlStmt.execute("drop table " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCarte(API.Carte carte) {
        try {
            sqlStmt = conn.createStatement();
            sqlStmt.execute("insert into " + dbUser + "." +
                    tableName + " values ('" + carte.getCota() + "', '" +
                    carte.getTitlu() + "', '" +
                    carte.getAutori() + "', " +
                    carte.getAn() + ", "+carte.getExemplare()+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCarte(API.Carte carte, String sign) {
        try {
            sqlStmt = conn.createStatement();
//            sqlStmt.executeUpdate("update " + tableName +
//                    " set cota = '" + carte.getCota() + "' where an = " + carte.getAn());
            sqlStmt.executeUpdate("update " + tableName +
                    " set exemplare =exemplare"+sign+ " where titlu like '" + carte.getTitlu()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCarte(API.Carte carte) {
        try {
            sqlStmt = conn.createStatement();
            sqlStmt.executeUpdate("delete from " + tableName +
                    " where an = " + carte.getAn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<API.Carte> selectCarti() {
        try {
            sqlStmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = sqlStmt.executeQuery("select * from " + tableName);

            List<API.Carte> carteList = new ArrayList<API.Carte>();
            resultSet.beforeFirst();
            while (resultSet.next()) {
                API.Carte localCarte = new API.Carte();

                localCarte.setCota(resultSet.getString("cota"));
                localCarte.setTitlu(resultSet.getString("titlu"));
                localCarte.setAutori(resultSet.getString(3));
                localCarte.setAn(resultSet.getInt(4));
                localCarte.setExemplare(resultSet.getInt(5));

                carteList.add(localCarte);
            }

            return carteList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void shutDown() {
        try {
              if (sqlStmt != null)
                sqlStmt.close();

              if (conn != null)
                  conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
