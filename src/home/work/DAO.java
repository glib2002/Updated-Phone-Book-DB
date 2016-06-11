/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.work;

import static home.work.CommandSQL.DRIVER_PATH;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Глеб
 */
public class DAO implements CommandSQL {

    public Scanner scn = new Scanner(System.in);
    public String hostname;
    public int hostport;
    public String database;
    public String username;
    public String password;
    public ResultSet rs;

    @Override
    public Connection connect() throws SQLException {
        loadDriver();
        String connectionString = String.format("jdbc:mysql://%s:%d/%s?useSSL=true", hostname, hostport, database);

        Connection conn = DriverManager.getConnection(connectionString, username, password);
        return conn;
    }

    @Override
    public void writeDB() {

        int i = 1;

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery("SHOW TABLES");
            addToDataBase(stmt);

        } catch (SQLException ex) {
            Logger.getLogger(MySQLoperator.class.getName()).log(Level.SEVERE, null, ex);
        }

       

    }

    @Override
    public void loadDriver() {
        try {
            System.out.println("The driver successfuly downloded");
            Class.forName(DRIVER_PATH);
        } catch (ClassNotFoundException ex) {
            System.err.println("\"The driver didnt downloded\"");

        }
    }

    @Override
    public void enterConnectionData() {
        System.out.println("Enter hostname");
        hostname = scn.nextLine();
        System.out.println("Enter hostport ");
        hostport = Integer.parseInt(scn.nextLine());
        System.out.println("Enter database");
        database = scn.nextLine();
        System.out.println("Enter username");
        username = scn.nextLine();
        System.out.println("Enter password");
        password = scn.nextLine();

    }

    public void start() {

        enterConnectionData();

//        connect();
        writeDB();
    }

    @Override
    public void addToDataBase(Statement stmt) throws SQLException {
        String inputname = ContactWriter.name;
        String inputnumber = ContactWriter.number;
        String inputemail = ContactWriter.e_mail;
        String inputskype = ContactWriter.skype;
        String insert = "INSERT INTO test.contacts (name, number, skype)\n"
                + "VALUES ('" + inputname + "' , '" + inputnumber + "', '" + inputemail + "', '" + inputskype + "')";
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|--------------------------CONTACT------------------------------------------|");
        System.out.println("|---------------------------------------------------------------------------|");
        System.out.println("Name----------------|Tel.Number-----|E - mail-------| Skype |---------------+");
        String name = rs.getString(1);
        String number = rs.getString(2);
        String email = rs.getString(3);
        String skype = rs.getString(4);
        System.out.printf("%-20s| %-13s | %-13s |%-20s|\n", name, number, email, skype);
        try {
            stmt.executeUpdate(insert);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Contact Add Finished!");
    }

}
