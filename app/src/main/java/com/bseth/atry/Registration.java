package com.bseth.atry;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Registration extends AppCompatActivity{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;


    private Button regis;
    Button back;

    private static final String REGISTER_URL = "http://www.trys.16mb.com/insert.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.use);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        regis = (Button) findViewById(R.id.regis);
        back = (Button) findViewById(R.id.back);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });




    } private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();

        register(name,username,password,email);
    }

    private void register(String name, String username, String password, String email) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registration.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,email);
    }
}