package com.example.c7.mywebview_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    private Button btn_go;
    private EditText et_url;
    private String urlHead="http://";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    public void initView(){
        btn_go=(Button) findViewById(R.id.btn_go);
        et_url=(EditText) findViewById(R.id.et_url);
        btn_go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri content_uri=Uri.parse(urlHead+et_url.getText().toString());
                        intent.setData(content_uri);
                        startActivity(Intent.createChooser(intent,"请选择一个浏览器"));
        }
        });
    }
}


