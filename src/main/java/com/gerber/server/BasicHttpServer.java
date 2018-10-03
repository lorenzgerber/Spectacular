package com.gerber.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class BasicHttpServer {

    private static final BasicHttpServer INSTANCE = new BasicHttpServer();

    private BasicHttpServer() {
         try{
             HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
             HttpContext context = server.createContext("/");
             context.setHandler(BasicHttpServer::handleRequest);
             server.start();

         } catch (IOException e){
             e.printStackTrace();
         }
    }


    public static BasicHttpServer getInstance() {

        return INSTANCE;
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        String response = "Hi there!";
        System.out.println(exchange.getRequestBody());
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }



}
