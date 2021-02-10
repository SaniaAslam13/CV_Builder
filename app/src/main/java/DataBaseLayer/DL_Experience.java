package DataBaseLayer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

import ObjectLayer.Experience;

public class DL_Experience {


    SQLiteOpenHelper db;

    protected static final String TABLE_NAME = "Experience";

    private static final String KEY_ID = "id";
    private static final String KEY_YEARSTART = "yearStart";
    private static final String KEY_YEAREND = "yearEnd";
    private static final String KEY_JOB = "job";
    private static final String KEY_INSTITUTENAME = "instituteName";

    String[] columnName = {KEY_ID, KEY_YEARSTART, KEY_YEAREND, KEY_JOB, KEY_INSTITUTENAME};


    public DL_Experience(SQLiteOpenHelper db) {
        this.db = db;
    }


    public String createTable() {
        return "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_YEARSTART + " TEXT,"
                + KEY_YEAREND + " TEXT,"
                + KEY_JOB + " TEXT,"
                + KEY_INSTITUTENAME + " TEXT "
                + " )";

    }


    public long insert(Experience experience) {

        SQLiteDatabase db = this.db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_YEARSTART, experience.getYear_Start());
        values.put(KEY_YEAREND, experience.getYear_end());
        values.put(KEY_JOB, experience.getJob());
        values.put(KEY_INSTITUTENAME, experience.getInstitute());



        // Inserting Row
        Long rowId = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        return rowId;
    }


    public Experience getExperience_ById(int experienceId) {

        SQLiteDatabase db = this.db.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columnName, KEY_ID + "=?",new String[] {String.valueOf(experienceId)} , null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();


        int id =  cursor.getInt(0);
        String year_start = cursor.getString(1);
        String year_End = cursor.getString(2);
        String job = cursor.getString(3);
        String institute = cursor.getString(4);


        Experience experience = new Experience(id,year_start,year_End,job,institute);

        return experience;

    }

    public void deleteExperience(int id) {

        SQLiteDatabase db = this.db.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] {Integer.toString(id)});
        db.close();

    }

}