package com.example.finalbudgetmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class ExpenseActivity extends AppCompatActivity {

    TextInputLayout note, amount, category;
    FloatingActionButton fb;
    Button sbmt,totalexpense,delete;
    TextView tvoutput;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        note = (TextInputLayout) findViewById(R.id.nametext);
        amount = (TextInputLayout) findViewById(R.id.contacttext);
        category = (TextInputLayout) findViewById(R.id.emailtext);
        fb = (FloatingActionButton) findViewById(R.id.fbtn);
        sbmt = (Button) findViewById(R.id.sbmt_add);
        totalexpense = (Button) findViewById(R.id.totalexpense);
        tvoutput = findViewById(R.id.tvoutput);
        delete = (Button) findViewById(R.id.delete);


        databaseHelper = new DatabaseHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteDataE();
            }
        });




        for (int i = 0;i<0;i++){
            int amount = new Random().nextInt(100);
            databaseHelper.addrecord(String.valueOf(note),String.valueOf(amount),String.valueOf(category));
        }







        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert(note.getEditText().getText().toString(),
                        amount.getEditText().getText().toString(),
                        category.getEditText().getText().toString());
            }
        });

        totalexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  tvoutput.setText(String.format("Total Amount : %s",databaseHelper.getsum()));
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FetchData.class));
            }
        });


    }



    private void processinsert(String n, String c, String e) {
        String result1 = new DatabaseHelper(this).addrecord(n, c, e);
        note.getEditText().setText("");
        amount.getEditText().setText("");
        category.getEditText().setText("");
        Toast.makeText(getApplicationContext(), result1, Toast.LENGTH_LONG).show();




    }
}



