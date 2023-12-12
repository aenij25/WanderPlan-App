package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.aplikasi.wanderplan.R;

public class Payment extends Activity {

    ImageButton btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goBackToPreviousActivity(); }
        });

    }
    private void goBackToPreviousActivity() {

        Intent intent = new Intent(this, HomeDashboard.class);
        startActivity(intent);
        finish();

    }
}
