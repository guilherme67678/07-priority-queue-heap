# Fila de Prioridade com Heap Binário — Pronto-Socorro "São Binário"

Sistema de triagem do Pronto-Socorro do Hospital fictício **"São Binário"**,
implementado como uma **Fila de Prioridade** baseada em **Heap Binário Máximo**.
Os pacientes são atendidos por **prioridade clínica**, não por ordem de chegada.

## Descrição

A aplicação registra a chegada de pacientes e os organiza em um heap binário
de modo que o próximo a ser atendido seja sempre o de maior prioridade clínica.
Cada paciente possui três atributos que, em conjunto, definem sua posição
na fila:

| Atributo               | Tipo            | Descrição                                                   |
| ---------------------- | --------------- | ----------------------------------------------------------- |
| `nome`                 | `String`        | Nome do paciente.                                           |
| `nivelUrgencia`        | `int` (1 a 5)   | Protocolo simplificado (veja tabela de cores abaixo).       |
| `tempoEsperaMinutos`   | `int`           | Minutos que o paciente aguarda atendimento.                 |
| `grupoVulneravel`      | `boolean`       | `true` se o paciente tem menos de 12 ou mais de 65 anos.    |

### Níveis de urgência (Protocolo de Manchester simplificado)

| Nível | Cor       | Classificação      |
| ----- | --------- | ------------------ |
| 5     | Vermelho  | Imediato           |
| 4     | Laranja   | Muito Urgente      |
| 3     | Amarelo   | Urgente            |
| 2     | Verde     | Pouco Urgente      |
| 1     | Azul      | Não Urgente        |

### Regras de prioridade (critérios em cascata)

1. **Nível de urgência:** paciente com `nivelUrgencia` maior é atendido primeiro.
2. **Tempo de espera:** em caso de empate, vence o de maior `tempoEsperaMinutos`.
3. **Grupo vulnerável:** persistindo o empate, vence quem tem `grupoVulneravel == true`.

## Estrutura do projeto

```
src/
├── Main.java                              # Simulação do cenário do hospital
├── TesteFilaEstatica.java                 # Testes da fila estática original
├── entidades/
│   ├── Paciente.java                      # Entidade com as regras de prioridade
│   └── Pessoa.java                        # Entidade legada do projeto base
├── estatica/
│   ├── FilaEstatica.java                  # Fila simples (FIFO) — base
│   └── FilaComPrioridade.java             # Fila de prioridade por ordenação linear
└── heap/
    └── FilaComPrioridadeHeap.java         # Fila de prioridade com Heap Binário
```

## Requisitos

- **JDK 21** ou superior (testado com Eclipse Temurin 21).
- Sistema operacional indiferente: Windows, Linux ou macOS.

Verifique a versão instalada:

```bash
javac -version
java  -version
```

## Compilação

A partir da raiz do projeto:

```bash
javac -d out -encoding UTF-8 \
  src/entidades/Paciente.java \
  src/entidades/Pessoa.java \
  src/estatica/FilaEstatica.java \
  src/estatica/FilaComPrioridade.java \
  src/heap/FilaComPrioridadeHeap.java \
  src/Main.java
```

No Windows (PowerShell), substitua as barras invertidas de continuação por
crases (`` ` ``) ou execute o comando em uma única linha.

## Execução

```bash
java -cp out Main
```

A saída registra a chegada dos 6 pacientes de teste, imprime o estado interno
do heap após cada inserção e, em seguida, remove todos em ordem de prioridade.

### Ordem de atendimento esperada

| # | Paciente | Justificativa                                         |
| - | -------- | ----------------------------------------------------- |
| 1 | Maria    | Vermelho, maior espera entre os críticos.             |
| 2 | Pedro    | Vermelho, mesma urgência, menor espera.               |
| 3 | Beatriz  | Amarelo, maior espera e vulnerável.                   |
| 4 | João     | Amarelo, menor espera.                                |
| 5 | Helena   | Verde, mesma espera de Carlos, porém vulnerável.      |
| 6 | Carlos   | Verde, última prioridade.                             |

## Algoritmos implementados

A classe [`FilaComPrioridadeHeap`](src/heap/FilaComPrioridadeHeap.java)
implementa as duas operações principais com complexidade **O(log n)**:

- **`enfileirar` (Sobe Heap / sift-up):** insere o novo elemento na última
  posição livre e o sobe trocando com o pai enquanto possuir prioridade maior,
  restaurando a propriedade de heap.
- **`desenfileirar` (Desce Heap / sift-down):** remove a raiz (elemento de
  maior prioridade), promove a última folha para a raiz e a faz descer
  trocando com o filho de maior prioridade.

Relação pai/filhos no array (índice base 0):

```
pai(i)          = (i - 1) / 2
filhoEsq(i)     = 2 * i + 1
filhoDir(i)     = 2 * i + 2
```

## Geração local do Javadoc

```bash
javadoc -d doc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 \
  -sourcepath src -subpackages estatica:heap:entidades
```

Abra `doc/index.html` no navegador para consultar a documentação.

## Javadoc publicado (GitHub Pages)

A documentação é gerada e publicada automaticamente a cada push em `main`
pelo workflow [`javadoc.yml`](.github/workflows/javadoc.yml).

🔗 **<https://guilherme67678.github.io/07-priority-queue-heap/>**

### Habilitar o GitHub Pages (uma única vez)

No repositório do GitHub:

1. Acesse **Settings → Pages**.
2. Em **Source**, selecione **GitHub Actions**.
3. Faça um push em `main` para disparar o workflow e publicar a documentação.

## Autor

Trabalho M2 — Estruturas de Dados.
