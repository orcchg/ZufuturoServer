package com.orcchg.zufuturo.server;

public class ServerMain {

    public static void main(String[] args) {
        ServerLooper server = new ServerLooper(9000);
        new Thread(server).start();

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }
}

