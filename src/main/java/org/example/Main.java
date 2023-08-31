package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

class Index implements HttpHandler {
    private final String input;

    public Index(String input) {
        this.input = input;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got " + input + " request");
        String file = Files.readString(Paths.get("src/main/html/index.html"));
        byte[] fileBytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, fileBytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(fileBytes);
        exchange.close();
        System.out.println("Closed");
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        HttpServer server = HttpServer.create(new InetSocketAddress(5678), 0);
        server.createContext("/", new Index("/"));
        server.createContext("/test", new Index("test"));
        server.start();
    }
}
