package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.aplikasi.wanderplan.R;

public class Payment extends Activity {

    ImageButton btnBack;

    Button btnPay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btnPay = findViewById(R.id.btn_selectRoom2);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goBackToPreviousActivity(); }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TransactionList.class);
                v.getContext().startActivity(intent);
            }
        });

    }
    private void goBackToPreviousActivity() {

        Intent intent = new Intent(this, HomeDashboard.class);
        startActivity(intent);
        finish();

    }
}
