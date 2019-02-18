package rk.com.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class HODActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod);
    }

    public void acceptRejectLeaves(View view)
    {

    }

    public void generateTimeTable(View view)
    {

    }

    public void viewTimeTable(View view)
    {
        if (true)
        {
            //Toast.makeText(this, "under implementation...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, ShowTimeTable.class));
        }
        else
        {
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
        }
    }
}


