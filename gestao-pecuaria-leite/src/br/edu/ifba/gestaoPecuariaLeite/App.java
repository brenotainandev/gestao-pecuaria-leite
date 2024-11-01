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
    private static final int PRODUCAO_MEDIA = 20;

    public static void main(String[] args) throws Exception {
        Sensoriamento<Leite> sensoriamento = new SensoriamentoImpl();
        Map<Vaca, List<Leite>> leituras = gerarLeituras(sensoriamento);

        Operacoes<Vaca, Leite> operacoes = new OperacoesImpl();

        // (d.1) Imprimindo as vacas monitoradas
        imprimirVacas(operacoes, leituras);

        // (d.2) Imprimindo as leituras de produção de leite por vaca
        imprimirLeituras(operacoes, leituras);

        // (d.3) Ordenando as leituras de produção de leite por vaca
        Map<Vaca, List<Leite>> leiturasOrdenadas = ordenarLeituras(operacoes, leituras);
        imprimirLeiturasOrdenadas(operacoes, leiturasOrdenadas);

        // (d.4) Procurando por um padrão de produção de leite nas leituras
        calcularPrudcaoMedia(operacoes, leituras);
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
     *
     * Este método pode gerar uma situação de necessidade de processamento via brute force (força bruta)
     * quando o número de vacas e o número de leituras de leite por vaca são grandes. Isso ocorre porque
     * o algoritmo itera sobre cada vaca e, para cada vaca, itera sobre suas leituras de leite, resultando
     * em uma complexidade quadrática. Dependendo da quantidade de vacas e leituras realizadas, a execução
     * deste algoritmo pode elevar o seu tempo/custo e torná-lo ineficiente e ineficaz.
     */
    private static void calcularPrudcaoMedia(Operacoes<Vaca, Leite> operacoes, Map<Vaca, List<Leite>> leituras) {
        System.out.println("Calculando a produção média de leite por vaca:");
        boolean encontrado = operacoes.calcularPrudcaoMedia(leituras, PRODUCAO_MEDIA);
        imprimirSeEncontrouPadrao(encontrado);
    }

    /**
     * Imprime se encontrou um padrão de produção de leite.
     * @param encontrado Se encontrou um padrão de produção de leite.
     */
    private static void imprimirSeEncontrouPadrao(boolean encontrado) {
        System.out.println(encontrado ? "Padrão de produção de leite encontrado." : "Padrão de produção de leite não encontrado.");
    }
}