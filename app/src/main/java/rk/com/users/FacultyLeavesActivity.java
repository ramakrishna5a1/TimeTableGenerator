package rk.com.users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class FacultyLeavesActivity extends AppCompatActivity
{
    ArrayList<FacultyLeavesData> facultyLeavesData;
    ListView facultyLeavesList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_faculty_leaves);

        facultyLeavesData = new ArrayList<FacultyLeavesData>();
        fetchLeavesData();

        LeavesAdapter leavesAdapter = new LeavesAdapter(facultyLeavesData, this);

        facultyLeavesList = findViewById(R.id.faculty_leaves_list);
        facultyLeavesList.setAdapter(leavesAdapter);
    }

    public void fetchLeavesData()
    {
        GenericTypeIndicator<HashMap<String, String>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, String>>()
        {
        };

        HashMap<String, String> hashMap = FireBase.leavesDataSnapshot.getValue(genericTypeIndicator);

        if(hashMap!=null)for (HashMap.Entry<String, String> oneEntry : hashMap.entrySet())
        {
            FacultyLeavesData facultyLeaves = new FacultyLeavesData(oneEntry.getKey(), oneEntry.getValue());
            facultyLeavesData.add(facultyLeaves);
        }
        else Toast.makeText(this, "No leave requests found", Toast.LENGTH_SHORT).show();
    }
}

class FacultyLeavesData implements Serializable
{
    private String id, message;

    FacultyLeavesData(String id, String message)
    {
        this.id = id;
        this.message = message;
    }

    public String getId()
    {
        return id;
    }

    String getMessage()
    {
        return message;
    }
}

