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
    private static DatabaseReference mDatabase;
    String users[] = {"admin", "faculty", "hod", "student"};
    private static DataSnapshot ds;

    FireBase()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ds = dataSnapshot;
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
        mDatabase.child(userType).child(userName).setValue(password);
    }


    //adding details of student to database
    static void setStudent(String userName, String password)
    {
        mDatabase.child("student").child(userName).setValue(password);
    }


    //adding details of faculty to firebase
    static void setFaculty(String userName, String subject, String password)
    {
        mDatabase.child("faculty").child(userName).child("subject").setValue(subject);
        mDatabase.child("faculty").child(userName).child("password").setValue(password);
    }


    static void changeDatabasePassword(String userType, String userTd, String password)
    {
        if (userType.equals("faculty"))
            mDatabase.child("faculty").child(userTd).child("password").setValue(password);
        else mDatabase.child(userType).child(userTd).setValue(password);
    }


    static String getStudentPassword(String userType, String userName)
    {
        try
        {
            return ds.child(userType).child(userName).getValue(String.class);
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
            return ds.child("faculty").child(userName).child("password").getValue(String.class);
        } catch (Exception e)
        {
            Log.d("AUTHENTICATION error:", e.toString());
        }

        return "";
    }


}


