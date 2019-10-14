package assistant.genuinecoder.s_assistant.main.database;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler {
    Activity activity;
    private SQLiteDatabase database;

    public DatabaseHandler(Activity activity) {
        this.activity = activity;
        database = activity.openOrCreateDatabase("ASSIST", Context.MODE_PRIVATE, null);
        createTable();
    }

    public void createTable() {
        try {
            String qu = "CREATE TABLE IF NOT EXISTS STUDENT(name varchar(1000), parentsName varchar(100)," +
                    "cl varchar(100), " +
                    "regno varchar(100) primary key, contact varchar(100),roll integer, email varchar(100));";
            database.execSQL(qu);
        } catch (Exception e) {
            Toast.makeText(activity, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }
        try {
            String qu = "CREATE TABLE IF NOT EXISTS ATTENDANCE(datex date," +
                    "hour int, " +
                    "register varchar(100) ,isPresent boolean);";
            database.execSQL(qu);
        } catch (Exception e) {
            Toast.makeText(activity, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }

        try {
            String qu = "CREATE TABLE IF NOT EXISTS NOTES(title varchar(100) not null," +
                    "body varchar(10000), cls varchar(1000), sub varchar(1000) ,datex TIMESTAMP default CURRENT_TIMESTAMP);";
            database.execSQL(qu);
        } catch (Exception e) {
            Toast.makeText(activity, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }

        try {
            String qu = "CREATE TABLE IF NOT EXISTS SCHEDULE(cl varchar(100),event varchar(1000)," +
                    "timex time, day_week varchar(100));";
            database.execSQL(qu);
        } catch (Exception e) {
            Toast.makeText(activity, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }
        try {
            String qu = "CREATE TABLE IF NOT EXISTS USER(name varchar(100), email varchar(100),password varchar(100)," +
                    "access_level varchar(50),joinDate TIMESTAMP default CURRENT_TIMESTAMP);";
            database.execSQL(qu);
        } catch (Exception e) {
            Toast.makeText(activity, "Error Occured for create table", Toast.LENGTH_LONG).show();
        }
    }

    public boolean execAction(String qu) {
        Log.i("DatabaseHandler", qu);
        try {
            database.execSQL(qu);
        } catch (Exception e) {
            Log.e("DatabaseHandler", qu);
            Toast.makeText(activity, "Error Occured for execAction", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public Cursor execQuery(String qu) {
        try {
            return database.rawQuery(qu, null);
        } catch (Exception e) {
            Log.e("DatabaseHandler", qu);
            Toast.makeText(activity, "Error Occurred for execAction", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
