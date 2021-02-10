package DataBaseLayer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DL_Conf {

    SQLiteOpenHelper db;

    public DL_Conf(SQLiteOpenHelper db) {
        this.db = db;
    }


    public int getIdOfLastRecord(String tableName, String columnName){
        String query = "SELECT * FROM " + tableName  + " ORDER BY "+ columnName +" DESC LIMIT 1";
        int id = 0;

        SQLiteDatabase db = this.db.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }

        return id;

    }

}