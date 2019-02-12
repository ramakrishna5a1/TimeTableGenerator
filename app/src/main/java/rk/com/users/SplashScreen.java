package rk.com.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SplashScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
            }
        },1000);
        */
    }


    public void gotoLogin(View view)
    {
        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
        finish();
    }
}
