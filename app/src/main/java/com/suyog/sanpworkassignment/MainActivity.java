package com.suyog.sanpworkassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMonth, spinnerYear;
    DateListAdapter adapter;
    ArrayList<DateList> dataList=new ArrayList<>();
    RecyclerView recyclerView;
    String TAG=MainActivity.class.getSimpleName();
    String year1,months1;
    List<String> yearlist = new ArrayList<String>();
    List<String> list = new ArrayList<String>();
    int firstdate,lastDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMonth =findViewById(R.id.spinnerMonth);
        spinnerYear =findViewById(R.id.spinnerYear);
        recyclerView =findViewById(R.id.reculerview);


        initRecyclerView();

        addDataToSpinner();



    }

    private void addDataToSpinner() {

        yearlist.clear();
        for (int i=1990;i<=2020;i++){
            Log.d("SUyog", "onCreate: "+i);
            yearlist.add(i+"");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearlist);
        dataAdapter.notifyDataSetChanged();
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(dataAdapter);


        list.clear();
        for (int i=1;i<=12;i++){
            Log.d("SUyog", "onCreate: "+i);
            list.add(i+"");
        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter1.notifyDataSetChanged();
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(dataAdapter1);



        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year1=spinnerYear.getSelectedItem().toString();

                Calendar c = Calendar.getInstance();
                int year = Integer.parseInt(year1);
                int month = Integer.parseInt(months1)-1;
                int day = 1;
                c.set(year, month, day);
                int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                System.out.println("First Day of month: " + c.getTime());
                firstdate= Integer.parseInt(c.getTime().toString().substring(8, c.getTime().toString().length() - 24));
                c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
                System.out.println("Last Day of month: " + c.getTime());
                lastDate= Integer.parseInt(c.getTime().toString().substring(8, c.getTime().toString().length() - 24));

                System.out.println("Day of month: " +firstdate+"  "+lastDate);
                    dataList.clear();
                for (int i=firstdate;i<=lastDate;i++){
                    Log.d("SUyog", "onCreate: "+i);

                    DateList dateList1 =new DateList();
                    dateList1.setDate(i+"-"+months1+"-"+year1);
                    dataList.add(dateList1);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                months1=spinnerMonth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                months1=1+"";
            }
        });
    }

    private void initRecyclerView(){
        adapter = new DateListAdapter(this,dataList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);







    }
}
