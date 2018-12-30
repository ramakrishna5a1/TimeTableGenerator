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
    private DatabaseReference mDatabase;
    String users[] = {"admin", "faculty", "hod", "student"};
    private DataSnapshot ds;

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


    public void setUser(String userType, String userName, String password)
    {
        mDatabase.child(userType).child(userName).setValue(password);
    }


    String getPassword(String userType, String userName)
    {
        try
        {
            return ds.child(userType).child(userName).getValue(String.class);
        }
        catch (Exception e)
        {
            Log.d("AUTHENTICATION error:", e.toString());
        }

        return "";
    }
}

