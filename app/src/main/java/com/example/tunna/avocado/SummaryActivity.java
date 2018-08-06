package com.example.tunna.avocado;

import android.app.Activity;

//import android.content.Context;

import android.content.Intent;

import android.content.SharedPreferences;

import android.database.Cursor;

//import android.graphics.Bitmap;

//import android.graphics.BitmapFactory;

//import android.net.ConnectivityManager;

//import android.net.NetworkInfo;

import android.net.Uri;

import android.os.AsyncTask;

import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

//import android.widget.EditText;

//import android.widget.ImageButton;

import android.widget.ImageView;

import android.widget.TextView;

import android.widget.Toast;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

//import java.net.HttpURLConnection;

//import java.net.URI;

public class SummaryActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 0;

    private ImageView profilePicture;

    private static final int PREFERENCE_MODE_PRIVATE = 0;

    private static final String MY_UNIQUE_PREFERENCE_FILE = "MyUniquePreferenceFile";

    private SharedPreferences preferenceSettingsUnique;

    private SharedPreferences.Editor preferenceEditorUnique;

    SharedPreferences settings;

    TextView emailTextDisplay;

    TextView name1View;

    TextView address1View;

    TextView phone1View;

    TextView dob1View;

    TextView height1View;

    TextView race1View;

    TextView genderView;

    TextView interestedGender;

    PersonDataActivity person;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_summary);

        emailTextDisplay = (TextView) findViewById(R.id.emailTextDisplay);

        name1View = (TextView) findViewById(R.id.name1View);

        address1View = (TextView) findViewById(R.id.address1View);

        phone1View = (TextView) findViewById(R.id.phone1View);

        dob1View = (TextView) findViewById(R.id.dob1View);

        height1View = (TextView) findViewById(R.id.height1View);

        race1View = (TextView) findViewById(R.id.race1View);

        genderView = (TextView) findViewById(R.id.genderView);

        interestedGender = (TextView) findViewById(R.id.interestedGender);

        Intent intent2 = getIntent();

        Bundle personal_data = intent2.getExtras();

        //String email_id= personal_data.getString("emailText");

        //emailTextDisplay.setText(email_id);

        String name = personal_data.getString("nameEditText");

        name1View.setText(name);

        String address = personal_data.getString("addressEditText");

        address1View.setText(address);

        String phone = personal_data.getString("phoneEditText");

        phone1View.setText(phone);

        String dob = personal_data.getString("dateEditText");

        dob1View.setText(dob);

        String height = personal_data.getString("heightEditText");

        height1View.setText(height);

        preferenceSettingsUnique = getSharedPreferences(MY_UNIQUE_PREFERENCE_FILE, PREFERENCE_MODE_PRIVATE);

        String emailText = preferenceSettingsUnique.getString("emailEditText", "xyz");

        emailTextDisplay.setText(emailText);

        // settings = getSharedPreferences("MYPREFS", 0);

        //race1View.setText(settings.getInt("VALUE", 0));

        //String data=personal_data.getString("spinnerValue");

        //race1View.setText(data);

        String value = getIntent().getStringExtra("getData");

        race1View.setText(value);

        String gender = personal_data.getString("genderEditText");

        genderView.setText(gender);

        String genderValue = getIntent().getStringExtra("getData2");

        interestedGender.setText(genderValue);

    }

    public void onClick(View view) {

        if (view.getId() == R.id.uploadButton) {

            openImageChooser();

        } else if (view.getId() == R.id.registerButton) {

            new HttpAsyncTask().execute("https://external.dev.pheramor.com");

            }

    }

    //Choose an image from Gallery

    void openImageChooser() {

        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {

                // Get the url from data

                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {

                    // Get the path from the Uri

                    String path = getPathFromURI(selectedImageUri);

                    ((ImageView) findViewById(R.id.profilePicture)).setImageURI(selectedImageUri);

                }

            }

        }
    }

        public String getPathFromURI (Uri contentUri){

            String res = null;

            String[] proj = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);

            if (cursor.moveToFirst()) {

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                res = cursor.getString(column_index);

            }

            cursor.close();

            return res;
        }


        public static String POST (String url, PersonDataActivity person){

        InputStream inputStream = null;

            String result = "";

            try {

                // creating HttpClient

                HttpClient httpclient = new DefaultHttpClient();

                // making POST request to the given URL

                HttpPost httpPost = new HttpPost(url);

                String json = "";

                // building jsonObject

                JSONObject jsonObject = new JSONObject();

                jsonObject.accumulate("name", person.getName());

                jsonObject.accumulate("phone", person.getPhone());

                jsonObject.accumulate("address", person.getAddress());

                jsonObject.accumulate("gender", person.getGender());

                jsonObject.accumulate("dateOfBirth", person.getDateOfBirth());

                jsonObject.accumulate("height", person.getHeight());

                jsonObject.accumulate("race", person.getRace());

                jsonObject.accumulate("InterestedIn", person.getInterestedIn());

                // convert JSONObject to JSON to String

                json = jsonObject.toString();

                //setting json to StringEntity

                StringEntity se = new StringEntity(json);

                //setting httpPost Entity

                httpPost.setEntity(se);

                // Setting some headers to inform server about the type of the content

                httpPost.setHeader("Accept", "application/json");

                httpPost.setHeader("Content-type", "application/json");

                // Executing POST request to the given URL

                HttpResponse httpResponse = httpclient.execute(httpPost);

                // receiving response as inputStream

                inputStream = httpResponse.getEntity().getContent();

                Log.i("Response", String.valueOf(httpResponse.getStatusLine()));

                //Log.i("inputStream", convertInputStreamToString(inputStream));

                // converting inputstream to string

                if (inputStream != null){

                    result = convertInputStreamToString(inputStream);

                    Log.i("message", "hello");

                    //Log.d("response", result);

                }

                else
                    result = "Did not work!";

                // Log.i("response", result);

            } catch (Exception e) {

                Log.d("InputStream", e.getLocalizedMessage());

            }

            // 11. return result

            return result;

    }


        private class HttpAsyncTask extends AsyncTask<String, Void, String> {

            @Override

            protected String doInBackground(String... urls) {

                person = new PersonDataActivity();

                person.setName(name1View.getText().toString());

                person.setPhone(phone1View.getText().toString());

                person.setAddress(address1View.getText().toString());

                person.setHeight(height1View.getText().toString());

                person.setGender(genderView.getText().toString());

                person.setPhone(dob1View.getText().toString());

                person.setRace(race1View.getText().toString());

                person.setInterestedIn(interestedGender.getText().toString());

                return POST(urls[0], person);

            }

            // onPostExecute displaying the results of the AsyncTask.

            @Override
            protected void onPostExecute(String result) {

                Toast.makeText(getBaseContext(),"Registered Successfully", Toast.LENGTH_LONG).show();

            }

    }

    //validations

        private static String convertInputStreamToString (InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            String result = "";

            while ((line = bufferedReader.readLine()) != null)

                result += line;

            inputStream.close();

            return result;

        }
    }

