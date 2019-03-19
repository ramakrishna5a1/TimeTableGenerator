package rk.com.users;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.GenericTypeIndicator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import scheduler.SchedulerMain;


public class TimeTableData extends AppCompatActivity implements View.OnClickListener
{

    static int ids;
    int facultyCount = 0;
    LinearLayout facultyLinearLayout, studentLinearLayout;
    HashMap<String, Faculty> facultyDetails;

    String facultyNames[];
    String facultySubjects[];
    String facultyId[];
    TreeSet<Integer> treeSet;
    SchedulerMain schedulerMain;

    //final time table data variables
    static int hoursOfDay = 3;
    String input = "";
    TextView hoursView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_data);

        facultyLinearLayout = findViewById(R.id.faculty_checkBoxes);
        studentLinearLayout = findViewById(R.id.section_TextViews);

        facultyDetails = new HashMap<>();
        treeSet = new TreeSet<>();
        schedulerMain = new SchedulerMain();
        fetchFacultyData();
        hoursView = findViewById(R.id.no_of_hours);

    }


    public void AddSection(View view)
    {
        facultyLinearLayout.removeAllViews();
        setFacultyData();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.single_section_row, null);


        rowView.findViewById(R.id.finalize).setId(ids);
        rowView.findViewById(ids).setOnClickListener(this);

        rowView.findViewById(R.id.section_text).setId(ids);

        studentLinearLayout.addView(rowView, studentLinearLayout.getChildCount() - 1);
        ids++;
    }

    @Override
    public void onClick(View v)
    {
        String result = "";
        EditText et = v.getRootView().findViewById(v.getId());

        result = result.concat(et.getText().toString());

        if (result.equals(""))
            Toast.makeText(this, "Enter section name", Toast.LENGTH_SHORT).show();
        else if (treeSet.isEmpty())
            Toast.makeText(this, "Select Faculties from the list", Toast.LENGTH_SHORT).show();
        else
        {
            if (treeSet.size() < 4)
                Toast.makeText(this, "select minimum 4 options", Toast.LENGTH_SHORT).show();
            else
            {
                facultyLinearLayout.removeAllViews();
                setFacultyData();

                int i = 0;
                for (Integer ele : treeSet)
                {
                    result = result.concat("#" + facultySubjects[ele - 1]) + "#" + "6";
                    Log.i("index", "" + i + " " + facultyNames[ele - 1]);
                    i++;
                }
                result = result.concat("\n");
                Log.i("result", "" + result);

                input = input.concat(result);

                treeSet.clear();
            }
        }
    }

    public void removeSection(View view)
    {
        studentLinearLayout.removeView((View) view.getParent());
    }

    public void setFacultyData()
    {
        View.OnClickListener checkBoxListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (treeSet.contains(v.getId()))
                {
                    treeSet.remove(v.getId());
                } else
                {
                    treeSet.add(v.getId());
                }

                Toast.makeText(getApplicationContext(), "" + treeSet, Toast.LENGTH_SHORT).show();
            }
        };


        int i = 1;
        for (TreeMap.Entry<String, Faculty> singleFacultyDetails : facultyDetails.entrySet())
        {
            CheckBox checkBox = new CheckBox(this);

            checkBox.setOnClickListener(checkBoxListener);
            checkBox.setText(String.format("%s  [Subject: %s]", singleFacultyDetails.getValue().getName().toUpperCase(), singleFacultyDetails.getValue().getSubject()));
            checkBox.setTextColor(getResources().getColor(R.color.colorPrimary));
            checkBox.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
            checkBox.setTextSize(15);
            checkBox.setId(i);
            facultyLinearLayout.addView(checkBox);
            i++;
        }

    }

    public void fetchFacultyData()
    {
        GenericTypeIndicator<HashMap<String, HashMap<String, String>>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, HashMap<String, String>>>()
        {
        };

        HashMap<String, HashMap<String, String>> hashMap = FireBase.facultyDataSnapshot.getValue(genericTypeIndicator);
        assert hashMap != null;
        facultyCount = hashMap.size();

        int i = 0, j = 0, k = 0;

        facultyNames = new String[facultyCount];
        facultySubjects = new String[facultyCount];
        facultyId = new String[facultyCount];

        for (HashMap.Entry<String, HashMap<String, String>> parent : hashMap.entrySet())
        {
            Faculty f1 = new Faculty();
            facultyId[k++] = parent.getKey();
            Log.i("key", "" + parent.getKey());

            for (HashMap.Entry<String, String> child : parent.getValue().entrySet())
            {
                switch (child.getKey())
                {
                    case "name":
                        facultyNames[i++] = child.getValue();
                        f1.setName(child.getValue());
                        Log.i("child:", "" + f1.getName());
                        break;

                    case "subject":
                        facultySubjects[j++] = child.getValue();
                        f1.setSubject(child.getValue());
                        Log.i("child:", "" + f1.getSubject());
                        break;

                    default:
                        f1.setPassword(child.getValue());
                        break;
                }
            }
            facultyDetails.put(parent.getKey(), f1);
        }
    }

    public void generateTimetableWithData(View view)
    {
        input = input.concat("teachers\n");
        for (int i = 0; i < facultyNames.length; i++)
            input = input.concat(facultyNames[i] + "#" + facultySubjects[i] + "\n");

        Log.i("INPUT DATA", "" + input);
        try
        {
            schedulerMain.geneticOperations(getApplicationContext(), hoursOfDay, input);
            hoursOfDay = 3;
        } catch (Exception e)
        {
            Toast.makeText(this, "something went wrong Please try again", Toast.LENGTH_LONG).show();
            finish();
            Log.i("Generation error", "" + e.getMessage());
        }
    }

    public void hoursPerDay(View view)
    {
        switch (view.getId())
        {
            case R.id.hours_btn_plus:
                if (hoursOfDay != 7)
                {
                    hoursOfDay++;
                    hoursView.setText("" + hoursOfDay);
                }
                break;
            case R.id.hours_btn_minus:
                if (hoursOfDay != 3)
                {
                    hoursOfDay--;
                    hoursView.setText("" + hoursOfDay);
                }

                break;
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
