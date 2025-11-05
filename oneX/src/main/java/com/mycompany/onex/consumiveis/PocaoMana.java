/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.consumiveis;

import com.mycompany.onex.personagens.Personagem;

public class PocaoMana implements Consumivel {
    private final String nome;
    private final int pontosDeMana;

    public PocaoMana(String nome, int pontosDeMana) {
        this.nome = nome;
        this.pontosDeMana = pontosDeMana;
    }

    @Override
    public String consumir(Personagem usuario) {
        usuario.receberMana(pontosDeMana);
        return usuario.getNome() + " usou " + this.nome + " e recuperou " + this.pontosDeMana + " de mana!";
    }

    @Override
    public String getNome() {
        return this.nome + " (Recupera " + this.pontosDeMana + " MP)";
    }
    
    @Override
    public String toString() {
        return getNome();
    }
}