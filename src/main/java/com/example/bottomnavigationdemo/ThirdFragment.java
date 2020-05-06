package com.example.bottomnavigationdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThirdFragment extends Fragment {

    private WebView webView;
    private SwipeRefreshLayout swip;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
//        paypal=view.findViewById(R.id.paypal);
//        tenect=view.findViewById(R.id.tenectpal);
//        wechatpal=view.findViewById(R.id.wechatpal);
//        linearLayout=view.findViewById(R.id.line1);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Resources res=getResources();
//                final Bitmap paypal= BitmapFactory.decodeResource(res,R.drawable.paypal);
//                final Bitmap  tenectpal =BitmapFactory.decodeResource(res,R.drawable.tenectpal);
//                final Bitmap wechatpal=BitmapFactory.decodeResource(res,R.drawable.wechatpal);
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("保存图片")
//                        .setMessage("保存图片到相册")
//                        .setCancelable(true)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                try {
//                                    saveImage(getActivity(),paypal);
//                                    saveImage(getActivity(),tenectpal);
//                                    saveImage(getActivity(),wechatpal);
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                }
//                                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
//                            }
//                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                }).show();
//
//            }
//        });
           webView=view.findViewById(R.id.hd);
           swip=view.findViewById(R.id.thirdFragment);
        return view;

    }

    public static void saveImage(Context context,Bitmap bitmap) throws FileNotFoundException {
        File appdir=new File("/sdcard/Pictures/","ale");
        if (!appdir.exists()){
            appdir.mkdirs();
        }
        String fileName=System.currentTimeMillis()+".jpg";
        File file=new File(appdir,fileName);
        FileOutputStream fos=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),fileName,null);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadurl();
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.clearCache(true);
                webView.clearHistory();
                webView.clearFormData();
                loadurl();
                swip.setRefreshing(false);
            }

        });
        swip.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(@NonNull SwipeRefreshLayout parent, @Nullable View child) {

                return webView.getScrollY()>0;
            }
        });
//        Toast.makeText(getActivity(), "点击图片保存到手机相册", Toast.LENGTH_SHORT).show();
    }

    private void loadurl() {
        //获取webSettings的设置对象
        WebSettings settings = webView.getSettings();
        //优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //允许javascript进行调用
        settings.setJavaScriptEnabled(true);
        //webview对象添加进url链接
        webView.loadUrl("http://www.suika.fun/hd/index.html");
//        webview.loadUrl("http://192.168.31.94:8080/1.html");
        //将图片调整到webview大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //编码格式
        settings.setDefaultTextEncodingName("utf-8");
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
//        webview.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

        });
    }
}
