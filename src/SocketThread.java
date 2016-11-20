import java.net.Socket;

/**
 * Created by lk on 2016. 11. 20..
 */
public class SocketThread extends Thread{

    private Socket socket;

    public SocketThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){

    }
}
