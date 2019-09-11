package com.bookapp.bookapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bookapp.bookapp.R;
import com.bookapp.bookapp.application.ApplicationClass;
import com.bookapp.bookapp.repository.DataRepository;
import com.bookapp.bookapp.utils.Constants;

public class LoginActivity extends AppCompatActivity {

    private DataRepository dataRepository;
    private EditText usernameET, passwordET;
    private Button registerBtn, loginBtn;
    private boolean isBtnPassEnabled, isBtnUserEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataRepository = DataRepository.getInstance(this, ApplicationClass.getInstance());

        initWidgets();
        onClickListeners();
        editUsernameTextListener();
        editPasswordTextListener();
        Toast.makeText(this, "daj bar 4-5 znakova za username i password", Toast.LENGTH_SHORT).show();

    }

    private void initWidgets(){
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);
    }

    private void editUsernameTextListener(){
        usernameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                registerBtn.setEnabled(true);
                loginBtn.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonEnabler(count, "user");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void editPasswordTextListener(){
        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonEnabler(start, "pass");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void onClickListeners(){

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                if (!dataRepository.getUsersFromDB(userName)) {
                    Toast.makeText(LoginActivity.this, "userName već postoji", Toast.LENGTH_SHORT).show();
                } else {
                    dataRepository.insertUserIntoDB(userName, password);
                    usernameET.setText("");
                    passwordET.setText("");
                    Toast.makeText(LoginActivity.this, "Hvala na registraciji, sad na login", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this, BooksActivity.class));
//                    Constants.user = dataRepository.getUsersFromDB(userName, password);
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                if (dataRepository.getUsersFromDB(userName, password).getUsername().equals(userName) && dataRepository.getUsersFromDB(userName, password).getPassword().equals(password)) {
                    Constants.user = dataRepository.getUsersFromDB(userName, password);
                    startActivity(new Intent(LoginActivity.this, BooksActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Krivi username ili password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void buttonEnabler(int count, String buttonName){
        if(showButtons(count, buttonName)){
            registerBtn.setEnabled(true);
            loginBtn.setEnabled(true);
            registerBtn.setAlpha(1f);
            loginBtn.setAlpha(1f);
        } else {
            registerBtn.setEnabled(false);
            registerBtn.setAlpha(0.4f);
            loginBtn.setEnabled(false);
            loginBtn.setAlpha(0.4f);
        }
    }

    private boolean showButtons(int count, String buttonName){
        if(buttonName.equals("pass")){
            if (count > 0){
                isBtnPassEnabled = true;
            } else {
                isBtnPassEnabled = false;
            }
        } else if (buttonName.equals("user")){
            if (count > -1 ){
                isBtnUserEnabled = true;
            } else {
                isBtnUserEnabled = false;
            }
        }
        return (isBtnUserEnabled && isBtnPassEnabled);
    }
}
