package com.example.covid_19.ui.allcountry;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covid_19.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class CovidCountryDeatil extends AppCompatActivity {

    TextView tvDetailCountryName, tvDetailTotalCases, tvDetailTodayCases, tvDetailTotalDeaths,
            tvDetailTodayDeaths, tvDetailTotalRecovered, tvDetailTotalActive, tvDetailTotalCritical;
    ImageView flagImg;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_deatil);

        // call view
        tvDetailCountryName = findViewById(R.id.tvDetailCountryName);
        tvDetailTotalCases = findViewById(R.id.tvDetailTotalCases);
        tvDetailTodayCases = findViewById(R.id.tvDetailTodayCases);
        tvDetailTotalDeaths = findViewById(R.id.tvDetailTotalDeaths);
        tvDetailTodayDeaths = findViewById(R.id.tvDetailTodayDeaths);
        tvDetailTotalRecovered = findViewById(R.id.tvDetailTotalRecovered);
        tvDetailTotalActive = findViewById(R.id.tvDetailTotalActive);
        tvDetailTotalCritical = findViewById(R.id.tvDetailTotalCritical);
        flagImg = findViewById(R.id.flagId);
        pieChart=findViewById(R.id.pichartId);

        // call Covid Country
        CovidCountry covidCountry = getIntent().getParcelableExtra("EXTRA_COVID");

        // set text view
        tvDetailCountryName.setText("Country: "+covidCountry.getmCovidCountry());
        tvDetailTotalCases.setText(covidCountry.getmCases());
        tvDetailTodayCases.setText(covidCountry.getmTodayCases());
        tvDetailTotalDeaths.setText(covidCountry.getmDeaths());
        tvDetailTodayDeaths.setText(covidCountry.getmTodayDeaths());
        tvDetailTotalRecovered.setText(covidCountry.getmRecovered());
        tvDetailTotalActive.setText(covidCountry.getmActive());
        tvDetailTotalCritical.setText(covidCountry.getmCritical());

        // Glide
        Glide.with(this)
                .load(covidCountry.getmFlags())
                .apply(new RequestOptions().override(240, 160))
                .into(flagImg);


        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(50,50,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.animateY(2000, Easing.EaseInOutCubic);


        ArrayList<PieEntry> pichartValue=new ArrayList<>();
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmRecovered()),"%R"));
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmCases()),"%I"));
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmDeaths()),"%D"));
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmActive()),"%A"));
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmTodayCases()),"%T"));
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmTodayDeaths()),"%TD"));
        pichartValue.add(new PieEntry(Float.parseFloat(covidCountry.getmCritical()),"%C"));
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

    }
}
