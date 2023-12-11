package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aplikasi.wanderplan.R;

public class BookRoom extends AppCompatActivity {

    EditText etNama, etEmail, etNoHp, etDate, etDate2,etQty;
    Spinner spRoom, spPayment;

    Button btConfirm;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookroom);

        etNama = (EditText) findViewById(R.id.textInputEditText);
        etEmail = (EditText) findViewById(R.id.editText);
        etNoHp = (EditText) findViewById(R.id.editText2);
        etDate = (EditText) findViewById(R.id.editTextDate);
        etDate2 = (EditText) findViewById(R.id.editTextDate2);
        etQty = (EditText) findViewById(R.id.editTextNumber);
        spRoom = (Spinner) findViewById(R.id.spinner2);
        spPayment = (Spinner) findViewById(R.id.spinner);
        btConfirm = (Button) findViewById(R.id.btn_selectRoom2);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, email, noHp, date, date2, Qty, room, payment;

                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                noHp = etNoHp.getText().toString();
                date = etDate.getText().toString();
                date2 = etDate2.getText().toString();
                Qty = etQty.getText().toString();
                room = spRoom.getSelectedItem().toString();
                payment = spPayment.getSelectedItem().toString();

                //validasi data
                if (nama.isEmpty()) {
                    etNama.setError("Nama harus diisi");
                    etNama.requestFocus();
                }
                if (email.isEmpty()) {
                    etEmail.setError("Email harus diisi");
                    etEmail.requestFocus();
                }
                if (noHp.isEmpty()) {
                    etNoHp.setError("Masukkan Nomor Handphone");
                    etNoHp.requestFocus();
                }
                if (date.isEmpty()) {
                    etDate.setError("  ");
                    etDate.requestFocus();
                }
                if (date2.isEmpty()) {
                    etDate2.setError(" ");
                    etDate2.requestFocus();
                }
                if (Qty.isEmpty()) {
                    etQty.setError(" ");
                    etQty.requestFocus();
                }

                System.out.println("Nama : " + nama);
                System.out.println("Email : " + email);
                System.out.println("Phone Number : " + noHp);
                System.out.println("From " + date);
                System.out.println("To " + date2);
                System.out.println("Qty " + Qty);
                System.out.println("Room Type  " + room);
                System.out.println("Payment Method " + payment);

                HotelForm hotelForm1 = new HotelForm();
                hotelForm1.setNama(nama);
                hotelForm1.setEmail(email);
                hotelForm1.setNoHp(noHp);
                hotelForm1.setDate(date);
                hotelForm1.setDate2(date2);
                hotelForm1.setQty(Qty);
                hotelForm1.setRoom(room);
                hotelForm1.setPayment(payment);

                Toast.makeText(getApplicationContext(), "Proses sedang berjalan", Toast.LENGTH_SHORT).show();
            }
        });


//        btnNext = (Button) findViewById(R.id.btn_next);
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), HomeDashboard.class);
//                v.getContext().startActivity(intent);
//            }
//        });
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goBackToPreviousActivity();
            }
        });
    }
    private void goBackToPreviousActivity() {
        // Create an Intent to navigate back to the PreviousActivity
        Intent intent = new Intent(this, DetailHotel.class);

        // If you want to pass data back to the previous activity, you can use putExtra here
        // intent.putExtra("key", "value");

        // Start the PreviousActivity
        startActivity(intent);

        // Finish the current activity (CurrentActivity)
        finish();
    }
}
