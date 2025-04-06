package com.example.finalbudgetmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");
    // Removed unnecessary comments and improved regex pattern..
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");

    DatabaseHelper databaseHelper;
    EditText email, password;
    Button registerbutton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerbutton = findViewById(R.id.registerbutton);
        loginRedirectText = findViewById(R.id.loginRedirectText);



        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();

                if (user.isEmpty()) {
                    email.setError("Email cannot be empty ");
                } else if (!EMAIL_PATTERN.matcher(user).matches()) {
                    email.setError("Invalid email address");
                }

                if (user.isEmpty() || pass.isEmpty()) {
                    // Handle empty fields
                    Toast.makeText(getApplicationContext(), "Username and password are required", Toast.LENGTH_SHORT).show();
                } else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                    // Check for password strength
                    password.setError("Password too weak");
                } else {
                    boolean isInserted = databaseHelper.addUser(user, pass);
                    if (isInserted) {
                        // Registration success
                        Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        // You might want to navigate to a different activity here
                    } else {
                        // Registration failed
                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}

