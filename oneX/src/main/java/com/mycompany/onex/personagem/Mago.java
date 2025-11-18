/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.personagem;

import com.mycompany.onex.armas.Arma;
import com.mycompany.onex.personagem.Personagem;

/**
 * Classe Mago, focada em inteligência e dano mágico.
 */
public class Mago extends Personagem {

    public Mago(String nome, Arma arma) {
        // (Nome, Vida, Mana, Força, Inteligência, Defesa, Arma)
        super(nome, 80, 150, 5, 20, 5, arma);
    }

    @Override
    public String atacar(Personagem alvo) {
        // Dano é baseado na Inteligência + Dano da Arma
        int dano = this.inteligencia + this.arma.getDanoBase();
        alvo.receberDano(dano);
        
        return this.nome + " " + this.arma.usar() + " em " + alvo.getNome() + " causando " + dano + " de dano mágico!";
    }

    @Override
    public String usarHabilidade(Personagem alvo) {
        int custoMana = 30;
        if (this.consumirMana(custoMana)) {
            // "Bola de Fogo" - Dano alto
            int dano = (int) (this.inteligencia * 2.5);
            alvo.receberDano(dano);
            return this.nome + " conjura 'Bola de Fogo' em " + alvo.getNome() + " causando " + dano + " de dano em área!";
        } else {
            return this.nome + " tentou conjurar 'Bola de Fogo' mas não tem mana suficiente!";
        }
    }
}