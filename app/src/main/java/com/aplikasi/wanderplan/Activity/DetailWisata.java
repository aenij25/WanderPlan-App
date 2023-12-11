package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;

import com.aplikasi.wanderplan.R;

public class DetailWisata extends Activity {
    Button btnBuy;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailwisata);

        btnBuy = (Button) findViewById(R.id.btn_buyTicket);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BuyTicket.class);
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
        // Create an Intent to navigate back to the PreviousActivity
        Intent intent = new Intent(this, HomeDashboard.class);

        // If you want to pass data back to the previous activity, you can use putExtra here
        // intent.putExtra("key", "value");

        // Start the PreviousActivity
        startActivity(intent);

        // Finish the current activity (CurrentActivity)
        finish();
    }
}
