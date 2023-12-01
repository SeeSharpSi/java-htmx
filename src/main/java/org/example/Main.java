package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Test Commit for Git
public class Main {
    public static int a;
    public static int b;

    public static void main(String[] args) throws IOException {
        int port = 5678;
        System.out.println("Server hosted on port: " + port);
        System.out.println("By default, go to localhost:" + port + " in your browser");
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new Index("/"));
        server.createContext("/test", new Test());
        server.createContext("/menu_submit", new Menu_Submit());
        server.createContext("/static", new Static());
        server.createContext("/home", new Home());
        server.createContext("/order", new Order());
        server.createContext("/other", new Other());
        server.createContext("/rewards", new Rewards());
        server.createContext("/cart", new Cart());
        server.createContext("/form", new Form());
        server.createContext("/form_html", new Form_Html());
        server.createContext("/receipt", new Receipt());
        server.createContext("/financial", new Financial());
        server.createContext("/inventory", new Inventory());
        server.start();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate(
                    "create table person (id integer primary key autoincrement, fname string, lname string)");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
