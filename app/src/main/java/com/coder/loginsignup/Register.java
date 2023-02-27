package com.coder.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.coder.loginsignup.databinding.ActivityRegisterBinding;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    public int timeInterval = 4000;
    public long lastClickedTime = 0;
    public static String phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        binding.registerBtn.setOnClickListener(v -> registerErrorsCatcher());

    }

    @Override
    public void onBackPressed() {

        if (lastClickedTime + timeInterval > System.currentTimeMillis()) {
            super.onBackPressed();

        } else {
            Toast.makeText(this, "Double tap back to exit!", Toast.LENGTH_SHORT).show();
            lastClickedTime = System.currentTimeMillis();
        }
    }

    public void registerErrorsCatcher() {

        if (Objects.requireNonNull(binding.editName.getText()).toString().length() < 3) {

            binding.editName.selectAll();
            binding.editName.requestFocusFromTouch();
            binding.editName.setError("Name must be including 3 letters or more");

        } else if (Objects.requireNonNull(binding.editPhone.getText()).toString().length() < 9) {

            binding.editPhone.selectAll();
            binding.editPhone.requestFocusFromTouch();
            binding.editPhone.setError("Phone number is not valid!");

        } else if (Objects.requireNonNull(binding.editPass.getText()).toString().length() < 6) {

            binding.editPass.selectAll();
            binding.editPass.setError("Enter password min 6 - max 16 character");
            binding.editPass.requestFocusFromTouch();

        } else if (!Objects.requireNonNull(binding.editConfirmPass.getText()).toString().equals(binding.editPass.getText().toString())) {

            binding.editConfirmPass.selectAll();
            binding.editConfirmPass.setError("Confirm password doesn't match with password");
            binding.editConfirmPass.requestFocusFromTouch();

        } else {

            Toast.makeText(this, "Registration completed succesfuly", Toast.LENGTH_SHORT).show();

            phone = binding.editPhone.getText().toString();
            password = binding.editPass.getText().toString();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

}

