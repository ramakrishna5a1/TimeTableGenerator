package rk.com.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class FacultyLeaves extends AppCompatActivity
{
    DatePicker datePicker;
    EditText leaveMessage;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_leaves);
        Intent facultyIntent = getIntent();
        userId = facultyIntent.getStringExtra("facultyId");

        datePicker = findViewById(R.id.leave_date);
        leaveMessage = findViewById(R.id.sent_leave_message);

        long now = System.currentTimeMillis();
        datePicker.setMinDate(now + (1000 * 60 * 60 * 24));
        datePicker.setMaxDate(now + (1000 * 60 * 60 * 24 * 7));
    }

    public void submitLeave(View view)
    {
        int day = datePicker.getDayOfMonth();
        int year = datePicker.getYear();
        int month = datePicker.getMonth();

        String message = leaveMessage.getText().toString();
        if (message.equals(""))
        {
            Toast.makeText(this, "Write a message", Toast.LENGTH_SHORT).show();
        } else
        {
            message = "Date of leave:" + day + "/" + month + "/" + year + "\nMessage:" + message;

            if (ConnectionCheck.connection)
            {
                FireBase.sendLeave(userId, message);
                Toast.makeText(this, "request submitted", Toast.LENGTH_LONG).show();
                Log.i("leave message", message);
            }
            else
                Toast.makeText(this, "network problem", Toast.LENGTH_LONG).show();

        }
    }
}
