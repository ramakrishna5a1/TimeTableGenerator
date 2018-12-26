package rk.com.users;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class FireBase
{
    DatabaseReference mDatabase;
    String users[] = {"admin", "faculty", "hod", "student"};

    public FireBase()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void userData()
    {
        mDatabase.child(users[1]).child("sir2").setValue("2");
    }
}
