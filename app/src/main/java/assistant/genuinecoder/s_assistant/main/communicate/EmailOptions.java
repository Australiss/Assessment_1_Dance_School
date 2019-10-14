package assistant.genuinecoder.s_assistant.main.communicate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import assistant.genuinecoder.s_assistant.R;

public class EmailOptions extends AppCompatActivity {
    public static ArrayList<String> items;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_options);

        activity = this;
        items = new ArrayList<>();
        items.add("Email students");
        items.add("Email others");

        ListView listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position ) {
                    case 0:
                        Intent firstActivity = new Intent(activity, StudentEmail.class);
                        startActivity(firstActivity);
                        break;
                    case 1:
                        String address ="";
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("*/*");
                        intent.putExtra(Intent.EXTRA_EMAIL, address);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                }
            }
        });
    }

}
