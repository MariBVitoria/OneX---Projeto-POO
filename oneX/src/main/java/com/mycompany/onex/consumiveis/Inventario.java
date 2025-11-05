/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.consumiveis;

import com.mycompany.onex.personagens.Personagem;
import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia os itens consumíveis de um personagem.
 */
public class Inventario {
    private List<Consumivel> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Consumivel item) {
        this.itens.add(item);
    }

    /**
     * Usa um item, aplica seu efeito e o remove do inventário.
     * @param item O item a ser usado.
     * @param usuario O personagem que está usando o item.
     * @return O log da ação.
     */
    public String usarItem(Consumivel item, Personagem usuario) {
        if (itens.contains(item)) {
            String log = item.consumir(usuario);
            itens.remove(item);
            return log;
        }
        return usuario.getNome() + " tentou usar um item que não possui!";
    }

    public List<Consumivel> getItens() {
        return this.itens;
    }
}