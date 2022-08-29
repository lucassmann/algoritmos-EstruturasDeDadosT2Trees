import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException {

        //ArrayList de guerreiros para facilitar o processamento de dados dos casos até que estes sejam incorporados na árvore.
        ArrayList<Guerreiro> guerreiros = new ArrayList<>();

        //Variável para armazenar o primeiro número, que representa as terras do primeiro guerreiro da linhagem.
        Integer terraInical=0;

        //Lendo os arquivos
        String pathName = "caso04a";
        Path path1 = Paths.get("casosDeTestes/"+pathName+".txt");
        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String aux[];
            String s = reader.readLine();
            if (s != null)
                //Armazena o primeiro número do arquivo para uso futuro na raiz da árvore.
                terraInical = Integer.parseInt(s);
            String line = null;
            while ((line = reader.readLine()) != null) {
                aux = line.split(" ");
                //Armazena o resto dos informações de cada linha do arquivo.
                String auxNomePai = aux[0];
                String auxNome = aux[1];
                Integer auxTerras = Integer.parseInt(aux[2]);
                //Usa essas informações para criar um Guerreiro e adicionar o mesmo para a lista de guerreiros.
                Guerreiro auxGuerreiro = new Guerreiro(auxNomePai, auxNome, auxTerras);
                guerreiros.add(auxGuerreiro);
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }       

        //Determinando qual guerreiro vai ser root: o guerreiro que for pai, mas não for filho é root.
        String nomeRoot="";
        Guerreiro guerreiroRoot = new Guerreiro(null, "", 0);
        ArrayList<String> pais = new ArrayList<>();
        ArrayList<String> filhos = new ArrayList<>();
        for (Guerreiro g : guerreiros){
            if (!pais.contains(g.getNomePai())){
                pais.add(g.getNomePai());
            }
            filhos.add(g.getNome());
        }
        for (String pai : pais) {
            if (!filhos.contains(pai)){
                guerreiroRoot.setNome(pai);
                guerreiroRoot.setTerras(terraInical);
            }
        }

        //Declara e inicia a árvore da tribo.
        GeneralTreeOfInteger tribo = new GeneralTreeOfInteger();
        
        //Adiciona o guerreiroRoot, que é o primeiro guerreiro da linhagem, na raiz da árvore.
        tribo.add(guerreiroRoot.getNome(), null, guerreiroRoot);

        //Adiciona todos os guerreiros citados no arquivo para a árvore, exceto o guerreiroRoot, usando do ArrayList guerreiros como intermediário.
        for (Guerreiro g : guerreiros) {
            tribo.add(g.getNome(), g.getNomePai(), g);
        }

        //O método passaTerraPre faz a herança de terras. Assim, somando as terras herdadas e conquistadas de cada guerreiro.
        tribo.passaTerraPre();
     
        //Determinando quais são os guerreiros da última geração da tribo, assim como qual destes tem mais terras.
        Integer maisTerras = 0;
        String nomeMaisTerras = " ";
        for (Guerreiro g : guerreiros) {
            //Os guerreiros da última geração da tribo são aqueles que estão em um nível igual à altura da árvore.
            if (tribo.level(g.getNome())==tribo.height()){
                if(g.getTerras()>maisTerras){
                    maisTerras = g.getTerras();
                    nomeMaisTerras = g.getNome();
                }
            }
        }
        
        //tribo.geraDOT();

        System.out.println(pathName + "   " + nomeMaisTerras + "   " + maisTerras);
    }
}