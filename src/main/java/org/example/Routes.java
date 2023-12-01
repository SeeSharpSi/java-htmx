package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Index implements HttpHandler {
    private final String input;

    public Index(String input) {
        this.input = input;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got " + input + " request");
        String file = Files.readString(Paths.get("src/main/html/index.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Home implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /home request");
        String file = Files.readString(Paths.get("src/main/html/home.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Receipt implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /receipt request");
        String file = Files.readString(Paths.get("src/main/html/receipt.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}
class Financial implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /financial report request");
        String file = Files.readString(Paths.get("src/main/html/Financial_Report.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Order implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /home request");
        String file = Files.readString(Paths.get("src/main/html/order.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Other implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /home request");
        String file = Files.readString(Paths.get("src/main/html/other.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Rewards implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /home request");
        String file = Files.readString(Paths.get("src/main/html/rewards.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Cart implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /home request");
        String file = Files.readString(Paths.get("src/main/html/cart.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Static implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI req_uri = exchange.getRequestURI();
        String uri_path = req_uri.getPath();
        System.out.println("got " + uri_path + " request");
        String file = Files.readString(Paths.get("src/main/" + uri_path));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        List<String> content_type = exchange.getRequestHeaders().get("Accept");
        System.out.println("MIME type: " + content_type.get(0));
        exchange.getResponseHeaders().add("Content-Type", content_type.get(0));
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Form implements HttpHandler {
    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /form request");
        byte[] bytes = "<div>form submitted</div> <button hx-get='/form_html' hx-target='#form' hx-swap='outerHTML'>New form</button>".getBytes();
        byte[] query = exchange.getRequestBody().readAllBytes();
        Map<String, String> query_map = splitQuery(new String(query, StandardCharsets.UTF_8));
        System.out.println(query_map);
        Connection connection = null;
        SQLController sqlController = new SQLController();
        sqlController.addTopping(1, query_map.get("lname"), 0.50F);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            statement.executeUpdate(String.format("insert into person values(null, '%s', '%s')", query_map.get("fname"),
                    query_map.get("lname")));
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                System.out.println("fname = " + rs.getString("fname"));
                System.out.println("lname = " + rs.getString("lname"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            bytes = "SQL Error".getBytes();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                bytes = "SQL Error".getBytes();
            }
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.close();
        }
    }
}

class Form_Html implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /form_html request");
        String file = Files.readString(Paths.get("src/main/html/form_html.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Test implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /test request");
        String[] stuff = {"test1", "Jacob", "bob"};
        StringBuilder file = new StringBuilder();
        for (String s : stuff) {
            file.append(String.format("<div>Item: %s</div>", s));
        }
        byte[] file_bytes = file.toString().getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Menu_Submit implements HttpHandler {
    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                    URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /menu_submit request");
        byte[] bytes = "<div>Menu Saved</div> <button hx-get='/order' hx-target='#main' hx-swap='innerHTML'>Edit Choices</button>".getBytes();
        byte[] query = exchange.getRequestBody().readAllBytes();
        Map<String, String> query_map = splitQuery(new String(query, StandardCharsets.UTF_8));
        System.out.println(query_map);
        Cart_Vals.CrustType = query_map.get("CrustType");
        Cart_Vals.supreme_pizza = query_map.get("surpreme_pizza");
        Cart_Vals.hawaiian_pizza = query_map.get("hawaiian_pizza");
        Cart_Vals.toppings_one = query_map.get("toppings_one");
        Cart_Vals.toppings_two = query_map.get("toppings_two");
        Cart_Vals.toppings_three = query_map.get("toppings_three");
        Cart_Vals.toppings_four = query_map.get("toppings_four");
        Cart_Vals.toppings_five = query_map.get("toppings_five");
        Cart_Vals.ultimate_pepperoni = query_map.get("ultimate_pepperoni");
        System.out.println("CART: " + Cart_Vals.CrustType);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}