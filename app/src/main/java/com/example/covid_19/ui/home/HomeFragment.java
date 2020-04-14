package com.example.covid_19.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private TextView tvTotalConfirmed, tvTotalDeaths, tvTotalRecovered, tvLastUpdated,caseSeeDeatils;
    private ProgressBar progressBar;
    TextView updateDate,totalCase,todayTotalCase,totalDeaths,todayTotaldeaths,recovered,criticalnum,activeNum,casesPerOneMillion,
            deathsPerOneMillion,affectedCountries;
    PieChart pieChart;
    Dialog dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        // call view
        tvTotalConfirmed = root.findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths = root.findViewById(R.id.tvTotalDeaths);
        tvTotalRecovered = root.findViewById(R.id.tvTotalRecovered);
        tvLastUpdated = root.findViewById(R.id.tvLastUpdated);
        progressBar = root.findViewById(R.id.progress_circular_home);
        caseSeeDeatils = root.findViewById(R.id.caseSeeDeatilsId);


        pieChart=root.findViewById(R.id.pichartId);


        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.see_sull_details_layout_dialog);
                updateDate=dialog.findViewById(R.id.updateDateId);
                totalCase=dialog.findViewById(R.id.totalCaseId);
                todayTotalCase=dialog.findViewById(R.id.todayTotalCaseId);
                totalDeaths=dialog.findViewById(R.id.totalDeathsId);
                todayTotaldeaths=dialog.findViewById(R.id.todayTotaldeathsId);
                recovered=dialog.findViewById(R.id.recoveredId);
                criticalnum=dialog.findViewById(R.id.criticalnumId);
                activeNum=dialog.findViewById(R.id.activeNumId);
                casesPerOneMillion=dialog.findViewById(R.id.casesPerOneMillionId);
                deathsPerOneMillion=dialog.findViewById(R.id.deathsPerOneMillionId);
                affectedCountries=dialog.findViewById(R.id.affectedCountriesId);

        //call volley
        getData();

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(50,50,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.animateY(2000, Easing.EaseInOutCubic);



        caseSeeDeatils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails();
            }
        });


        return root;
    }

    private String getDate(long milliSecond)
    {
        // Mon, 23 Mar 2020 02:01:04 PM
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa");

        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return formatter.format(calendar.getTime());
    }

    private void getData() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());

        String url="https://corona.lmao.ninja/all";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvTotalConfirmed.setText(jsonObject.getString("cases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTotalRecovered.setText(jsonObject.getString("recovered"));
                    tvLastUpdated.setText("Last Updated"+"\n"+getDate(jsonObject.getLong("updated")));


                    ArrayList<PieEntry> pichartValue=new ArrayList<>();
                    pichartValue.add(new PieEntry(Float.parseFloat(jsonObject.getString("recovered")),"%R"));
                    pichartValue.add(new PieEntry(Float.parseFloat(jsonObject.getString("cases")),"%I"));
                    pichartValue.add(new PieEntry(Float.parseFloat(jsonObject.getString("deaths")),"%D"));
                    PieDataSet pieDataSet=new PieDataSet(pichartValue,":Persent");
                    //to different pie slice middle spaces
                    pieDataSet.setSliceSpace(0.7f);
                    pieDataSet.setSelectionShift(5f);
                    pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                    PieData pieData=new PieData(pieDataSet);
                    pieData.setValueTextSize(10f);
                    pieData.setValueTextColor(Color.WHITE);

                    pieChart.setData(pieData);
                    //pie Chaert
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private void showDetails() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());

        String url="https://corona.lmao.ninja/all";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    tvTotalConfirmed.setText(jsonObject.getString("cases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTotalRecovered.setText(jsonObject.getString("recovered"));
                    tvLastUpdated.setText("Last Updated"+"\n"+getDate(jsonObject.getLong("updated")));


                    updateDate.setText("Last Updated"+"\n"+getDate(jsonObject.getLong("updated")));
                    totalCase.setText(jsonObject.getString("cases"));
                    todayTotalCase.setText(jsonObject.getString("todayCases"));
                    totalDeaths.setText(jsonObject.getString("deaths"));
                    todayTotaldeaths.setText(jsonObject.getString("todayDeaths"));
                    recovered.setText(jsonObject.getString("recovered"));
                    criticalnum.setText(jsonObject.getString("critical"));
                    activeNum.setText(jsonObject.getString("active"));
                    casesPerOneMillion.setText(jsonObject.getString("casesPerOneMillion"));
                    deathsPerOneMillion.setText(jsonObject.getString("deathsPerOneMillion"));
                    affectedCountries.setText(jsonObject.getString("affectedCountries"));

                    dialog.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);

    }
}
