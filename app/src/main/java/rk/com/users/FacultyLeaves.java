package rk.com.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class FacultyLeaves extends AppCompatActivity
{

    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_leaves);

        datePicker=findViewById(R.id.leave_date);

        long now=System.currentTimeMillis();
        datePicker.setMinDate(now+(1000*60*60*24));
        datePicker.setMaxDate(now+(1000*60*60*24*7));
    }

    public void submitLeave(View view)
    {

    }
}
