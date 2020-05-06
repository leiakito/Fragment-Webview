package com.example.bottomnavigationdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import utils.JsonBean;
import utils.Sqlite;

public class StartActivity extends AppCompatActivity {
    public ImageView imageView;
    public String result;

    @Override
    protected void onPostResume() {
        super.onPostResume();
//        getJson();
    }

    private void getJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path = "http://www.suika.fun/Sponsored.json";
                try {
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setConnectTimeout(5000);
                        int code=conn.getResponseCode();
                        if (code==200){
                         InputStream inputStream=  conn.getInputStream();
                            StringBuffer stringBuffer=new StringBuffer();
                                byte[] byt=new byte[4096];
                               for (int i;(i=inputStream.read(byt))!=-1;){
                                    stringBuffer.append(new String(byt,0,i));
                               }
                            result = stringBuffer.toString();
                            JSONArray jsonArray=new JSONArray(result);
                            JsonBean jsonBean;
                            Sqlite sqlite=new Sqlite(getApplicationContext());
                            SQLiteDatabase db=sqlite.getReadableDatabase();
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                String count = object.optString("Count");
                                String player=object.optString("Player");
                                String message=object.optString("Message");
                                jsonBean=new JsonBean(count,message,player);
                                ContentValues values=new ContentValues();
                                values.put("Count",jsonBean.getCount());
                                values.put("Message",jsonBean.getMessage());
                                values.put("Player",jsonBean.getPlayer());
                                db.insert("info",null,values);
                            }
                            db.close();
                        }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
//        Context context=getApplicationContext();
//       NetWorkUtil.isNetWorkAvailable(context);
        //设置画面启动
//         intitView();
        Intent intent=new Intent(StartActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void intitView( ) {
        //找id
        imageView=findViewById(R.id.start_image);
        //设置AlphaAnimation对象 参数为0.2f-1.0f 意味不透明度从0.2到1.0f
        AlphaAnimation animation=new AlphaAnimation(0.3f,1.0f );
        imageView.setAnimation(animation);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                    startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}
