package rk.com.timetablegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity
{
    final String users[]={"Administrator","Student","Faculty","HOD"};
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spin=findViewById(R.id.user_type);

        ArrayAdapter aa=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,users);
        spin.setAdapter(aa);

    }


    public void userLogin(View view)
    {

    }
}
