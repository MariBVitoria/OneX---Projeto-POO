package com.mycompany.onex.personagem;

import com.mycompany.onex.armas.Arma;

public class Boss extends Personagem {

    public Boss(String nome, Arma arma) {
        // Atributos do Boss
        super(nome, 200, 200, 25, 10, arma, "/imagens/boss.png");
    }

    @Override
    public String atacar(Personagem alvo) {
        int dano = this.forca + this.arma.getDanoBase();
        alvo.receberDano(dano);
        return this.nome + " ESMAGA " + alvo.getNome() + " causando " + dano + " de dano!";
    }

    @Override
    public String usarHabilidade(Personagem alvo) {
        // Habilidade do Boss
        int custoMana = 40;
        if (this.consumirMana(custoMana)) {
            int dano = this.inteligencia * 3;
            alvo.receberDano(dano);
            return this.nome + " 'conjura Bola de Fogo' em " + alvo.getNome() + " causando " + dano + " de dano!";
        } else {
            return this.nome + " tenta usar magia, mas falha!";
        }
    }
}