/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.armas;

/**
 * Arma para classes de longo alcance, como Arqueiro.
 */
public class Arco extends Arma {
    
    public Arco(String nome, int danoBase) {
        super(nome, danoBase, "FISICO");
    }

    @Override
    public String usar() {
        return "dispara uma flecha com seu " + this.nome;
    }
}
