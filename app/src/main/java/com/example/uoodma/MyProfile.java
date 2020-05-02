package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.WriterException;

import java.io.WriteAbortedException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MyProfile extends AppCompatActivity {

    TextView myProfileName, myProfileUID;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ImageView myProfileQRCode;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String stringData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myProfileName = findViewById(R.id.myProfileName);
        firebaseUser = firebaseAuth.getCurrentUser();
        myProfileQRCode = findViewById(R.id.myProfileQRCode);
        myProfileUID = findViewById(R.id.myProfileUID);

        myProfileName.setText(firebaseUser.getDisplayName());
        stringData = firebaseAuth.getUid();

        Intent intent = new Intent();
        String UID = intent.getStringExtra("carryUID");
        myProfileUID.setText(UID);


        generateQr();
        encryptUID();

    }

    private void encryptUID() {


    }


    public void generateQr() {
        if (stringData != null) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerdimension = width < height ? width : height;
            smallerdimension = smallerdimension * 3 / 4;
            qrgEncoder = new QRGEncoder(stringData, null, QRGContents.Type.TEXT, smallerdimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                myProfileQRCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Toast.makeText(MyProfile.this, "Qr generation Failed", Toast.LENGTH_LONG).show();
            }

        }
    }

}
