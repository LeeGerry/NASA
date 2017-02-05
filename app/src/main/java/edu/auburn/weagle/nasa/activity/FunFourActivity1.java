package edu.auburn.weagle.nasa.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
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
/*
public class FunFourActivity1 extends BaseActivity implements View.OnClickListener{
    private Button btnDate;
    private GridView gvCamera;
    private LinearLayout lltips1, lltips2,llCheck;
    private MyRadioGroup mrg1, mrg2;
//    private Spinner spinner;
    private static String solInput;
    private static int selected;

    private TextView tvDateStart, tvTipsCamera;
    private RadioGroup radioGroup, rgSolDate;
    private RadioButton rbSol, rbDate, rbRover1, rbRover2, rbRover3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_four);
        btnDate = (Button) findViewById(R.id.btnDate);
        llCheck = (LinearLayout) findViewById(R.id.llCheck);
        btnDate.setOnClickListener(this);
        lltips1 = (LinearLayout) findViewById(R.id.ll_tips1);
        lltips2 = (LinearLayout) findViewById(R.id.ll_tips2);
        mrg1 = (MyRadioGroup) findViewById(R.id.mrg1);
        mrg2 = (MyRadioGroup) findViewById(R.id.mrg2);
        llCheck.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.rg_mode);
        rgSolDate = (RadioGroup) findViewById(R.id.rg_sol_date);
        tvDateStart = (TextView) findViewById(R.id.tv_start_date);
        rbSol = (RadioButton) findViewById(R.id.rb_sol);
        rbDate = (RadioButton)findViewById(R.id.rb_date);
        rbRover1 = (RadioButton)findViewById(R.id.rb_r1);
       // tvTipsCamera = (TextView) findViewById(R.id.tv_camera_tips);
        rbRover2 = (RadioButton)findViewById(R.id.rb_r2);

        rbRover3 = (RadioButton)findViewById(R.id.rb_r3);
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

    private void initCameraList(int checkedId) {
        if(checkedId == R.id.rb_r1){
            types = AppConfig.types1;
        }else {
            types = AppConfig.types2;
        }
        Log.i(TAG,checkedId+"");
        chooseCamera();
    }

    private static String[] types = AppConfig.types1;

    private void chooseCamera(){
        AlertDialog.Builder builder = new AlertDialog.Builder(FunFourActivity1.this);
        builder.setTitle(getString(R.string.select_camera));
        builder.setSingleChoiceItems(types, selected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = i;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                btnSelect.setText(types[selected]);
//                oldString = btnSelect.getText().toString().trim();
                tvTipsCamera.setText(types[selected]);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                btnSelect.setText(oldString);

                dialog.dismiss();
            }
        });
        builder.show();
    }
    String oldString = null;
    private String date = null;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Calendar calendar = Calendar.getInstance();
        switch (id){
            case R.id.rb_sol:
                AlertDialog.Builder builder = new AlertDialog.Builder(FunFourActivity1.this);
                builder.setTitle(getString(R.string.tips_sol));
                final EditText etName=  new EditText(FunFourActivity1.this);
                etName.setHint("1000");
                etName.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(etName);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = etName.getText().toString().trim();
                        if (!TextUtils.isEmpty(name)){
                            solInput = name;
                            tvDateStart.setText(solInput);
                        }

                        else
                            showToast("Please enter an integer");
                    }
                });
                builder.show();
                break;
//            case R.id.rb_date:
//                Date d = new Date();
//                calendar.setTime(d);
//
//                DatePickerDialog dialog = new DatePickerDialog(FunFourActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        date = (monthOfYear+1)+"/"+dayOfMonth+"/"+year;
//                        tvDateStart.setText(date);
//                    }
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                dialog.show();
//                break;
//            case R.id.btnCheck:
//
//                break;
            case R.id.rb_r1:
                initCameraList(R.id.rb_r1);
                break;
            case R.id.rb_r2:
                initCameraList(R.id.rb_r2);
                break;
            case R.id.rb_r3:
                initCameraList(R.id.rb_r3);
                break;

            case R.id.btnDate:
                Date d = new Date();
                calendar.setTime(d);

                DatePickerDialog dialog = new DatePickerDialog(FunFourActivity1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date = (monthOfYear+1)+"/"+dayOfMonth+"/"+year;
                        tvDateStart.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.llCheck:
                Intent intent = new Intent(FunFourActivity1.this, ResultActivity.class);
                startActivity(intent);
                break;
        }
    }


}
    */