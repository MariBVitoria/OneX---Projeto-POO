/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.onex.jogo;

import com.mycompany.onex.personagem.*;
import com.mycompany.onex.armas.*;
import com.mycompany.onex.ui.*; // Importando todas as interfaces do jogo

import java.awt.*;


/**
 * Ponto de entrada da aplicação (Main).
 * @author maria
 */
public class OneX {

    public static void main(String[] args) {
        // --- Setup do Jogo ---
      //Inicia A classe TeleSelecao
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new TelaSelecao().setVisible(true);
            }
        });


    }
}