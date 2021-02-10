package FireBaseLayer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ObjectLayer.User;

public class FireBaseHandler {

    private static final String DATABASE_PATH = "UserData";

    FirebaseDatabase database;



    DatabaseReference databaseReference;

    List<User_fireBase> list_User;

    public FireBaseHandler(){

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(DATABASE_PATH);

        this.list_User = new ArrayList<>();




    }


    public String insert(User user) {

        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(user);
        return id;

    }

    public List<User_fireBase> getList_User() {

        if(this.list_User == null)
            this.list_User = new ArrayList<>();
        return this.list_User;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }


    public void delete( String id) {
        databaseReference.child(id).removeValue();
    }

}
