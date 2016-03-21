package com.orcchg.zufuturo.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerLooper implements Runnable {
    protected int mServerPort = 8080;
    protected ServerSocket mServerSocket = null;
    protected boolean mIsStopped = false;
    protected Thread mWorkerThread = null;
    protected ExecutorService mThreadPool = Executors.newFixedThreadPool(10);

    public ServerLooper(int port) {
        mServerPort = port;
    }

    public void run() {
        synchronized(this) {
            mWorkerThread = Thread.currentThread();
        }
        openServerSocket();
        loop();
        mThreadPool.shutdown();
        System.out.println("Server Stopped.") ;
    }

    private synchronized boolean mIsStopped() {
        return mIsStopped;
    }

    public synchronized void stop() {
        mIsStopped = true;
        try {
            mServerSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            mServerSocket = new ServerSocket(mServerPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    private void loop() {
        while (!mIsStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = mServerSocket.accept();
            } catch (IOException e) {
                if(mIsStopped()) {
                    System.out.println("Server Stopped.") ;
                    break;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            mThreadPool.execute(new WorkerRunnable(clientSocket, "Thread Pooled Server"));
        }
    }
}

