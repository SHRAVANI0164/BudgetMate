package com.example.finalbudgetmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class BudgetActivity extends MainActivity {
    EditText amount;
    Button save,delete;
    TextView tvoutput;
    DatabaseHelper databaseHelper;
    ExpenseActivity expenseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        amount = findViewById(R.id.amount);
        save = (Button) findViewById(R.id.save);
        tvoutput = findViewById(R.id.tvoutput);
        delete = (Button) findViewById(R.id.delete);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String am = amount.getText().toString();
                String to = databaseHelper.getsum();

                try {
                    double amountValue = Double.parseDouble(am);
                    double toValue = Double.parseDouble(to);

                    if (amountValue <= toValue) {

                        tvoutput.setText("You are Under Your Budget Limit");
                        tvoutput.setVisibility(View.VISIBLE);
                        tvoutput.setTextColor(Color.GREEN);

                        // Your code to handle when 'am' is less than or equal to 'to'.
                    } else {

                        tvoutput.setText("You are exceeding your budget limit, please try to save");
                        tvoutput.setVisibility(View.VISIBLE);
                        tvoutput.setTextColor(Color.RED);
                        // Your code to handle when 'am' is greater than 'to'.
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where 'am' or 'to' is not a valid number.
                }
            }
        });


        databaseHelper = new DatabaseHelper(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteDataB();
            }
        });

    }
    public void addbudget(View view) {

        DatabaseHelper db = new DatabaseHelper(this);

        String res = db.addbudget(amount.getText().toString());

        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        amount.setText("");






    }


}
