package heap;

/**
 * Fila de prioridade implementada como <strong>Heap Binário Máximo</strong>
 * armazenado em um array.
 *
 * <p>O elemento de maior prioridade (segundo {@link Comparable#compareTo})
 * fica sempre na raiz, permitindo:</p>
 * <ul>
 *   <li>{@link #enfileirar(Comparable)} — inserção em O(log n) via algoritmo
 *       <em>Sobe Heap</em> (sift-up);</li>
 *   <li>{@link #desenfileirar()} — remoção do elemento de maior prioridade
 *       em O(log n) via algoritmo <em>Desce Heap</em> (sift-down).</li>
 * </ul>
 *
 * <p>Para um nó na posição {@code i} (base 0):</p>
 * <pre>
 *   pai(i)      = (i - 1) / 2
 *   esquerda(i) = 2 * i + 1
 *   direita(i)  = 2 * i + 2
 * </pre>
 *
 * @param <T> tipo dos elementos armazenados; deve implementar {@link Comparable}
 */
public class FilaComPrioridadeHeap<T extends Comparable<T>> {

    private final T[] dados;
    private int qtd;

    /**
     * Cria um heap vazio com a capacidade máxima informada.
     *
     * @param capacidade número máximo de elementos suportados
     * @throws IllegalArgumentException se {@code capacidade <= 0}
     */
    @SuppressWarnings("unchecked")
    public FilaComPrioridadeHeap(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade informada deve ser positiva.");
        }
        this.dados = (T[]) new Comparable[capacidade];
        this.qtd = 0;
    }

    /**
     * Insere um elemento mantendo a propriedade de heap por meio do algoritmo
     * <strong>Sobe Heap</strong>: o novo elemento é colocado na próxima posição
     * livre e troca com o pai enquanto possuir prioridade maior.
     *
     * <p>Complexidade: O(log n).</p>
     *
     * @param elemento elemento a inserir
     * @throws RuntimeException se o heap estiver cheio
     */
    public void enfileirar(T elemento) {
        if (estaCheia()) {
            throw new RuntimeException("Não é possível enfileirar: a fila está cheia.");
        }

        dados[qtd] = elemento;
        sobeHeap(qtd);
        qtd++;
    }

    /**
     * Remove e retorna o elemento de maior prioridade (a raiz) usando o
     * algoritmo <strong>Desce Heap</strong>: a última folha sobe para a raiz
     * e desce trocando com o filho de maior prioridade até restaurar a
     * propriedade de heap.
     *
     * <p>Complexidade: O(log n).</p>
     *
     * @return elemento de maior prioridade
     * @throws RuntimeException se o heap estiver vazio
     */
    public T desenfileirar() {
        if (estaVazia()) {
            throw new RuntimeException("Não é possível desenfileirar: a fila está vazia.");
        }

        T topo = dados[0];
        qtd--;
        dados[0] = dados[qtd];
        dados[qtd] = null;

        if (qtd > 0) {
            desceHeap(0);
        }

        return topo;
    }

    /**
     * Retorna sem remover o elemento de maior prioridade.
     *
     * @return raiz do heap
     * @throws RuntimeException se o heap estiver vazio
     */
    public T frente() {
        if (estaVazia()) {
            throw new RuntimeException("A fila está vazia.");
        }
        return dados[0];
    }

    /**
     * Algoritmo <em>Sobe Heap</em> (sift-up). Faz o elemento da posição
     * {@code i} subir, trocando com o pai enquanto possuir prioridade
     * maior, até restaurar a propriedade de heap.
     *
     * @param i posição inicial do elemento a subir
     */
    private void sobeHeap(int i) {
        while (i > 0) {
            int indicePai = (i - 1) / 2;
            if (dados[i].compareTo(dados[indicePai]) > 0) {
                trocar(i, indicePai);
                i = indicePai;
            } else {
                break;
            }
        }
    }

    /**
     * Algoritmo <em>Desce Heap</em> (sift-down). Faz o elemento da posição
     * {@code i} descer, trocando com o filho de maior prioridade até
     * restaurar a propriedade de heap.
     *
     * @param i posição inicial do elemento a descer
     */
    private void desceHeap(int i) {
        while (true) {
            int filhoEsq = 2 * i + 1;
            int filhoDir = 2 * i + 2;
            int indiceMaior = i;

            if (filhoEsq < qtd && dados[filhoEsq].compareTo(dados[indiceMaior]) > 0) {
                indiceMaior = filhoEsq;
            }
            if (filhoDir < qtd && dados[filhoDir].compareTo(dados[indiceMaior]) > 0) {
                indiceMaior = filhoDir;
            }

            if (indiceMaior == i) {
                break;
            }

            trocar(i, indiceMaior);
            i = indiceMaior;
        }
    }

    private void trocar(int i, int j) {
        T aux = dados[i];
        dados[i] = dados[j];
        dados[j] = aux;
    }

    public boolean estaVazia() {
        return qtd == 0;
    }

    public boolean estaCheia() {
        return qtd == dados.length;
    }

    public int tamanho() {
        return qtd;
    }

    public int capacidade() {
        return dados.length;
    }

    /**
     * Representação textual do heap em ordem de nível (raiz primeiro, depois
     * cada nível da esquerda para a direita). Útil para inspecionar a
     * estrutura interna após cada operação.
     *
     * @return string no formato {@code [e0; e1; e2; ...]}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < qtd; i++) {
            sb.append(dados[i]);
            if (i < qtd - 1) {
                sb.append("; ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
