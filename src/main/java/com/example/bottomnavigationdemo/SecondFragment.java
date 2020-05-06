package com.example.bottomnavigationdemo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utils.JsonBean;
import utils.Sqlite;

public class SecondFragment extends Fragment {
    private WebView webview;
    private List<JsonBean> jsonBeans;
    private SwipeRefreshLayout swipe;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        webview=view.findViewById(R.id.zhanzhubang);
        swipe=view.findViewById(R.id.frist_fragment_SwipeRefreshLayout);
//        recyclerView = view.findViewById(R.id.list_item);

//        imageView = view.findViewById(R.id.imageView);
        return view;
    }
    //重写方法 onCreateOptionsMenu
    //查找menu文件
    //在onActivityCreated中 setHasOptionsMenu(true) 设置为true
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.refrsh,menu);
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

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        //刷新监听
        loadurl();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webview.clearHistory();
                webview.clearFormData();
                loadurl();
                swipe.setRefreshing(false);
            }
        });
        //当子控件返回顶部监听
        swipe.setOnChildScrollUpCallback(new SwipeRefreshLayout.OnChildScrollUpCallback() {
            @Override
            public boolean canChildScrollUp(@NonNull SwipeRefreshLayout parent, @Nullable View child) {
                return webview.getScrollY()>0;
            }
        });
        //加载url
        //获取menu


        //        Toast.makeText(getActivity(), "查询赞助榜", Toast.LENGTH_SHORT).show();
//        searchData();
//        showList();
//        mViewModel = ViewModelProviders.of(requireActivity()).get(SecondViewModel.class);
//        imageView.setScaleX(mViewModel.scaleFactor);
//        imageView.setScaleY(mViewModel.scaleFactor);
//        // TODO: Use the ViewModel
//        final ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 0);
//        final ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 0);
//        objectAnimatorX.setDuration(500);
//        objectAnimatorY.setDuration(500);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!objectAnimatorX.isRunning()) {
//                    objectAnimatorX.setFloatValues(imageView.getScaleX() + 0.1f);
//                    objectAnimatorY.setFloatValues(imageView.getScaleY() + 0.1f);
//                    mViewModel.scaleFactor += 0.1;
//                    objectAnimatorX.start();
//                    objectAnimatorY.start();
//                }
//            }
//        });

    }

//    private void showList() {
//        LinearLayoutManager manager=new LinearLayoutManager(getActivity().getApplication().getApplicationContext());
//            manager.setOrientation(LinearLayoutManager.VERTICAL);
//            manager.setReverseLayout(false);
//            recyclerView.setLayoutManager(manager);
//        ListViewAdapter adapter=new ListViewAdapter(getActivity().getApplicationContext(),jsonBeans);
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("msg","我被销毁了");
    }

    private void loadurl(){
        //获取webSettings的设置对象
        WebSettings settings = webview.getSettings();
        //优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //允许javascript进行调用
        settings.setJavaScriptEnabled(true);
        //webview对象添加进url链接
        webview.loadUrl("http://www.suika.fun/zanzhu/baltop.html");
//        webview.loadUrl("http://192.168.31.94:8080/1.html");
        //将图片调整到webview大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //编码格式
        settings.setDefaultTextEncodingName("utf-8");
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
//        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }
    private void searchData() {
        Sqlite sqlite = new Sqlite(Objects.requireNonNull(getActivity()).getApplicationContext());
        SQLiteDatabase db = sqlite.getWritableDatabase();
        Cursor cursor = db.query("info", new String[]{"Count","Message","Player"}, null, null, null, null, null,"40");
            int count1 =cursor.getCount();
            jsonBeans = new ArrayList<>();
                jsonBeans.clear();
                Log.d("msg","开始查询");
                if (cursor.getCount()>0){
                    cursor.moveToFirst();
                    for (int i=0;i<cursor.getCount();i++){
                        String count =cursor.getString(cursor.getColumnIndex("Count"));
                        String Message= cursor.getString(cursor.getColumnIndex("Message"));
                        String player= cursor.getString(cursor.getColumnIndex("Player"));
                        JsonBean jsonBean =new JsonBean();
                        jsonBean.setCount(count);
                        jsonBean.setMessage(Message);
                        jsonBean.setPlayer(player);
                        jsonBeans.add(jsonBean);
                        cursor.moveToNext();
                    }
                }
                cursor.close();
                Log.d("msg", jsonBeans.toString());
                  Log.d("msg",count1+"");
                db.close();
    }
}
