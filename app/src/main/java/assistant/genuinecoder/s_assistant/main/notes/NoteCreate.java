package assistant.genuinecoder.s_assistant.main.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.AppBase;
import assistant.genuinecoder.s_assistant.main.Login.Login;

public class NoteCreate extends AppCompatActivity {
    EditText title, body, sub;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_create);

        Button btn = (Button) findViewById(R.id.noteSaveButton);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        spinner = (Spinner) findViewById(R.id.pinSpinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.classes);
        assert spinner != null;
        spinner.setAdapter(adapterSpinner);
    }

    private void saveData() {
        title = (EditText) findViewById(R.id.noteTitle);
        body = (EditText) findViewById(R.id.noteBody);
        sub = (EditText) findViewById(R.id.subjectNote);
        String qu = " INSERT INTO NOTES(title,body,cls,sub) VALUES('" + title.getText().toString() + "','" + body.getText().toString() + "'," +
                "'" + spinner.getSelectedItem().toString() + "','" + sub.getText().toString().toUpperCase() + "')";
        if (Login.handler.execAction(qu)) {
            Toast.makeText(getBaseContext(), "Note Saved", Toast.LENGTH_LONG).show();
            this.finish();
        }
    }

}
