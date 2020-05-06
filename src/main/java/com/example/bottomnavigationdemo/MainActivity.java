package com.example.bottomnavigationdemo;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            return  true;
        }else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] PERMISSIONS = {
//                "android.permission.READ_EXTERNAL_STORAGE",
//                "android.permission.WRITE_EXTERNAL_STORAGE" };
//        //检测是否有写的权限
//        int permission = ContextCompat.checkSelfPermission(this,
//                "android.permission.WRITE_EXTERNAL_STORAGE");
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // 没有写的权限，去申请写的权限，会弹出对话框
//            ActivityCompat.requestPermissions(this, PERMISSIONS,1);
//        }

        //底部导航视图 对象 找到控件
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        //导航控制器 对象 查找到导航控制对象 里面填写的为fragment控件对象
        NavController navController = Navigation.findNavController(MainActivity.this,R.id.fragment);
        //应用栏配置对象 参数为 底部导航视图对象.getMenu()获取菜单,并构建
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //UI导航
        NavigationUI.setupActionBarWithNavController(MainActivity.this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }
}
