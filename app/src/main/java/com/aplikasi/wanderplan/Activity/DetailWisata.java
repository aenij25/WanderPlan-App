package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import com.aplikasi.wanderplan.R;

public class DetailWisata extends Activity {
    Button btnBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailwisata);

        btnBuy = (Button) findViewById(R.id.btn_buyTicket);

        btnBuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BuyTicket.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
