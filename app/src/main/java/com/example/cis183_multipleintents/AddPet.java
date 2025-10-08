package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet extends AppCompatActivity
{
    Button btn_j_back;
    Intent intent_j_mainActivity;
    Intent intent_j_addPetType;
    EditText et_j_name;
    EditText et_j_age;
    Spinner sp_j_type;
    Button btn_j_add;
    TextView tv_j_addType;

    //we can use an array adapter for the spinner
    ArrayAdapter<String> adapter;

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
        btn_j_add  = findViewById(R.id.btn_v_add_addPet);
        et_j_age   = findViewById(R.id.et_v_add_age);
        et_j_name  = findViewById(R.id.et_v_add_name);
        sp_j_type  = findViewById(R.id.sp_v_add_type);
        intent_j_mainActivity = new Intent(AddPet.this, MainActivity.class);
        intent_j_addPetType = new Intent(AddPet.this, AddPetType.class);
        tv_j_addType = findViewById(R.id.tv_v_add_newPetType);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Pet.PetType.getAllPetTypes());
        sp_j_type.setAdapter(adapter);

        backButtonClickListener();
        addButtonClickListener();
        addNewTypeEventListener();
    }

    private void backButtonClickListener()
    {
        btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getPetDataFromTextBoxes();
                startActivity(intent_j_mainActivity);

            }
        });
    }

    private void addButtonClickListener()
    {
        btn_j_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getPetDataFromTextBoxes();
            }
        });
    }

    private void addNewTypeEventListener()
    {
        tv_j_addType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(intent_j_addPetType);
            }
        });
    }

    private void getPetDataFromTextBoxes()
    {
        Pet p = new Pet();
        p.setName(et_j_name.getText().toString());
        p.setType(sp_j_type.getSelectedItem().toString());
        p.setAge(Integer.parseInt(et_j_age.getText().toString()));

        intent_j_mainActivity.putExtra("PetData", p);
        startActivity(intent_j_mainActivity);
    }


}