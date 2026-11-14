# Missão Galáctica · Resultados medidos (dados para o relatório)

Cenário A · Estação Espacial Órbita-7 · 50 tripulantes no arquivo (20 do Apêndice + 30 do grupo), 18 planetas diferentes.

---

## 1. Tabela hash

### 1.1 Exibição com M = 13, soma simples (antes de remover o Zibo)

| Módulo | Tripulantes | Planetas no módulo |
|---|---|---|
| [0] | 9 | Marte (só Marte) |
| [1] | 13 | Andrômeda, Plutão, Júpiter, Órion |
| [2] | 0 | (vazio) |
| [3] | 1 | Mercúrio |
| [4] | 0 | (vazio) |
| [5] | 7 | Europa, Netuno, Vega |
| [6] | 2 | Ceres |
| [7] | 3 | Titã |
| [8] | 0 | (vazio) |
| [9] | 1 | Kepler |
| [10] | 4 | Saturno |
| [11] | 7 | Proxima, Vênus, Sírius |
| [12] | 3 | Gliese, Urano |

Conta manual de exemplo (soma simples):
- MARTE: 77 + 65 + 82 + 84 + 69 = 377 → 377 % 13 = **0** · 377 % 17 = **3**
- VEGA: 86 + 69 + 71 + 65 = 291 → 291 % 13 = **5**
- NETUNO: 78 + 69 + 84 + 85 + 78 + 79 = 473 → 473 % 13 = **5** (colisão real com VEGA)
- MARTE na soma ponderada: 77×1 + 65×2 + 82×3 + 84×4 + 69×5 = 1134 → 1134 % 13 = **3**

### 1.2 Estatísticas: 3 tamanhos × 2 funções hash

Colisões contadas como **planetas diferentes no mesmo módulo** (em cada módulo: planetas diferentes − 1).

| M | Função | Fator de carga | Módulos vazios | Maior lista | Colisões reais |
|---|---|---|---|---|---|
| 13 | Soma simples | 3,85 | 3 de 13 | 13 | 8 |
| 13 | Soma ponderada | 3,85 | 3 de 13 | 12 | 8 |
| 17 | Soma simples | 2,94 | 5 de 17 | 15 | 6 |
| 17 | Soma ponderada | 2,94 | 6 de 17 | 11 | 7 |
| 19 | Soma simples | 2,63 | 7 de 19 | 9 | 6 |
| 19 | Soma ponderada | 2,63 | 5 de 19 | 15 | **4** |

Observações:
- Marte tem 9 tripulantes, então a maior lista nunca fica abaixo de 9, qualquer que seja a função.
- Com só 18 planetas, nenhuma função venceu em todos os critérios.

---

## 2. Ordenação: a mesma fila de 50 tripulantes (vinda da tabela M = 13)

| Algoritmo | Comparações | Movimentos |
|---|---|---|
| Bubble Sort | 1.189 | 574 trocas |
| Insertion Sort | 621 | 574 deslocamentos |
| Quick Sort | 295 | 181 trocas |

Referências teóricas para n = 50: máximo de comparações n(n−1)/2 = 1.225 · trocas médias esperadas n(n−1)/4 ≈ 612 · n·log₂(n) ≈ 282.

## 3. Modo passo a passo: os 10 primeiros do arquivo

| Algoritmo | Comparações | Movimentos |
|---|---|---|
| Bubble Sort | 45 (9 passagens) | 21 trocas |
| Insertion Sort | 28 | 21 deslocamentos |
| Quick Sort | 28 | 11 trocas |

Destaques:
- A narração do Bubble Sort bateu exatamente com o exemplo do enunciado.
- Nebulosa Nina (16672043, 2º menor código, começou na última posição): no Bubble precisou de 8 passagens, andando uma poltrona por vez; no Insertion chegou à frente numa única rodada.
- Quick Sort: os pivôs foram quase sempre dos menores do trecho (1º pivô = Nina), as partições ficaram desequilibradas, e ele empatou com o Insertion em comparações.

---

## 4. A Grande Corrida (3 execuções)

### 4.1 Tempo médio das 3 execuções (milissegundos)

| Algoritmo | Situação | n = 50 | n = 1.000 | n = 10.000 |
|---|---|---|---|---|
| Bubble Sort | Aleatório | 0,117 | 11,663 | 125,318 |
| Bubble Sort | Já ordenado | 0,001 | 0,002 | 0,014 |
| Bubble Sort | Ordem inversa | 0,026 | 2,483 | 121,481 |
| Insertion Sort | Aleatório | 0,064 | 5,558 | 51,177 |
| Insertion Sort | Já ordenado | 0,019 | 0,084 | 0,125 |
| Insertion Sort | Ordem inversa | 0,007 | 1,539 | 78,600 |
| Quick Sort | Aleatório | 0,035 | 0,771 | 2,120 |
| Quick Sort | Já ordenado | 0,011 | 1,134 | 100,636 |
| Quick Sort | Ordem inversa | 0,008 | 0,735 | 89,143 |

