package com.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** 
* @author: pycrab
* @Date: 2018年8月30日 下午5:56:55 
*/
public class TestServer {
	
	public static void main(String[] args) {
		while (true) {
			try {
				ServerSocket ss = new ServerSocket(5678);
				Socket socket = ss.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("hello socket");
				System.out.println(socket.getInetAddress() + " Port#" + socket.getPort());
				
				dos.flush();
				dos.close();
				socket.close();
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}
