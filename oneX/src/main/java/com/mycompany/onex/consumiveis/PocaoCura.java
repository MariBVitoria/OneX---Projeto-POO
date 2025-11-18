/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.consumiveis;

import com.mycompany.onex.personagem.Personagem;

public class PocaoCura implements Consumivel {
    private final String nome;
    private final int pontosDeCura;

    public PocaoCura(String nome, int pontosDeCura) {
        this.nome = nome;
        this.pontosDeCura = pontosDeCura;
    }

    @Override
    public String consumir(Personagem usuario) {
        usuario.receberCura(pontosDeCura);
        return usuario.getNome() + " usou " + this.nome + " e curou " + this.pontosDeCura + " de vida!";
    }

    @Override
    public String getNome() {
        return this.nome + " (Cura " + this.pontosDeCura + " HP)";
    }
    
    // Usado pelo JComboBox na GUI para exibir um nome simples
    @Override
    public String toString() {
        return getNome();
    }
}