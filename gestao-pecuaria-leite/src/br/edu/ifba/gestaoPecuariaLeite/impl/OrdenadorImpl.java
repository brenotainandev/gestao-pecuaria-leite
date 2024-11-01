package br.edu.ifba.gestaoPecuariaLeite.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.gestaoPecuariaLeite.ordenador.Ordenador;

public class OrdenadorImpl extends Ordenador<Leite> {

    public OrdenadorImpl(List<Leite> leituras) {
        super(leituras);
    }

    /**
     * Complexidade exponencial, O(2^N)
     */
    @Override
    public void ordenar() {
        ordenar(0, leituras.size() - 1);
    }

    /**
     * Complexidade exponential, O(2^N)
     *
     * @param inicio
     * @param fim
     */
    private void ordenar(int inicio, int fim) {
        if (inicio < fim && (fim - inicio) >= 1) {
            int meio = (fim + inicio) / 2;
            ordenar(inicio, meio);
            ordenar(meio + 1, fim);
            ordenar(inicio, meio, fim);
        }
    }

    /**
     * Complexidade linear, O(N)
     *
     * @param inicio
     * @param meio
     * @param fim
     */
    public void ordenar(int inicio, int meio, int fim) {
        List<Leite> leiturasTemp = new ArrayList<>();

        int esquerda = inicio;
        int direita = meio + 1;

        while (esquerda <= meio && direita <= fim) {
            if (leituras.get(esquerda).getQuantidade() <= leituras.get(direita).getQuantidade()) {
                leiturasTemp.add(leituras.get(esquerda));
                esquerda++;
            } else {
                leiturasTemp.add(leituras.get(direita));
                direita++;
            }
        }

        while (esquerda <= meio) {
            leiturasTemp.add(leituras.get(esquerda));
            esquerda++;
        }

        while (direita <= fim) {
            leiturasTemp.add(leituras.get(direita));
            direita++;
        }

        for (int i = 0; i < leiturasTemp.size(); inicio++) {
            leituras.set(inicio, leiturasTemp.get(i++));
        }
    }
}