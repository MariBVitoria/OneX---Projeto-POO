/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.onex.consumiveis;

import com.mycompany.onex.personagens.Personagem;

/**
 * Interface para todos os itens que podem ser consumidos.
 */
public interface Consumivel {
    /**
     * Aplica o efeito do consumível no personagem.
     * @param usuario O personagem que está usando o item.
     * @return Uma string descrevendo a ação para o log de batalha.
     */
    String consumir(Personagem usuario);
    
    String getNome();
}