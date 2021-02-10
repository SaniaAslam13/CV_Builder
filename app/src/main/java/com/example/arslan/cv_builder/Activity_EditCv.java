package com.example.arslan.cv_builder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import DataBaseLayer.DatabaseHandler;
import FireBaseLayer.FireBaseHandler;
import FireBaseLayer.User_fireBase;
import ObjectLayer.User;

public class Activity_EditCv extends AppCompatActivity {

    FireBaseHandler fbHandler;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit_cv);

        final List<Integer> list_int = new ArrayList<>();

        fbHandler = new FireBaseHandler();
        Button nextButton = findViewById(R.id.ViewCv_Next);
        Button deleteButton = findViewById(R.id.ViewCv_delete);
        final Button pdfButton = findViewById(R.id.button_SavePdf);
        final List<DisplayCv> list_DisplayCv = new ArrayList<>();


        final List<User_fireBase> list_user = new ArrayList<>();

        fbHandler.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if( list_int.size() == 0) {

                    list_int.add(0);

                    list_user.clear();


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        User userTemp = snapshot.getValue(User.class);
                        String id = snapshot.getKey();

                        User_fireBase user_fireBase = new User_fireBase(id, userTemp);

                        list_user.add(user_fireBase);

                    }


//        final List<User_fireBase> list_user = fbHandler.getList_User();


                    if (list_user.size() > 0) {

                        for (User_fireBase user : list_user) {
                            list_DisplayCv.add(new DisplayCv(user));
                        }


                        getSupportFragmentManager().beginTransaction().add(R.id.ViewCv_frame_Container, list_DisplayCv.get(i)).commit();

                        TextView textView = (TextView) findViewById(R.id.textView_CVNumber);
                        textView.setText(Integer.toString(i + 1));

                    } else {

                        finish();
                        startActivity(new Intent(getApplicationContext(), Activity_Nothing.class));


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                increment_I(list_DisplayCv.size());

                changeFragment(list_DisplayCv.get(i));

                TextView textView = (TextView) findViewById(R.id.textView_CVNumber);
                textView.setText(Integer.toString(i + 1));

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder strToBeDisplayedByToast = new StringBuilder();

                User_fireBase user = list_DisplayCv.get(i).getUser();

                fbHandler.delete(user.id());

                strToBeDisplayedByToast.append("ID: ");
                strToBeDisplayedByToast.append( Integer.toString( user.getUserBasic().getId()));
                strToBeDisplayedByToast.append(" deleted");

                list_DisplayCv.remove(i);

                if (list_DisplayCv.size() == 0) {
                    finish();

                    strToBeDisplayedByToast.append("\n Nothing More to show");

                    Toast.makeText(getApplicationContext(), strToBeDisplayedByToast,Toast.LENGTH_LONG ).show();

                    startActivity(new Intent(getApplicationContext(),Activity_Nothing.class));

                }

                else {

                    if(i >= list_DisplayCv.size())
                        i = 0;

                    changeFragment(list_DisplayCv.get(i));

                }

                Toast.makeText(getApplicationContext(), strToBeDisplayedByToast,Toast.LENGTH_LONG ).show();

                TextView textView = (TextView) findViewById(R.id.textView_CVNumber);
                textView.setText(Integer.toString(i + 1));


            }
        });

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = list_DisplayCv.get(i).getUser();

                PDFSaver pdfSaver = new PDFSaver(getApplicationContext());
                String response = pdfSaver.saveFile(user);

                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        });



    }

    private void changeFragment(Fragment fragment) {

        FragmentTransaction f_trans = getSupportFragmentManager().beginTransaction();

        f_trans.replace(R.id.ViewCv_frame_Container, fragment);

        f_trans.commit();
    }

    private void increment_I(int max_size) {

        i++;

        if(i >= max_size)
            i = 0;

    }
}
