package com.example.arslan.cv_builder;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import FireBaseLayer.User_fireBase;
import ObjectLayer.Education;
import ObjectLayer.Experience;
import ObjectLayer.User;
import ObjectLayer.UserBasic;


public class DisplayCv extends Fragment {

    User_fireBase user;

    public DisplayCv(){}


    @SuppressLint("ValidFragment")
    public DisplayCv(User_fireBase user) {
        super();
        this.user = user;
    }

    public void setUser(User_fireBase user) {
        this.user = user;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_cv, container, false);

        setField(view);

        return view;
    }

    public User_fireBase getUser() {
        return user;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void setField(View view) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.display_cv_container);


        setBasic(view, this.user.getUserBasic());
        setEducation(view, this.user.getList_Education());
        setExperience(view, this.user.getList_Experience());

    }

    private void setBasic(View view, UserBasic userBasic) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout_Basic);

        layout.addView(getTextView(view,"Name: " + userBasic.getName()) );
        layout.addView(getTextView(view,"Number: " + userBasic.getNumber()));
        layout.addView(getTextView(view,"Email: " + userBasic.getEmail()));
        layout.addView(getTextView(view,"Address: " + userBasic.getAddress()));


    }

    private  void setEducation(View view, List<Education> listEducation) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout_education);


        int i = 1;
        for (Education education : listEducation) {

            layout.addView(getTextView(view, "Education " + Integer.toString(i) + " : "  + education.education()));
            i++;
        }

    }

    private  void setExperience(View view, List<Experience> listExperience) {
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout_experience);


        int i = 1;
        for (Experience experience : listExperience) {

            layout.addView(getTextView(view, "Experience " + Integer.toString(i) + " : " + experience.experience()));
            i++;
        }

    }



    private TextView getTextView(View view, String name) {
        TextView textView = new TextView(view.getContext());
        textView.setText(name);
        textView.setPadding(0,0,0,10);
        return textView;
    }


}
