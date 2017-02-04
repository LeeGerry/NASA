package edu.auburn.weagle.nasa.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.config.AppConfig;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class FunFourActivity extends BaseActivity {
    private Button btnSelect;
//    private Spinner spinner;
    private static int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_four);
        btnSelect = (Button) findViewById(R.id.btn_select);
//        spinner = (Spinner) findViewById(R.id.spinner);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCamera();
            }
        });
    }
    private void chooseCamera(){
        AlertDialog.Builder builder = new AlertDialog.Builder(FunFourActivity.this);
        builder.setTitle(getString(R.string.select_camera));
        builder.setIcon(android.R.drawable.btn_star_big_on);
        builder.setSingleChoiceItems(AppConfig.types, selected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = i;
                btnSelect.setText(AppConfig.types[i]);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }
}
