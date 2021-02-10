package com.example.arslan.cv_builder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import DataBaseLayer.DatabaseHandler;
import FireBaseLayer.FireBaseHandler;
import FireBaseLayer.User_fireBase;
import ObjectLayer.Education;
import ObjectLayer.Experience;
import ObjectLayer.User;
import ObjectLayer.UserBasic;

public class Activity_Create extends AppCompatActivity {

    int fragmentCount = 1;

    FireBaseHandler fbHandler;
    User_fireBase user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbHandler = new FireBaseHandler();
        user = new User_fireBase();



        final Create_basic fragment_Basic = new Create_basic();
        final List<Create_education> list_education = new ArrayList<>();
        final List<Create_experience> list_experience = new ArrayList<>();
        final DisplayCv dc  = new DisplayCv();



        getSupportFragmentManager().beginTransaction().add(R.id.frame_Container,fragment_Basic).commit();

        final Button nextButton = ( (Button) findViewById(R.id.Main_buttonNext) );



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nextButton.getText().equals("Next")) {

                    if (fragmentCount >= 1 && fragmentCount <= 2) {

                        int index = fragmentCount - 1;

                        if (fragmentCount == 1) {
                            user.setUserBasic(fragment_Basic.getUserBasic());
                        } else {
                            user.getList_Education().add(list_education.get(index - 1).getEducation());
                        }

                        list_education.add(new Create_education());
                        changeFragment(list_education.get(index));
                    } else if (fragmentCount >= 3 && fragmentCount <= 6) {

                        int index = fragmentCount - 3;

                        if (fragmentCount == 3) {
                            user.getList_Education().add(list_education.get(fragmentCount - 2).getEducation());
                        } else {
                            user.getList_Experience().add(list_experience.get(index - 1).getExperience());
                        }

                        list_experience.add(new Create_experience());
                        changeFragment(list_experience.get(index));


                    }

                    if (fragmentCount == 7) {

                        user.getList_Experience().add(list_experience.get(fragmentCount - 4).getExperience());

                        //adding user to database
                        String id = fbHandler.insert(user);

                        dc.setUser(user);
                        changeFragment(dc);

                        nextButton.setText("Close");
                    }

                    fragmentCount++;

                }

                else {
                    finish();
                }

            }

        });


    }


    public void changeFragment(Fragment fragment) {

        FragmentTransaction f_trans = getSupportFragmentManager().beginTransaction();

        f_trans.replace(R.id.frame_Container, fragment);

        f_trans.commit();


    }
}
