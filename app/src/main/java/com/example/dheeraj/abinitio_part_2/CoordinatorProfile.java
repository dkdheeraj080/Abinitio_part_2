package com.example.dheeraj.abinitio_part_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CoordinatorProfile extends AppCompatActivity {
    private TextView Coordinator;
    private Button Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_profile);
        Coordinator=findViewById(R.id.tvCoordinator);
        Logout=findViewById(R.id.btnLogout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoordinatorProfile.this,MainActivity.class));
                finish();
            }
        });

    }
}
