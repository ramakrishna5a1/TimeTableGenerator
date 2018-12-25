package rk.com.users;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    final String users[] = {"Login Type", "Administrator", "Student", "Faculty", "HOD"};
    Spinner spin;

    EditText etUserName, etPassword;
    String userName, password, userType = " ";

    FireBase fireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spin = findViewById(R.id.user_type);

        etUserName = findViewById(R.id.user_id);
        etPassword = findViewById(R.id.password);

        fireBase = new FireBase();

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, users);
        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(this);

        //registering the NETWORK CHANGE action
        registerReceiver(new ConnectionCheck(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }


    /**
     * This method will execute whenever user press the submit button during login
     * It checks for the internet connection and the fields are empty or not before login.
     *
     * @param view
     * @return void
     */

    public void userLogin(View view)
    {
        if (ConnectionCheck.connection)
        {
            userName = etUserName.getText().toString();
            password = etPassword.getText().toString();

            if (userType.equals("Login Type"))
            {
                Toast.makeText(this, "Select Login Type", Toast.LENGTH_LONG).show();
            } else if (!userName.equals("") || !password.equals(""))
            {
                //login code here
                fireBase.userData();
            } else
                Toast.makeText(this, "Enter all the details", Toast.LENGTH_LONG).show();
        } else
        {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        userType = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }

}
