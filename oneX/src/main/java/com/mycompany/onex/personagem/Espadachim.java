/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.personagem;

import com.mycompany.onex.armas.Arma;
import com.mycompany.onex.personagem.Personagem;

/**
 *
 * @author maria
 */
public class Espadachim extends Personagem {
    
    public Espadachim(String nome, Arma arma) {
        // Construtor com atributos balanceados para um Espadachim
        // (Nome, Vida, Mana, Força, Inteligência, Defesa, Arma)
        super(nome, 120, 50, 15, 5, 10, arma,"/imagens/espadachim.png");
    }

    @Override
    public String atacar(Personagem alvo) {
        // Dano é baseado na Força + Dano da Arma
        int dano = this.forca + this.arma.getDanoBase();
        alvo.receberDano(dano);
        
        // Retorna a string para o log de batalha
        return this.nome + " " + this.arma.usar() + " em " + alvo.getNome() + " causando " + dano + " de dano!";
    }

    @Override
    public String usarHabilidade(Personagem alvo) {
        int custoMana = 20;
        if (this.consumirMana(custoMana)) {
            // "Corte Furioso" - Dano aumentado
            int dano = (int) ((this.forca + this.arma.getDanoBase()) * 1.8);
            alvo.receberDano(dano);
            return this.nome + " usa 'Corte Furioso' em " + alvo.getNome() + " causando " + dano + " de dano devastador!";
        } else {
            return this.nome + " tentou usar 'Corte Furioso' mas não tem mana suficiente!";
        }
    }
}