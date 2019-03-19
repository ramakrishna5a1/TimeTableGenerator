package rk.com.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LeavesAdapter extends ArrayAdapter<FacultyLeavesData>
{
    private ArrayList<FacultyLeavesData> facultyLeavesData;
    private Context context;

    LeavesAdapter(ArrayList<FacultyLeavesData> facultyLeavesData, Context context)
    {
        super(context, 0, facultyLeavesData);

        this.facultyLeavesData = facultyLeavesData;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).inflate(R.layout.single_leave_view, null);

        TextView messageView = convertView.findViewById(R.id.fetched_leave_message);
        TextView idView = convertView.findViewById(R.id.leave_faculty_display_id);

        FacultyLeavesData facultyLeaves = facultyLeavesData.get(position);

        messageView.setText(facultyLeaves.getMessage());
        idView.setText(facultyLeaves.getId());

        return convertView;
    }
}

