package test.socket;

import test.Global;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerThread extends Thread{

    @Override
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(Global.PORT);

            /** 1. 소켓을 받을 수 있는 상태로 대기한다.
                2. 새로운 소켓 어셉 요청이 오면 새로운 소켓을 열어 스레드로 전달해준다.
                3. 다시 새로운 소켓을 받을 수 있는 상태로 대기한다.
            */
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress()+" 님이 접속했습니다.");
                SocketThread socketThread = new SocketThread(socket);
                socketThread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
