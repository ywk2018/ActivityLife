package com.example.activitylife;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 1.跳转到第二个活动，然后返回第一个活动  onPause->onStop->onRestart->onCreate->onResume
 * 2.跳转到主题为对话框的活动，然后在返回  onPause->onResume
 * 3.回到桌面，然后再返回     onpause->onStop->onRestart->onCreate->onResume
 * 4.退出应用程序的生命周期   onpause->onstop->onDestory
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    Button btnStartActivity,btnStartDialog;
    EditText mEditTextTransMit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
           String getString =  savedInstanceState.getString("save_data");
            Toast.makeText(this, "" + getString, Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "onCreate: ");       //初始化操作

        btnStartActivity = findViewById(R.id.btn_start_activity);
        btnStartDialog = findViewById(R.id.btn_start_dialog);
        btnStartActivity.setOnClickListener(this);
        btnStartDialog.setOnClickListener(this);
        mEditTextTransMit = findViewById(R.id.edit_transmit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_activity:
                String edtTransMit =mEditTextTransMit.getText().toString();
                Intent intent = new Intent(this,SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data_bundle",edtTransMit);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn_start_dialog:
                startActivity(new Intent(this,NormalActivity.class));
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState,
     PersistableBundle outPersistentState) {          //活动因为内存不足时，回收停止的activity会调用这个方法
        String saveNum = "123456";
        outState.putString("save_data",saveNum);
    }

    @Override
    protected void onStart() {      //活动可以被看见
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {     //活动可以被交互
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {      //活动被遮挡  保存数据，但是不能做耗时操作
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {      //活动不可见 资源回收
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {    //活动被销毁 内存回收
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
