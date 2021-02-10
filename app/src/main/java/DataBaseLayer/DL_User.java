package DataBaseLayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ObjectLayer.UserBasic;

public class DL_User {

    SQLiteOpenHelper db;

    protected static final String TABLE_NAME = "User";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME_FIRST = "firstName";
    private static final String KEY_NAME_LAST = "lastName";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";

    String[] columnName = {KEY_ID, KEY_NAME_FIRST, KEY_NAME_LAST, KEY_NUMBER, KEY_EMAIL, KEY_ADDRESS};


    public DL_User(SQLiteOpenHelper db) {
        this.db = db;
    }


    public String createTable() {
        return "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME_FIRST + " TEXT,"
                + KEY_NAME_LAST + " TEXT,"
                + KEY_NUMBER + " TEXT,"
                + KEY_EMAIL + " TEXT, "
                + KEY_ADDRESS + " TEXT "
                + " )";

    }


    public long insert(UserBasic userBasic) {

        Long rowId;

        SQLiteDatabase db = this.db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME_FIRST, userBasic.getFirstName());
        values.put(KEY_NAME_LAST, userBasic.getLastName());
        values.put(KEY_NUMBER, userBasic.getNumber());
        values.put(KEY_EMAIL, userBasic.getEmail());
        values.put(KEY_ADDRESS, userBasic.getAddress());


        // Inserting Row
        rowId = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection

        return rowId;
    }

    public Boolean checkIfExist(int userId) {
        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columnName, KEY_ID + "=?", new String[]{String.valueOf(userId)}, null, null, null, null);

        if(cursor.getCount() > 0)
            return true;
        else
            return false;

    }


    public UserBasic getUser_ById(int userId) {

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columnName, KEY_ID + "=?", new String[]{String.valueOf(userId)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        int id = cursor.getInt(0);
        String name_First = cursor.getString(1);
        String name_last = cursor.getString(2);
        String number = cursor.getString(3);
        String email = cursor.getString(4);
        String address = cursor.getString(5);

        UserBasic userBasic = new UserBasic(id, name_First, name_last, number, email, address);

        return userBasic;


    }

    public void deleteUser(int userID) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] {Integer.toString(userID)});
        db.close();

    }


    public List<Integer> getlistUsersId() {

        List<Integer> list_UserId = new ArrayList<Integer>();


        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int userId = cursor.getInt(0);

                list_UserId.add(userId);

            } while (cursor.moveToNext());
        }

        return list_UserId;
    }



}
