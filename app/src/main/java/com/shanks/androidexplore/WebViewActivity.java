package com.shanks.androidexplore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by shanksYao on 10/18/15.
 */
public class WebViewActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        WebView webView= (WebView) findViewById(R.id.webview);
      //  webView.setWebChromeClient(new WebChromeClient());
        webView.loadData(getIntent().getStringExtra("data"),"text/html","utf-8");
    }

    public static void startActivity(String data,Context context){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

}
