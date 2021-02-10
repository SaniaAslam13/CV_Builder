package com.example.arslan.cv_builder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ObjectLayer.User;
import ObjectLayer.UserBasic;


public class Create_basic extends Fragment {

    User user;

    public Create_basic() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public Create_basic(User user) {
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
        View view = inflater.inflate(R.layout.fragment_create_basic, container, false);


        return view;
    }

    public UserBasic getUserBasic() {

        View view = getView();


        String firstName = ( (EditText) view.findViewById(R.id.firstName)).getText().toString();
        String lastName = ( (EditText) view.findViewById(R.id.lastName)).getText().toString();
        String email = ( (EditText) view.findViewById(R.id.email)).getText().toString();
        String number = ( (EditText) view.findViewById(R.id.number)).getText().toString();
        String address = ( (EditText) view.findViewById(R.id.address)).getText().toString();


        UserBasic userBasic = new UserBasic(firstName,lastName,email,number,address);

        return userBasic;
    }





    @Override
    public void onDetach() {
        super.onDetach();

    }

}
