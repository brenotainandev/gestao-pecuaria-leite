package br.edu.ifba.gestaoPecuariaLeite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.ifba.gestaoPecuariaLeite.impl.OperacoesImpl;
import br.edu.ifba.gestaoPecuariaLeite.impl.Vaca;
import br.edu.ifba.gestaoPecuariaLeite.impl.SensoriamentoImpl;
import br.edu.ifba.gestaoPecuariaLeite.impl.Leite;
import br.edu.ifba.gestaoPecuariaLeite.operacoes.Operacoes;
import br.edu.ifba.gestaoPecuariaLeite.sensoriamento.Sensoriamento;

public class App {

    private static final int TOTAL_VACAS = 10;
    private static final int TOTAL_LEITURAS = 10;
    private static final String[] NOMES_VACAS = {
            "Mimosa", "Estrela", "Luzia", "Jurema", "Mariana",
            "Bela", "Lola", "Nina", "Lia", "Dora"
    };

    public static void main(String[] args) throws Exception {
        Sensoriamento<Leite> sensoriamento = new SensoriamentoImpl();
        Map<Vaca, List<Leite>> leituras = gerarLeituras(sensoriamento);

        Operacoes<Vaca, Leite> operacoes = new OperacoesImpl();

        // Imprimindo as vacas
        imprimirVacas(operacoes, leituras);

        // Imprimindo leituras por vaca
        imprimirLeituras(operacoes, leituras);

        // Ordenando os dados das leituras por vaca
        Map<Vaca, List<Leite>> leiturasOrdenadas = ordenarLeituras(operacoes, leituras);
        imprimirLeiturasOrdenadas(operacoes, leiturasOrdenadas);

        // Procurando por um padrão de produção nas leituras
        procurarPadrao(operacoes, leituras);
    }

    /**
     * Gera leituras de produção de leite para cada vaca.
     *
     * @param sensoriamento Instância de sensoriamento para gerar leituras.
     * @return Mapa de vacas e suas respectivas leituras de produção de leite.
     * @throws Exception Se ocorrer um erro ao gerar as leituras.
     */
    private static Map<Vaca, List<Leite>> gerarLeituras(Sensoriamento<Leite> sensoriamento) throws Exception {
        Map<Vaca, List<Leite>> leituras = new TreeMap<>();
        for (int i = 0; i < TOTAL_VACAS; i++) {
            Vaca vaca = new Vaca(String.valueOf(i + 1), NOMES_VACAS[i]);
            leituras.put(vaca, sensoriamento.gerar(TOTAL_LEITURAS));
        }
        return leituras;
    }

    /**
     * Imprime a lista de vacas monitoradas.
     *
     * @param operacoes Instância de operações para imprimir as vacas.
     * @param leituras Mapa de vacas e suas respectivas leituras de produção de leite.
     */
    private static void imprimirVacas(Operacoes<Vaca, Leite> operacoes, Map<Vaca, List<Leite>> leituras) {
        System.out.println("Imprimindo as vacas:");
        operacoes.imprimir(new ArrayList<>(leituras.keySet()));
    }

    /**
     * Imprime as leituras de produção de leite por vaca.
     *
     * @param operacoes Instância de operações para imprimir as leituras.
     * @param leituras Mapa de vacas e suas respectivas leituras de produção de leite.
     */
    private static void imprimirLeituras(Operacoes<Vaca, Leite> operacoes, Map<Vaca, List<Leite>> leituras) {
        System.out.println("Imprimindo leituras por cada vaca:");
        operacoes.imprimir(leituras);
    }

    /**
     * Ordena as leituras de produção de leite por vaca.
     *
     * @param operacoes Instância de operações para ordenar as leituras.
     * @param leituras Mapa de vacas e suas respectivas leituras de produção de leite.
     * @return Mapa de vacas e suas respectivas leituras de produção de leite ordenadas.
     */
    private static Map<Vaca, List<Leite>> ordenarLeituras(Operacoes<Vaca, Leite> operacoes, Map<Vaca, List<Leite>> leituras) {
        System.out.println("Ordenando as leituras de produção de leite por cada vaca:");
        return operacoes.ordenar(leituras);
    }

    /**
     * Imprime as leituras de produção de leite ordenadas por vaca.
     *
     * @param operacoes Instância de operações para imprimir as leituras ordenadas.
     * @param leiturasOrdenadas Mapa de vacas e suas respectivas leituras de produção de leite ordenadas.
     */
    private static void imprimirLeiturasOrdenadas(Operacoes<Vaca, Leite> operacoes, Map<Vaca, List<Leite>> leiturasOrdenadas) {
        operacoes.imprimir(leiturasOrdenadas);
    }

    /**
     * Procura por um padrão de produção de leite nas leituras.
     *
     * @param operacoes Instância de operações para procurar o padrão.
     * @param leituras Mapa de vacas e suas respectivas leituras de produção de leite.
     */
    private static void procurarPadrao(Operacoes<Vaca, Leite> operacoes, Map<Vaca, List<Leite>> leituras) {
        List<Leite> padrao = criarPadrao();
        System.out.println("Procurando pelo padrão");
        boolean encontrado = operacoes.procurarPadrao(leituras, padrao);
        imprimirResultadoPadrao(encontrado);
    }

    /**
     * Cria o padrão de produção de leite a ser procurado.
     *
     * @return Lista de leituras de produção de leite que representam o padrão.
     */
    private static List<Leite> criarPadrao() {
        List<Leite> padrao = new ArrayList<>();
        padrao.add(new Leite(18));
        padrao.add(new Leite(20));
        return padrao;
    }

    /**
     * Imprime o resultado da busca pelo padrão de produção de leite.
     *
     * @param encontrado Indica se o padrão foi encontrado ou não.
     */
    private static void imprimirResultadoPadrao(boolean encontrado) {
        if (encontrado) {
            System.out.println("Padrão encontrado");
        } else {
            System.out.println("Padrão não encontrado");
        }
    }
}