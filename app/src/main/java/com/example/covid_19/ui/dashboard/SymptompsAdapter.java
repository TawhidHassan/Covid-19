package com.example.covid_19.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;

import java.util.List;

public class SymptompsAdapter extends RecyclerView.Adapter<SymptompsAdapter.ViewHolder>{

    List<SymptompsModel> symptompsModelList;

    public SymptompsAdapter(List<SymptompsModel> symptompsModels) {
        this.symptompsModelList = symptompsModels;
    }

    @NonNull
    @Override
    public SymptompsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.symptomps_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptompsAdapter.ViewHolder holder, int position) {
        String name=symptompsModelList.get(position).getName();
        int img=symptompsModelList.get(position).getImg();

        holder.setData(img,name);

    }

    @Override
    public int getItemCount() {
        return symptompsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        ImageView sympImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.sympNameId);
            sympImg=itemView.findViewById(R.id.sympImgId);

        }

        public void  setData(int photo,String name)
        {
            nameText.setText(name);
            sympImg.setImageResource(photo);
        }
    }
}
