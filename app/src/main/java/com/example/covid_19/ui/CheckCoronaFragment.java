package com.example.covid_19.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckCoronaFragment extends Fragment {

    RadioGroup radioGroupGender,oldRogRadioGroup,radioGroupSymptomps,radioGroupFiver;
    private RadioButton radioButton;
    EditText ageEdittext;
    Button submitbtn;

    TextView resuktText,warnningText,res;

    String gender="";
    int age;
    String oldDeses="";
    String sympotms="";
    String fiver="";

    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_check_corona, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        submitbtn=view.findViewById(R.id.submitBtnId);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_check);

         resuktText= (TextView) dialog.findViewById(R.id.resuktTextId);
         warnningText= (TextView) dialog.findViewById(R.id.warnningTextId);
         res= (TextView) dialog.findViewById(R.id.resId);
         warnningText.setVisibility(View.GONE);


        //age edit text
        ageEdittext=view.findViewById(R.id.ageEdittextId);

        //gender
        radioGroupGender=view.findViewById(R.id.radioGroupGenderId);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioGenderFemalId:
                    // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        gender=radioButton.getText().toString();
                    break;
                    case R.id.radioGenderMealId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        gender=radioButton.getText().toString();
                        break;
                    case R.id.radioGenderOtherId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        gender=radioButton.getText().toString();
                        break;

                }
            }
        });
        //old desise
        oldRogRadioGroup=view.findViewById(R.id.oldRogRadioGroupId);
        oldRogRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.oldRDButtnyesId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        oldDeses=radioButton.getText().toString();
                        break;
                    case R.id.oldRDButtnNoId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        oldDeses=radioButton.getText().toString();
                        break;

                }
            }
        });

        //Sympotms
        radioGroupSymptomps=view.findViewById(R.id.radioGroupSymptompsId);
        radioGroupSymptomps.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.symRdoYesId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        sympotms=radioButton.getText().toString();
                        break;
                    case R.id.symRdoNoId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        sympotms=radioButton.getText().toString();
                        break;

                }
            }
        });

        //Sympotms
        radioGroupFiver=view.findViewById(R.id.radioGroupFiverID);
        radioGroupFiver.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.fvRdYesId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        fiver=radioButton.getText().toString();
                        break;
                    case R.id.fvRdNoId:
                        // do operations specific to this selection
                        radioButton = (RadioButton) view.findViewById(checkedId);
                        fiver=radioButton.getText().toString();
                    break;

                }
            }
        });


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {


                if( !TextUtils.isEmpty(ageEdittext.getText()))
                { if (gender.isEmpty()|| oldDeses.isEmpty()|| sympotms.isEmpty()||fiver.isEmpty()) {

                    resuktText.setText("Please fillup the full Form ");
                    resuktText.setTextColor(R.color.red);
                    warnningText.setVisibility(View.VISIBLE);
                    warnningText.setText("..............................................");
                    dialog.show();
                    }
                    else {
                    age=Integer.parseInt(ageEdittext.getText().toString());
                    ShowResult(age,gender,oldDeses,sympotms,fiver);
                }
                }else
                {
                    ageEdittext.setError("Input your age");
                    Toast.makeText(getActivity(), "Input Your Age, Gender, Condition, Symptoms, Fiver", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void ShowResult(int age, String gender, String oldDeses, String sympotms, String fiver) {

            if (age>=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("Yes") && sympotms.equals("Yes") && fiver.equals("Yes"))
            {

                resuktText.setText("You are in High Risk");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }

            else if (age<=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("Yes") && sympotms.equals("Yes") && fiver.equals("Yes"))
            {
                Toast.makeText(getActivity(),"You are in denger",Toast.LENGTH_LONG).show();
                resuktText.setText("You are in High Risk, Annd way from your Family");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }

            else if (age>=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("Yes") && fiver.equals("Yes"))
            {

                resuktText.setText("I think you will checkUp you body");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();

            }
            else if (age>=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("Yes") && sympotms.equals("No") && fiver.equals("Yes"))
            {
                resuktText.setText("I think you will checkUp you body");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
            else if (age>=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("Yes") && sympotms.equals("No") && fiver.equals("No"))
            {

                resuktText.setText("I think you are ok");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("But dont go outside the home\n Stay At home Stay Safe");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            } else if (age<=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("Yes") && sympotms.equals("No") && fiver.equals("No"))
            {
                resuktText.setText("I think you are ok");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("But dont go outside the home\n Stay At home Stay Safe");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }

            else if ((age>=18||age<=18) && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("No") && fiver.equals("No"))
            {
                resuktText.setText("I think you are ok");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("But dont go outside the home\n Stay At home Stay Safe");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
            else if (age>=18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("Yes") && fiver.equals("Yes"))
            {
                resuktText.setText("I think you will checkUp your body you are in risk");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
            else if (age<= 18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("Yes") && fiver.equals("Yes"))
            {
                resuktText.setText("I think you will checkUp your body you are in risk");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
            else if (age<= 18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("Yes") && fiver.equals("No"))
            {
                resuktText.setText("I think you are ok");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("But dont go outside the home\n Stay At home Stay Safe");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
            else if (age>= 18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("Yes") && fiver.equals("No"))
            {
                resuktText.setText("I think you are ok");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("But dont go outside the home\n Stay At home Stay Safe");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }

            else if (age>= 18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("No") && fiver.equals("Yes"))
            {
                resuktText.setText("I think you will checkUp your body you are in risk");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
            else if (age<= 18 && (gender.equals("Female")||gender.equals("Male")||gender.equals("Other")) && oldDeses.equals("No") && sympotms.equals("No") && fiver.equals("Yes"))
            {
                resuktText.setText("Your chance is very Low");
                resuktText.setTextColor(R.color.red);
                warnningText.setText("Warnning: \nYou will need to contact, Your nearest health center, and Stay home Stay Safe");
                warnningText.setVisibility(View.VISIBLE);
                dialog.show();
            }
    }
}
