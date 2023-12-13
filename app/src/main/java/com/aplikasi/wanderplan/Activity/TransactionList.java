package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aplikasi.wanderplan.R;


public class TransactionList extends AppCompatActivity {

    Button btnHome;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionlist);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeDashboard.class);
                v.getContext().startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goBackToPreviousActivity();
            }
        });
    }
    private void goBackToPreviousActivity() {
        Intent intent = new Intent(this, HomeDashboard.class);
        startActivity(intent);
        finish();
    }
}