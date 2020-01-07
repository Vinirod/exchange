package br.com.module.exchange.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.module.exchange.R;
import br.com.module.exchange.utils.VersionUtils;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mLayUser;
    private TextInputEditText mEdtUser;
    private TextView mTxtUserRequired;
    private TextInputLayout mLayPassword;
    private TextInputEditText mEdtPassword;
    private TextView mTxtPasswordRequired;
    private Button mBtnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        VersionUtils.checkVersionAndEnableTransition(getWindow());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        bindAll();
        signIn();
        toDefineVisibilityOnView();
    }

    private void toDefineVisibilityOnView() {
        this.mTxtUserRequired.setVisibility(View.GONE);
        this.mTxtPasswordRequired.setVisibility(View.GONE);
    }

    private void signIn() {
        this.mBtnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdtUser.getText().toString().isEmpty())
                    mTxtUserRequired.setVisibility(View.VISIBLE);

                if(mEdtPassword.getText().toString().isEmpty())
                    mTxtPasswordRequired.setVisibility(View.VISIBLE);

                if(!mEdtUser.getText().toString().isEmpty() && !mEdtPassword.getText().toString().isEmpty()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void bindAll() {
        mLayUser = (TextInputLayout) findViewById(R.id.idLayUser);
        mEdtUser = (TextInputEditText) findViewById(R.id.idEdtUser);
        mTxtUserRequired = (TextView) findViewById(R.id.idTxtUserRequired);
        mLayPassword = (TextInputLayout) findViewById(R.id.idLayPassword);
        mEdtPassword = (TextInputEditText) findViewById(R.id.idEdtPassword);
        mTxtPasswordRequired = (TextView) findViewById(R.id.idTxtPasswordRequired);
        mBtnLogIn = (Button) findViewById(R.id.idBtnLogIn);
    }
}
