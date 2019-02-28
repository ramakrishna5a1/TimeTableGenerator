package rk.com.users;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class FireBase
{
    private static DatabaseReference wholeDatabaseReference;

    private static DatabaseReference facultyDataReference;
    protected static DataSnapshot facultyDataSnapshot;

    String users[] = {"admin", "faculty", "hod", "student"};
    private static DataSnapshot totalDataSnapshot;

    FireBase()
    {

        wholeDatabaseReference = FirebaseDatabase.getInstance().getReference();

        wholeDatabaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                totalDataSnapshot = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });

        facultyDataReference=FirebaseDatabase.getInstance().getReference().child("faculty");

        facultyDataReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                facultyDataSnapshot=dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }


    //To add any other users to the database
    static void setUser(String userType, String userName, String password)
    {
        wholeDatabaseReference.child(userType).child(userName).setValue(password);
    }


    //adding details of HOD to database
    static void setHOD(String userName, String password)
    {
        wholeDatabaseReference.child("HOD").child(userName).setValue(password);
    }

    static void leaveData(String userName, String data)
    {

    }

    //adding details of student to database
    static void setStudent(String userName, String password)
    {
        wholeDatabaseReference.child("student").child(userName).setValue(password);
    }


    //adding details of faculty to database
    static void setFaculty(String userName,String name, String subject, String password)
    {

        wholeDatabaseReference.child("faculty").child(userName).child("name").setValue(name);
        wholeDatabaseReference.child("faculty").child(userName).child("subject").setValue(subject);
        wholeDatabaseReference.child("faculty").child(userName).child("password").setValue(password);
        //wholeDatabaseReference.child("faculty").child(userName).child("leaveStatus").setValue("no");
    }

    static void changeDatabasePassword(String userType, String userTd, String password)
    {
        if (userType.equals("faculty"))
            wholeDatabaseReference.child("faculty").child(userTd).child("password").setValue(password);
        else wholeDatabaseReference.child(userType).child(userTd).setValue(password);
    }

    //getting the password from the database for userType admin,HOD,student
    static String getPassword(String userType, String userName)
    {
        try
        {
            return totalDataSnapshot.child(userType).child(userName).getValue(String.class);
        } catch (Exception e)
        {
            Log.d("AUTHENTICATION error:", e.toString());
        }
        return "";
    }


    static String getFacultyPassword(String userName)
    {
        try
        {
            return totalDataSnapshot.child("faculty").child(userName).child("password").getValue(String.class);
        } catch (Exception e)
        {
            Log.d("AUTHENTICATION error:", e.toString());
        }

        return "";
    }

    //leave related operations are performed through this function\\
    static String leaves(String userId, String data)
    {

        return "";
    }
}


