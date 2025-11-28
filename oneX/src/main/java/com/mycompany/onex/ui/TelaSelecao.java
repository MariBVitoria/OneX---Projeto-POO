package com.mycompany.onex.ui;
//Tela para fazer a seleção da Classe no jogo

//Importações do jogo
import com.mycompany.onex.jogo.*;
import com.mycompany.onex.armas.*;
import com.mycompany.onex.consumiveis.*;
import com.mycompany.onex.personagem.*;


//Importações para a Ui
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSelecao extends JFrame {

    public TelaSelecao() {
        setTitle("Escolha Sua Classe");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Criando botões
        JButton btnEspadachim = new JButton("Espadachim");
        JButton btnMago = new JButton("Mago");
        JButton btnArqueiro = new JButton("Arqueiro");

        // Adicionar na tela as opcoes

        add(new JLabel("Escolha sua classe:")); //
        add(btnEspadachim);
        add(btnMago);
        add(btnArqueiro);

        // Ação Escolheu Espadachim
        btnEspadachim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Criando e add as coisas ao personagem Espadachim
                Arma espada = new Espada ("Espada Celeste",15);
                Personagem espadachim = new Espadachim("Espadachim",espada);

                // 3. Inicia o jogo
                 iniciar_Batalha(espadachim); // Começa a partida pegando Espadachim

            }
        });

        // Ação Escolheu Mago
        btnMago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                Arma cajadoArcano = new Cajado("Cajado Antigo de Diamante",20);
                Personagem arcanista = new Mago("Arcanista",cajadoArcano);

                 iniciar_Batalha(arcanista); // Começa a partida pegando Arqcanista
            }
        });

        // Ação Escolheu Arqueiro
        btnArqueiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Arma espectroDaLuz = new Arco("Arco de Luz",30);
                Personagem arqueiro = new Arqueiro("Caçador",espectroDaLuz);
                 iniciar_Batalha(arqueiro);
            }
        });
    } // -> Fim do Metodo TelaSelecao

    // É aqui que a classe inicia realmente o jogo
    private void iniciar_Batalha(Personagem player){
        // Criando Cpu
        Arma cajadoBoss = new Cajado("Cajado Arcano",30);
        Personagem cpu = new Boss("Mago Negro",cajadoBoss);

        //adicionando itens ao inventário
        player.getInventario().adicionarItem(new PocaoCura("Poção Grande de Cura", 50));
        player.getInventario().adicionarItem(new PocaoMana("Mana Potion", 50));
        cpu.getInventario().adicionarItem(new PocaoCura("Cura do Vilão", 50));
        cpu.getInventario().adicionarItem(new PocaoMana("Mana Potion", 25));
        cpu.getInventario().adicionarItem(new PocaoCura("Cura do Boss", 50));


        Batalha batalha =  new Batalha(player,cpu); //Cria a Batalha

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaBatalha(batalha).setVisible(true);
            }
        });
        this.dispose();
    }


}