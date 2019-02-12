package rk.com.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static android.app.Activity.RESULT_OK;


public class AdministeringStudent extends Fragment
{
    private static final int REQUEST_TAKE_GALLERY_VIDEO = 1;


    EditText editUserId, editUserPassword;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.activity_administering_student, container, false);

        Button uploadFile = layout.findViewById(R.id.student_upload_file);
        Button register = layout.findViewById(R.id.register_student);

        editUserId = layout.findViewById(R.id.student_id);
        editUserPassword = layout.findViewById(R.id.student_password);

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

        //registering the student manually
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ConnectionCheck.connection)//checking internet connection.
                {
                    String studentId, studentPassword;

                    studentId = editUserId.getText().toString().trim();
                    studentPassword = editUserPassword.getText().toString().trim();

                    if (studentId.equals("") || studentPassword.equals(""))
                    {
                        Toast.makeText(getContext(), "enter all details", Toast.LENGTH_LONG).show();
                    } else
                    {
                        FireBase.setStudent(studentId, studentPassword);
                    }
                } else
                {
                    Toast.makeText(getContext(), "No internet..", Toast.LENGTH_LONG).show();
                }

            }
        });

        return layout;
    }

    public void readDataFile()
    {

        Intent textFileRead = new Intent();
        textFileRead.addCategory(Intent.CATEGORY_OPENABLE);

        textFileRead.setType("*/*");

        textFileRead.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(textFileRead, "Select File"), REQUEST_TAKE_GALLERY_VIDEO);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == RESULT_OK)
        {
            if (resultCode == REQUEST_TAKE_GALLERY_VIDEO)
            {
                Toast.makeText(getContext(), "file selected", Toast.LENGTH_LONG).show();
            }
        }
    }
}

