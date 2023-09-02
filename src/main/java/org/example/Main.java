package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 5678;
        System.out.println("Server hosted on port: " + port);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new Index("/"));
        server.createContext("/test", new Index("/test"));
        server.createContext("/static", new Static());
        server.createContext("/first", new First());
        server.createContext("/second", new Second());
        server.createContext("/form", new Form());
        server.start();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer primary key autoincrement, fname string, lname string)");
            statement.executeUpdate("insert into person values(null, 'leo', 'yang')");
            statement.executeUpdate("insert into person values(null, 'yui', 'lew')");
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                // read the result set
                System.out.println("fname = " + rs.getString("fname"));
                System.out.println("lname = " + rs.getString("lname"));
                System.out.println("id = " + rs.getInt("id"));
            }
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
