
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class GeneralTreeOfInteger {

    // Classe interna Node
    private class Node {
        // Atributos da classe Node
        // Node agora tem os atributos String ao invés de Integer. Devido a essa mudança, todos os métodos que usavam element como Integer foram adaptados para utilizar String.
        // Node também tem um guerreiro.

        public Node father;
        public String element;
        public LinkedList<Node> subtrees;
        public Guerreiro guerreiro;

        // Métodos da classe Node
        public Node(String element) {
            father = null;
            this.element = element;
            subtrees = new LinkedList<>();
        }

        public Node(String element, Guerreiro g) {
            father = null;
            this.element = element;
            subtrees = new LinkedList<>();
            this.guerreiro = g;
        }

        private void addSubtree(Node n) {
            n.father = this;
            subtrees.add(n);
        }
        private boolean removeSubtree(Node n) {
            n.father = null;
            return subtrees.remove(n);
        }
        public Node getSubtree(int i) {
            if ((i < 0) || (i >= subtrees.size())) {
                throw new IndexOutOfBoundsException();
            }
            return subtrees.get(i);
        }
        public int getSubtreesSize() {
            return subtrees.size();
        }
        public Guerreiro getGuerreiro(){
            return this.guerreiro;
        }
        public void setGuerreiro(Guerreiro g){
            this.guerreiro = g;
        }
    }
    
    // Atributos da classe GeneralTreeOfInteger
    private Node root;
    private int count;
    
    // Metodos da classe GeneralTreeOfInteger
    
    // Construtor
    public GeneralTreeOfInteger() {
        root = null;
        count = 0;
    }
    
    // Procura por "elem" a partir de "n" seguindo um
    // caminhamento pre-fixado. Retorna a referencia
    // para o nodo no qual "elem" esta armazenado.
    // Se não encontrar "elem", ele retorna null.    
    private Node searchNodeRef(String elem, Node n) {
        if (n == null)
            return null;
        
        // visita a raiz
        if (elem.equals(n.element)) { // se elem esta no nodo n
            return n; // retorna a referencia para n
        }
        // visita os filhos
        else {
            Node aux = null;
            for (int i=0; i<n.getSubtreesSize() && aux==null; i++) {
                aux = searchNodeRef(elem, n.getSubtree(i));
            }
            return aux;
        }        
    }
    
    // Insere "elem" na árvore como filho de "elemPai" e retorna true.
    // Retora false se não encontrar "elemPai".
    public boolean add(String elem, String elemPai) {
        // Primeiro cria o nodo
        Node n = new Node(elem);
        
        if (elemPai == null) { // inserir elem na raiz
            if (root!=null) { // se a arvore nao estava vazia
                n.addSubtree(root);// inserir elemPai como raiz
                root.father = n;
            }
            root = n;
            count ++;
            return true;
        }
        else { // inserir "elem" como filho de "elemPai"
            Node aux = searchNodeRef(elemPai, root);
            if (aux != null) { 
                // aux esta apontando para o nodo onde elemPai esta armazenado
                aux.addSubtree(n);
                n.father = aux;
                count++;
                return true;
            }
        }
        
        return false;
    }

    //Método utilizado para criar um nodo adicionando também um guerreiro.
    public boolean add(String elem, String elemPai, Guerreiro g) {
        // Primeiro cria o nodo
        Node n = new Node(elem, g);
        
        if (elemPai == null) { // inserir elem na raiz
            if (root!=null) { // se a arvore nao estava vazia
                n.addSubtree(root);// inserir elemPai como raiz
                root.father = n;
            }
            root = n;
            count ++;
            return true;
        }
        else { // inserir "elem" como filho de "elemPai"
            Node aux = searchNodeRef(elemPai, root);
            if (aux != null) { 
                // aux esta apontando para o nodo onde elemPai esta armazenado
                aux.addSubtree(n);
                n.father = aux;
                count++;
                return true;
            }
        }
        
        return false;
    }
    
    // Retorna uma lista com todos os elementos da árvore numa ordem de 
    // caminhamento em largura
    public LinkedList<Guerreiro> positionsWidth() {
        LinkedList<Guerreiro> lista = new LinkedList<>();
        Queue<Node> fila = new Queue<>(); // cria fila de nodos
        if (root != null) { // se a arvore nao estiver vazia
            // Primeiro coloca a raiz na fila
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                // Retira da fila e coloca na lista
                Node aux = fila.dequeue();
                lista.add(aux.guerreiro);
                // Coloco os filhos de aux na fila
                for (int i=0; i<aux.getSubtreesSize(); i++) {
                    fila.enqueue(aux.getSubtree(i));
                }
            }
        }       
        return lista;
    }    
    
    // Retorna uma lista com todos os elementos da árvore numa ordem de 
    // caminhamento pré-fixado
    public LinkedList<String> positionsPre() {  
        LinkedList<String> lista = new LinkedList<>();
        positionsPreAux(root,lista);
        return lista;
    }  
    private void positionsPreAux(Node n, LinkedList<String> lista) { // metodo recursivo
        if (n != null) {
            // visita a raiz
            lista.add(n.element);
            // visita os filhos
            for (int i=0; i<n.getSubtreesSize(); i++) {
                positionsPreAux(n.getSubtree(i), lista);
            }
        } 
    }

    //Implementa a herança de terras
    public LinkedList<String> passaTerraPre() {  
        LinkedList<String> lista = new LinkedList<>();
        passaTerraPreAux(root,lista);
        return lista;
    }  
    private void passaTerraPreAux(Node n, LinkedList<String> lista) { // metodo recursivo
        if (n != null) {
            // visita a raiz
            lista.add(n.element);

            // visita os filhos
            for (int i=0; i<n.getSubtreesSize(); i++) {
                // divide a herança deixa pelo pai para os filhos de forma igual, de acordo com o número de filhos.
                Integer heranca = n.guerreiro.getTerras()/n.getSubtreesSize();
                // soma a herança às terras conquistadas em vida e armazena o valor em um objeto guerreiro.
                n.getSubtree(i).getGuerreiro().setTerras(heranca + n.getSubtree(i).getGuerreiro().getTerras());
                passaTerraPreAux(n.getSubtree(i), lista);
            }
        } 
    }

    // Retorna uma lista com todos os elementos da árvore numa ordem de 
    // caminhamento pós-fixado
    public LinkedList<String> positionsPos() {  
        LinkedList<String> lista = new LinkedList<>();
        positionsPosAux(root,lista);
        return lista;
    }  
    private void positionsPosAux(Node n, LinkedList<String> lista) {
        if (n!=null) {
            // visita os filhos
             for (int i=0; i<n.getSubtreesSize(); i++) {
                positionsPosAux(n.getSubtree(i), lista);
            }           
            // visita a raiz
            lista.add(n.element);
        }
    }    
    
    // Retorna o numero de elementos da arvore
    public int size() {
        return count;
    }
    
    // Verifica se elem esta na arvore
    public boolean contains(String elem) {
        // Procura por "elem" a partir da raiz
        Node aux = searchNodeRef(elem, root);
        if (aux==null)
            return false;
        else
            return true;
    }
   
    
    // Remove um galho da arvore
    public boolean removeBranch(String element) { 
        if (root == null) // se arvore vazia
            return false;
        
        // se element está na raiz
        if (element.equals(root.element)) {
            root = null;  // PERGUNTA: Em C++ será necessario remover os filhos da raiz. Aqui não?
            count = 0;    // Acredito que nao precise pois o Garbarbage collector faz isto, correto?
            return true;
        }
        
        // Procura por element na arvore
        Node aux = searchNodeRef(element, root);
        
        if (aux == null) // se nao encontrou element
            return false;
        
        Node pai = aux.father;
        pai.removeSubtree(aux); // PERGUNTA: isto remove aux da árvore, certo?
        aux.father = null; // opcional  // PERGUNTA: isto nao daria erro já que aux acabou de ser removido?
        count = count - countNodes(aux);// PERGUNTA: depois que o método removeSubtree(aux) foi chamado 
                                        // o countNodes ainda funciona para o aux? 
        return true; 
    }

    // Conta o numero de nodos da subarvore suja raiz eh passada por parametro
    private int countNodes(Node n) {
        if (n==null)
            return 0;
        int c = 1;
        for (int i=0; i<n.getSubtreesSize(); i++) {
            c = c + countNodes(n.getSubtree(i));
        }
        return c;
    } 
    
    // Retorna em que nível o elemento está 
    public int level(String element) {    
        // Busca element
         Node aux = searchNodeRef(element,root);
        
        // Se não encontrou
        if (aux == null) // se nao encontrou
            throw new NoSuchElementException();
        
        // Se encontrou, entra num laço
        // e faz receber referência para o
        // pai até chegar a raiz, e vai contando.
        int c = 0;
        while (aux != root) {
            aux = aux.father;
            c++;
        }
        return c;
    }      
    
    // Procura pelo elemento e retorna true se ele estiver 
    // armazenado em um nodo folha.
    public boolean isExternal(String element) {
        // Procura por element
         Node aux = searchNodeRef(element,root);
        
        // Se não encontrou
        if (aux == null) // se nao encontrou
            throw new NoSuchElementException();
        
        if (aux.getSubtreesSize()==0)
            return true;
        
        return false;
    } 

    // Procura pelo elemento e retorna true se ele estiver 
    // armazenado em um nodo interno.
    public boolean isInternal(String element) {
        // Procura por element
         Node aux = searchNodeRef(element,root);
        
        // Se não encontrou
        if (aux == null) // se nao encontrou
            throw new NoSuchElementException();
        
        if (aux != root && aux.getSubtreesSize()>0)
            return true;
        
        return false;
    }
    
    // VALE EMBLEMA!!!
    // Retorna quantos filhos tem, o nodo com mais filhos.
    public int getMaxChildren() {
        return getMaxChildren(root, 0);
    } 
    private int getMaxChildren(Node n, int numFilhos) {
        // if (n == null)
        //  return numFilhos
        // Se n não tem filhos
        //  return numFilhos
        // Verifica se o número de filho de n eh maior que numFilhos
        // Se for, atualiza numFilhos
        // Chama o metodo recursivamente para cada filho
        return numFilhos;
    }
    
    // Metodo que retorna a altura da arvore.
    public int height() { 
        if (root==null || root.getSubtreesSize()==0)
          return 0;
        else
            return height(root);
    }
    private int height(Node n) {
        if (n.getSubtreesSize()==0)
            return 0;
        else {
            int h=0;
            for(int i=0; i<n.getSubtreesSize(); i++) {
                h = Math.max(h, height(n.getSubtree(i)));
            }
            return 1+h;
        }
    }    
    void imprimeString (String S)
    {
        System.out.println (S);
    }

    // Utiliza de um objeto guerreiro, atributo do parametro Node n, para fazer uma label com nome e terras, utilizando apenas uma lista.
    public void geraNodosDOT(Node n)
    {
        
        imprimeString("node [shape = circle];\n");
        
        LinkedList<Guerreiro> L = new LinkedList<>();
        L = positionsWidth();

        for (int i = 0; i< L.size(); i++ )
        {
            // node1 [label = "1"]
            imprimeString("node" + L.get(i).getNome() + " [label = \"" +  L.get(i).getNome()+" tem "+L.get(i).getTerras() +" terras"+ "\"]") ;
        }
    }

    public void geraConexoesDOT(Node n)
    {
        for (int i=0; i<n.getSubtreesSize(); i++)
        {
            Node aux = n.getSubtree(i);
            imprimeString("node" + n.element + " -> " + "node" + aux.element + ";");
            geraConexoesDOT(aux);
        }
    }
// Gera uma saida no formato DOT
// Esta saida pode ser visualizada no GraphViz
// Versoes online do GraphViz pode ser encontradas em
// http://www.webgraphviz.com/
// http://viz-js.com/
// https://dreampuf.github.io/GraphvizOnline 
    public void geraDOT()
    {
        imprimeString("digraph g { \n");
        // node [style=filled];
        
        geraNodosDOT(root);
        
        geraConexoesDOT(root);
        imprimeString("}\n");

    }

}
