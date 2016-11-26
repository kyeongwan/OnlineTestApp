package kr.realtimetest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by lk on 2016. 11. 25..
 */

public class SocketThread extends Thread {


    Callback callback;
    Socket socket;
    BufferedWriter writer;
    BufferedReader reader;

    public SocketThread(Callback callback) {
        this.callback = callback;
    }

    public void run() {
        try {
            socket = new Socket("192.168.0.7", 8080);
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
}
