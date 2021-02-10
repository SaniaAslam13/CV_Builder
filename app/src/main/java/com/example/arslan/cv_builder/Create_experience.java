package com.example.arslan.cv_builder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.sql.Date;

import ObjectLayer.Education;
import ObjectLayer.Experience;

public class Create_experience extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_experience, container, false);



        return view;
    }


    public Experience getExperience() {

        View view = getView();

        String institute = ( (EditText) view.findViewById(R.id.experience_company)).getText().toString();
        String job = ((EditText) view.findViewById(R.id.experience_job)).getText().toString();
        String start_year = ((EditText) view.findViewById(R.id.experience_year_Start)).getText().toString();
        String end_year = ((EditText) view.findViewById(R.id.experience_year_End)).getText().toString();


        Experience experience = new Experience( start_year , end_year ,institute,job );

        return experience;

    }


    @Override
    public void onPause() {
        super.onPause();
    }
}
