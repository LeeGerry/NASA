package edu.auburn.weagle.nasa.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.activity.view.MyRadioGroup;
import edu.auburn.weagle.nasa.config.AppConfig;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class FunFourActivity extends BaseActivity implements View.OnClickListener{
    private Button btnDate;
    private EditText etNumber;
    private LinearLayout lltips1, lltips2,llCheck;
    private MyRadioGroup mrg1, mrg2;
    private ImageView ivBack;
    private TextView tvDateStart;
    private RadioGroup radioGroup, rgSolDate;
    private RadioButton rbSol, rbDate;
    private RadioButton rbRover1, rbRover2, rbRover3;
    private RadioButton mg1c1,mg1c2,mg1c3,mg1c4,mg1c5,mg1c6,mg1c7;
    private RadioButton mg2c1,mg2c2,mg2c7,mg2c8,mg2c9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_four);
        btnDate = (Button) findViewById(R.id.btnDate);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etNumber = (EditText) findViewById(R.id.et_number);
        llCheck = (LinearLayout) findViewById(R.id.llCheck);
        btnDate.setOnClickListener(this);
        lltips1 = (LinearLayout) findViewById(R.id.ll_tips1);
        lltips2 = (LinearLayout) findViewById(R.id.ll_tips2);
        mrg1 = (MyRadioGroup) findViewById(R.id.mrg1);
        mrg2 = (MyRadioGroup) findViewById(R.id.mrg2);

        mg1c1 = (RadioButton) findViewById(R.id.mg1_c1);
        mg1c2 = (RadioButton) findViewById(R.id.mg1_c2);
        mg1c3 = (RadioButton) findViewById(R.id.mg1_c3);
        mg1c4 = (RadioButton) findViewById(R.id.mg1_c4);
        mg1c5 = (RadioButton) findViewById(R.id.mg1_c5);
        mg1c6 = (RadioButton) findViewById(R.id.mg1_c6);
        mg1c7 = (RadioButton) findViewById(R.id.mg1_c7);
        mg2c1 = (RadioButton) findViewById(R.id.mg2_c1);
        mg2c2 = (RadioButton) findViewById(R.id.mg2_c1);
        mg2c7 = (RadioButton) findViewById(R.id.mg2_c7);
        mg2c8 = (RadioButton) findViewById(R.id.mg2_c8);
        mg2c9 = (RadioButton) findViewById(R.id.mg2_c9);

        llCheck.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.rg_mode);
        rgSolDate = (RadioGroup) findViewById(R.id.rg_sol_date);
        tvDateStart = (TextView) findViewById(R.id.tv_start_date);
        rbSol = (RadioButton) findViewById(R.id.rb_sol);
        rbDate = (RadioButton)findViewById(R.id.rb_date);
        rbRover1 = (RadioButton)findViewById(R.id.rb_r1);
//        tvTipsCamera = (TextView) findViewById(R.id.tv_camera_tips);
        rbRover2 = (RadioButton)findViewById(R.id.rb_r2);
        rbRover3 = (RadioButton)findViewById(R.id.rb_r3);
        radioGroup.check(R.id.rb_r1);
        mrg1.checkRadioButton(mg1c1);
        mrg2.checkRadioButton(mg2c1);
        Log.i(TAG,""+mrg1.getCheckedRadioButtonId()+","+mrg1.getId()+","+mrg2.getId()+","+mrg2.getCheckedRadioButtonId());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb_r1){
                    mrg1.setVisibility(View.VISIBLE);
                    mrg2.setVisibility(View.GONE);
                }else {
                    mrg1.setVisibility(View.GONE);
                    mrg2.setVisibility(View.VISIBLE);
                }
            }
        });


        rgSolDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_sol){
                    lltips1.setVisibility(View.VISIBLE);
                    lltips2.setVisibility(View.GONE);
                }else {
                    lltips1.setVisibility(View.GONE);
                    lltips2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private String rover, camera, earth_date,sol;
    private String date = null;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Calendar calendar = Calendar.getInstance();
        switch (id){
            case R.id.btnDate:
                Date d = new Date();
                calendar.setTime(d);

                DatePickerDialog dialog = new DatePickerDialog(FunFourActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        String month="",day="";
                        if (monthOfYear<10)
                            month = "0"+ monthOfYear;
                        else
                            month = String.valueOf(monthOfYear);
                        if (dayOfMonth<10)
                            day = "0"+dayOfMonth;
                        else
                            day = String.valueOf(dayOfMonth);
                        date = year+"-"+month+"-"+day;
                        tvDateStart.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.llCheck:
                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_r1){
                    rover = AppConfig.ROVER_NAMES[0];
                    camera = getCamera(1);
                }else if(radioGroup.getCheckedRadioButtonId() == R.id.rb_r2){
                    rover = AppConfig.ROVER_NAMES[1];
                    camera = getCamera(2);
                }else {
                    rover = AppConfig.ROVER_NAMES[2];
                    camera = getCamera(2);
                }
                sol = etNumber.getText().toString().trim();
                earth_date = tvDateStart.getText().toString().trim();
                //Log.i(TAG, rover+","+camera+","+earth_date+","+sol);
                Intent intent = new Intent(FunFourActivity.this, ResultActivity.class);
                if (rbDate.isChecked()){
                    intent.putExtra("rover",rover);
                    intent.putExtra("camera",camera);
                    intent.putExtra("date",date);
                }else {
                    intent.putExtra("rover",rover);
                    intent.putExtra("camera",camera);
                    intent.putExtra("sol",sol);
                }
                startActivity(intent);
                break;
        }
    }
    private String getCamera(int i){
        String result;
        if(i == 1){
            Log.i(TAG,mrg1.getCheckedRadioButtonId()+"");
            if(mrg1.getCheckedRadioButtonId() == R.id.mg1_c1){
                result = AppConfig.types[0];
            }else if(mrg1.getCheckedRadioButtonId() == R.id.mg1_c2){
                result = AppConfig.types[1];
            }else if(mrg1.getCheckedRadioButtonId() == R.id.mg1_c3){
                result = AppConfig.types[2];
            }else if(mrg1.getCheckedRadioButtonId() == R.id.mg1_c4){
                result = AppConfig.types[3];
            }else if(mrg1.getCheckedRadioButtonId() == R.id.mg1_c5){
                result = AppConfig.types[4];
            }else if(mrg1.getCheckedRadioButtonId() == R.id.mg1_c6){
                result = AppConfig.types[5];
            }else{
                result = AppConfig.types[6];
            }
            Log.i(TAG,result);
        }else {
            Log.i(TAG,"2");
            if(mrg2.getCheckedRadioButtonId() == R.id.mg2_c1){
                result = AppConfig.types[0];
            }else if(mrg2.getCheckedRadioButtonId() == R.id.mg2_c2){
                result = AppConfig.types[1];
            }else if(mrg2.getCheckedRadioButtonId() == R.id.mg2_c7){
                result = AppConfig.types[6];
            }else if(mrg2.getCheckedRadioButtonId() == R.id.mg2_c8){
                result = AppConfig.types[7];
            }else{
                result = AppConfig.types[8];
            }
            Log.i(TAG,result);
        }
        return result;
    }
}
