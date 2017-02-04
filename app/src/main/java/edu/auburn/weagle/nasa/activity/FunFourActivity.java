package edu.auburn.weagle.nasa.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.config.AppConfig;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class FunFourActivity extends BaseActivity implements View.OnClickListener{
    private Button btnSelect,btnCheck;

//    private Spinner spinner;
    private static int selected;
    private TextView tvDateStart, tvDateEnd;
//    private DatePicker startDatePicker, endDatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_four);
        btnSelect = (Button) findViewById(R.id.btn_select);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(this);
        tvDateStart = (TextView) findViewById(R.id.tv_start_date);
        tvDateEnd = (TextView) findViewById(R.id.tv_end_date);
//        spinner = (Spinner) findViewById(R.id.spinner);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCamera();
            }
        });
        tvDateStart.setOnClickListener(this);
        tvDateEnd.setOnClickListener(this);
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
    private String date = null;
    private String time = null;
    private String finalDate = null;
    private String date1 = null;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Calendar calendar = Calendar.getInstance();
        switch (id){
            case R.id.tv_start_date:
                tvDateStart.setFocusable(true);
                tvDateStart.setFocusableInTouchMode(true);

                Date d = new Date();
                calendar.setTime(d);

                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date = (monthOfYear+1)+"/"+dayOfMonth+"/"+year;
                        tvDateStart.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.tv_end_date:
                tvDateEnd.setFocusable(true);
                tvDateEnd.setFocusableInTouchMode(true);

                Date d1 = new Date();
                calendar.setTime(d1);

                DatePickerDialog dialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date1 = (monthOfYear+1)+"/"+dayOfMonth+"/"+year;
                        tvDateEnd.setText(date1);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog1.show();
                break;
            case R.id.btnCheck:
                Intent intent = new Intent(FunFourActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
        }
    }
}
