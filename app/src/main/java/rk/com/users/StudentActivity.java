package rk.com.users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity
{

    private String userId, password;
    TextView displayUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        displayUserId = findViewById(R.id.display_id);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId").toUpperCase();
        password = intent.getStringExtra("password").trim();

        displayUserId.setText(userId);
    }

    public void viewTimeTable(View view)
    {
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show();
    }

    public void changePassword(View view)
    {
        changePassword(StudentActivity.this,"student",userId,password);
    }

    public void changePassword( final Context context,final String userType,final String userId,final String password)
    {
        final boolean[] ok = {true};
        final int ids[] = {R.id.old_password, R.id.new_password, R.id.confirm_password};
        final EditText editText[] = new EditText[ids.length];
        final String passwords[] = new String[ids.length];

        AlertDialog.Builder changePasswordDialog = new AlertDialog.Builder(context);

        LayoutInflater changePasswordLayout = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View changePasswordLayoutView = changePasswordLayout.inflate(R.layout.change_password_dialog, null);
        Button changePassword = changePasswordLayoutView.findViewById(R.id.change_password);

        changePasswordDialog.setView(changePasswordLayoutView);
        AlertDialog myDialog = changePasswordDialog.create();
        myDialog.show();

        changePassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (int i = 0; i < ids.length; i++)
                {
                    editText[i] = changePasswordLayoutView.findViewById(ids[i]);
                    passwords[i] = editText[i].getText().toString().trim();

                    if (passwords[i].equals(""))
                    {
                        ok[0] = false;
                        Toast.makeText(context, "Enter all details...", Toast.LENGTH_LONG).show();
                    }
                }

                if (ConnectionCheck.connection)
                {
                    if (ok[0] && !password.equals(passwords[0]))
                    {
                        Toast.makeText(context, "Old password incorrect", Toast.LENGTH_LONG).show();
                    } else if (passwords[1].equals(passwords[2]))
                    {
                        FireBase.changeDatabasePassword(userType,userId,passwords[1]);

                    } else
                        Toast.makeText(context, "Password mismatch...", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(context, "No internet connection...", Toast.LENGTH_LONG).show();

            }
        });
    }
}
