package com.example.heiderlopes.sensogames.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable{

    private String id;
    private String nome;
    private int pontuacaoUsabilidade;
    private int pontuacaoGrafico;
    private int pontuacaoRoteiro;
    private int pontuacaoGeral;
    private boolean avaliado;

    protected Game(Parcel in) {
        id = in.readString();
        nome = in.readString();
        pontuacaoUsabilidade = in.readInt();
        pontuacaoGrafico = in.readInt();
        pontuacaoRoteiro = in.readInt();
        pontuacaoGeral = in.readInt();
        avaliado = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nome);
        dest.writeInt(pontuacaoUsabilidade);
        dest.writeInt(pontuacaoGrafico);
        dest.writeInt(pontuacaoRoteiro);
        dest.writeInt(pontuacaoGeral);
        dest.writeByte((byte) (avaliado ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacaoUsabilidade() {
        return pontuacaoUsabilidade;
    }

    public void setPontuacaoUsabilidade(int pontuacaoUsabilidade) {
        this.pontuacaoUsabilidade = pontuacaoUsabilidade;
    }

    public int getPontuacaoGrafico() {
        return pontuacaoGrafico;
    }

    public void setPontuacaoGrafico(int pontuacaoGrafico) {
        this.pontuacaoGrafico = pontuacaoGrafico;
    }

    public int getPontuacaoRoteiro() {
        return pontuacaoRoteiro;
    }

    public void setPontuacaoRoteiro(int pontuacaoRoteiro) {
        this.pontuacaoRoteiro = pontuacaoRoteiro;
    }

    public int getPontuacaoGeral() {
        return pontuacaoGeral;
    }

    public void setPontuacaoGeral(int pontuacaoGeral) {
        this.pontuacaoGeral = pontuacaoGeral;
    }

    public boolean isAvaliado() {
        return avaliado;
    }

    public void setAvaliado(boolean avaliado) {
        this.avaliado = avaliado;
    }
}
