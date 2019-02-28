package rk.com.users;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import scheduler.SchedulerMain;

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
        startActivity(new Intent(this,TimeTableData.class));
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


    public class TimeTableGenerateTask extends AsyncTask<SchedulerMain,String,String>
    {
        SchedulerMain schedulerMain;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(SchedulerMain... schedulerMains)
        {
            schedulerMain=schedulerMains[0];
            //new SchedulerMain();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
        }
    }
}


