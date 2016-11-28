package test.socket;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread{

    private Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    public SocketThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        while(true){
            try {
                String request = reader.readLine();
                System.out.println(request);
                String response = API.getInstance().request(request);
                writer.write(response);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
