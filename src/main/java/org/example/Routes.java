package org.example;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


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
        System.out.println("Closed");
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
        System.out.println("Closed");
    }
}
