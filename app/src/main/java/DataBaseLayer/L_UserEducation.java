package DataBaseLayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class L_UserEducation  {

    SQLiteOpenHelper db;

    private static final String TABLE_NAME = "UserEducation";

    private static final String KEY_USERID = "UserId";
    private static final String KEY_EDUCATIONID = "EducationId";
;

    String[] columnName = {KEY_USERID, KEY_EDUCATIONID};


    public L_UserEducation(SQLiteOpenHelper db) {
        this.db = db;
    }



    public static String createTable() {
        return "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_USERID + " INTEGER, "
                + KEY_EDUCATIONID + " INTEGER, "
                + "FOREIGN KEY( " + KEY_USERID + " ) REFERENCES  " + DL_User.TABLE_NAME + "(id), "
                + "FOREIGN KEY( " + KEY_EDUCATIONID + " ) REFERENCES  " + DL_Education.TABLE_NAME + "(id) "
                + " )";

    }

    void insert(int userId, int educationId) {

        SQLiteDatabase db = this.db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERID, userId);
        values.put(KEY_EDUCATIONID,educationId);


        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<Integer> getListEducationId(int userId) {

        List<Integer> educationList = new ArrayList<Integer>();


        String selectQuery = "SELECT * FROM " + TABLE_NAME + " Where " + KEY_USERID + " = " + String.valueOf(userId);

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int educationId =  cursor.getInt(1);
                educationList.add(educationId);

            } while (cursor.moveToNext());
        }

        return educationList;

    }


    public void deleteUserEducation(int userId) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_USERID + " = ?",
                new String[] {Integer.toString(userId)});
        db.close();

    }
}