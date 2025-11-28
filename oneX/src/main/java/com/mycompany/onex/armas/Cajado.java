/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.armas;

/**
 * Arma para classes mágicas, como Mago.
 */
public class Cajado extends Arma {

    public Cajado(String nome, int danoBase) {
        super(nome, danoBase, "MAGICO");
    }

    @Override
    public String usar() {
        return "conjura um feitiço com seu " + this.nome;
    }
}