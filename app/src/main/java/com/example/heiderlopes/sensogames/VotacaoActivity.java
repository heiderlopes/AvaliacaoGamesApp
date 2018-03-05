package com.example.heiderlopes.sensogames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heiderlopes.sensogames.api.GameAPI;
import com.example.heiderlopes.sensogames.api.NetworkHelper;
import com.example.heiderlopes.sensogames.model.Game;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VotacaoActivity extends AppCompatActivity {

    private TextView tvNomeJogo;
    private SeekBar sbUsabilidade;
    private MaterialRatingBar rbUsabilidade;

    private SeekBar sbRoteiro;
    private MaterialRatingBar rbRoteiro;

    private SeekBar sbGrafico;
    private MaterialRatingBar rbGrafico;

    private Button btVotar;

    private View loading;

    private Game game;

    private TextView tvMensagemLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacao);

        tvNomeJogo = findViewById(R.id.tvNomeJogo);

        loading = findViewById(R.id.loading);

        sbUsabilidade = findViewById(R.id.sbUsabilidade);
        rbUsabilidade = findViewById(R.id.rbUsabilidade);

        sbRoteiro = findViewById(R.id.sbRoteiro);
        rbRoteiro = findViewById(R.id.rbRoteiro);

        sbGrafico = findViewById(R.id.sbGrafico);
        rbGrafico = findViewById(R.id.rbGrafico);

        tvMensagemLoading = findViewById(R.id.tvMensagemLoading);

        btVotar = findViewById(R.id.btVotar);

        setListener();

        game = getIntent().getParcelableExtra("GAME");

        tvNomeJogo.setText(game.getNome());
        sbUsabilidade.setProgress(game.getPontuacaoUsabilidade());
        sbRoteiro.setProgress(game.getPontuacaoRoteiro());
        sbGrafico.setProgress(game.getPontuacaoGrafico());


        btVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
                tvMensagemLoading.setText("Realizando a votação. Aguarde!!!");
                GameAPI service = NetworkHelper.getRetrofit().create(GameAPI.class);

                game.setPontuacaoGrafico(sbGrafico.getProgress());
                game.setPontuacaoRoteiro(sbRoteiro.getProgress());
                game.setPontuacaoUsabilidade(sbUsabilidade.getProgress());

                service.votar(game).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(VotacaoActivity.this, "Votação realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        dismissProgressDialog();
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(VotacaoActivity.this, "Não foi possivel votar. Tente novamente.", Toast.LENGTH_SHORT).show();
                        dismissProgressDialog();
                    }
                });
            }
        });

    }

    private void showProgressDialog() {
        loading.setVisibility(View.VISIBLE);
    }

    private void dismissProgressDialog() {
        loading.setVisibility(View.GONE);
    }


    private void setListener() {
        sbUsabilidade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rbUsabilidade.setNumStars(i);
                rbUsabilidade.setRating(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbRoteiro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rbRoteiro.setNumStars(i);
                rbRoteiro.setRating(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbGrafico.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rbGrafico.setNumStars(i);
                rbGrafico.setRating(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
