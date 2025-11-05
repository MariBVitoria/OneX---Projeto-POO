/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.ui;

import com.mycompany.onex.consumiveis.Consumivel;
import com.mycompany.onex.jogo.Batalha;
import com.mycompany.onex.personagens.Personagem;
import com.mycompany.onex.armas.Arma;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

/**
 * A "View" (Visão) - Interface Gráfica (GUI) da Batalha.
 * Construída com Java Swing (JFrame).
 */
public class TelaBatalha extends javax.swing.JFrame {

    private Batalha batalha; // O "Controller"

    /**
     * Creates new form TelaBatalha
     */
    public TelaBatalha(Batalha batalha) {
        this.batalha = batalha;
        initComponents(); // Método gerado pelo NetBeans para desenhar a tela
        configurarBatalhaInicial();
    }

    /**
     * Configura os nomes, barras de vida e inventário no início do jogo.
     */
    private void configurarBatalhaInicial() {
        this.setTitle("Batalha de POO - " + batalha.getJogador1().getNome() + " vs " + batalha.getComputador().getNome());
        
        // Nomes
        labelNomeJogador.setText(batalha.getJogador1().getNome());
        labelNomeComputador.setText(batalha.getComputador().getNome());
        
        // Atualiza a UI (barras, texto, etc.)
        atualizarUI();
        
        // Carrega o inventário do jogador
        atualizarInventario();
        
        // Adiciona um log inicial
        logBatalha.append("A batalha começou! " + batalha.getJogador1().getNome() + " enfrenta " + batalha.getComputador().getNome() + ".\n");
    }
    
    /**
     * Atualiza todos os componentes visuais (barras de vida, mana, etc.)
     */
    private void atualizarUI() {
        Personagem p1 = batalha.getJogador1();
        Personagem p2 = batalha.getComputador();

        // --- Jogador 1 ---
        barraVidaJogador.setMaximum(p1.getVidaMaxima());
        barraVidaJogador.setValue(p1.getVida());
        labelVidaJogador.setText("HP: " + p1.getVida() + "/" + p1.getVidaMaxima());
        
        barraManaJogador.setMaximum(p1.getManaMaxima());
        barraManaJogador.setValue(p1.getMana());
        labelManaJogador.setText("MP: " + p1.getMana() + "/" + p1.getManaMaxima());

        // --- Computador ---
        barraVidaComputador.setMaximum(p2.getVidaMaxima());
        barraVidaComputador.setValue(p2.getVida());
        labelVidaComputador.setText("HP: " + p2.getVida() + "/" + p2.getVidaMaxima());
        
        barraManaComputador.setMaximum(p2.getManaMaxima());
        barraManaComputador.setValue(p2.getMana());
        labelManaComputador.setText("MP: " + p2.getMana() + "/" + p2.getManaMaxima());
        
        // Habilita/Desabilita botões baseado em quem é o turno
        boolean turnoJogador = batalha.isTurnoDoJogador();
        botaoAtacar.setEnabled(turnoJogador);
        botaoHabilidade.setEnabled(turnoJogador);
        botaoUsarItem.setEnabled(turnoJogador);
        comboInventario.setEnabled(turnoJogador);
    }
    
    /**
     * Atualiza a lista de itens no ComboBox (dropdown) do inventário.
     */
    private void atualizarInventario() {
        // Cria um modelo de dados para o ComboBox
        DefaultComboBoxModel<Consumivel> model = new DefaultComboBoxModel<>();
        for (Consumivel item : batalha.getJogador1().getInventario().getItens()) {
            model.addElement(item);
        }
        comboInventario.setModel(model);
    }
    
    /**
     * Adiciona uma linha de texto ao log de batalha.
     */
    private void adicionarLog(String mensagem) {
        logBatalha.append(mensagem + "\n");
        // Auto-scroll para o final
        logBatalha.setCaretPosition(logBatalha.getDocument().getLength());
    }
    
