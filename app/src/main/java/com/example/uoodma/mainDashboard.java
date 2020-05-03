package com.example.uoodma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uoodma.helperClass.recyclerAdapter;
import com.example.uoodma.login_register.MainActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.Contacts.SettingsColumns.KEY;

public class mainDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int CHOOSE_IMAGE = 00001;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button sendData;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    TextView headerEmail, mainDashboardName, mainDashboardUID;
    FirebaseUser user;
    ImageView profileImage;
    Uri uriProfilePic;
    String downloadImageLink;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        sendData = findViewById(R.id.sendData);
        headerEmail = findViewById(R.id.headerEmail);
        profileImage = findViewById(R.id.profileImage);
        mainDashboardName = findViewById(R.id.mainDashboardName);
        mainDashboardUID = findViewById(R.id.mainDashboardUID);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.dashboardRecycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        String[] items={"Document One","Document Two","Document Three","Document Four","Document Five","Document Six"};
        recyclerView.setAdapter(new recyclerAdapter(items));



//        getPin();

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.navLogout).setVisible(true);
        menu.findItem(R.id.navProfile).setVisible(true);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navDashboard);
        View headView = navigationView.getHeaderView(0);
        TextView headerEmail = headView.findViewById(R.id.headerEmail);
        headerEmail.setText(user.getEmail());
        ImageView headerImageView = headView.findViewById(R.id.headerImageView);
        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfileImage();
            }
        });

        if (user.getPhotoUrl() != null) {
            Log.d("realURL", user.getPhotoUrl().toString());
            Glide.with(mainDashboard.this)
                    .load(user.getPhotoUrl().toString())
                    .into(profileImage);
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("BuyyaPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String uids = pref.getString("IMPUID", "  ");
        String finalUID = "UID- " + "(" + uids.substring(0, 2) + ")(" + uids.substring(2, 5) + ")(" + uids.substring(5, 8)
                + ")(" + uids.substring(8, 11) + ")(" + uids.substring(11, 14) + ")";
        mainDashboardName.setText(user.getDisplayName());
        mainDashboardUID.setText(finalUID);



    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
          switch (menuItem.getItemId()){
              case R.id.navDashboard:
                  break;
              case R.id.navEditProfile:
                  Intent intent=new Intent(mainDashboard.this,editProfile.class);
                  startActivity(intent);
                  break;
              case R.id.navUploadDoc:
                  Intent intent1=new Intent(mainDashboard.this,uploadDocuments.class);
                  startActivity(intent1);
                  break;
              case R.id.navProfile:
                  Intent intent3=new Intent(mainDashboard.this,PinCodeActivity.class);
                  startActivity(intent3);
                  break;

              case R.id.navLogout:
                  mAuth.signOut();
                  SharedPreferences pref = getApplicationContext().getSharedPreferences("BuyyaPref", MODE_PRIVATE);
                  SharedPreferences.Editor editor = pref.edit();
                  editor.remove("IMPUID");
                  Intent intentLogout = new Intent(mainDashboard.this, MainActivity.class);
                  startActivity(intentLogout);
                  finish();

          }
          drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

    public void onSendRequest(View view) {
        Intent intent2=new Intent(mainDashboard.this,sendData.class);
        startActivity(intent2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfilePic = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfilePic);
                profileImage.setImageBitmap(bitmap);
                uploadImageToFirebase();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void userProfileImage() {
        Intent picIntent = new Intent();
        picIntent.setType("image/*");
        picIntent.setAction(picIntent.ACTION_GET_CONTENT);
        startActivityForResult(picIntent.createChooser(picIntent, "Select profile Image"), CHOOSE_IMAGE);
    }

    private void uploadImageToFirebase() {
        StorageReference mStorageRef = FirebaseStorage.getInstance().
                getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        if (uriProfilePic != null) {
            //show progress bar
            mStorageRef.putFile(uriProfilePic).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //hide progress Bar
                    downloadImageLink = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    saveImageAsUserImage();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //hide progress bar
                    Toast.makeText(mainDashboard.this, "unSuccesFull", Toast.LENGTH_LONG).toString();
                }
            });
        }


    }

    private void saveImageAsUserImage() {
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(downloadImageLink))
                .build();
        user.updateProfile(userProfileChangeRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        String pin = sharedPreferences.getString("KEY", null);
        Log.d("____", "onCreate: spref" + pin);

    }
    


}