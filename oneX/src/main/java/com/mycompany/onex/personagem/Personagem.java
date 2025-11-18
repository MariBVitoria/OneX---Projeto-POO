/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.personagem;

import com.mycompany.onex.armas.Arma;
import com.mycompany.onex.consumiveis.Inventario;
import com.mycompany.onex.personagem.*;
/**
 * Classe abstrata base para todos os personagens.
 * (Refinada para incluir mais atributos e getters/setters para a GUI)
 * @author maria
 */
public abstract class Personagem implements HabilidadeEspecial, AtaqueBasico {
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int mana;
    protected int manaMaxima;
    protected int forca; // Afeta dano físico
    protected int inteligencia; // Afeta dano mágico
    protected int defesa;
    protected Arma arma;
    protected Inventario inventario;

    public Personagem(String nome, int vidaMaxima, int manaMaxima, int forca, int inteligencia, int defesa, Arma arma) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.manaMaxima = manaMaxima;
        this.mana = manaMaxima;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.defesa = defesa;
        this.arma = arma;
        this.inventario = new Inventario();
    }

    /**
     * Aplica dano ao personagem, considerando sua defesa.
     * @param dano O dano bruto recebido.
     */
    public void receberDano(int dano) {
        int danoReduzido = dano - this.defesa;
        if (danoReduzido < 1) {
            danoReduzido = 1; // Garante que sempre haja pelo menos 1 de dano
        }
        
        this.vida -= danoReduzido;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }
    
    public void receberCura(int cura) {
        this.vida += cura;
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }
    }
    
    public void receberMana(int mana) {
        this.mana += mana;
        if (this.mana > this.manaMaxima) {
            this.mana = this.manaMaxima;
        }
    }
    
    public boolean consumirMana(int custo) {
        if (this.mana >= custo) {
            this.mana -= custo;
            return true;
        }
        return false; // Mana insuficiente
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    // Métodos abstratos que as subclasses devem implementar
    @Override
    public abstract String atacar(Personagem alvo);

    @Override
    public abstract String usarHabilidade(Personagem alvo);

    // Getters - Essenciais para a GUI atualizar as barras de vida, etc.
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
}