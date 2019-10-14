package assistant.genuinecoder.s_assistant.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Delayed;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.Login.Login;
import assistant.genuinecoder.s_assistant.main.Login.SignUp;
import assistant.genuinecoder.s_assistant.main.components.About;
import assistant.genuinecoder.s_assistant.main.components.GridAdapter;
import assistant.genuinecoder.s_assistant.main.components.SettingsActivity;
import assistant.genuinecoder.s_assistant.main.database.DatabaseHandler;

public class AppBase extends AppCompatActivity {

    public static ArrayList<String> classes;
    public static Activity activity;
    ArrayList<String> basicFields;
    GridAdapter adapter;
    GridView gridView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        basicFields = new ArrayList<>();
        activity = this;

        getSupportActionBar().show();
        classes = new ArrayList<>();
        classes.add("Class 1");
        classes.add("Class 2");
        classes.add("Class 3");
        classes.add("Class 4");
        classes.add("Class 5");
        classes.add("Class 6");
        classes.add("Class 7");
        gridView = (GridView) findViewById(R.id.grid);

        Intent i = getIntent();
        String accessLevel = i.getStringExtra("access_level");

        if (accessLevel.equals("Teacher")) {

            basicFields.add("ATTENDANCE");
            basicFields.add("SCHEDULER");
            basicFields.add("NOTES");
            basicFields.add("PROFILE");
            basicFields.add("COMMUNICATE");
        }
        if (accessLevel.equals("User")) {
            basicFields.add("COMMUNICATE");
            basicFields.add("NOTES");

        }
        adapter = new GridAdapter(this, basicFields);
        gridView.setAdapter(adapter);
    }


    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this, SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this, About.class);
        startActivity(launchIntent);
    }
    public void Logout(MenuItem item) {
        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging out...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent launchIntent = new Intent(AppBase.this, Login.class);
                        startActivity(launchIntent);
                    }
                }, 3000);
    }
}
