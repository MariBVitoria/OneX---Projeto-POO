/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.personagem;

import com.mycompany.onex.armas.Arma;
import com.mycompany.onex.consumiveis.Inventario;

public abstract class Personagem implements HabilidadeEspecial, AtaqueBasico {
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int mana;
    protected int manaMaxima;
    protected int forca;
    protected int inteligencia;
    protected Arma arma;
    protected Inventario inventario;
    protected String caminhoImagem; // Atributo para o Sprite


    public Personagem(String nome, int vidaMaxima, int manaMaxima, int forca, int inteligencia, Arma arma, String caminhoImagem) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.manaMaxima = manaMaxima;
        this.mana = manaMaxima;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.arma = arma;
        this.inventario = new Inventario();
        this.caminhoImagem = caminhoImagem;
    }


    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) { this.vida = 0; }
    }

    public void receberCura(int cura) {
        this.vida += cura;
        if (this.vida > this.vidaMaxima) { this.vida = this.vidaMaxima; }
    }

    public void receberMana(int mana) {
        this.mana += mana;
        if (this.mana > this.manaMaxima) { this.mana = this.manaMaxima; }
    }

    public boolean consumirMana(int custo) {
        if (this.mana >= custo) {
            this.mana -= custo;
            return true;
        }
        return false;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    @Override
    public abstract String atacar(Personagem alvo);

    @Override
    public abstract String usarHabilidade(Personagem alvo);

    // Getters.
    public String getNome() {
        return nome;
    }
    public int getVida() {
        return vida;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public int getMana() {
        return mana;
    }
    public int getManaMaxima() {
        return manaMaxima;
    }
    public Arma getArma() {
        return arma;
    }
    public Inventario getInventario() {
        return inventario;
    }
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
}