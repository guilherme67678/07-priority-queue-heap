package entidades;

/**
 * Representa um paciente do Pronto-Socorro do Hospital "São Binário".
 *
 * <p>A prioridade clínica é definida por três critérios em cascata, na ordem:</p>
 * <ol>
 *   <li>Maior {@code nivelUrgencia} (1 a 5, sendo 5 = Vermelho/Imediato);</li>
 *   <li>Em caso de empate, maior {@code tempoEsperaMinutos};</li>
 *   <li>Persistindo o empate, pacientes com {@code grupoVulneravel == true}
 *       (menores de 12 anos ou maiores de 65 anos) têm prioridade.</li>
 * </ol>
 *
 * <p>{@link #compareTo(Paciente)} segue a convenção <em>max-heap</em>: retorna
 * positivo quando este paciente possui prioridade <strong>maior</strong> que
 * o outro, garantindo que a raiz do heap seja sempre o paciente mais crítico.</p>
 */
public class Paciente implements Comparable<Paciente> {

    private final String nome;
    private final int nivelUrgencia;
    private final int tempoEsperaMinutos;
    private final boolean grupoVulneravel;

    /**
     * Cria um novo paciente.
     *
     * @param nome               nome do paciente
     * @param nivelUrgencia      nível clínico de 1 (Azul) a 5 (Vermelho)
     * @param tempoEsperaMinutos minutos aguardando atendimento (não negativo)
     * @param grupoVulneravel    {@code true} se o paciente tem menos de 12
     *                           ou mais de 65 anos
     * @throws IllegalArgumentException se {@code nivelUrgencia} estiver fora
     *         de [1, 5] ou se {@code tempoEsperaMinutos} for negativo
     */
    public Paciente(String nome, int nivelUrgencia, int tempoEsperaMinutos, boolean grupoVulneravel) {
        if (tempoEsperaMinutos < 0) {
            throw new IllegalArgumentException("O tempo de espera não pode ser negativo.");
        }
        if (nivelUrgencia < 1 || nivelUrgencia > 5) {
            throw new IllegalArgumentException("O nível de urgência precisa estar no intervalo [1, 5].");
        }

        this.nome = nome;
        this.nivelUrgencia = nivelUrgencia;
        this.tempoEsperaMinutos = tempoEsperaMinutos;
        this.grupoVulneravel = grupoVulneravel;
    }

    public String getNome() {
        return nome;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public int getTempoEsperaMinutos() {
        return tempoEsperaMinutos;
    }

    public boolean isGrupoVulneravel() {
        return grupoVulneravel;
    }

    /**
     * Retorna a cor do protocolo de triagem associada ao nível de urgência.
     *
     * @return "Vermelho", "Laranja", "Amarelo", "Verde", "Azul" ou "Desconhecido"
     */
    public String getCor() {
        switch (nivelUrgencia) {
            case 5: return "Vermelho";
            case 4: return "Laranja";
            case 3: return "Amarelo";
            case 2: return "Verde";
            case 1: return "Azul";
            default: return "Desconhecido";
        }
    }

    /**
     * Compara este paciente com outro segundo as regras de prioridade clínica.
     *
     * @param p paciente a ser comparado
     * @return valor positivo se este paciente tem prioridade maior, negativo
     *         se menor e zero se forem equivalentes em todos os critérios
     */
    @Override
    public int compareTo(Paciente p) {
        int delta = Integer.compare(this.nivelUrgencia, p.nivelUrgencia);
        if (delta != 0) {
            return delta;
        }

        delta = Integer.compare(this.tempoEsperaMinutos, p.tempoEsperaMinutos);
        if (delta != 0) {
            return delta;
        }

        return Boolean.compare(this.grupoVulneravel, p.grupoVulneravel);
    }

    @Override
    public String toString() {
        return String.format(
            "Paciente{nome='%s', urgencia=%d (%s), espera=%dmin, vulneravel=%s}",
            nome, nivelUrgencia, getCor(), tempoEsperaMinutos, grupoVulneravel
        );
    }
}
