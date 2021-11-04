package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserPiechart extends AppCompatActivity {
    List<String> arraylist1 ;
    List<String> arraylist2 ;
    PieChart pieChart;
    ArrayList<PieEntry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;
    float result1;
    float result2;
    float floatime1;
    float solarstarttime1;
    float power1;
    float power2;
    float starttime1;
    float starttime2;
    float endtime1;
    float endtime2;
    float answer1;
    float answer2;
    SharedPreferences preferences;
    Context context;
    public static  final String TAG="userdetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_piechart);

        AddValuesToPIEENTRY();


//        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);
        pieChart.setCenterText("Energy Breakdown");

        pieChart.animateY(3000);



    }

    private void AddValuesToPIEENTRY() {
        preferences=this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        String arraylist4 = preferences.getString("arraylist2","");
        String arraylist5 =preferences.getString("arraylist3","");
        arraylist1= Arrays.asList(arraylist4.split(","));
        arraylist2 =Arrays.asList(arraylist5.split(","));

        pieChart=findViewById(R.id.piechart1);
        entries=new ArrayList<>();
        PieEntryLabels = new ArrayList<String>();
        String time= getCurrentTimeUsingCalendar();
        floatime1=Float.valueOf(time);
        starttime1=Float.valueOf(arraylist1.get(1));
        starttime2=Float.valueOf(arraylist2.get(1));
        power1=Float.valueOf(arraylist1.get(3));
        power2=Float.valueOf(arraylist2.get(3));
        endtime1=Float.valueOf(arraylist1.get(2));
        endtime2=Float.valueOf(arraylist2.get(2));
        if(endtime1<starttime1){
            result1=(floatime1-starttime1)*power1;
        }
        else{
            result1=(endtime1-starttime1)*power1;
        }
        if(endtime2<starttime2){
            result2=(floatime1-starttime2)*power2;
        }
        else{
            result2=(endtime2-starttime2)*power2;
        }
        answer1 =(result1/(result1+result2))*100;
        answer2 = (result2/(result1+result2))*100;


        entries.add(new PieEntry(20, 0));
        entries.add(new PieEntry(30, 1));
    }


    public static String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH");
        String formattedDate=dateFormat.format(date);
        Log.d("hellpw","helloworld");
        return formattedDate;
    }
}
