package DataBaseLayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class L_UserExperience{


    SQLiteOpenHelper db;

    private static final String TABLE_NAME = "UserExperience";

    private static final String KEY_USERID = "UserId";
    private static final String KEY_EXPERIENCEID = "ExperienceId";


    String[] columnName = {KEY_USERID, KEY_EXPERIENCEID};


    public L_UserExperience(SQLiteOpenHelper db) {
        this.db = db;
    }



    public static String createTable() {
        return "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_USERID + " INTEGER, "
                + KEY_EXPERIENCEID + " INTEGER, "
                + "FOREIGN KEY( " + KEY_USERID + " ) REFERENCES  " + DL_User.TABLE_NAME + "(id), "
                + "FOREIGN KEY( " + KEY_EXPERIENCEID + " ) REFERENCES " + DL_Experience.TABLE_NAME + "(id) "
                + " )";

    }

    void insert(int userId, int experienceId) {

        SQLiteDatabase db = this.db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERID, userId);
        values.put(KEY_EXPERIENCEID,experienceId);


        // Inserting Row
        Long RowId = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection

    }


    public int getExperienceId(int userId) {

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columnName, KEY_USERID + "=?",new String[] {String.valueOf(userId)} , null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();


        int experienceId =  cursor.getInt(1);

        return experienceId;

    }


    public List<Integer> getListExperienceId(int userId) {

        List<Integer> list_ExperienceId = new ArrayList<Integer>();


        String selectQuery = "SELECT * FROM " + TABLE_NAME + " Where " + KEY_USERID + " = " + String.valueOf(userId);

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int experienceId =  cursor.getInt(1);
                list_ExperienceId.add(experienceId);

            } while (cursor.moveToNext());
        }

        return list_ExperienceId;

    }

    public void deleteUserExperience(int userId) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_USERID + " = ?",
                new String[] {Integer.toString(userId)});
        db.close();

    }




}
