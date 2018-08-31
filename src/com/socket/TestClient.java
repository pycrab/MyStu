package com.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/** 
* @author: pycrab
* @Date: 2018年8月30日 下午6:08:28 
*/
public class TestClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 5678);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			System.out.println(dis.readUTF());
			
			dis.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
