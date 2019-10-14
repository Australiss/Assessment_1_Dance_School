package assistant.genuinecoder.s_assistant.main.communicate;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.AppBase;
import assistant.genuinecoder.s_assistant.main.Login.Login;
import assistant.genuinecoder.s_assistant.main.components.ListAdapter;

import static java.net.Proxy.Type.HTTP;

public class txtMessage extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;
    ArrayAdapter<String> adapterSpinner;
    ArrayList<String> names;
    ArrayList<String> registers;
    Activity thisActivity = this;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.txt_message);

        listView = (ListView) findViewById(R.id.ContactListView);
        names = new ArrayList<>();
        registers = new ArrayList<>();

        Button btn = (Button) findViewById(R.id.loadButton);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadListView(v);
            }
        });

        Button btnx = (Button) findViewById(R.id.buttonSendTxt);
        assert btnx != null;
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getContact();
                ArrayList<String> ContactArrayList = new ArrayList<String>(ListAdapter.ContactNumbers);
                String[] contactNo = ContactArrayList.toArray(new String[0]);
                String contact = TextUtils.join(",", contactNo);
                if (contactNo!=null && contactNo.length>0) {

                    String body ="";
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("sms:" + contact));
                    intent.putExtra("sms_body", body);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(getBaseContext(), "No Students selected", Toast.LENGTH_LONG).show();
                }

            }
        });

        spinner = (Spinner) findViewById(R.id.studentspinner);
        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.classes);
        assert spinner != null;
        spinner.setAdapter(adapterSpinner);
    }

    public void loadListView(View view) {
        names.clear();
        registers.clear();
        String qu = "SELECT * FROM STUDENT WHERE cl = '" + spinner.getSelectedItem().toString() + "' " +
                "ORDER BY ROLL";
        Cursor cursor = Login.handler.execQuery(qu);
        if (cursor == null || cursor.getCount() == 0) {
            Log.e("Txt message", "Null cursor");
        } else {
            int ctr = 0;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                names.add(cursor.getString(0) + " (" + cursor.getInt(5) + ')');
                registers.add(cursor.getString(3));
                cursor.moveToNext();
                ctr++;
            }
            if (ctr == 0) {
                Toast.makeText(getBaseContext(), "No Students Found", Toast.LENGTH_LONG).show();
            }
            Log.d("Email", "Got " + ctr + " students");
        }
        adapter = new ListAdapter(thisActivity, names, registers);
        listView.setAdapter(adapter);
    }
}
