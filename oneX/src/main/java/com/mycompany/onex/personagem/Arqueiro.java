/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.personagem;

import com.mycompany.onex.armas.Arma;

/**
 * Classe Arqueiro, balanceado.
 */
public class Arqueiro extends Personagem {

    public Arqueiro(String nome, Arma arma) {
        // (Nome, Vida, Mana, Força, Inteligência, Arma)
        super(nome, 100, 70, 12, 8, arma, "/imagens/arqueira.png");
    }

    @Override
    public String atacar(Personagem alvo) {
        // Dano é baseado na Força + Dano da Arma
        int dano = this.forca + this.arma.getDanoBase();
        alvo.receberDano(dano);

        return this.nome + " " + this.arma.usar() + " em " + alvo.getNome() + " causando " + dano + " de dano!";
    }

    @Override
    public String usarHabilidade(Personagem alvo) {
        int custoMana = 25;
        if (this.consumirMana(custoMana)) {
            // "Flecha Perfurante" - Ignora parte da defesa
            int dano = this.forca + this.arma.getDanoBase() + 15; // Dano bônus
            alvo.receberDano(dano);
            return this.nome + " usa 'Flecha Perfurante' em " + alvo.getNome() + " causando " + dano + " de dano!";
        } else {
            return this.nome + " tentou usar 'Flecha Perfurante' mas não tem mana suficiente!";
        }
    }
}