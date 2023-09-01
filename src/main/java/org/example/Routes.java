package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
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
        byte[] bytes = "<div>form submitted</div>".getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        byte[] query = exchange.getRequestBody().readAllBytes();
        Map<String, String> query_map = splitQuery(new String(query, StandardCharsets.UTF_8));
        System.out.println(query_map);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}

class First implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /first request");
        String file = Files.readString(Paths.get("src/main/html/first.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}

class Second implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("got /second request");
        String file = Files.readString(Paths.get("src/main/html/second.html"));
        byte[] file_bytes = file.getBytes();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file_bytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.getResponseBody().write(file_bytes);
        exchange.close();
    }
}
