package DataBaseLayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

import ObjectLayer.Education;

public class DL_Education {

    SQLiteOpenHelper db;

    protected static final String TABLE_NAME = "Education";

    private static final String KEY_ID = "id";
    private static final String KEY_YEARSTART = "yearStart";
    private static final String KEY_YEAREND = "yearEnd";
    private static final String KEY_DEGREE = "degree";
    private static final String KEY_INSTITUTENAME = "instituteName";

    String[] columnName = {KEY_ID, KEY_YEARSTART, KEY_YEAREND, KEY_DEGREE, KEY_INSTITUTENAME};


    public DL_Education(SQLiteOpenHelper db) {
        this.db = db;
    }



    public String createTable() {
        return "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_YEARSTART + " TEXT,"
                + KEY_YEAREND + " TEXT,"
                + KEY_DEGREE + " TEXT,"
                + KEY_INSTITUTENAME + " TEXT "
                + " )";

    }


    public long insert(Education education) {

        SQLiteDatabase db = this.db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_YEARSTART, education.getYear_Start());
        values.put(KEY_YEAREND, education.getYear_End());
        values.put(KEY_DEGREE, education.getDegree());
        values.put(KEY_INSTITUTENAME, education.getInstitute());



        // Inserting Row
        Long rowId = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return rowId;
    }



    public Education getEducation_ById(int educationId) {

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columnName, KEY_ID + "=?",new String[] {String.valueOf(educationId)} , null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();


        int id =  cursor.getInt(0);
        String year_start = cursor.getString(1);
        String year_End = cursor.getString(2);
        String degree = cursor.getString(3);
        String institute = cursor.getString(4);


        Education education = new Education(id,year_start,year_End,degree,institute);

        return education;

    }

    public void deleteEducation(int id) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] {Integer.toString(id)});
        db.close();

    }

}