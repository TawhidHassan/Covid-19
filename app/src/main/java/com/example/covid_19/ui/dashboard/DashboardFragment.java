package com.example.covid_19.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

   RecyclerView sympotmsRecyclerView;
   List<SymptompsModel> symptompsModels=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sympotmsRecyclerView=root.findViewById(R.id.symptompsRecyclerViewId);
        LinearLayoutManager symptomptoslayoutManager = new LinearLayoutManager(getActivity());
        symptomptoslayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sympotmsRecyclerView.setLayoutManager(symptomptoslayoutManager);

        symptompsModels.add(new SymptompsModel(R.drawable.headache,"Headache"));
        symptompsModels.add(new SymptompsModel(R.drawable.caugh,"Cough"));
        symptompsModels.add(new SymptompsModel(R.drawable.fever,"Fever"));
        symptompsModels.add(new SymptompsModel(R.drawable.high_fiver,"High Fever"));
        symptompsModels.add(new SymptompsModel(R.drawable.caugh,"diarrhea"));
        symptompsModels.add(new SymptompsModel(R.drawable.nasal,"Nasal Congestion"));
        symptompsModels.add(new SymptompsModel(R.drawable.fever,"pains"));

        SymptompsAdapter symptompsAdapter=new SymptompsAdapter(symptompsModels);
        sympotmsRecyclerView.setAdapter(symptompsAdapter);




        return root;
    }
}
