package pk.edu.uiit.arid_2471.checkingemulator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "MyDatabase";
    private static final String table1 = "signup";
    private static final String table2 = "tournament";
    private static final String table3 = "matches";
    private static final int db_version = 1;
    SQLiteDatabase sqLiteDatabase;

    public databaseHelper(Context context){
        super(context, database_name, null, db_version);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + table1 + "(" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "user_name VARCHAR,"+
                "user_email VARCHAR,"+
                "user_city VARCHAR,"+
                "user_gender VARCHAR,"+
                "user_skill VARCHAR,"+
                "user_password VARCHAR,"+
                "is_admin INTEGER DEFAULT 0)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + table2 + "(" +
                "tor_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "tor_name VARCHAR,"+
                "tor_format VARCHAR,"+
                "tor_startDate VARCHAR,"+
                "tor_endDate VARCHAR,"+
                "tor_location VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + table3 + "(" +
                "match_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "match_team1 VARCHAR,"+
                "match_team2 VARCHAR,"+
                "match_date VARCHAR,"+
                "match_time VARCHAR,"+
                "match_stats VARCHAR,"+
                "match_ground VARCHAR,"+
                "match_tournament INTEGER,"+
                "FOREIGN KEY(match_tournament) REFERENCES "+table2+"(tor_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + table1);
        onCreate(db);
    }
}
