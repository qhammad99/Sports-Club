package pk.edu.uaar.group_sports_club.sports_club;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "MyDatabase";
    private static final String table1 = "user_detail";
//    private static final String table2 = "tournament";
//    private static final String table3 = "matches";
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
                "user_phone VARCHAR,"+
                "user_city VARCHAR,"+
                "user_gender VARCHAR,"+
                "user_skill VARCHAR,"+
                "batting_skill_detail VARCHAR,"+
                "bowling_skill_detail VARCHAR,"+
                "user_password VARCHAR,"+
                "is_admin INTEGER DEFAULT 0)");
//
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + table2 + "(" +
//                "tor_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                "tor_name VARCHAR,"+
//                "tor_format VARCHAR,"+
//                "tor_startDate VARCHAR,"+
//                "tor_endDate VARCHAR,"+
//                "tor_location VARCHAR)");
//
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + table3 + "(" +
//                "match_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                "match_team1 VARCHAR,"+
//                "match_team2 VARCHAR,"+
//                "match_date VARCHAR,"+
//                "match_time VARCHAR,"+
//                "match_stats VARCHAR,"+
//                "match_ground VARCHAR,"+
//                "match_tournament INTEGER,"+
//                "FOREIGN KEY(match_tournament) REFERENCES "+table2+"(tor_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + table1);
        onCreate(db);
    }

    public long signup(String name, String email, String phone, String city, String gender,
                       String skills, String battingDetails, String bowlingDetail, String password) {

        //  SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("user_name", name);
        contentValues.put("user_email", email);
        contentValues.put("user_phone", phone);
        contentValues.put("user_city", city);
        contentValues.put("user_gender", gender);
        contentValues.put("user_skill", skills);
        contentValues.put("batting_skill_detail", battingDetails);
        contentValues.put("bowling_skill_detail", bowlingDetail);
        contentValues.put("user_password", password);

        long user_data = sqLiteDatabase.insert(table1, null, contentValues);
        return user_data;

    }

    public Cursor login(String email, String password) {

        // SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor user_data = sqLiteDatabase.rawQuery("SELECT * from " + table1 + " WHERE user_email='" + email + "'" + "AND user_password ='" + password + "'", null);

        return user_data;
    }
}
