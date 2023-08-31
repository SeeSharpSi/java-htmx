package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 5678;
        System.out.println("Server hosted on port " + port);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new Index("/"));
        server.createContext("/test", new Index("/test"));
        server.createContext("/static", new Static());
        server.createContext("/first", new First());
        server.createContext("/second", new Second());
        server.start();
    }
}
