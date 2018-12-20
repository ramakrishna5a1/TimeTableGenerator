package rk.com.timetablegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity
{
    final String users[]={"Administrator","Student","Faculty","HOD"};
    Spinner spin;

    EditText etUserName, etPassword;
    String userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spin=findViewById(R.id.user_type);

        etUserName =findViewById(R.id.user_id);
        etPassword =findViewById(R.id.password);

        ArrayAdapter aa=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,users);
        spin.setAdapter(aa);

    }


    public void userLogin(View view)
    {
        userName = etUserName.getText().toString();
        password = etPassword.getText().toString();

        if(!userName.equals(""))
        {
            if(!password.equals(""))
            {

            }
            else
                etPassword.setError("empty...");
        }else
            etUserName.setError("empty...");
    }

}
