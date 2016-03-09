package com.parku.au.janetpark.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parku.au.janetpark.R;
import com.parku.au.janetpark.network.RetroClient;
import com.parku.au.janetpark.network.model.User;
import com.parku.au.janetpark.util.EncryptPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername, etPassword;
    private TextView tvSignup;
    private Button btnLogin;
    private ImageView ivInstruction;

    private String strUsername, strPassword, encryptedPassword;

    private RetroClient retroClient;
    private EncryptPassword encryptPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.username_editText);
        etPassword = (EditText) findViewById(R.id.password_editText);

        btnLogin = (Button) findViewById(R.id.login_button);
        btnLogin.setOnClickListener(this);

        tvSignup = (TextView)findViewById(R.id.signup_textview);
        tvSignup.setOnClickListener(this);

        ivInstruction = (ImageView)findViewById(R.id.instruction_imageView);
        ivInstruction.setOnClickListener(this);



        retroClient = new RetroClient();
        encryptPassword = new EncryptPassword();
    }

    @Override
    public void onClick(View v) {

        // On click login button
        if (v.getId() == R.id.login_button) {

            // Pass input data to a variable
            strUsername = etUsername.getText().toString();
            strPassword = etPassword.getText().toString();
            encryptedPassword = encryptPassword.computeSHAHash(strPassword);

            // Check input data
            if (strUsername.isEmpty()) {
                etUsername.setError("Enter Username");
            }

            if (strPassword.isEmpty()) {
                etPassword.setError("Enter Password");
            }

            if(!strUsername.isEmpty() && !strPassword.isEmpty()) {
                loginUser();
            }

            // On click Signup button
        } else if (v.getId() == R.id.signup_textview) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.instruction_imageView){
            Intent intent = new Intent(this, InstructionsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginUser() {

        User user = new User();
        user.setUsername(strUsername);
        user.setPassword(encryptedPassword);

        retroClient.getApiService().loginUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User user = response.body();

                if (user != null) {

                    Toast.makeText(LoginActivity.this, "Username = " + user.getUsername() + "Password = " + user.getPassword() + "Email = " + user.getEmail() + "Contact = " + user.getContactNumber(), Toast.LENGTH_SHORT).show();

                    //passing the class object using serializable to the HomeActivity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userDetails",user);
                    intent.putExtras(bundle);
                    startActivity(intent);





                } else {

                    Toast.makeText(LoginActivity.this, "Username or Password is not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("", "ON FAILURE!!!!");
                Toast.makeText(LoginActivity.this, "ERROR !!! " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

}
