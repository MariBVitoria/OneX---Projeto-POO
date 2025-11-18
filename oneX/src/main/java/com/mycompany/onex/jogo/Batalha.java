/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.jogo;

import com.mycompany.onex.consumiveis.Consumivel;
import com.mycompany.onex.personagem.Personagem;

/**
 *  Controlador do jogo
 * Esta classe gerencia a lógica da batalha, o estado, e os turnos.
 * Ela NÃO lida com a interface, apenas com as regras de funcionamento da partida
 */
public class Batalha {
    
    private final Personagem player1;
    private final Personagem cpu;
    private boolean turnoDoJogador;
    
    public Batalha(Personagem jogador1, Personagem cpu) {
        this.player1 = jogador1;
        this.cpu = cpu;
        this.turnoDoJogador = true; // Jogador começa
    }
    
    public String executarAtaqueJogador() {
        if (!turnoDoJogador || !player1.estaVivo()) return "";
        String log = player1.atacar(cpu);
        trocarTurno();
        return log;
    }
    
    public String executarHabilidadeJogador() {
        if (!turnoDoJogador || !player1.estaVivo()) return "";
        String log = player1.usarHabilidade(cpu);
        // Só troca o turno se a habilidade foi usada com sucesso (tinha mana)
        if (!log.contains("não tem mana")) {
            trocarTurno();
        }
        return log;
    }
    
    public String executarItemJogador(Consumivel item) {
        if (!turnoDoJogador || !player1.estaVivo() || item == null) return "";
        String log = player1.getInventario().usarItem(item, player1);
        trocarTurno();
        return log;
    }
    
    public String executarTurnoComputador() {
        // Se não for o turno do CPU, ou se ele estiver morto, não faz nada.
        if (turnoDoJogador || !cpu.estaVivo()) {
            return "";
        }

        String log;
        Consumivel pocaoCura = null;

        // --- // PRIORIDADE 1: SOBREVIVÊNCIA (O 'cpu' checa a si mesmo) ---

        // Procura por uma poção de cura no inventário do 'cpu'
        for (Consumivel item : cpu.getInventario().getItens()) {
            if (item instanceof com.mycompany.onex.consumiveis.PocaoCura) {
                pocaoCura = item;
                break; // Para de procurar assim que acha
            }
        }

        // Se o 'cpu' está com vida baixa (<= 40%) E achou uma poção, ele se cura.
        if (cpu.getVida() <= (cpu.getVidaMaxima() * 0.4) && pocaoCura != null) {
            log = cpu.getInventario().usarItem(pocaoCura, cpu);
        }

        // --- // PRIORIDADE 2: OFENSIVA (A "MALDADE") ---
        // Se o 'cpu' não precisou se curar, ele decide como atacar.
        else {

            // O 'cpu' "espia" a vida do 'player1'
            boolean jogadorComPoucaVida = player1.getVida() <= (player1.getVidaMaxima() * 0.25);

            // O 'cpu' checa se tem mana para usar a skill (ex: 30)
            boolean podeUsarSkill = cpu.getMana() >= 30;

            // A "Maldade": Se o 'player1' está morrendo E o 'cpu' tem mana...
            if (jogadorComPoucaVida && podeUsarSkill) {
                // ...ele SEMPRE usa a habilidade (100% de chance) para finalizar.
                log = cpu.usarHabilidade(player1);
            }

            // --- // PRIORIDADE 3: ATAQUE NORMAL ---
            // Se o 'player1' não está morrendo...
            else if (podeUsarSkill && Math.random() > 0.3) { // 70% de chance de usar skill
                log = cpu.usarHabilidade(player1);
            }

            // --- // PRIORIDADE 4: ATAQUE BÁSICO (Fallback) ---
            // Se não tem mana ou se caiu nos 30% de chance
            else {
                log = cpu.atacar(player1);
            }
        }

        // Passa o turno de volta para o jogador
        trocarTurno();
        return log; // Retorna o que aconteceu para a tela (log)
    }
    
    private void trocarTurno() {
        this.turnoDoJogador = !this.turnoDoJogador;
    }
    
    public Personagem getJogador1() {
        return player1;
    }

    public Personagem getCpu() {
        return cpu;
    }
    
    public boolean isTurnoDoJogador() {
        return turnoDoJogador;
    }
    
    public String verificarFimDeJogo() {
        if (!player1.estaVivo()) {
            return "VOCÊ PERDEU!\n " + cpu.getNome() + " venceu a batalha.";
        }
        if (!cpu.estaVivo()) {
            return "VOCÊ VENCEU!\n " + player1.getNome() + " derrotou " + cpu.getNome() + ".";
        }
        return null; // Jogo continua
    }
}
