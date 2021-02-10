package com.example.arslan.cv_builder;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ObjectLayer.Education;
import ObjectLayer.Experience;
import ObjectLayer.User;
import ObjectLayer.UserBasic;

public class MainActivity extends AppCompatActivity {

    int fragmentCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        for(int i=1; i<=5;i++) {
//
//             String strI = "1";
//
//        UserBasic userBasic = new UserBasic("FirstName" + strI, "LastNumber"+ strI, "email" + strI , "Number" + strI, "Address" + strI);
//
//        List<Education> list_Education = new ArrayList<>();
//        list_Education.add(new Education("1997", "2018", "institute" + strI , "degree" + strI));
//        list_Education.add(new Education("1997", "2018", "institute" + strI, "degree " + strI));
//
//        List<Experience> list_Experience = new ArrayList<>();
//        list_Experience.add(new Experience("1997", "2018", "institute"  + strI, "job" + strI));
//        list_Experience.add(new Experience("1997", "2018", "institute"  + strI, "job" + strI));
//        list_Experience.add(new Experience("1997", "2018", "institute" + strI, "job" + strI));
//        list_Experience.add(new Experience("1997", "2018", "institute"  + strI, "job" + strI));
//
//            final User user = new User(userBasic, list_Education, list_Experience);
//
//            dbHandler.insertUser(userBasic, list_Education, list_Experience);
//
//
//
//        }

        startActivity(new Intent(getApplicationContext(), Activity_Menu.class));
    }
}
