package com.coder.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.coder.loginsignup.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public int timeInterval = 4000;
    public long lastClickedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String phone = Register.phone;
        String password = Register.password;

        binding.SignUp.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, Register.class);
            startActivity(intent1);
            finish();
        });

        binding.loginBtn.setOnClickListener(v -> checkLogin(phone, password));

    }

    @Override
    public void onBackPressed() {

        if (lastClickedTime + timeInterval > System.currentTimeMillis()) {
            binding.editPhoneLogin.setText(null);
            binding.editPassLogin.setText(null);
            binding.editPassLogin.clearFocus();
            binding.editPhoneLogin.clearFocus();
            super.onBackPressed();

        } else {
            Toast.makeText(this, "Double tap back to exit!", Toast.LENGTH_SHORT).show();
            lastClickedTime = System.currentTimeMillis();
        }
    }


    public void checkLogin(String phone, String password){
        if (!Objects.requireNonNull(binding.editPhoneLogin.getText()).toString().equals(phone)) {

            binding.editPhoneLogin.setError("Phone number not found! Register before login!");
            binding.editPhoneLogin.selectAll();
            binding.editPhoneLogin.requestFocusFromTouch();

        } else if (binding.editPhoneLogin.getText().toString().length() < 9) {
            binding.editPassLogin.setError("Phone number is not valid!");
            binding.editPhoneLogin.selectAll();
            binding.editPassLogin.requestFocusFromTouch();

        } else if (!Objects.requireNonNull(binding.editPassLogin.getText()).toString().equals(password)) {
            binding.editPassLogin.setError("Incorrect password");
            binding.editPassLogin.selectAll();
            binding.editPassLogin.requestFocusFromTouch();
        } else {
            Intent intent2 = new Intent(this, LastUI.class);
            startActivity(intent2);
        }
    }

}