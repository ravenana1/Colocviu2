package com.example.colcviu.threaduri;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.example.colcviu.general.Utilities;

import android.util.Log;

public class CommunicationThread extends Thread {
	
	private ServerThread serverThread;
	private Socket       socket;
	
	public CommunicationThread(ServerThread serverThread, Socket socket) {
		this.serverThread = serverThread;
		this.socket       = socket;
	}
	
	@Override
	public void run() {
		if (socket != null) {
			try {
				BufferedReader bufferedReader = Utilities.getReader(socket);
				PrintWriter    printWriter    = Utilities.getWriter(socket);
				if (bufferedReader != null && printWriter != null) {
					Log.i("DEBUG", "[COMMUNICATION THREAD] Waiting for parameters from client (city / information type)!");
					String linie            = bufferedReader.readLine();
					
					String[] totul = linie.split(",");
					
					if(totul[0].equals("add")){
						int rez = Integer.parseInt(totul[1]) + Integer.parseInt(totul[2]);
						printWriter.println(rez);
						printWriter.flush();
					}
					else{
						try {
							sleep(1000);
							int rez = Integer.parseInt(totul[1]) * Integer.parseInt(totul[2]);
							printWriter.println(rez);
							printWriter.flush();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}	
				}
				socket.close();
			}
			 catch (IOException ioException) {
				Log.e("DEBUG", "[COMMUNICATION THREAD] An exception has occurred: " + ioException.getMessage());
				
					ioException.printStackTrace();
				
			
		} 
	}
		else {
			Log.e("DEBUG", "[COMMUNICATION THREAD] Socket is null!");
		}
	}

}
