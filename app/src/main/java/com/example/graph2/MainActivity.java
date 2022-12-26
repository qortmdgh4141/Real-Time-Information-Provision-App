package com.example.graph2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;

public class MainActivity extends AppCompatActivity {

    private TextView textview1, textview2;
    private Button read;
    private ImageView violenceimg, downimg;

    private String violence, down, safe1, safe2, safe3, human = "0";

    final private String vTitle = "상태 메시지";
    private String vContent;

    private NotificationHelper mNotificationhelper;

    private FirebaseStorage storage = FirebaseStorage.getInstance("gs://saem-29570.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read = findViewById(R.id.read);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        violenceimg = findViewById(R.id.violenceimg);
        downimg = findViewById(R.id.downimg);
        mNotificationhelper = new NotificationHelper(this);

        /**데베 연결 및 기능 작동**/
        read.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        violence = dataSnapshot.child("폭력감지").child("경고").getValue(String.class);
                        down = dataSnapshot.child("쓰러짐감지").child("경고").getValue(String.class);
                        safe1 = dataSnapshot.child("제한구역").child("경고").getValue(String.class);
                        safe2 = dataSnapshot.child("충돌방지").child("경고").getValue(String.class);
                        safe3 = dataSnapshot.child("안전보호구").child("경고").getValue(String.class);
                        human = dataSnapshot.child("인원수").child("인원수").getValue(String.class);


                        /*위험 단계에 따른 텍스트 출력*/
                        if(violence.equals("0") && down.equals("0")
                                && safe1.equals("0") && safe2.equals("0") && safe3.equals("0")){
                            textview1.setText("      현재 상황:  안전한 상황.");
                            violenceimg.setImageResource(R.drawable.safe);
                            vContent = "안전 상태";
                        }else{
                            textview1.setText("      현재 상황:  위험한 상황");
                            violenceimg.setImageResource(R.drawable.danger);
                            vContent = "위험 상태";
                            sendOnChannel1(vTitle, vContent);
                        }

                        textview2.setText(String.format("      현재 탐지되는 인원:  %s", human));
                    }
                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    }
                });
            }
        });

        downimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageRef = storage.getReference();

                storageRef.child("/capture.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Got the download URL for 'users/me/profile.png'
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .into(downimg);
                        Toast.makeText(getApplicationContext(),"sucess",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        Toast.makeText(getApplicationContext(),"fuck",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    /**알림 메시지 함수**/
    public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationhelper.getChannel1Notification(title,message);
        mNotificationhelper.getManger().notify(1, nb.build());
    }
}
