package com.mycompany.onex.ui;

import com.mycompany.onex.consumiveis.Consumivel;
import com.mycompany.onex.jogo.Batalha;
import com.mycompany.onex.personagem.Personagem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaBatalha extends JFrame {

    private final Batalha batalha;

    // Componentes
    private JLabel labelNomeJogador, labelNomeComputador;
    private JProgressBar barraVidaJogador, barraManaJogador;
    private JProgressBar barraVidaComputador, barraManaComputador;
    private JLabel labelHpJogador, labelMpJogador, labelHpComputador, labelMpComputador;
    private JTextArea logBatalha;
    private JButton botaoAtacar, botaoHabilidade, botaoUsarItem;
    private JComboBox<Consumivel> comboInventario;

    // Painel para o Cenário de Fundo
    private class PainelFundo extends JPanel {
        private Image imagemFundo;
        public PainelFundo(String caminho) {
            try {
                java.net.URL url = getClass().getResource(caminho);
                if (url != null) {
                    this.imagemFundo = new ImageIcon(url).getImage();
                }
            } catch (Exception e) {
                System.err.println("Erro cenário: " + e.getMessage());
            }
            setLayout(null); // Layout absoluto
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagemFundo != null) g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public TelaBatalha(Batalha batalha) {
        this.batalha = batalha;
        configurarJanela();
        inicializarComponentes();
        configurarBatalhaInicial();
    }

    private void configurarJanela() {
        setTitle("OneX - Batalha");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        int sorteio = (Math.random() > 0.5) ? 1 : 2;
        String cenario = "/imagens/cenario" + sorteio + ".jpeg"; //
        setContentPane(new PainelFundo(cenario));
    }

    // Coordenadas em Y; Quanto Maior, mais para baixo a Ui ou Sprite ficará
    // Coor Y: Quanto menor o Numero, mais para cima ficará
    private void inicializarComponentes() {
        // --- LADO DO JOGADOR (Esquerda) ---

        // 1. Sprite
        labelNomeJogador = new JLabel("Jogador");
        labelNomeJogador.setBounds(50, 245, 200, 240); // Coordenadas dos Sprites do jogador
        labelNomeJogador.setForeground(Color.WHITE);
        labelNomeJogador.setFont(new Font("Arial", Font.BOLD, 16));
        labelNomeJogador.setHorizontalTextPosition(JLabel.CENTER);
        labelNomeJogador.setVerticalTextPosition(JLabel.BOTTOM);
        add(labelNomeJogador);

        // 2. Barras (Acima do Sprite)
        barraManaJogador = new JProgressBar();
        barraManaJogador.setBounds(50, 230, 200, 10);
        barraManaJogador.setForeground(new Color(0, 102, 255));
        add(barraManaJogador);

        labelMpJogador = new JLabel("MP: 50/50");
        labelMpJogador.setBounds(50, 215, 200, 15);
        labelMpJogador.setForeground(Color.BLACK);
        labelMpJogador.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMpJogador);

        barraVidaJogador = new JProgressBar();
        barraVidaJogador.setBounds(50, 200, 200, 15);
        barraVidaJogador.setForeground(new Color(0, 204, 51));
        add(barraVidaJogador);

        labelHpJogador = new JLabel("HP: 100/100");
        labelHpJogador.setBounds(50, 180, 200, 20);
        labelHpJogador.setForeground(Color.BLACK);
        labelHpJogador.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelHpJogador);

        // Coordenadas em Y; Quanto Maior, mais para baixo a Ui ou Sprite ficará
        // --- LADO DO COMPUTADOR (Direita) ---

        // 1. Sprite
        labelNomeComputador = new JLabel("Computador");
        labelNomeComputador.setBounds(550, 245, 200, 240); // Coordenadas da CPU - Sprite
        labelNomeComputador.setForeground(Color.WHITE);
        labelNomeComputador.setFont(new Font("Arial", Font.BOLD, 16));
        labelNomeComputador.setHorizontalTextPosition(JLabel.CENTER);
        labelNomeComputador.setVerticalTextPosition(JLabel.BOTTOM);
        add(labelNomeComputador);

        // 2. Barras
        barraManaComputador = new JProgressBar();
        barraManaComputador.setBounds(550, 230, 200, 10);
        barraManaComputador.setForeground(new Color(0, 102, 255));
        add(barraManaComputador);

        labelMpComputador = new JLabel("MP: 50/50");
        labelMpComputador.setBounds(550, 215, 200, 15);
        labelMpComputador.setForeground(Color.BLACK);
        labelMpComputador.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMpComputador);

        barraVidaComputador = new JProgressBar();
        barraVidaComputador.setBounds(550, 200, 200, 15);
        barraVidaComputador.setForeground(new Color(255, 51, 51));
        add(barraVidaComputador);

        labelHpComputador = new JLabel("HP: 100/100");
        labelHpComputador.setBounds(550, 180, 200, 20);
        labelHpComputador.setForeground(Color.BLACK);
        labelHpComputador.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelHpComputador);


        // --- LOG (CORRIGIDO) ---
        // Alteração: Sobrescrevemos o paintComponent para desenhar o fundo transparente corretamente
        logBatalha = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        logBatalha.setEditable(false);
        logBatalha.setBackground(new Color(0, 0, 0, 150)); // Fundo semi-transparente
        logBatalha.setOpaque(false); // <--- MUDANÇA CRUCIAL: Agora deve ser FALSE
        logBatalha.setForeground(Color.WHITE); // Texto branco
        logBatalha.setFont(new Font("Monospaced", Font.BOLD, 14));
        logBatalha.setLineWrap(true);
        logBatalha.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(logBatalha);
        scroll.setBounds(150, 20, 500, 150);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(scroll);


        // --- BOTÕES ---
        int btnY = 530;

        botaoAtacar = new JButton("Atacar");
        botaoAtacar.setBounds(50, btnY, 120, 30);
        add(botaoAtacar);

        botaoHabilidade = new JButton("Habilidade");
        botaoHabilidade.setBounds(180, btnY, 120, 30);
        add(botaoHabilidade);

        comboInventario = new JComboBox<>();
        comboInventario.setBounds(450, btnY, 180, 30);
        add(comboInventario);

        botaoUsarItem = new JButton("Usar Item");
        botaoUsarItem.setBounds(640, btnY, 110, 30);
        add(botaoUsarItem);

        // --- LISTENERS ---
        botaoAtacar.addActionListener(e -> {
            String log = batalha.executarAtaqueJogador();
            adicionarLog(log);
            atualizarUI();
            if (verificarFimDeJogo() == null) executarTurnoComputador();
        });

        botaoHabilidade.addActionListener(e -> {
            String log = batalha.executarHabilidadeJogador();
            adicionarLog(log);
            atualizarUI();
            if (verificarFimDeJogo() == null && !log.contains("não tem mana")) {
                executarTurnoComputador();
            }
        });

        botaoUsarItem.addActionListener(e -> {
            Consumivel item = (Consumivel) comboInventario.getSelectedItem();
            if (item != null) {
                String log = batalha.executarItemJogador(item);
                adicionarLog(log);
                atualizarInventario();
                atualizarUI();
                if (verificarFimDeJogo() == null) executarTurnoComputador();
            } else {
                adicionarLog("Nenhum item selecionado!");
            }
        });
    }

    private void configurarBatalhaInicial() {
        atualizarUI();
        atualizarInventario();
        adicionarLog("A Batalha Começou!");
        adicionarLog(batalha.getJogador1().getNome() + " vs " + batalha.getCpu().getNome());
    }

    private void atualizarUI() {
        Personagem p1 = batalha.getJogador1();
        Personagem p2 = batalha.getCpu();

        // Sprites
        setSprite(labelNomeJogador, p1.getCaminhoImagem(), p1.getNome());
        setSprite(labelNomeComputador, p2.getCaminhoImagem(), p2.getNome());

        // Status
        barraVidaJogador.setMaximum(p1.getVidaMaxima());
        barraVidaJogador.setValue(p1.getVida());
        labelHpJogador.setText(p1.getVida() + "/" + p1.getVidaMaxima());

        barraManaJogador.setMaximum(p1.getManaMaxima());
        barraManaJogador.setValue(p1.getMana());
        labelMpJogador.setText(p1.getMana() + "/" + p1.getManaMaxima());

        barraVidaComputador.setMaximum(p2.getVidaMaxima());
        barraVidaComputador.setValue(p2.getVida());
        labelHpComputador.setText(p2.getVida() + "/" + p2.getVidaMaxima());

        barraManaComputador.setMaximum(p2.getManaMaxima());
        barraManaComputador.setValue(p2.getMana());
        labelMpComputador.setText(p2.getMana() + "/" + p2.getManaMaxima());

        boolean turnoJogador = batalha.isTurnoDoJogador();
        botaoAtacar.setEnabled(turnoJogador);
        botaoHabilidade.setEnabled(turnoJogador);
        botaoUsarItem.setEnabled(turnoJogador);
        comboInventario.setEnabled(turnoJogador);
    }

    private void setSprite(JLabel label, String caminho, String nomePersonagem) {
        label.setText(nomePersonagem);
        try {
            java.net.URL url = getClass().getResource(caminho);
            if (url != null) {
                ImageIcon iconOriginal = new ImageIcon(url);
                // Redimensiona para 150x150
                Image imgRedimensionada = iconOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(imgRedimensionada));
            } else {
                System.err.println("Sprite não encontrado: " + caminho);
            }
        } catch (Exception e) {
            System.err.println("Erro sprite: " + e.getMessage());
        }
    }

    private void atualizarInventario() {
        DefaultComboBoxModel<Consumivel> model = new DefaultComboBoxModel<>();
        for (Consumivel item : batalha.getJogador1().getInventario().getItens()) {
            model.addElement(item);
        }
        comboInventario.setModel(model);
    }

    private void adicionarLog(String texto) {
        logBatalha.append(texto + "\n");
        logBatalha.setCaretPosition(logBatalha.getDocument().getLength());
    }

    private void executarTurnoComputador() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000);
                return null;
            }
            @Override
            protected void done() {
                String log = batalha.executarTurnoComputador();
                adicionarLog(log);
                atualizarUI();
                verificarFimDeJogo();
            }
        }.execute();
    }

    private String verificarFimDeJogo() {
        String resultado = batalha.verificarFimDeJogo();
        if (resultado != null) {
            adicionarLog("\n=== FIM DE JOGO ===");
            adicionarLog(resultado.toUpperCase());

            botaoHabilidade.setEnabled(false);
            botaoUsarItem.setEnabled(false);
            comboInventario.setEnabled(false);

            botaoAtacar.setEnabled(true);
            botaoAtacar.setText("JOGAR DE NOVO");

            for (ActionListener al : botaoAtacar.getActionListeners()) {
                botaoAtacar.removeActionListener(al);
            }

            botaoAtacar.addActionListener(e -> {
                this.dispose();
                new TelaSelecao().setVisible(true);
            });
        }
        return resultado;
    }
}