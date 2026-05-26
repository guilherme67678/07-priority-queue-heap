import entidades.Paciente;
import heap.FilaComPrioridadeHeap;

/**
 * Simulação do sistema de triagem do Pronto-Socorro do Hospital "São Binário".
 *
 * <p>Registra a chegada dos 6 pacientes de teste especificados no enunciado,
 * imprimindo o estado interno do heap após cada inserção, e em seguida remove
 * todos em ordem de prioridade clínica.</p>
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Hospital São Binário - Triagem do Pronto-Socorro ===\n");

        FilaComPrioridadeHeap<Paciente> fila = new FilaComPrioridadeHeap<>(10);

        Paciente[] chegadas = {
            new Paciente("Carlos",  2, 45, false),
            new Paciente("Maria",   5,  5, false),
            new Paciente("João",    3, 20, false),
            new Paciente("Beatriz", 3, 35, true),
            new Paciente("Pedro",   5,  2, false),
            new Paciente("Helena",  2, 45, true)
        };

        System.out.println(">> Registrando chegada dos pacientes\n");
        for (Paciente paciente : chegadas) {
            registrarChegada(fila, paciente);
        }

        System.out.println(">> Ordem de atendimento\n");
        int posicao = 1;
        while (!fila.estaVazia()) {
            Paciente atendido = fila.desenfileirar();
            System.out.printf("%d. %s%n", posicao, atendido);
            posicao++;
        }
    }

    /**
     * Registra a chegada de um paciente na fila de triagem e imprime o estado
     * interno do heap após a inserção, conforme exigido pelo enunciado.
     *
     * @param fila     fila de prioridade da triagem
     * @param paciente paciente que acabou de chegar
     */
    private static void registrarChegada(FilaComPrioridadeHeap<Paciente> fila, Paciente paciente) {
        fila.enfileirar(paciente);
        System.out.println("Chegou: " + paciente.getNome());
        System.out.println("Heap:   " + fila);
        System.out.println();
    }
}
