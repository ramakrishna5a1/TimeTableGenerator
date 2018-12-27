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


    public void setUser(String userType,String userName, String password)
    {
        mDatabase.child(userType).child(userName).setValue("");
    }

    public String getPassword(String userType,String userName)
    {
        String password = mDatabase.child(userType).child(userName).getKey();

        return password;
    }


}
