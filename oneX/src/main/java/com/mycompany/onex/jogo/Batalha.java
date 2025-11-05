/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onex.jogo;

import com.mycompany.onex.consumiveis.Consumivel;
import com.mycompany.onex.personagens.Personagem;

/**
 * O "Controller" (Controlador).
 * Esta classe gerencia a lógica da batalha, o estado do jogo e os turnos.
 * Ela NÃO lida com a interface, apenas com as regras.
 */
public class Batalha {
    
    private final Personagem jogador1;
    private final Personagem computador;
    private boolean turnoDoJogador;
    
    public Batalha(Personagem jogador1, Personagem computador) {
        this.jogador1 = jogador1;
        this.computador = computador;
        this.turnoDoJogador = true; // Jogador começa
    }
    
    public String executarAtaqueJogador() {
        if (!turnoDoJogador || !jogador1.estaVivo()) return "";
        String log = jogador1.atacar(computador);
        trocarTurno();
        return log;
    }
    
    public String executarHabilidadeJogador() {
        if (!turnoDoJogador || !jogador1.estaVivo()) return "";
        String log = jogador1.usarHabilidade(computador);
        // Só troca o turno se a habilidade foi usada com sucesso (tinha mana)
        if (!log.contains("não tem mana")) {
            trocarTurno();
        }
        return log;
    }
    
    public String executarItemJogador(Consumivel item) {
        if (!turnoDoJogador || !jogador1.estaVivo() || item == null) return "";
        String log = jogador1.getInventario().usarItem(item, jogador1);
        trocarTurno();
        return log;
    }
    
    public String executarTurnoComputador() {
        if (turnoDoJogador || !computador.estaVivo()) return "";
        
        String log;
        // IA Simples:
        // Se a vida estiver baixa (<= 40%) e tiver poção de cura, usa poção.
        Consumivel pocaoCura = null;
        for (Consumivel item : computador.getInventario().getItens()) {
            if (item instanceof com.mycompany.onex.consumiveis.PocaoCura) {
                pocaoCura = item;
                break;
            }
        }

        if (computador.getVida() <= (computador.getVidaMaxima() * 0.4) && pocaoCura != null) {
            log = computador.getInventario().usarItem(pocaoCura, computador);
        }
        // Se tiver mana para habilidade, 50% de chance de usar
        else if (computador.getMana() >= 30 && Math.random() > 0.5) {
            log = computador.usarHabilidade(jogador1);
        } 
        // Senão, ataca
        else {
            log = computador.atacar(jogador1);
        }
        
        trocarTurno();
        return log;
    }
    
    private void trocarTurno() {
        this.turnoDoJogador = !this.turnoDoJogador;
    }
    
    public Personagem getJogador1() {
        return jogador1;
    }

    public Personagem getComputador() {
        return computador;
    }
    
    public boolean isTurnoDoJogador() {
        return turnoDoJogador;
    }
    
    public String verificarFimDeJogo() {
        if (!jogador1.estaVivo()) {
            return "VOCÊ PERDEU! " + computador.getNome() + " venceu a batalha.";
        }
        if (!computador.estaVivo()) {
            return "VOCÊ VENCEU! " + jogador1.getNome() + " derrotou " + computador.getNome() + ".";
        }
        return null; // Jogo continua
    }
}
