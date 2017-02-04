package edu.auburn.weagle.nasa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Author: Gary
 * Time: 17/2/3
 */

public class BaseActivity extends Activity {
    public static String TAG ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getName();
    }
    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    protected void mylog(String message){
        Log.i(TAG,message);
    }
}

