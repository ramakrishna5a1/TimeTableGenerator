package rk.com.users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import scheduler.SchedulerMain;

public class TimeTableData extends AppCompatActivity
{
    int facultyCount = 0;
    LinearLayout linearLayout;
    TreeMap<String, Faculty> facultyDetails;

    String facultyNames[] = new String[10];
    String facultySubjects[] = new String[10];
    String facultyId[] = new String[10];
    TreeSet<String> treeSet;
    SchedulerMain schedulerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_data);

        linearLayout = findViewById(R.id.checkBoxes);
        facultyDetails = new TreeMap<>();
        treeSet = new TreeSet<String>();
        schedulerMain = new SchedulerMain();
        try
        {
            fetchFacultyData();
        } catch (Exception e)
        {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        String selectedNames[] = new String[facultyCount];

        View.OnClickListener checkBoxListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (treeSet.contains("" + v.getId()))
                {
                    treeSet.remove("" + v.getId());
                } else
                {
                    treeSet.add("" + v.getId());
                }

                Toast.makeText(getApplicationContext(), "" + treeSet, Toast.LENGTH_SHORT).show();
            }
        };


        int i = 1;
        for (TreeMap.Entry<String, Faculty> singleFacultyDetails : facultyDetails.entrySet())
        {
            CheckBox checkBox = new CheckBox(this);

            checkBox.setOnClickListener(checkBoxListener);
            checkBox.setText(String.format("%s  [Subject: %s]", singleFacultyDetails.getValue().getName(), singleFacultyDetails.getValue().getSubject()));
            checkBox.setTextColor(getResources().getColor(R.color.colorPrimary));
            checkBox.setTextSize(20);
            checkBox.setId(i);
            linearLayout.addView(checkBox);
            i++;
        }
    }

    public void fetchFacultyData()
    {
        GenericTypeIndicator<HashMap<String, HashMap<String, String>>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, HashMap<String, String>>>()
        {
        };

        HashMap<String, HashMap<String, String>> hashMap = FireBase.facultyDataSnapshot.getValue(genericTypeIndicator);
        facultyCount = (int) FireBase.facultyDataSnapshot.getChildrenCount();

        assert hashMap != null;
        int i = 1, j = 1, k = 1;
        for (Map.Entry<String, HashMap<String, String>> parent : hashMap.entrySet())
        {
            Faculty f1 = new Faculty();
            facultyId[i++] = parent.getKey();
            for (HashMap.Entry<String, String> child : parent.getValue().entrySet())
            {
                switch (child.getKey())
                {
                    case "name":
                        facultyNames[i++] = child.getValue();
                        f1.setName(child.getValue());
                        break;
                    case "subject":
                        facultySubjects[j++] = child.getValue();
                        f1.setSubject(child.getValue());
                        break;
                    default:
                        f1.setPassword(child.getValue());
                        break;
                }
            }
            //Toast.makeText(this,""+facultyCount,Toast.LENGTH_LONG).show();

            facultyCount++;
            facultyDetails.put(parent.getKey(), f1);
        }
    }

    public void generateTimetableWithData(View view)
    {
        if (treeSet.isEmpty())
            Toast.makeText(this, "Select Faculties from the list", Toast.LENGTH_LONG).show();
        else
        {
            if (treeSet.size() < 3)
                Toast.makeText(this, "select atleast 4 options", Toast.LENGTH_LONG).show();
            else
            {
                for(int i=1;i<=treeSet.size();i++){

                }

                new Thread(() -> {

                    while (schedulerMain.geneticOperations(getApplicationContext()) == 1) ;

                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}


class Faculty implements Serializable
{
    protected String name, password, subject;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}
