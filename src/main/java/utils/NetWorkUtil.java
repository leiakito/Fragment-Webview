package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/*
    Author:leia
    Write The Code Change The World    
*/public class NetWorkUtil  {

    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                if (manager==null){
                    Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
                    return false;
                }
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                    if (networkInfo==null||!networkInfo.isConnected()){
                        return false;
                    }
                return true;
    }
}
