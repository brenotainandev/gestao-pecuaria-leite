package br.edu.ifba.gestaoPecuariaLeite.impl;

import java.util.*;

import br.edu.ifba.gestaoPecuariaLeite.operacoes.Operacoes;
import br.edu.ifba.gestaoPecuariaLeite.ordenador.Ordenador;

public class OperacoesImpl implements Operacoes<Vaca, Leite> {

    /**
     * Método que imprime as vacas.
     * Complexidade linear, O(N)
     */
    @Override
    public void imprimir(List<Vaca> monitorados) {
        for (Vaca vaca : monitorados) {
            System.out.println("vaca sendo monitorada: " + vaca);
        }
    }

    /**
     * Método que imprime as leituras de produção de leite por vaca.
     * Complexidade quadrática, O(N²)
     */
    @Override
    public void imprimir(Map<Vaca, List<Leite>> leituras) {
        for (Vaca vaca : leituras.keySet()) {
            System.out.println("leituras da vaca " + vaca + ":");
            for (Leite leitura : leituras.get(vaca)) {
                System.out.println(leitura);
            }
        }
    }

    /**
     * Método que ordena as leituras de produção de leite por vaca.
     * Complexidade exponencial, O(N2^N)
     */
    @Override
    public Map<Vaca, List<Leite>> ordenar(Map<Vaca, List<Leite>> leituras) {
        Map<Vaca, List<Leite>> leiturasOrdenadas = new TreeMap<>();

        for (Vaca vaca : leituras.keySet()) {
            System.out.println("ordenando leituras da vaca: " + vaca);

            List<Leite> producoes = leituras.get(vaca);
            Ordenador<Leite> ordenador = new OrdenadorImpl(producoes);
            ordenador.ordenar();

            leiturasOrdenadas.put(vaca, producoes);
        }

        return leiturasOrdenadas;
    }


    /**
     * Método que calcula a produção média de leite por vaca e compara a produção média de cada vaca com
     * as médias de todas as outras vacas.
     * Complexidade: O(N³)
     *
     * A complexidade O(N³) é causada pela presença de três loops aninhados:
     * 1. O primeiro loop percorre as vacas (O(N)).
     * 2. O segundo loop percorre novamente as vacas, comparando cada vaca com outra vaca (O(N)).
     * 3. O terceiro loop compara a vaca com outra vaca e uma terceira vaca (O(N)).
     *
     * Isso resulta em um tempo de execução que cresce cubicamente com relação ao número de vacas (O(N³)).
     * Em situações com muitas vacas, essa complexidade pode tornar o algoritmo muito ineficiente.
     */
    @Override
    public boolean calcularProducaoMedia(Map<Vaca, List<Leite>> leituras, int producaoMedia) {
        boolean encontrado = false;
        List<Vaca> vacasComMediaAlta = new ArrayList<>();
        List<Vaca> vacasComMediaBaixa = new ArrayList<>();
        Map<Vaca, Double> mediasProducoes = new HashMap<>();

        for (Vaca vaca : leituras.keySet()) {
            List<Leite> producoes = leituras.get(vaca);
            int somaProducoes = 0;

            // Somando as produções de leite
            for (Leite producao : producoes) {
                somaProducoes += producao.getQuantidade();
            }

            double mediaProducoes = (double) somaProducoes / producoes.size();
            mediasProducoes.put(vaca, mediaProducoes);

            // Comparando a média de leite da vaca com as médias de todas as outras vacas (O(N³))
            for (Vaca outraVaca : leituras.keySet()) {
                for (Vaca outraOutraVaca : leituras.keySet()) {
                    if (!outraVaca.equals(vaca) && !outraOutraVaca.equals(vaca) && !outraVaca.equals(outraOutraVaca)) {
                        System.out.println("Comparando a vaca " + vaca.getNome() + " (média: " + mediaProducoes + ") " +
                                "com as vacas " + outraVaca.getNome() + " (média: " + mediasProducoes.get(outraVaca) + ") " +
                                "e " + outraOutraVaca.getNome() + " (média: " + mediasProducoes.get(outraOutraVaca) + ").");
                    }
                }
            }


            if (mediaProducoes >= producaoMedia) {
                vacasComMediaAlta.add(vaca);
                encontrado = true;
            } else {
                vacasComMediaBaixa.add(vaca);
            }
        }

        // Exibindo vacas com média alta ou baixa
        System.out.println("Vacas com produção média maior ou igual a " + producaoMedia + ":");
        for (Vaca vaca : vacasComMediaAlta) {
            System.out.println("id: " + vaca.getId() + ", nome: " + vaca.getNome() + ", média: " + mediasProducoes.get(vaca));
        }

        System.out.println("Vacas com produção média menor que " + producaoMedia + ":");
        for (Vaca vaca : vacasComMediaBaixa) {
            System.out.println("id: " + vaca.getId() + ", nome: " + vaca.getNome() + ", média: " + mediasProducoes.get(vaca));
        }

        return encontrado;
    }

}