    /**
     * Chamado após uma ação do jogador para executar o turno do computador.
     */
    private void executarTurnoComputador() {
        // Desabilita botões enquanto o PC "pensa"
        atualizarUI(); 
        
        String logComputador = batalha.executarTurnoComputador();
        adicionarLog(logComputador);
        
        // Atualiza UI após ação do PC
        atualizarUI();
        verificarFimDeJogo();
    }
    
    /**
     * Verifica se a batalha terminou e desabilita os botões.
     */
    private String verificarFimDeJogo() {
        String resultado = batalha.verificarFimDeJogo();
        if (resultado != null) {
            adicionarLog("------------------------------------");
            adicionarLog(resultado.toUpperCase());
            
            // Desabilita todas as ações
            botaoAtacar.setEnabled(false);
            botaoHabilidade.setEnabled(false);
            botaoUsarItem.setEnabled(false);
            comboInventario.setEnabled(false);
        }
        return resultado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelJogador = new javax.swing.JPanel();
        labelNomeJogador = new javax.swing.JLabel();
        barraVidaJogador = new javax.swing.JProgressBar();
        labelVidaJogador = new javax.swing.JLabel();
        barraManaJogador = new javax.swing.JProgressBar();
        labelManaJogador = new javax.swing.JLabel();
        painelComputador = new javax.swing.JPanel();
        labelNomeComputador = new javax.swing.JLabel();
        barraVidaComputador = new javax.swing.JProgressBar();
        labelVidaComputador = new javax.swing.JLabel();
        barraManaComputador = new javax.swing.JProgressBar();
        labelManaComputador = new javax.swing.JLabel();
        painelAcoes = new javax.swing.JPanel();
        botaoAtacar = new javax.swing.JButton();
        botaoHabilidade = new javax.swing.JButton();
        botaoUsarItem = new javax.swing.JButton();
        comboInventario = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        logBatalha = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batalha POO");
        setResizable(false);

        painelJogador.setBorder(javax.swing.BorderFactory.createTitledBorder("Jogador"));

        labelNomeJogador.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNomeJogador.setText("Nome do Jogador");

        barraVidaJogador.setForeground(new java.awt.Color(0, 204, 51));
        barraVidaJogador.setValue(100);

        labelVidaJogador.setText("HP: 100/100");

        barraManaJogador.setForeground(new java.awt.Color(0, 102, 255));
        barraManaJogador.setValue(50);

        labelManaJogador.setText("MP: 50/50");

        javax.swing.GroupLayout painelJogadorLayout = new javax.swing.GroupLayout(painelJogador);
        painelJogador.setLayout(painelJogadorLayout);
        painelJogadorLayout.setHorizontalGroup(
            painelJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraVidaJogador, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(barraManaJogador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelJogadorLayout.createSequentialGroup()
                        .addGroup(painelJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomeJogador)
                            .addComponent(labelVidaJogador)
                            .addComponent(labelManaJogador))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        painelJogadorLayout.setVerticalGroup(
            painelJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomeJogador)
                .addGap(18, 18, 18)
                .addComponent(labelVidaJogador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraVidaJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelManaJogador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraManaJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        painelComputador.setBorder(javax.swing.BorderFactory.createTitledBorder("Computador"));

        labelNomeComputador.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNomeComputador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNomeComputador.setText("Nome do Computador");

        barraVidaComputador.setForeground(new java.awt.Color(255, 51, 51));
        barraVidaComputador.setValue(100);

        labelVidaComputador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelVidaComputador.setText("HP: 100/100");

        barraManaComputador.setForeground(new java.awt.Color(0, 102, 255));
        barraManaComputador.setValue(50);

        labelManaComputador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelManaComputador.setText("MP: 50/50");

        javax.swing.GroupLayout painelComputadorLayout = new javax.swing.GroupLayout(painelComputador);
        painelComputador.setLayout(painelComputadorLayout);
        painelComputadorLayout.setHorizontalGroup(
            painelComputadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelComputadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelComputadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraVidaComputador, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(labelNomeComputador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelVidaComputador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barraManaComputador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelManaComputador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelComputadorLayout.setVerticalGroup(
            painelComputadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelComputadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomeComputador)
                .addGap(18, 18, 18)
                .addComponent(labelVidaComputador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraVidaComputador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelManaComputador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraManaComputador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        painelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações do Jogador"));

        botaoAtacar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoAtacar.setText("Atacar");
        botaoAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtacarActionPerformed(evt);
            }
        });

        botaoHabilidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoHabilidade.setText("Habilidade");
        botaoHabilidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoHabilidadeActionPerformed(evt);
            }
        });

        botaoUsarItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoUsarItem.setText("Usar Item");
        botaoUsarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoUsarItemActionPerformed(evt);
            }
        });

        

        javax.swing.GroupLayout painelAcoesLayout = new javax.swing.GroupLayout(painelAcoes);
        painelAcoes.setLayout(painelAcoesLayout);
        painelAcoesLayout.setHorizontalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoUsarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painelAcoesLayout.setVerticalGroup(
            painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoHabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoUsarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        logBatalha.setEditable(false);
        logBatalha.setBackground(new java.awt.Color(255, 255, 255));
        logBatalha.setColumns(20);
        logBatalha.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        logBatalha.setRows(5);
        logBatalha.setBorder(javax.swing.BorderFactory.createTitledBorder("Log de Batalha"));
        jScrollPane1.setViewportView(logBatalha);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(painelAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(painelJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(painelComputador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelComputador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento do clique no botão "Atacar".
     */
    private void botaoAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtacarActionPerformed
        String logJogador = batalha.executarAtaqueJogador();
        adicionarLog(logJogador);
        
        atualizarUI();
        if (verificarFimDeJogo() == null) {
            executarTurnoComputador();
        }
    }//GEN-LAST:event_botaoAtacarActionPerformed

    /**
     * Evento do clique no botão "Habilidade".
     */
    private void botaoHabilidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoHabilidadeActionPerformed
        String logJogador = batalha.executarHabilidadeJogador();
        adicionarLog(logJogador);
        
        atualizarUI();
        if (verificarFimDeJogo() == null && !logJogador.contains("não tem mana")) {
             executarTurnoComputador();
        }
    }//GEN-LAST:event_botaoHabilidadeActionPerformed

    /**
     * Evento do clique no botão "Usar Item".
     */
    private void botaoUsarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUsarItemActionPerformed
        Consumivel itemSelecionado = (Consumivel) comboInventario.getSelectedItem();
        
        if (itemSelecionado == null) {
            adicionarLog("Você não tem nenhum item para usar!");
            return;
        }
        
        String logJogador = batalha.executarItemJogador(itemSelecionado);
        adicionarLog(logJogador);
        
        atualizarInventario(); // Remove o item da lista
        atualizarUI();
        
        if (verificarFimDeJogo() == null) {
            executarTurnoComputador();
        }
    }//GEN-LAST:event_botaoUsarItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraManaComputador;
    private javax.swing.JProgressBar barraManaJogador;
    private javax.swing.JProgressBar barraVidaComputador;
    private javax.swing.JProgressBar barraVidaJogador;
    private javax.swing.JButton botaoAtacar;
    private javax.swing.JButton botaoHabilidade;
    private javax.swing.JButton botaoUsarItem;
    private javax.swing.JComboBox<Consumivel> comboInventario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelManaComputador;
    private javax.swing.JLabel labelManaJogador;
    private javax.swing.JLabel labelNomeComputador;
    private javax.swing.JLabel labelNomeJogador;
    private javax.swing.JLabel labelVidaComputador;
    private javax.swing.JLabel labelVidaJogador;
    private javax.swing.JTextArea logBatalha;
    private javax.swing.JPanel painelAcoes;
    private javax.swing.JPanel painelComputador;
    private javax.swing.JPanel painelJogador;
    // End of variables declaration//GEN-END:variables
}