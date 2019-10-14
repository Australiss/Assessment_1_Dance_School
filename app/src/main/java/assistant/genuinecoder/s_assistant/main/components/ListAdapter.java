package assistant.genuinecoder.s_assistant.main.components;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.AppBase;
import assistant.genuinecoder.s_assistant.main.Login.Login;
import assistant.genuinecoder.s_assistant.main.attendance.AttendanceActivity;
import assistant.genuinecoder.s_assistant.main.communicate.StudentEmail;

public class ListAdapter extends BaseAdapter {
    ArrayList<String> nameList;
    ArrayList<String> registers;
    Activity activity;
    public static ArrayList<String> emails;
    public static ArrayList<String> ContactNumbers;

    ArrayList<Boolean> studentList;

    public ListAdapter(Activity activity, ArrayList<String> nameList, ArrayList<String> reg) {
        this.nameList = nameList;
        this.activity = activity;
        this.registers = reg;
        studentList = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            studentList.add(new Boolean(true));
        }
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.list_ele, null);
        }
        final int pos = position;
        TextView textView = (TextView) v.findViewById(R.id.Names);
        textView.setText(nameList.get(position));
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.attMarker);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentList.set(pos, checkBox.isChecked());
                Log.d("Attendance", nameList.get(position).toString() + " is absent " + studentList.get(position));
            }
        });
        return v;
    }

    public void saveAll() {
        for (int i = 0; i < nameList.size(); i++) {
            int sts = 1;
            if (studentList.get(i))
                sts = 1;
            else sts = 0;
            String qu = "INSERT INTO ATTENDANCE VALUES('" + AttendanceActivity.time + "'," +
                    "" + AttendanceActivity.period + "," +
                    "'" + registers.get(i) + "'," +
                    "" + sts + ");";
            Login.handler.execAction(qu);
            activity.finish();
        }
    }

    public void getEmails() {
        emails = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            if (studentList.get(i)) {
                String qu = "SELECT email FROM STUDENT WHERE regno ='" + registers.get(i) + "';";
                Cursor cursor = Login.handler.execQuery(qu);
                try {
                    while (cursor.moveToNext()) {
                        emails.add(cursor.getString(0));
                    }
                } finally {
                    cursor.close();
                }
            }
        }
    }

    public void getContact() {
        ContactNumbers = new ArrayList<>();
       // String NumberSplitter = ",";
        for (int i = 0; i < nameList.size(); i++) {
            if (studentList.get(i)) {
                String qu = "SELECT contact FROM STUDENT WHERE regno ='" + registers.get(i) + "';";
                Cursor cursor = Login.handler.execQuery(qu);
                try {
                    while (cursor.moveToNext()) {
                        ContactNumbers.add(cursor.getString(0));
                    }
                } finally {
                    cursor.close();
                }
            }
        }
    }
}
