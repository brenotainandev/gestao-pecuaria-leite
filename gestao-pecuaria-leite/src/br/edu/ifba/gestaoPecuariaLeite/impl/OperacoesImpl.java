package br.edu.ifba.gestaoPecuariaLeite.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.gestaoPecuariaLeite.operacoes.Operacoes;
import br.edu.ifba.gestaoPecuariaLeite.ordenador.Ordenador;

public class OperacoesImpl implements Operacoes<Vaca, Leite> {

    /**
     * Método que imprime as vacas.
     * Complexidade: O(N), onde N é o número de vacas.
     */
    @Override
    public void imprimir(List<Vaca> monitorados) {
        for (Vaca vaca : monitorados) {
            System.out.println("vaca sendo monitorada: " + vaca);
        }
    }

    /**
     * Método que imprime as leituras de produção de leite por vaca.
     * Complexidade: O(N²), onde N é o número de vacas e leituras.
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
     * Complexidade: O(N log N), onde N é o número de leituras.
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
     * Método que procura por um padrão de produção de leite nas leituras.
     * Complexidade: O(N * M), onde N é o número de leituras e M é o tamanho do padrão.
     */
    @Override
    public boolean procurarPadrao(Map<Vaca, List<Leite>> leituras, List<Leite> padrao) {
        boolean encontrado = false;
        List<Vaca> vacasComPadrao = new ArrayList<>();
        List<Vaca> vacasSemPadrao = new ArrayList<>();

        for (Vaca vaca : leituras.keySet()) {
            System.out.println("procurando por padrão nas leituras da vaca: " + vaca);

            List<Leite> producoes = leituras.get(vaca);
            int totalDeIguais = 0;
            boolean padraoEncontradoNaVaca = false;

            for (int i = 0; i <= producoes.size() - padrao.size(); i++) {
                for (int j = 0; j < padrao.size(); j++) {
                    if (producoes.get(i + j).getQuantidade().equals(padrao.get(j).getQuantidade())) {
                        totalDeIguais++;
                    } else {
                        totalDeIguais = 0;
                        break;
                    }
                }

                if (totalDeIguais == padrao.size()) {
                    System.out.println("Padrão encontrado na vaca: " + vaca);
                    vacasComPadrao.add(vaca);
                    padraoEncontradoNaVaca = true;
                    encontrado = true;
                    break;
                }
            }

            if (!padraoEncontradoNaVaca) {
                vacasSemPadrao.add(vaca);
            }
        }

        System.out.println("Vacas com o padrão encontrado:");
        for (Vaca vaca : vacasComPadrao) {
            System.out.println(vaca + " com o padrão: " + padrao);
        }

        System.out.println("Vacas sem o padrão encontrado:");
        for (Vaca vaca : vacasSemPadrao) {
            System.out.println(vaca);
        }

        return encontrado;
    }
}