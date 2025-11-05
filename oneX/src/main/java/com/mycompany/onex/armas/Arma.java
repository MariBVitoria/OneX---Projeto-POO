/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.armas;

/**
 * Classe abstrata base para todas as armas.
 * Define os atributos e comportamentos essenciais de uma arma.
 */
public abstract class Arma {
    protected String nome;
    protected int danoBase;
    protected String tipoDeDano; // "FISICO" ou "MAGICO"

    public Arma(String nome, int danoBase, String tipoDeDano) {
        this.nome = nome;
        this.danoBase = danoBase;
        this.tipoDeDano = tipoDeDano;
    }

    // Método que descreve o uso da arma, para o log de batalha.
    public abstract String usar();

    // Getters que serão usados pela lógica de batalha
    public String getNome() {
        return nome;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public String getTipoDeDano() {
        return tipoDeDano;
    }
}