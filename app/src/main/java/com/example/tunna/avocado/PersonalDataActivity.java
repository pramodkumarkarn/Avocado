package com.example.tunna.avocado;

import android.content.Intent;

import android.content.SharedPreferences;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SELECT_PICTURE = 0;

    private ImageView imageView;

    EditText nameEditText;

    EditText addressEditText;

    EditText zipcodeEditText;

    EditText phoneEditText;

    EditText heightEditText;

    EditText dateEditText;

    EditText genderEditText;

    Spinner spinner1 ;

    Spinner spinner2;

   // EditText genderAgeRange;

   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = getPath(data.getData());
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        // cursor.close();
        // Convert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }*/

    public void onClick(View view){

        Button buttonToSummary = (Button) findViewById(R.id.buttonToSummary);

        nameEditText = (EditText) findViewById(R.id.nameEditText);

        addressEditText = (EditText) findViewById(R.id.addressEditText);

        zipcodeEditText = (EditText) findViewById(R.id.zipcodeEditText);

        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        heightEditText = (EditText) findViewById(R.id.heightEditText);

        dateEditText = (EditText) findViewById(R.id.dateEditText);

        genderEditText = (EditText) findViewById(R.id.genderEditText);

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

     //   genderAgeRange = (EditText) findViewById(R.id.genderAgeRange);


        if(view.getId()== R.id.buttonToSummary){

        String gender = genderEditText.getText().toString().trim();

        if(!validate() ){

            Toast.makeText(this, "Fields cannot be left blank!!", Toast.LENGTH_SHORT).show();

        } else showSummary();

        }


    }

    public void showSummary(){

        spinner1.setOnItemSelectedListener(new MySpinnerClass());

        spinner2.setOnItemSelectedListener(new MySpinnerClass());

        String spinnerValue = spinner1.getSelectedItem().toString();

        String spinnerValue2 = spinner2.getSelectedItem().toString();



        Intent intent1 = new Intent(getApplicationContext(), SummaryActivity.class);

        intent1.putExtra("nameEditText", nameEditText.getText().toString());

        intent1.putExtra("addressEditText", addressEditText.getText().toString());

        intent1.putExtra("zipcodeEditText", zipcodeEditText.getText().toString());

        intent1.putExtra("phoneEditText", phoneEditText.getText().toString());

        intent1.putExtra("heightEditText", heightEditText.getText().toString());

        intent1.putExtra("dateEditText", dateEditText.getText().toString());

        intent1.putExtra("genderEditText", genderEditText.getText().toString());

        // intent1.putExtra("genderView", genderView.getText().toString());

        intent1.putExtra("getData",spinnerValue.toString());

        intent1.putExtra("getData2",spinnerValue2.toString());

       // intent1.putExtra("genderAgeRange", genderAgeRange.getText().toString());

        startActivity(intent1);

    }

   /* private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }*/

    private boolean validate(){

        if(nameEditText.getText().toString().trim().equals(""))

            return false;

        else if(phoneEditText.getText().toString().trim().equals(""))

            return false;

        else if(addressEditText.getText().toString().trim().equals(""))

            return false;

        else if(heightEditText.getText().toString().trim().equals(""))

            return false;

        else if(dateEditText.getText().toString().trim().equals(""))

            return false;

        else if(genderEditText.getText().toString().trim().equals(""))

            return false;

        else if(spinner1.getSelectedItem().toString().trim().equals("Select a race"))

            return false;

        else if(spinner2.getSelectedItem().toString().trim().equals("Interested in.."))

            return false;

        else return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_data);

        }

        /*


*/
}
