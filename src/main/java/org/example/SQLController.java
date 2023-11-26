package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Class written by Joshua Green
public class SQLController {

    private Connection connect() {
        String url = "jdbc:sqlite:pizzaDatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addTopping (int id, String name, float price) {
        String pizzaTopping = "INSERT INTO pizzaToppings(id, name, price) VALUES(?, ?, ?)";

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(pizzaTopping, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.setString(2, name);
            ptsmt.setFloat(3, price);

            ptsmt.executeUpdate();

            conn.commit();

        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());

        } finally {
            try {
                if (ptsmt != null) {
                    ptsmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }

    }
}
