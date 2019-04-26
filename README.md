# MyWebView_Intent_test5_2
这是实验五的第二个应用：自定义WebView来加载URL

# 实验五
学号：123012016072 
姓名：陈琪
班级：软工2班

## 一、实验内容
1、第一个应用：获取URL地址并启动隐式Intent的调用
2、第二个应用：自定义WebView来加载URL
## 二、实验步骤和结果
### 1、获取URL地址并启动隐式Intent的调用
#### (1)关键代码：
**1、MainActivity.java:**
```
package com.example.c7.intent_implicit;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    private Button btn_go;
    private EditText et_url;
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
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri content_uri=Uri.parse(et_url.getText().toString());
                intent.setData(content_uri);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}


```
**2、AndroidManifest.xml:**
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.c7.intent_implicit">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

#### (2)结果截图：
#### 1、输入网址：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019042609183018.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)

#### 2、跳转系统默认的谷歌浏览器：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190426091904361.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)


### （2）第二个应用：自定义WebView来加载URL
#### (1)关键代码：
**1、MainActivity.java**
```
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
```


**2、MyWebView.java**
```
package com.example.c7.mywebview_intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by c7 on 2019/4/17.
 */

public class MyWebView extends AppCompatActivity{
    private WebView webView;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywebview);
        initView();
        setWebView();
    }

    private void initView(){
        webView=(WebView) findViewById(R.id.my_webView);
        url=getIntent().getData().toString();
    }

    private void setWebView(){
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;
            }
        });
    }
}
```


**3、AndroidManifest.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.c7.mywebview_intent">
    <uses-permission android:name="android.permission.INTERNET"/>
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity android:name=".MyWebView">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT"/>
                <data android:scheme="@string/protocolhead"/>
            </intent-filter>
        </activity>
    </application>
</manifest>
```



#### (2)结果截图：
#### 1、输入网址：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190426092243119.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)

#### 2、选择其中一个浏览器：选择自定义的webview浏览器
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190426092318635.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)

#### 3、跳转自定义的webview浏览器：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190426092352887.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ZpdmljNw==,size_16,color_FFFFFF,t_70)


## 三、总结
 使用隐式Intent，在AndroidManifest.xml通过在<activity>标签下配置<intent-filter>的内容，可以指定当前活动能够响应的action和category，我们不仅可以启动自己程序内的活动，还可以启动其他程序的活动，比如调用系统的浏览器来打开某网页
1、实例化一个Intent对象
2、使用Uri.parse()方法解析网页地址
3、intent.setAction()方法 PS：action规定了intent要完成的动作，是一个字符串常量，可以使系统自定义的action，比如本博客使用的ACTION_VIEW。
4、intent.setData()方法，传入的是Uri，用于数据过滤，可以让系统自动自动去寻找匹配目标组件
