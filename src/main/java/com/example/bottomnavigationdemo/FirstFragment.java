package com.example.bottomnavigationdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FirstFragment extends Fragment {
    private WebView webivew;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        webivew = view.findViewById(R.id.webview);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.refrsh,menu);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        loadurl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.reload:
                loadurl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadurl(){
        //获取webSettings的设置对象
        WebSettings settings = webivew.getSettings();
        //优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //允许javascript进行调用
        settings.setJavaScriptEnabled(true);
        //webview对象添加进url链接
        webivew.loadUrl("http://mc.suika.fun:8123/index.html");
        //将图片调整到webview大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //编码格式
        settings.setDefaultTextEncodingName("utf-8");

        //开启DOM存储功能
        settings.setDomStorageEnabled(true);
        //开启应用存储目录
        settings.setAppCacheEnabled(true);
        //开启数据库存储功能
        settings.setDatabaseEnabled(true);

        settings.setAllowFileAccess(true);

        webivew.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }
}
