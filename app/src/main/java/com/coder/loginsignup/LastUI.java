package com.coder.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.coder.loginsignup.databinding.ActivityLastUiBinding;

public class LastUI extends AppCompatActivity {

    public int timeInterval = 4000;
    public long lastClickedTime = 0;
    private ActivityLastUiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLastUiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.coder.setOnClickListener(v -> onClicked());

        binding.coder.setOnLongClickListener(v -> {
            onLongPressed();
            return false;
        });

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

    public void onClicked(){
        Toast.makeText(this, "Long press to show or hide the text", Toast.LENGTH_SHORT).show();
    }

    public void onLongPressed(){
        if (binding.logical.getVisibility() == View.INVISIBLE){
            binding.logical.setVisibility(View.VISIBLE);
        } else {
            binding.logical.setVisibility(View.INVISIBLE);
        }
    }

}