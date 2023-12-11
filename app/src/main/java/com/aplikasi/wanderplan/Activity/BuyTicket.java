package com.aplikasi.wanderplan.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.aplikasi.wanderplan.R;

public class BuyTicket extends AppCompatActivity {

    EditText etnama, etemail, etphone, etfrom, etTo, etquantity;

    Spinner sppayment;

    Button btbuyticket;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyticket);

        etnama = (EditText) findViewById(R.id.textInputEditText2);
        etemail = (EditText) findViewById(R.id.editText);
        etphone = (EditText) findViewById(R.id.editText2);
        etfrom = (EditText) findViewById(R.id.editTextDate);
        etTo = (EditText) findViewById(R.id.editTextDate2);
        etquantity = (EditText) findViewById(R.id.editTextNumber);
        sppayment = (Spinner) findViewById(R.id.spinner);
        btbuyticket = (Button) findViewById(R.id.btn_selectRoom2);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btbuyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nama, Email, Phone, From, To, quantity, Payment;

                Nama = etnama.getText().toString();
                Email = etemail.getText().toString();
                Phone = etphone.getText().toString();
                From = etfrom.getText().toString();
                To = etTo.getText().toString();
                quantity = etquantity.getText().toString();
                Payment = sppayment.getSelectedItem().toString();

                //validasi data
                if (Nama.isEmpty()) {
                    etnama.setError("Nama harus diisi");
                    etnama.requestFocus();
                }
                if (Email.isEmpty()) {
                    etemail.setError("Email harus diisi");
                    etemail.requestFocus();
                }
                if (Phone.isEmpty()) {
                    etphone.setError("Masukkan Nomor Handphone");
                    etphone.requestFocus();
                }
                if (From.isEmpty()) {
                    etfrom.setError(" ");
                    etfrom.requestFocus();
                }
                if (To.isEmpty()) {
                    etTo.setError(" ");
                    etTo.requestFocus();
                }
                if (quantity.isEmpty()) {
                    etquantity.setError(" ");
                    etquantity.requestFocus();
                }

                System.out.println("Nama : " + Nama);
                System.out.println("Email : " + Email);
                System.out.println("Phone Number : " + Phone);
                System.out.println("From " + From);
                System.out.println("To " + To);
                System.out.println("Qty " + quantity);
                System.out.println("Payment Method " + Payment);

                WisataForm wisataForm1 = new WisataForm();
                wisataForm1.setNama(Nama);
                wisataForm1.setEmail(Email);
                wisataForm1.setPhone(Phone);
                wisataForm1.setFrom(From);
                wisataForm1.setTo(To);
                wisataForm1.setQuantity(quantity);
                wisataForm1.setPayment(Payment);

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
        Intent intent = new Intent(this, DetailWisata.class);

        // If you want to pass data back to the previous activity, you can use putExtra here
        // intent.putExtra("key", "value");

        // Start the PreviousActivity
        startActivity(intent);

        // Finish the current activity (CurrentActivity)
        finish();
    }
}
