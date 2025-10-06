package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet extends AppCompatActivity
{
    Button btn_j_back;
    Intent intent_j_mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //this is code to get information passed from mainactivity.java
        //get the intent the called me
        Intent cameFrom = getIntent();
        //get the bundle that was passed to me from "cameFrom" intent
        Bundle infoPassedToMe = cameFrom.getExtras();

        //we do not know if infoPassedToMe will have any data
        //becuase we do not know who loaded this intent.
        if(infoPassedToMe != null)
        {
            String data = infoPassedToMe.getString("InfoPassed");
            Log.d("INFO PASSED FROM MAIN: ", data);
        }

        btn_j_back = findViewById(R.id.btn_v_addPet_back);
        intent_j_mainActivity = new Intent(AddPet.this, MainActivity.class);
        backButtonClickListener();
    }

    private void backButtonClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getPetDataFromTextBoxes();

            }
        });
    }

    private void getPetDataFromTextBoxes()
    {
        //get data from textboxes
        //make a pet
        //send pet to main
        //add pet to list
    }
}