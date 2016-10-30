package com.example.kashish.valetparking;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class activity2 extends AppCompatActivity {

    TextView txtNewCar;
    EditText etxtCar, etxtName, etxtPhone;
    Button btnEnter;
    RegisterBean bean;
    ContentResolver resolver;
    String datetime;

    void initViews(){
        txtNewCar=(TextView)findViewById(R.id.txtNewCar);
        etxtCar=(EditText)findViewById(R.id.etxtCar);
        etxtName=(EditText)findViewById(R.id.etxtName);
        etxtPhone=(EditText)findViewById(R.id.etxtPhone);
        btnEnter=(Button)findViewById(R.id.btnDetails);

        btnEnter.setOnClickListener(clickListener);

        bean=new RegisterBean();
        resolver=getContentResolver();
    }

    String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String datetime = dateFormat.format(new Date()).toString();
        return datetime;

    }

    void insertIntoCP(){
        ContentValues values=new ContentValues();
        values.put(utils.col_car, bean.getCar());
        values.put(utils.col_name, bean.getName());
        values.put(utils.col_phone, bean.getPhone());
        datetime=getDateTime();
        values.put(utils.col_datetime,datetime);

        Uri uri=resolver.insert(utils.uri, values);
        Toast.makeText(this,"Record inserted "+uri.getLastPathSegment(),Toast.LENGTH_SHORT).show();

        clearFields();
    }

    void clearFields()
    {
        etxtCar.setText("");
        etxtName.setText("");
        etxtPhone.setText("");
    }


    boolean validateFields(){
        boolean flag = true;

        if(bean.getCar().isEmpty()){
            flag = false;
            etxtName.setError("Please Enter Car Number");
        }

        if(bean.getName().isEmpty()){
            flag = false;
            etxtName.setError("Please Enter Name");
        }

        if(bean.getPhone().isEmpty()){
            flag = false;
            etxtPhone.setError("Please Enter Phone Number");
        }else{
            if(bean.getPhone().length()!=10){
                flag = false;
                etxtPhone.setError("Please Enter 10 digit Phone Number");
            }
        }
        return flag;
    }


    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnDetails){
                bean.setCar(etxtCar.getText().toString().trim());
                bean.setName(etxtName.getText().toString().trim());
                bean.setPhone(etxtPhone.getText().toString().trim());

                if(validateFields()) {
                    insertIntoCP();
                }
                else{
                    Toast.makeText(activity2.this,"Kindly Rectify Errors",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}
