package br.edu.ifba.gestaoPecuariaLeite.operacoes;

import java.util.List;
import java.util.Map;

public interface Operacoes<Monitorado, Sensor> {
    void imprimir(List<Monitorado> monitorados);
    void imprimir(Map<Monitorado, List<Sensor>> leituras);
    Map<Monitorado, List<Sensor>> ordenar(Map<Monitorado, List<Sensor>> leituras);
    boolean procurarPadrao(Map<Monitorado, List<Sensor>> leituras, List<Sensor> padrao);
}