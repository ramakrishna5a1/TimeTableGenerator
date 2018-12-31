package rk.com.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AdministeringFaculty extends Fragment
{
    AdminActivity admin;
    Button uploadFile;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        admin=new AdminActivity();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.activity_administering_faculty, container, false);
        //v.findViewById()
        return inflater.inflate(R.layout.activity_administering_faculty, container, false);
    }
}
