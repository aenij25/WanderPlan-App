package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aplikasi.wanderplan.R;

    public class HomeDashboard extends Activity {

        ImageView imgWisata1, imgHotel1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            imgWisata1 = (ImageView) findViewById(R.id.imageView1);
            imgHotel1 = (ImageView) findViewById(R.id.imageView3);

            imgWisata1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailWisata.class);
                    v.getContext().startActivity(intent);
                }
            });

            imgHotel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailHotel.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
}
