

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.onex.armas;

/**
 * Arma para classes de combate físico, como Espadachim.
 */
public class Espada extends Arma {

    public Espada(String nome, int danoBase) {
        super(nome, danoBase, "FISICO");
    }

    @Override
    public String usar() {
        // Retorna a descrição da ação para o log de batalha
        return "ataca com sua " + this.nome;
    }
}