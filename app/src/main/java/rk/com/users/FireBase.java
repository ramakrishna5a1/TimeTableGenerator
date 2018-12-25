package rk.com.users;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class FireBase {
    DatabaseReference mDatabase;

    public void userData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.setValue("krishna");
    }
}
