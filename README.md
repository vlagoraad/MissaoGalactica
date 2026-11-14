# ★ Missão Galáctica · Estação Espacial Órbita-7

Projeto final da disciplina **Resolução de Problemas Estruturados em Computação** (Turma A · Noite · Profa. Lisiane Reips).

Programa de console em **Java** que registra, encontra e organiza os tripulantes de uma estação espacial, usando uma **tabela hash própria** e os algoritmos **Bubble Sort**, **Insertion Sort** e **Quick Sort**, com uma comparação de desempenho entre eles: a **Grande Corrida**.

**Grupo:** Amanda da Silva Freire e Valentina Lago Raad

---

## O cenário (Cenário A)

Os tripulantes chegam à estação e são registrados numa tabela hash, usando o **planeta natal** como chave: tripulantes do mesmo planeta sempre vão para o mesmo **módulo**. Na hora de voltar, a **cápsula de retorno** embarca todo mundo em ordem de **código galáctico**, e cada troca de assento vira uma manobra.

| No cenário | Corresponde a |
|---|---|
| Módulo da estação | Posição (balde) da tabela hash |
| Planeta natal | Chave da tabela (em maiúsculas e sem acento) |
| Tripulantes do mesmo planeta | Mesma chave → mesmo balde |
| Planetas diferentes no mesmo módulo | Colisão (tratada por lista encadeada) |
| Fila da cápsula de retorno | Vetor ordenado por código galáctico |
| Troca de assento | Uma troca (swap) do algoritmo |

---

## Como executar

1. Abra a pasta do projeto no **IntelliJ IDEA** (JDK 17 ou mais novo).
2. Confirme que o arquivo `tripulantes.txt` está na **raiz do projeto**, fora da pasta `src`.
3. Abra `Main.java` e clique no botão ▶ ao lado do método `main`.
4. Use o menu digitando o número da opção no console.

```
╔══════════════════════════════════════════╗
║          ★ MISSÃO GALÁCTICA ★            ║
║        Estação Espacial Órbita-7         ║
╠══════════════════════════════════════════╣
║ 1 - Registrar tripulante                 ║
║ 2 - Buscar tripulantes por planeta       ║
║ 3 - Remover tripulante (código)          ║
║ 4 - Mostrar módulos (tabela hash)        ║
║ 5 - Estatísticas da tabela               ║
║ 6 - Organizar embarque na cápsula        ║
║ 7 - Modo passo a passo                   ║
║ 8 - A Grande Corrida                     ║
║ 0 - Encerrar a missão                    ║
╚══════════════════════════════════════════╝
```

---

## Estrutura do projeto

| Arquivo | Responsabilidade |
|---|---|
| `Tripulante.java` | Dados de um tripulante: código, nome, planeta e função |
| `No.java` | Um elo da lista encadeada (tripulante + referência ao próximo nó) |
| `ListaEncadeada.java` | Lista de um balde: inserir, imprimir, buscar por planeta, remover por código e contar planetas diferentes |
| `TabelaHash.java` | Vetor de listas, normalização da chave, as duas funções hash, operações e estatísticas |
| `BubbleSort.java` | Bubble Sort com contadores, parada antecipada e modo narrado |
| `InsertionSort.java` | Insertion Sort com contadores (deslocamentos) e modo narrado |
| `QuickSort.java` | Quick Sort (pivô = último elemento) com contadores e modo narrado |
| `Gerador.java` | Cria 50, 1.000 ou 10.000 tripulantes aleatórios com códigos únicos |
| `GrandeCorrida.java` | Mede tempo, comparações e trocas dos 3 algoritmos em 3 situações e 3 tamanhos |
| `Main.java` | Leitura do arquivo e menu do programa |
| `tripulantes.txt` | Os 50 tripulantes iniciais |

### Formato do arquivo de dados

Um tripulante por linha, com os campos separados por `;`:

```
codigo;nome;planeta;funcao
50318842;Zibo Zumbido;Marte;Piloto
```

São 50 tripulantes (20 do Apêndice e 30 criados pelo grupo), de 18 planetas diferentes. Marte tem 9 tripulantes de propósito, para encher um módulo.

---

## Decisões do grupo

**Tabela hash**
- **Implementação própria**, sem `HashMap`, `Hashtable`, `HashSet` ou outra estrutura pronta. As colisões são tratadas por **encadeamento exterior**: cada posição do vetor guarda uma lista encadeada feita à mão.
- **Normalização da chave:** o planeta é convertido para maiúsculas e sem acento (`"Plutão"` → `"PLUTAO"`) antes de qualquer cálculo. O planeta original é mantido no tripulante, para exibição.
- **Inserção no início da lista**, porque é imediata e a ordem dentro do balde não importa.
- **Remoção por código** percorre todos os módulos, porque o código não é a chave da tabela.
- **Colisões contadas como colisões reais:** só contam planetas *diferentes* no mesmo módulo (em cada módulo, planetas diferentes − 1). Tripulantes do mesmo planeta no mesmo módulo não são colisão.
- **Tamanhos testados:** M = 13 e M = 17 (pedidos no enunciado) e M = 19, escolhido pelo grupo.

**Funções hash**
1. **Soma simples** (material de apoio): soma dos códigos dos caracteres, `% M`. Ignora a ordem das letras (`ROMA` e `AMOR` dão o mesmo resultado).
2. **Soma ponderada** (criada pelo grupo): cada caractere é multiplicado pela sua posição na palavra (1ª, 2ª, 3ª...) antes de somar, `% M`. Assim, a ordem das letras passa a mudar o resultado.

**Ordenação**
- Sem `Arrays.sort`, `Collections.sort` ou qualquer ordenação pronta.
- Ordena por **código galáctico** (8 dígitos, único).
- Cada algoritmo trabalha sobre uma **cópia dos mesmos dados**.
- Contadores do tipo `long`, porque o Bubble Sort com 10.000 elementos passa de 49 milhões de comparações.
- O Insertion Sort usa a variável `chave` e conta **deslocamentos**, em vez de trocas.
- O Quick Sort usa o **último elemento como pivô**, como no material da aula. A troca de um elemento por ele mesmo também é contada.
- **Modo passo a passo:** um interruptor (`narrar`) em cada algoritmo liga a narração. Usa de 2 a 10 tripulantes do início do arquivo. Com 10 e o Bubble Sort, reproduz o exemplo do enunciado.

**Grande Corrida**
- Tempo medido com `System.nanoTime()`, com a narração desligada.
- **Códigos únicos sem `HashSet`:** cada tripulante sorteia um código dentro de uma faixa própria ("degrau" de 90.000.000 / n), então nunca há repetição. O vetor nasce em ordem crescente; para as outras situações, ele é invertido ou embaralhado (Fisher-Yates, implementado à mão).
- Depois de cada corrida, o método `estaOrdenado` confere se o resultado está correto.

---

## Resultados

Os resultados medidos (estatísticas da tabela hash e as três execuções da Grande Corrida) estão em [`resultados_missao_galactica.md`](resultados_missao_galactica.md). A análise completa está no relatório em PDF enviado junto com o código fonte.