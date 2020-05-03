package com.example.uoodma.Passcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.uoodma.Passcode.CreatePinFragment;
import com.example.uoodma.Passcode.EnterPincodeFragment;
import com.example.uoodma.R;

public class PinCodeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);


        changeFragment();


    }

    public void changeFragment(){


        SharedPreferences s = getSharedPreferences("SP", 0);
        String fPin = s.getString("onlinePin",null);
        Log.d("___", "OnCallBackact: " + fPin);

        if (fPin == null){
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
