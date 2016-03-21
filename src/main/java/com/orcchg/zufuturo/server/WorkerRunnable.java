package com.orcchg.zufuturo.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

    protected Socket mClientSocket = null;
    protected String mServerText = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.mClientSocket = mClientSocket;
        this.mServerText = serverText;
    }

    public void run() {
        try {
            InputStream input  = mClientSocket.getInputStream();
            OutputStream output = mClientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + mServerText + " - " + time + "").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