### 4.2 Tempos de cada execução (milissegundos)

| Algoritmo | Situação | Execução | n = 50 | n = 1.000 | n = 10.000 |
|---|---|---|---|---|---|
| Bubble Sort | Aleatório | 1 / 2 / 3 | 0,105 / 0,120 / 0,126 | 8,936 / 12,056 / 13,996 | 128,677 / 123,319 / 123,958 |
| Bubble Sort | Já ordenado | 1 / 2 / 3 | 0,001 / 0,001 / 0,001 | 0,003 / 0,001 / 0,001 | 0,014 / 0,014 / 0,015 |
| Bubble Sort | Ordem inversa | 1 / 2 / 3 | 0,041 / 0,021 / 0,017 | 1,476 / 4,790 / 1,183 | 117,585 / 132,346 / 114,513 |
| Insertion Sort | Aleatório | 1 / 2 / 3 | 0,046 / 0,068 / 0,079 | 5,503 / 5,543 / 5,629 | 53,012 / 53,250 / 47,269 |
| Insertion Sort | Já ordenado | 1 / 2 / 3 | 0,021 / 0,021 / 0,016 | 0,102 / 0,077 / 0,073 | 0,113 / 0,102 / 0,160 |
| Insertion Sort | Ordem inversa | 1 / 2 / 3 | 0,006 / 0,006 / 0,010 | 0,792 / 0,774 / 3,050 | 77,565 / 76,202 / 82,034 |
| Quick Sort | Aleatório | 1 / 2 / 3 | 0,033 / 0,032 / 0,040 | 0,792 / 0,732 / 0,789 | 2,070 / 2,173 / 2,116 |
| Quick Sort | Já ordenado | 1 / 2 / 3 | 0,011 / 0,011 / 0,012 | 1,160 / 1,094 / 1,149 | 101,198 / 100,024 / 100,687 |
| Quick Sort | Ordem inversa | 1 / 2 / 3 | 0,010 / 0,008 / 0,007 | 0,712 / 0,747 / 0,746 | 87,152 / 91,201 / 89,077 |

### 4.3 Comparações (execução 1)

Nas situações "já ordenado" e "ordem inversa" os valores são idênticos nas 3 execuções. No "aleatório" variam um pouco (faixa das 3 execuções entre colchetes).

| Algoritmo | Situação | n = 50 | n = 1.000 | n = 10.000 |
|---|---|---|---|---|
| Bubble Sort | Aleatório | 1.180 [1.180 – 1.224] | 499.290 [498.759 – 499.290] | 49.965.839 [49.965.839 – 49.994.790] |
| Bubble Sort | Já ordenado | 49 | 999 | 9.999 |
| Bubble Sort | Ordem inversa | 1.225 | 499.500 | 49.995.000 |
| Insertion Sort | Aleatório | 675 [644 – 682] | 251.858 [248.818 – 251.858] | 25.094.579 [25.003.958 – 25.094.579] |
| Insertion Sort | Já ordenado | 49 | 999 | 9.999 |
| Insertion Sort | Ordem inversa | 1.225 | 499.500 | 49.995.000 |
| Quick Sort | Aleatório | 244 [236 – 290] | 11.009 [10.109 – 11.729] | 161.622 [150.034 – 161.622] |
| Quick Sort | Já ordenado | 1.225 | 499.500 | 49.995.000 |
| Quick Sort | Ordem inversa | 1.225 | 499.500 | 49.995.000 |

### 4.4 Trocas (deslocamentos no Insertion Sort), execução 1

| Algoritmo | Situação | n = 50 | n = 1.000 | n = 10.000 |
|---|---|---|---|---|
| Bubble Sort | Aleatório | 628 [601 – 635] | 250.866 [247.826 – 250.866] | 25.084.586 [24.993.966 – 25.084.586] |
| Bubble Sort | Já ordenado | 0 | 0 | 0 |
| Bubble Sort | Ordem inversa | 1.225 | 499.500 | 49.995.000 |
| Insertion Sort | Aleatório | 628 [601 – 635] | 250.866 [247.826 – 250.866] | 25.084.586 [24.993.966 – 25.084.586] |
| Insertion Sort | Já ordenado | 0 | 0 | 0 |
| Insertion Sort | Ordem inversa | 1.225 | 499.500 | 49.995.000 |
| Quick Sort | Aleatório | 187 [148 – 187] | 6.801 [5.507 – 6.801] | 76.587 [76.587 – 86.082] |
| Quick Sort | Já ordenado | 1.274 | 500.499 | 50.004.999 |
| Quick Sort | Ordem inversa | 649 | 250.499 | 25.004.999 |

Observações:
- Em todas as execuções, trocas do Bubble = deslocamentos do Insertion (o número de inversões da fila).
- Nenhum aviso de erro do verificador `estaOrdenado`.