package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aplikasi.wanderplan.R;

public class DetailHotel extends Activity {
    Button btnBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailhotel);
        btnBook = (Button) findViewById(R.id.btn_selectRoom);

        btnBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookRoom.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
