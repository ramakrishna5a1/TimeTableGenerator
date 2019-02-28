package rk.com.users;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.app.Activity.RESULT_OK;

public class AdministeringFaculty extends Fragment
{
    private static final int ACTIVITY_CHOOSE_FILE = 1;
    AdministeringStudent a;

    String text;

    int editTextIds[] = {R.id.faculty_id, R.id.faculty_name, R.id.faculty_subject, R.id.faculty_password};
    private EditText facultyEditTexts[] = new EditText[editTextIds.length];

    String facultyDetails[] = new String[editTextIds.length];

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate(R.layout.activity_administering_faculty, container, false);

        //finding the button views
        Button uploadFile = layout.findViewById(R.id.faculty_upload_file);
        Button register = layout.findViewById(R.id.register_faculty);

        //finding the edit text views
        for (int i = 0; i < editTextIds.length; i++)
            facultyEditTexts[i] = layout.findViewById(editTextIds[i]);

        facultyEditTexts[3].setText(getString(R.string.time_table));

        //uploading the CSV file form device memory
        uploadFile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "not implemented", Toast.LENGTH_SHORT).show();
                //onBrowse();
            }
        });

        //registering the facultyEditTexts manually
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean ok = true;
                for (int i = 0; i < editTextIds.length; i++)
                    facultyDetails[i] = facultyEditTexts[i].getText().toString();

                if (ConnectionCheck.connection)
                {
                    for (int i = 0; i < editTextIds.length; i++)
                        if (facultyDetails[i].equals(""))
                        {
                            ok = false;
                            Toast.makeText(getContext(), "enter all details", Toast.LENGTH_LONG).show();
                            break;
                        }

                    if (ok)
                    {
                        //update faculty id,name, subject and password
                        FireBase.setFaculty(facultyDetails[0],facultyDetails[1], facultyDetails[2], facultyDetails[3]);
                    }

                } else
                    Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_LONG).show();
            }
        });

        //uploadFile.setOnClickListener(this);
        return layout;
    }

    public void onBrowse()
    {
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("file/*");
        intent = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != RESULT_OK) return;

        if (requestCode == ACTIVITY_CHOOSE_FILE)
        {
            Uri uri = data.getData();
            String FilePath = getRealPathFromURI(uri); // should the path be here in this string
            try
            {
                textFile(FilePath);
                Toast.makeText(getContext(), "Path  = " + FilePath, Toast.LENGTH_SHORT).show();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }


    public String getRealPathFromURI(Uri contentUri)
    {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) return null;

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void textFile(String path) throws IOException
    {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line;

        while ((line = br.readLine()) != null) text = text.concat(line);

        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

        Toast.makeText(getContext(), "sample...", Toast.LENGTH_LONG).show();

        br.close();
    }
}
