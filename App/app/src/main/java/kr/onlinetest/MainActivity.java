package kr.onlinetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLogin;
    Button btStart;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;

    String idStr;
    EditText etID;
    EditText etName;
    EditText etRoomCode;
    String roomCode;
    SocketThread thread;
    TextView tvQuestion;
    int problemNo = 0;

    int btLoginID;
    int btStartID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = (Button) findViewById(R.id.bt_login);
        etID = (EditText) findViewById(R.id.et_id);
        etName = (EditText) findViewById(R.id.et_name);
        btLoginID = btLogin.getId();

        thread = new SocketThread(new Callback() {
            @Override
            public void response(String res) {
                System.out.println(res);
                final String[] data = res.split("/");
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (data[0].equals("login")) {
                            roomCode = data[1];
                        } else if (data[0].equals("question")) {
                            tvQuestion.setText(data[1]);
                        }
                    }
                });
            }
        });
        thread.start();

        btLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (btLoginID == id) {
            idStr = etID.getText().toString();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    thread.request("login " + etID.getText() + " " + etName.getText() + "\r\n", "login");
                }
            }).start();
            setContentView(R.layout.activity_roomcode);
            btStart = (Button) findViewById(R.id.bt_start);
            btStart.setOnClickListener(this);
            etRoomCode = (EditText) findViewById(R.id.et_roomcode);
            btStartID = btStart.getId();
        } else if (btStartID == id) {
            System.out.println("Roon Code = " + roomCode + "     /  입력값 : " + etRoomCode.getText());
            if (etRoomCode.getText().toString().equals(roomCode)) {
                setContentView(R.layout.activity_problem);
                problemNo++;
                tvQuestion = (TextView) findViewById(R.id.tv_question);
                bt1 = (Button) findViewById(R.id.bt_1);
                bt2 = (Button) findViewById(R.id.bt_2);
                bt3 = (Button) findViewById(R.id.bt_3);
                bt4 = (Button) findViewById(R.id.bt_4);
                bt5 = (Button) findViewById(R.id.bt_5);
                bt1.setOnClickListener(this);
                bt2.setOnClickListener(this);
                bt3.setOnClickListener(this);
                bt4.setOnClickListener(this);
                bt5.setOnClickListener(this);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        thread.request("question " + problemNo + "\r\n", "question");
                    }
                }).start();
                Toast.makeText(getApplicationContext(), "시험이 시작되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "RoomCode 가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (id == bt1.getId()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(problemNo > 10){

                        }else{
                            thread.request("answer " + problemNo + " " + 1 + " " + idStr + "\r\n", "answer");
                            problemNo++;
                            thread.request("question " + problemNo + "\r\n", "question");
                        }

                    }
                }).start();
            } else if (id == bt2.getId()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(problemNo > 10){

                        }else{
                            thread.request("answer " + problemNo + " " + 2 + " " + idStr + "\r\n", "answer");
                            problemNo++;
                            thread.request("question " + problemNo + "\r\n", "question");
                        }
                    }
                }).start();
            } else if (id == bt3.getId()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(problemNo > 10){

                        }else{
                            thread.request("answer " + problemNo + " " + 3 + " " + idStr + "\r\n", "answer");
                            problemNo++;
                            thread.request("question " + problemNo + "\r\n", "question");
                        }
                    }
                }).start();
            } else if (id == bt4.getId()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if(problemNo > 10){

                        }else{
                            thread.request("answer " + problemNo + " " + 4 + " " + idStr + "\r\n", "answer");
                            problemNo++;
                            thread.request("question " + problemNo + "\r\n", "question");
                        }
                    }
                }).start();
            } else if (id == bt5.getId()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(problemNo > 10){

                        }else{
                            thread.request("answer " + problemNo + " " + 5 + " " + idStr + "\r\n", "answer");
                            problemNo++;
                            thread.request("question " + problemNo + "\r\n", "question");
                        }
                    }
                }).start();
            }
        }
    }
}
