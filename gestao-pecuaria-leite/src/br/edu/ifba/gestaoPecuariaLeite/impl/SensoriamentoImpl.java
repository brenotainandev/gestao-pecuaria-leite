package br.edu.ifba.gestaoPecuariaLeite.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifba.gestaoPecuariaLeite.sensoriamento.Sensoriamento;

public class SensoriamentoImpl implements Sensoriamento<Leite> {

    private static final int PRODUCAO_MEDIA = 20; // média de produção em litros
    private static final int OSCILACAO_MAXIMA = 5; // oscilação máxima em litros

    /**
     * Este método gera randomicamente as leituras de produção de leite.
     * Complexidade: O(N), onde N é o total de leituras.
     */
    @Override
    public List<Leite> gerar(int totalLeituras) {
        List<Leite> leituras = new ArrayList<>();
        Random randomizador = new Random();

        for (int i = 0; i < totalLeituras; i++) {
            int oscilacao = randomizador.nextInt(OSCILACAO_MAXIMA);
            int quantidade = PRODUCAO_MEDIA + (randomizador.nextBoolean() ? oscilacao : -oscilacao);
            leituras.add(new Leite(quantidade));
        }

        return leituras;
    }
}