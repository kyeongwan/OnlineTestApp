package kr.realtimetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lk on 2016. 11. 28..
 */

public class ConnectionActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_connect);

        final EditText etIP = (EditText) findViewById(R.id.et_ip);
        final Button btConnect = (Button) findViewById(R.id.bt_connect);

        btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnectionActivity.this, MainActivity.class);
                intent.putExtra("ip", etIP.getText().toString());
                startActivity(intent);
            }
        });

    }


}
