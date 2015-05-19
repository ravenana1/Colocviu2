package com.example.colcviu.threaduri;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

public class ServerThread extends Thread {
	
	private int          port         = 2020;
	private ServerSocket serverSocket = null;
	
	
	public ServerThread(int port) {
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException ioException) {
			Log.e("DEBUG", "An exception has occurred: " + ioException.getMessage());
			ioException.printStackTrace();
		}
	}
	
	public void setServerSocker(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	@Override
	public void run() {
		try {		
			while (!Thread.currentThread().isInterrupted()) {
				Log.i("DEBUG", "[SERVER] Waiting for a connection...");
				Socket socket = serverSocket.accept();
				Log.i("DEBUG", "[SERVER] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
				CommunicationThread communicationThread = new CommunicationThread(this, socket);
				communicationThread.start();
			}			
		} catch (ClientProtocolException clientProtocolException) {
			Log.e("DEBUG", "An exception has occurred: " + clientProtocolException.getMessage());
			
				clientProtocolException.printStackTrace();
					
		} catch (IOException ioException) {
			Log.e("DEBUG", "An exception has occurred: " + ioException.getMessage());
			
				ioException.printStackTrace();
			
		}
	}
	
	public void stopThread() {
		if (serverSocket != null) {
			interrupt();
			try {
				serverSocket.close();
			} catch (IOException ioException) {
				Log.e("DEBUG", "An exception has occurred: " + ioException.getMessage());
				
					ioException.printStackTrace();
								
			}
		}
	}

}
