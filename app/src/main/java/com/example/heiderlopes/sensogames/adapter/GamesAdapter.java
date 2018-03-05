package com.example.heiderlopes.sensogames.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heiderlopes.sensogames.R;
import com.example.heiderlopes.sensogames.listener.ItemClickListener;
import com.example.heiderlopes.sensogames.model.Game;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {

    private List<Game> games;
    private ItemClickListener clickListener;

    public GamesAdapter(List<Game> games) {
        this.games = games;
    }

    public List<Game> getItems() {
        return games;
    }

    public Game getItem(int position) {
        return games.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvNomeJogo;
        public MaterialRatingBar rbUsabilidade, rbGrafico, rbRoteiro;


        public GameViewHolder(View view) {
            super(view);
            tvNomeJogo = view.findViewById(R.id.tvNomeJogo);
            rbUsabilidade = view.findViewById(R.id.rbUsabilidade);
            rbGrafico = view.findViewById(R.id.rbGrafico);
            rbRoteiro = view.findViewById(R.id.rbRoteiro);
            itemView.setOnClickListener(this); // bind the listener
        }


        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game_lista, parent, false);

        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = games.get(position);
        holder.tvNomeJogo.setText(game.getNome());

        holder.rbGrafico.setNumStars(game.getPontuacaoGrafico());
        holder.rbRoteiro.setNumStars(game.getPontuacaoRoteiro());
        holder.rbUsabilidade.setNumStars(game.getPontuacaoUsabilidade());

        holder.rbGrafico.setRating(game.getPontuacaoGrafico());
        holder.rbRoteiro.setRating(game.getPontuacaoRoteiro());
        holder.rbUsabilidade.setRating(game.getPontuacaoUsabilidade());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}