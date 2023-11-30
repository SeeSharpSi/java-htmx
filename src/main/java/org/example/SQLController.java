package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//Class written by Joshua Green
public class SQLController {

    private Connection connect() {
        String url = "jdbc:sqlite:pizzaDatabase.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addTopping(int id, String name, float price) {
        String pizzaTopping = "INSERT INTO pizzaToppings(id, name, price) VALUES(?, ?, ?)";

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

    public void deleteTopping (int id) {
        String deleteTopping = "DELETE FROM pizzaToppings where id = ?";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(deleteTopping, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.executeUpdate();
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

    public void createAdmin(int id, String username, String password, String status) {
        String admin = "INSERT INTO adminUserProfile(id, username, password, status) VALUES(?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(admin, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.setString(2, username);
            ptsmt.setString(3, password);
            ptsmt.setString(4, status);

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

    public void deleteAdmin(int id) {
        String deleteAdmin = "DELETE FROM adminUserProfile where id = ?";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(deleteAdmin, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.executeUpdate();
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

    public void addCustomer(int id, String name, String username, String password, String address, int cardNumber, int cardSVC, int ordersDelivered, int accessLevel) {
        String customer = "INSERT INTO pizzaToppings(id, name, username, password, address, cardNumber, cardSVC, ordersDelivered, accessLevel) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(customer, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.setString(2, name);
            ptsmt.setString(3, username);
            ptsmt.setString(4, password);
            ptsmt.setString(5, address);
            ptsmt.setInt(6, cardNumber);
            ptsmt.setInt(7, cardSVC);
            ptsmt.setInt(8, ordersDelivered);
            ptsmt.setInt(9, accessLevel);

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

    public void deleteCustomer (int id) {
        String deleteCustomer = "DELETE FROM customerUserProfile where id = ?";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(deleteCustomer, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.executeUpdate();
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

    public void addEmployee(int id, String username, String password, String status, int ordersDelivered, int accessLevel) {
        String employee = "INSERT INTO employeeUserProfile(id, username, password, status, ordersDelivered, accessLevel) VALUES(?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(employee, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.setString(2, username);
            ptsmt.setString(3, password);
            ptsmt.setString(4, status);
            ptsmt.setInt(5, ordersDelivered);
            ptsmt.setInt(6, accessLevel);

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

    public void deleteEmployee(int id) {
        String deleteEmployee = "DELETE FROM customerUserProfile where id = ?";

        Connection conn = null;
        PreparedStatement ptsmt = null;

        try {
            conn = this.connect();
            if (conn == null) {
                return;
            }

            conn.setAutoCommit(false);

            ptsmt = conn.prepareStatement(deleteEmployee, Statement.RETURN_GENERATED_KEYS);
            ptsmt.setInt(1, id);
            ptsmt.executeUpdate();
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
