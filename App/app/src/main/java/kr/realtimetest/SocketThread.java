package kr.realtimetest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class SocketThread extends Thread {


    Callback callback;          // 콜백을 위한 인터페이스
    Socket socket;
    BufferedWriter writer;
    BufferedReader reader;
    String ip;
    Context context;

    public SocketThread(Callback callback, Context context, String ip) {
        this.callback = callback;
        this.context = context;
        this.ip = ip;
    }

    public void run() {
        try {
            socket = new Socket(ip, 8080);
            Log.e("socket", socket.getInetAddress() + "");
            callback.response("connect");
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request(String request, String api) {
        try {
            Log.i(api, request);
            writer.write(request);
            writer.flush();
            String response = reader.readLine();
            callback.response(api + "/" + response);
        }catch (IOException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
            callback.response("null");
        }
    }

    public void endServer() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
