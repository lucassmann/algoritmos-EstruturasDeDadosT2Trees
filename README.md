# algoritmos-EstruturasDeDadosT2Trees
Algorithms and data structures college final project about tree data structures (and warriors).

Projeto final da disciplina de algoritmos e estrutura de dados sobre estruturas de árvore (e guerreiros).

Trabalho 2 

# Algoritmos e Estrutura de Dados I 

## Alunos: Gabriel Isdra Moszkowicz e Lucas Pereira Assmann 
 

O problema desde trabalho se trata de uma estrutura de dados de árvore, sendo possível relacionar com a ideia intuitiva de árvore genealógica no português. 

Os principais pontos de partida para nossa solução foram os dados que constam nos casos de teste, o código base disponibilizado para o trabalho, principalmente no que tange a leitura desses dados e do código de árvore genérica, trabalhado em aula e disponibilizado no Moodle da disciplina. 

A solução utilizada foi modelada pensando em 4 momentos: 

Como descobrir quem é o primeiro guerreiro da linhagem (root da árvore)? 

Como relacionar as informações dos guerreiros com a árvore genealógica da tribo? 

Uma vez com a árvore da tribo construída, como implementar a herança de terras? 

Implementada a herança de terras, como determinar qual o guerreiro da última geração da tribo que ficou com mais terras, considerando aquelas conquistadas e herdadas? 

Quanto à descoberta de qual guerreiro é o primeiro da linguagem, utilizamos de ArrayList de String para fazer duas listas: uma de nomes de pais e outra de nomes de filhos. Em seguida, iteramos o ArrayList para procurar pela String que representa o nome de um guerreiro que está na lista de pais, mas não na lista de filhos. 

Já quanto ao relacionamento das informações dos guerreiros com a árvore, utilizamos de uma combinação de programação orientada a objetos e modificações nas classes GeneralTreeOfInteger, Node e seus métodos. 

Para mantermos informações sobre cada guerreiro organizadas e acessíveis, criamos uma classe que define um objeto Guerreiro, com as informações obtidas nos arquivos de casos. Em seguida, armazenamos cada guerreiro em um ArrayList de Guerreiro. A principal vantagem desse método é uma facilidade para a leitura e processamento dos arquivos de texto. Outro ponto positivo que vale ressaltar é a possibilidade de iterar a lista de guerreiros com apenas um forEach, que foi utilizado para popular a árvore com os guerreiros de pai conhecido, ou seja, todos aqueles que não vão ser a raiz da árvore.   

Visto que já havíamos determinado o primeiro guerreiro da linhagem, que agora é a raiz da árvore, este foi relacionado com a quantidade de terras inicial, através do objeto Guerreiro. 

Para a relação entre os guerreiros e a árvore, a classe da árvore foi modificada. Entre as principais modificações, se destacam: o atributo element, da classe GeneralTreeOfInteger, passa a ser do tipo String, para identificar os guerreiros por nome, e Node passa ter também um objeto Guerreiro como atributo. 

Para implementar a herança de terras, partimos do método positionsPre. Entendemos que trabalhar em uma ordem pré-fixada é ideal para esse problema, pois as terras vão dos guerreiros mais ancestrais para os últimos guerreiros da última geração da tribo. 

Para verificar quais são os guerreiros da última geração da tribo, utilizamos dos métodos level e height. Assim, iteramos pelos guerreiros da tribo e selecionamos aqueles de mesmo nível que a altura da árvore. Em seguida armazenamos os guerreiros dessa última linhagem e comparamos a quantidade de terras de cada, por fim determinando qual o guerreiro da última geração da tribo que mais tem terras. 

Quanto ao código para geração de arquivos para graphviz, novamente utilizamos dos métodos já existentes no código de árvore genérica e do objeto Guerreiro para, em uma mesma LinkedList, armazenar nomes e quantidades de terras. 
