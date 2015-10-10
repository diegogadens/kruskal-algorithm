import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Kruskal
{
    public ArrayList<Integer> n�s_�rvore = new ArrayList<Integer>();
    public ArrayList<Integer> n�s_visitados = new ArrayList<Integer>();
    public ArrayList<Arco> ciclo = new ArrayList<Arco>();
    public ArrayList<Arco> �rvore_final = new ArrayList<Arco>();;
    
    public Arco grafo_original[];
    
    public Scanner entrada = new Scanner(System.in);
    int num_n�s, num_arestas;
    
    public Kruskal(int num_n�s, int num_arestas)
    {
        this.num_n�s = num_n�s;
        this.num_arestas = num_arestas;
        grafo_original = new Arco[num_arestas];
                
        l�_entrada();

        ordena();
        
        inicia_Kruskal();
        
        imprime_�rvore_final();
    }

    public void l�_entrada()
    {
        int x, y, z;  
        
        System.out.println("Para inserir um arco, insira uma linha com os 3 par�metros \n(origem destino custo), separados por 1 espa�o\n\n");

        for(int i=0; i<num_arestas; i++)
        {
            System.out.print("Insira os dados do arco: ");
            String linha_digitada = entrada.nextLine();    
        
            StringTokenizer tokenizer = new StringTokenizer(linha_digitada, " ");
              x = Integer.parseInt(tokenizer.nextToken());
              y = Integer.parseInt(tokenizer.nextToken());
              z = Integer.parseInt(tokenizer.nextToken());
            
            grafo_original[i] = new Arco(x, y, z);
        }        
    }
    
    public void imprime_�rvore_final()
    {
        System.out.println("\n�rvore final:");
        for(Arco c : �rvore_final)
        {
            if(c!= null)
               System.out.println(c.toString());
        }
    }
    
    public void ordena()
    {
        int i, j;
        Arco aux;
        
        for(i=0; i<num_arestas; i++)
          for(j=0; j<num_arestas-1; j++)
            {
                if(grafo_original[j].getCusto() > grafo_original[j+1].getCusto())
                {
                   aux = grafo_original[j];
                   grafo_original[j] = grafo_original[j+1];
                   grafo_original[j+1] = aux;
                }
            }
    }
    
    public void inicia_Kruskal()
    {
        System.out.println("\nInicia Kruskal");
        �rvore_final.add(grafo_original[0]);
        n�s_�rvore.add(grafo_original[0].getOrigem());
        n�s_�rvore.add(grafo_original[0].getDestino());
        imprime_resultado(0);
        
        for(int i=1; i<num_arestas; i++)
        {
           if((n�s_�rvore.contains(grafo_original[i].getOrigem())!=true) && (n�s_�rvore.contains(grafo_original[i].getDestino())!=true))
           {
               n�s_�rvore.add(grafo_original[i].getOrigem());
               n�s_�rvore.add(grafo_original[i].getDestino());
               �rvore_final.add(grafo_original[i]);
               imprime_resultado(i);
           } 
           
           else
           if((n�s_�rvore.contains(grafo_original[i].getOrigem())==true) && (n�s_�rvore.contains(grafo_original[i].getDestino())!=true))
           {
               n�s_�rvore.add(grafo_original[i].getDestino());
               �rvore_final.add(grafo_original[i]);
               imprime_resultado(i);
           }
           
           else
           if((n�s_�rvore.contains(grafo_original[i].getOrigem())!=true) && (n�s_�rvore.contains(grafo_original[i].getDestino())==true))
           {
               n�s_�rvore.add(grafo_original[i].getOrigem());
               �rvore_final.add(grafo_original[i]);
               imprime_resultado(i);
           }
           
           else
           if((n�s_�rvore.contains(grafo_original[i].getOrigem())==true) && (n�s_�rvore.contains(grafo_original[i].getDestino())==true))
           {
              if(verifica_ciclo((grafo_original[i].getDestino()),(grafo_original[i].getOrigem())) == false)
               {
                    �rvore_final.add(grafo_original[i]);
                    imprime_resultado(i);   
               }
               else
                    System.out.println("O arco c("+grafo_original[i].getOrigem()+","+grafo_original[i].getDestino()+") n�o foi inclu�do, pois forma ciclo");
           }

        }
    }
     
    public boolean verifica_ciclo(int x, int y)
    {
        Fifo fila = new Fifo();
        
        for(Arco c : �rvore_final)
        {
            if (c != null)
            {
                ciclo.add(c);
                ciclo.add(new Arco(c.getDestino(),c.getOrigem(),c.getCusto()));
            }
        }
     
        fila.enfileira((Integer)x);
        n�s_visitados.add((Integer)x);
        do
        {
            for(Arco d : ciclo)
            {
                if((d.getOrigem() == fila.pegaPrimeiro()) && (n�s_visitados.contains(d.getDestino())==false))
                {
                    if(d.getDestino() == y)
                        {
                            fila.clear();
                            n�s_visitados.clear();
                            return true;
                        }
                    else
                    {
                         fila.enfileira(d.getDestino());
                         n�s_visitados.add(d.getDestino());
                    }
                }
            }
            fila.desenfileira();    
        }while(fila.isEmpty()==false);
        
        fila.clear();
        n�s_visitados.clear();
        return false; 
    }
    
    public void imprime_resultado(int x)
    {
        System.out.println("O arco c("+grafo_original[x].getOrigem()+","+grafo_original[x].getDestino()+") foi incluido na �rvore, pois n�o forma ciclo");
    }
}
