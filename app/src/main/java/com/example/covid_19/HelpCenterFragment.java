package com.example.covid_19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HelpCenterFragment extends Fragment {

    TextView national,iedcr1,iedcr2,iedcr3,iedcr4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_help_center, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
         national=view.findViewById(R.id.nationLNumId);
        iedcr1=view.findViewById(R.id.iedcr1id);
        iedcr2=view.findViewById(R.id.iedcr2id);
        iedcr3=view.findViewById(R.id.iedcr3id);
        iedcr4=view.findViewById(R.id.iedcr4id);

        national.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" +"333");
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getActivity(), "calling", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        iedcr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" +"10655");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getActivity(), "calling", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        iedcr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" +"01944333222");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getActivity(), "calling", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        iedcr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" +"01937000011");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getActivity(), "calling", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        iedcr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" +"01927711784");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    Toast.makeText(getActivity(), "calling", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        return view;
    }
}
