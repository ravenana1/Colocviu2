package com.example.colcviu.threaduri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.example.colcviu.general.Utilities;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ClientThread extends Thread {
	
	private int      port;
	private String   operatie;
	private String op1;
	private String op2;
	private EditText rez1;
	private EditText rez2;
	
	
	private Socket   socket;
	
	public ClientThread(
			int port,
			String operatie,
			String op1,
			String op2,
			EditText rez1,
			EditText rez2) {
		this.port                    = port;
		this.operatie                = operatie;
		this.op1                = op1;
		this.op2                = op2;
		this.rez1         = rez1;
		this.rez2         = rez2;
		
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket("localhost", port);
			if (socket == null) {
				Log.e("DEBUG", "[CLIENT THREAD] Could not create socket!");
			}
			
			BufferedReader bufferedReader = Utilities.getReader(socket);
			PrintWriter    printWriter    = Utilities.getWriter(socket);
			if (bufferedReader != null && printWriter != null) {
				printWriter.println(operatie + "," + op1 + "," + op2);
				printWriter.flush();
				
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					final String rez = line;
					rez1.post(new Runnable() {
						@Override
						public void run() {
							if(operatie.equals("add")){
								rez1.append(rez + "\n");
							}
							else{
								rez2.append(rez + "\n");
							}
							
							
						}
					});
				}
			} else {
				Log.e("DEBUG", "[CLIENT THREAD] BufferedReader / PrintWriter are null!");
			}
			socket.close();
		} catch (IOException ioException) {
			Log.e("DEBUG", "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
			
		}
	}

}
