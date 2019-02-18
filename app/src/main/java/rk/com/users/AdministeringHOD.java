package rk.com.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministeringHOD extends Fragment
{


    EditText editDepartmentName, editDepartmentPassword;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.activity_administering_hod, container, false);

        Button uploadFile=layout.findViewById(R.id.hod_upload_file);
        Button register=layout.findViewById(R.id.register_department);


        editDepartmentName = layout.findViewById(R.id.department_name);
        editDepartmentPassword = layout.findViewById(R.id.department_password);


        //uploading the file from the device folder
        uploadFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "not implemented", Toast.LENGTH_SHORT).show();
                //readDataFile();
            }
        });


        //registering the department manually
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ConnectionCheck.connection)//checking internet connection.
                {
                    String departmentName, departmentPassword;

                    departmentName = editDepartmentName.getText().toString().trim();
                    departmentPassword = editDepartmentPassword.getText().toString().trim();

                    if (departmentName.equals("") || departmentPassword.equals(""))
                    {
                        Toast.makeText(getContext(), "enter all details", Toast.LENGTH_LONG).show();
                    } else
                    {
                        FireBase.setHOD(departmentName, departmentPassword);
                    }

                } else
                {
                    Toast.makeText(getContext(), "No internet..", Toast.LENGTH_LONG).show();
                }

            }
        });

        return layout;



    }
}
