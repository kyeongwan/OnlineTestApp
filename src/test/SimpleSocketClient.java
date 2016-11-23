package test;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleSocketClient {
	// 연결할 포트를 지정합니다.
	private static final int PORT = 8080;
	public static void main(String[] args) {
		try {
			// 소켓을 생성합니다.
			Socket socket = new Socket("localhost", PORT);

			Scanner s = new Scanner(System.in);
			while(true) {
				String d = s.nextLine();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				writer.write(d+"\r\n");
				writer.flush();

				// 스트림을 얻어옵니다.
				InputStream stream = socket.getInputStream();
				// 스트림을 래핑합니다.
				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
				// 결과를 읽습니다.
				String response = br.readLine();
				System.out.println(response); // 결과물 출력
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}