package com.tobar.woke.woke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartingActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                Intent toSignInIntent = new Intent(StartingActivity.this, LoginActivity.class);
                startActivity(toSignInIntent);
                break;
            case R.id.getStartedButton:
                Intent toSignUpIntent = new Intent(StartingActivity.this, LoginActivity.class);
                startActivity(toSignUpIntent);
                break;
            
        }

    }
}
