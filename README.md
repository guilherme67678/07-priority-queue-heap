# Fila de Prioridade com Heap

Trabalho M2 - Estrutura de Dados

## Descrição

Sistema de triagem do Pronto-Socorro do Hospital São Binário implementado com uma Fila de Prioridade baseada em Heap Binário.

Os pacientes são atendidos por prioridade clínica (não por ordem de chegada). A prioridade é determinada por 3 critérios em cascata:

1. Nível de urgência (1 a 5) — maior atende primeiro
2. Tempo de espera — maior espera atende primeiro
3. Grupo vulnerável (menores de 12 ou maiores de 65 anos) — tem preferência

Níveis de urgência:

- 5 — Vermelho — Imediato
- 4 — Laranja — Muito Urgente
- 3 — Amarelo — Urgente
- 2 — Verde — Pouco Urgente
- 1 — Azul — Não Urgente

## Requisitos

1. Utilização de Heap para implementação da Fila de Prioridade;
2. Criar um fork do projeto 07-priority-queue-heap;
3. Implementar o método de enfileirar com o algoritmo "Sobe Heap";
4. Implementar o método de desenfileirar com o algoritmo "Desce Heap";
5. Implementar a entidade Paciente com as regras da seção anterior;
6. README.md elaborado seguindo as boas práticas apresentadas durante a disciplina (descrição, requisitos, compilação, execução, etc);
7. Publicação do Javadoc do projeto no Github Pages.

## Estrutura do projeto

```
src/
├── entidades/
│   └── Paciente.java
├── heap/
│   └── FilaComPrioridadeHeap.java
└── Main.java
```

## Compilação

```bash
javac -d out src/entidades/Paciente.java src/heap/FilaComPrioridadeHeap.java src/Main.java
```

## Execução

```bash
java -cp out Main
```

## Gerar Javadoc

```bash
javadoc -d doc -sourcepath src -subpackages entidades:heap
```

A documentação fica disponível em `doc/index.html`.

## Saída esperada

Ordem de atendimento dos pacientes de teste:

1. Maria (Vermelho) — maior espera entre os críticos
2. Pedro (Vermelho) — mesma urgência, menor espera
3. Beatriz (Amarelo) — maior espera e vulnerável
4. João (Amarelo) — menor espera
5. Helena (Verde) — mesma espera de Carlos, mas vulnerável
6. Carlos (Verde) — última prioridade
