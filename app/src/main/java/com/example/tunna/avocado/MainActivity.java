package com.example.tunna.avocado;

import android.content.Context;

import android.content.Intent;

import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.view.animation.Animation;

import android.view.animation.AnimationUtils;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PREFERENCE_MODE_PRIVATE=0;

    private static final String MY_UNIQUE_PREFERENCE_FILE = "MyUniquePreferenceFile";

    private SharedPreferences preferenceSettingsUnique;

    private SharedPreferences.Editor preferenceEditorUnique;

    EditText emailText;

    EditText passwordText;

    public void onClick(View view){

        emailText = (EditText) findViewById(R.id.emailText);

        passwordText = (EditText) findViewById(R.id.passwordText);

        ImageButton imageButtonView = (ImageButton) findViewById(R.id.imageButtonView);

        preferenceSettingsUnique = getSharedPreferences(MY_UNIQUE_PREFERENCE_FILE , PREFERENCE_MODE_PRIVATE);

        preferenceEditorUnique = preferenceSettingsUnique.edit();

        preferenceEditorUnique.putString("emailEditText", emailText.getText() .toString());

        preferenceEditorUnique.commit();

        if(view.getId()== R.id.imageButtonView){

            if(emailText.getText().toString().trim().equals("") || passwordText.getText().toString().trim().equals("")){

                Toast.makeText(this, "Email or password cannot be left blank", Toast.LENGTH_LONG).show();

            }else
            //showSummary();

            personalDetails();

        }

    }

    public void personalDetails(){

        Intent intent = new Intent(getApplicationContext(), PersonalDataActivity.class);

        //intent.putExtra("emailText", emailText.getText().toString());

        startActivity(intent);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

}
