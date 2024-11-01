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
     * Método que calcula a produção média de leite por vaca.
     * Complexidade quadrática, O(N²)
     *
     * Este método pode gerar uma situação de necessidade de processamento via brute force (força bruta)
     * quando o número de vacas e o número de leituras de leite por vaca são grandes. Isso ocorre porque
     * o algoritmo itera sobre cada vaca e, para cada vaca, itera sobre suas leituras de leite, resultando
     * em uma complexidade quadrática. Dependendo da quantidade de vacas e leituras realizadas, a execução
     * deste algoritmo pode elevar o seu tempo/custo e torná-lo ineficiente e ineficaz.
     */
    @Override
    public boolean calcularPrudcaoMedia(Map<Vaca, List<Leite>> leituras, int producaoMedia) {
        boolean encontrado = false;
        List<Vaca> vacasComMediaAlta = new ArrayList<>();
        List<Vaca> vacasComMediaBaixa = new ArrayList<>();
        Map<Vaca, Double> mediasProducoes = new HashMap<>();

        for (Vaca vaca : leituras.keySet()) {
            System.out.println("Calculando a produção média de leite da vaca: " + vaca);

            List<Leite> producoes = leituras.get(vaca);
            int somaProducoes = 0;

            for (Leite producao : producoes) {
                somaProducoes += producao.getQuantidade();
            }

            double mediaProducoes = (double) somaProducoes / producoes.size();
            mediasProducoes.put(vaca, mediaProducoes);
            System.out.println("Produção média da vaca " + vaca + ": " + mediaProducoes);

            if (mediaProducoes >= producaoMedia) {
                vacasComMediaAlta.add(vaca);
                encontrado = true;
            } else {
                vacasComMediaBaixa.add(vaca);
            }
        }

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