package rk.com.users;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    final String users[] = {"Login Type", "Administrator", "Student", "Faculty", "HOD"};
    Spinner spin;

    EditText etUserName, etPassword;
    String userName, password;
    ConnectionCheck connectionCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spin = findViewById(R.id.user_type);

        etUserName = findViewById(R.id.user_id);
        etPassword = findViewById(R.id.password);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, users);
        spin.setAdapter(aa);

        connectionCheck = new ConnectionCheck();

        //registering the NETWORK CHANGE action
        registerReceiver(new ConnectionCheck(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    public void userLogin(View view)
    {
        userName = etUserName.getText().toString();
        password = etPassword.getText().toString();

        if (connectionCheck.connection) {
            if (!userName.equals("") || !password.equals("")) {
                //login code here
            } else
                Toast.makeText(this, "Enter all the details", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
    }

}
