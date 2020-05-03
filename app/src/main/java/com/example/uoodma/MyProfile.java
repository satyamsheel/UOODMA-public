package com.example.uoodma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.WriteAbortedException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MyProfile extends AppCompatActivity {

    TextView myProfileName, myProfileUID;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ImageView myProfileQRCode, myPtofileGoBack;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String stringData, encryptedData;
    String AES = "AES";
    Button myProfileScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myProfileName = findViewById(R.id.myProfileName);
        firebaseUser = firebaseAuth.getCurrentUser();
        myProfileQRCode = findViewById(R.id.myProfileQRCode);
        myProfileUID = findViewById(R.id.myProfileUID);
        myPtofileGoBack = findViewById(R.id.myPtofileGoBack);
        myProfileScan = findViewById(R.id.myProfileScan);

        myProfileName.setText(firebaseUser.getDisplayName());
        stringData = firebaseAuth.getUid();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("BuyyaPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String uids = pref.getString("IMPUID", "  ");
        String finalUID = "UID- " + "(" + uids.substring(0, 2) + ")(" + uids.substring(2, 5) + ")(" + uids.substring(5, 8)
                + ")(" + uids.substring(8, 11) + ")(" + uids.substring(11, 14) + ")";
        myProfileName.setText(firebaseUser.getDisplayName());
        myProfileUID.setText(finalUID);

        try {
            encryptedData = encryptUID(stringData, uids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        generateQr(encryptedData);

        myProfileScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MyProfile.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(MyProfile.this, "Scanning cancelled", Toast.LENGTH_LONG).show();
            } else {


            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private String encryptUID(String data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptionValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptionValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;

    }

    public void generateQr(String recievedData) {
        String dataToSend = recievedData;
        if (dataToSend != null) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerdimension = width < height ? width : height;
            smallerdimension = smallerdimension * 3 / 4;
            qrgEncoder = new QRGEncoder(dataToSend, null, QRGContents.Type.TEXT, smallerdimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                myProfileQRCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Toast.makeText(MyProfile.this, "Qr generation Failed", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void goBackToDashboard(View view) {
        Intent intent = new Intent(MyProfile.this, mainDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
