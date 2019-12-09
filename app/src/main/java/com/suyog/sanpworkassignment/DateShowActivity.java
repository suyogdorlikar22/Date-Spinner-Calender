package com.suyog.sanpworkassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DateShowActivity extends AppCompatActivity {

    String date;
    TextView tvdate;
    EditText edData;
    Button btnSave;
    String data,oldDate;

    ArrayList<String> dataList=new ArrayList<>();
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_show);

        date=getIntent().getStringExtra("Date");
        tvdate=findViewById(R.id.tvDate);
        edData=findViewById(R.id.edData);
        btnSave=findViewById(R.id.btnSave);
        appPreferences=AppPreferences.getAppPreferences(this);
        tvdate.setText(date);
        data=appPreferences.getString(date,"");
        if(!data.equals("")) {

            oldDate = data.substring(0, 8);
            Log.d("SuyogCheck", "onCreate: " + data.substring(0, 8) + "  " + date);
            if (oldDate.trim().equals(date)) {
                edData.setText(data.substring(8));
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edData.getText().toString().equals("")){
                    appPreferences.putString(date,date+""+edData.getText().toString());
                    startActivity(new Intent(DateShowActivity.this,MainActivity.class));
                }
            }
        });

    }

}
