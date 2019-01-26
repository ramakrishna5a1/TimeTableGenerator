package rk.com.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.core.Context;

public class StudentActivity extends AppCompatActivity
{

    private String userId;
    TextView displayUserId;
    private FireBase fireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        displayUserId = findViewById(R.id.display_id);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId").toUpperCase();

        displayUserId.setText(userId);
    }

    public void viewTimeTable(View view)
    {
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show();
    }

    public void changePassword(View view)
    {
        AlertDialog.Builder changePasswordDialog=new AlertDialog.Builder(this);

        LayoutInflater changePasswordLayout= (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (ConnectionCheck.connection)
        {
            FireBase.setStudent(userId, "123");
        } else Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();

        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show();
    }
}
