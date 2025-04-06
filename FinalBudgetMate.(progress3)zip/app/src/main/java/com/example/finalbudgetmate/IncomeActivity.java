package com.example.finalbudgetmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class IncomeActivity extends AppCompatActivity {

    EditText amount,note;
    Button save,delete;
    TextView tvoutput;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        amount =  findViewById(R.id.amount);
        note = findViewById(R.id.note);
        save = (Button) findViewById(R.id.save);
        tvoutput = findViewById(R.id.tvoutput);
        delete = (Button) findViewById(R.id.delete);





        databaseHelper = new DatabaseHelper(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteData();
            }
        });














        }


     public void addRecord(View view)
    {

        DatabaseHelper db=new DatabaseHelper(this);

        String res=db.addincome(amount.getText().toString(),note.getText().toString());

        Toast.makeText(this,res, Toast.LENGTH_LONG).show();
        amount.setText("");
        note.setText("");


    }
}
