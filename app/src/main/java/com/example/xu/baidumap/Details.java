package com.example.xu.baidumap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Xu on 2016/10/20.
 */
public class Details extends Activity {
    private TextView name= null;
    private TextView number= null;
    private TextView long_lang= null;
    private TextView altitude= null;
    private TextView city= null;
    private TextView last_update= null;
    private TextView next_update= null;
    private Button list= null;
    private Button radar = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_detail);

        final Intent intent = getIntent();
        name = (TextView)findViewById(R.id.txt_friend_name);
        String d = intent.getStringExtra("name");
        name.setText(d);

        number = (TextView)findViewById(R.id.txt_friend_number);
        String n = intent.getStringExtra("number");
        number.setText(n);

        list = (Button)findViewById(R.id.btn_friends_list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radar = (Button)findViewById(R.id.btn_radar);
        radar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
