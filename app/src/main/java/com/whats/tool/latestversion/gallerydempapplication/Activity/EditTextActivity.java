package com.whats.tool.latestversion.gallerydempapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.whats.tool.latestversion.gallerydempapplication.R;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        TextInputEditText editText = findViewById(R.id.txtPwd);
        Button btn = findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = editText.getText().toString().trim();
                if (isValidInput(userInput)) {
                    Intent i = new Intent(EditTextActivity.this, GridActivity.class);
                    i.putExtra("Position", Integer.parseInt(editText.getText().toString()));
                    startActivity(i);
                } else {
                    Toast.makeText(EditTextActivity.this, "Input must be a number between 3 and 12", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isValidInput(String input) {
        try {
            int numericInput = Integer.parseInt(input);
            return numericInput >= 3 && numericInput <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}