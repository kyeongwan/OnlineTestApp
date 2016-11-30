package test.socket;

import test.ui.Main;

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
                if(request.contains("done")){
                    while(!Main.getInstance().end){
                        this.sleep(1000);
                    }
                    String res= API.getInstance().request(request);
                    writer.write(res);
                    writer.flush();
                }else {
                    String response = API.getInstance().request(request);
                    writer.write(response);
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
