package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class PinCodeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);


        changeFragment();


    }

    public void changeFragment(){


        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        String pin = sharedPreferences.getString("KEY", null);
        Log.d("____", "onCreate: spref" + pin);

        if (pin == null){
            Log.d("___", "changeFragment: one");
            Fragment fragment1 = new CreatePinFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainframe, fragment1);
            fragmentTransaction.commit();

        }else {
            Log.d("___", "changeFragment: two");
            Fragment fragment = new EnterPincodeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainframe, fragment);
            fragmentTransaction.commit();

        }

    }
}
