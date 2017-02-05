package edu.auburn.weagle.nasa.activity;

import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.activity.BaseActivity;

/**
 * Created by yan on 2/5/17.
 */

public class DescriptionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
    }
}
