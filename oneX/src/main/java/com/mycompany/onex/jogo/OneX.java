/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.onex.jogo;

import com.mycompany.onex.personagem.Espadachim;
import com.mycompany.onex.armas.*;
import com.mycompany.onex.consumiveis.*;
import com.mycompany.onex.personagens.*;
import com.mycompany.onex.ui.TelaBatalha;

/**
 * Ponto de entrada da aplicação (Main).
 * @author maria
 */
public class OneX {

    public static void main(String[] args) {
        // --- Setup do Jogo ---
        
        // 1. Criar Armas
        Arma espadaHeroi = new Espada("Espada do Herói", 15);
        Arma cajadoMaligno = new Cajado("Cajado Sombrio", 18);
        Arma arcoElfico = new Arco("Arco Élfico", 14);

        // 2. Criar Personagens (Jogador 1 e Computador)
        // Você pode trocar 'new Espadachim' por 'new Mago' ou 'new Arqueiro' para testar
        Personagem jogador1 = new Espadachim("Herói Valente", espadaHeroi);
        Personagem computador = new Mago("Feiticeiro Negro", cajadoMaligno);

        // 3. Adicionar Itens ao Inventário
        jogador1.getInventario().adicionarItem(new PocaoCura("Poção de Cura Fraca", 30));
        jogador1.getInventario().adicionarItem(new PocaoCura("Poção de Cura Fraca", 30));
        jogador1.getInventario().adicionarItem(new PocaoMana("Poção de Mana", 25));
        
        computador.getInventario().adicionarItem(new PocaoCura("Poção de Cura Fraca", 30));

        // 4. Criar a lógica da Batalha
        Batalha batalha = new Batalha(jogador1, computador);

        // 5. Iniciar a Interface Gráfica (GUI)
        // Isso garante que a GUI rode na "Thread de Despacho de Eventos" do Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Passa a lógica da batalha para a tela
                new TelaBatalha(batalha).setVisible(true);
            }
        });
    }
}