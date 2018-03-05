package com.example.heiderlopes.sensogames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.heiderlopes.sensogames.api.GameAPI;
import com.example.heiderlopes.sensogames.api.NetworkHelper;
import com.example.heiderlopes.sensogames.model.Health;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    //Tempo que nosso splashscreen ficará visivel
    private final int SPLASH_DISPLAY_LENGTH = 3500;

    private final int TOTAL_TENTATIVAS = 3;
    private final int totalTentativasRealizadas = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Executando o método que iniciará nossa animação
        carregar();
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash);
        anim.reset();

        //Pegando o nosso objeto criado no layout
        ImageView iv = findViewById(R.id.splash);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        GameAPI service = NetworkHelper.getRetrofit().create(GameAPI.class);

        service.checkHealth().enqueue(new Callback<Health>() {
            @Override
            public void onResponse(Call<Health> call, Response<Health> response) {
                Intent intent = new Intent(SplashActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashActivity.this.finish();
            }

            @Override
            public void onFailure(Call<Health> call, Throwable t) {
                if (totalTentativasRealizadas > TOTAL_TENTATIVAS) {
                    Toast.makeText(SplashActivity.this,
                            "Nao foi possível iniciar o aplicativo. Tente novamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    carregar();
                }
            }
        });

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Após o tempo definido irá executar a próxima tela
                Intent intent = new Intent(SplashActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);*/
    }
}
