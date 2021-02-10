package com.example.arslan.cv_builder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Date;

import ObjectLayer.Education;

public class Create_education extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_education, container, false);

        return view;
    }

    public Education getEducation() {

        View view = getView();

       String institute = ( (EditText) view.findViewById(R.id.education_institute)).getText().toString();
       String degree = ((EditText) view.findViewById(R.id.education_degree)).getText().toString();
       String start_year = ((EditText) view.findViewById(R.id.education_date_Start)).getText().toString();
       String end_year = ((EditText) view.findViewById(R.id.education_date_end)).getText().toString();


        Education education = new Education(start_year , end_year, institute, degree );

        return education;

    }

    @Override
    public void onPause() {
        super.onPause();

    }



}
