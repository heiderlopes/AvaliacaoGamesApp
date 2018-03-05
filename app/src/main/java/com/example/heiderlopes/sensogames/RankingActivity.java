package com.example.heiderlopes.sensogames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.heiderlopes.sensogames.adapter.GamesAdapter;
import com.example.heiderlopes.sensogames.api.GameAPI;
import com.example.heiderlopes.sensogames.api.NetworkHelper;
import com.example.heiderlopes.sensogames.listener.ItemClickListener;
import com.example.heiderlopes.sensogames.model.Game;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RankingActivity extends AppCompatActivity implements ItemClickListener {

    private GamesAdapter mAdapter;
    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        loading = findViewById(R.id.loading);

        setupToolbar();
        inicializaLista(new ArrayList<Game>());

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaGames();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void inicializaLista(List<Game> games) {
        RecyclerView rvMeusGames = findViewById(R.id.rvGames);

        mAdapter = new GamesAdapter(games);
        mAdapter.setClickListener(this);

        rvMeusGames.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMeusGames.setItemAnimator(new DefaultItemAnimator());
        rvMeusGames.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvMeusGames.setAdapter(mAdapter);
    }

    private void showProgressDialog() {
        loading.setVisibility(View.VISIBLE);
    }

    private void dismissProgressDialog() {
        loading.setVisibility(View.GONE);
    }

    private void carregaGames() {
        GameAPI service = NetworkHelper.getRetrofit().create(GameAPI.class);

        showProgressDialog();

        service.getRanking().enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                inicializaLista(response.body());
                dismissProgressDialog();

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Toast.makeText(RankingActivity.this,
                        "Não foi possível carregar os dados", Toast.LENGTH_SHORT).show();
                dismissProgressDialog();

            }
        });

        mAdapter.notifyDataSetChanged();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ranking, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                *//*List<Game> newGames = gameDao.findAllFields(newText);
                inicializaLista(newGames);*//*
                return true;
            }
        });
        return true;
    }*/

    @Override
    public void onClick(View view, int position) {
        final Game game = mAdapter.getItem(position);
        Intent votacao = new Intent(this, VotacaoActivity.class);
        votacao.putExtra("GAME", game);
        startActivity(votacao);
    }
}
