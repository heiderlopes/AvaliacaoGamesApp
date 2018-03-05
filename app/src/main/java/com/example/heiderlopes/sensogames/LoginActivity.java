package com.example.heiderlopes.sensogames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;
    private Button btLogin;
    private TextView tvMensagemStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        btLogin = findViewById(R.id.btLogin);
        tvMensagemStatus = findViewById(R.id.tvMensagemStatus);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();
                if (login.equals("admin") && senha.equals("admin")) {
                    startActivity(new Intent(LoginActivity.this, RankingActivity.class));
                    finish();
                } else {
                    tvMensagemStatus.setText("Usuário ou senha inválido");
                }
            }
        });
    }
}
