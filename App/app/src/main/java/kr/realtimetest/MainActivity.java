package kr.realtimetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
    int answerID[];

    BroadcastReceiver mPowerBroadcast;
    boolean powerOn = false;


    @Override
    public void onStop() {
        super.onStop();
        System.out.println("stop");


        if(powerOn){
            // 화면이 꺼진 상태
        }else{
            // 앱이 내려간 상태
            new Thread(new Runnable() {
                @Override
                public void run() {
                    thread.request("event " + idStr + " stop" + "\r\n", "event");
                }
            }).start();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        System.out.println("restart");


        if(powerOn){
            // 화면이 꺼진 상태
        }else{
            // 앱이 내려간 상태
            new Thread(new Runnable() {
                @Override
                public void run() {
                    thread.request("event " + idStr + " restart" + "\r\n", "event");
                }
            }).start();
        }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mPowerBroadcast);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);

        mPowerBroadcast = new BroadcastReceiver()
        {
            public static final String ScreenOff = "android.intent.action.SCREEN_OFF";
            public static final String ScreenOn = "android.intent.action.SCREEN_ON";

            public void onReceive(Context contex, Intent intent)
            {
                if (intent.getAction().equals(ScreenOff))
                {
                    Log.e("MainActivity", "Screen Off");
                    powerOn = true;
                }
                else if (intent.getAction().equals(ScreenOn))
                {
                    Log.e("MainActivity", "Screen On");
                    powerOn = false;
                }
            }
        };

        registerReceiver(mPowerBroadcast, intentFilter);

        btLogin = (Button) findViewById(R.id.bt_login);
        etID = (EditText) findViewById(R.id.et_id);
        etName = (EditText) findViewById(R.id.et_name);
        btLoginID = btLogin.getId();

        thread = new SocketThread(new Callback() {
            @Override
            public void response(String res) {
                System.out.println("res : " + res);
                final String[] data = res.split("/");
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (data[0].equals("login")) {
                            roomCode = data[1];
                        } else if (data[0].equals("question")) {
                            if (Integer.parseInt(data[1]) == 1) {
                                bt3.setVisibility(View.GONE);
                                bt4.setVisibility(View.GONE);
                                bt5.setVisibility(View.GONE);
                            } else {
                                bt3.setVisibility(View.VISIBLE);
                                bt4.setVisibility(View.VISIBLE);
                                bt5.setVisibility(View.VISIBLE);
                            }
                            tvQuestion.setText(data[2]);
                        } else if (data[0].equals("null")) {
                            Toast.makeText(getApplicationContext(), "서버에 연결되어 있지 않습니다", Toast.LENGTH_SHORT).show();
                        } else if (data[0].equals("connect")) {
                            Toast.makeText(getApplicationContext(), "서버에 연결되었습니다", Toast.LENGTH_SHORT).show();
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

                answerID = new int[5];
                answerID[0] = bt1.getId();
                answerID[1] = bt2.getId();
                answerID[2] = bt3.getId();
                answerID[3] = bt4.getId();
                answerID[4] = bt5.getId();

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
            for (int i = 0; i < 5; i++) {
                if (id == answerID[i]) {
                    final int finalI = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (problemNo > 9) {
                                thread.request("answer " + problemNo + " " + (finalI + 1) + " " + idStr + "\r\n", "answer");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), problemNo + "번을 푸셨습니다.", Toast.LENGTH_SHORT).show();
                                        setContentView(R.layout.activity_done);
                                    }
                                });

                            } else {
                                thread.request("answer " + problemNo + " " + (finalI + 1) + " " + idStr + "\r\n", "answer");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), problemNo + "번을 푸셨습니다.", Toast.LENGTH_SHORT).show();
                                        problemNo++;
                                    }
                                });
                                int p = problemNo;
                                p++;                // thread 간 싱크를 위해 변수를 따로 둠.
                                thread.request("question " + p + "\r\n", "question");
                            }

                        }
                    }).start();
                }
            }
        }
    }
}
