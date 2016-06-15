package forsterkarp;

import java.io.PrintWriter;
import java.util.Date;

public class ForsterKarp {
    public static void main(String[] args) {
        
        long start, end;
        double av_time, av_memory;
        PrintWriter writer = null;
        
        Graph graph;
        HeldKarp hk = new HeldKarp();
        
        try{
            writer = new PrintWriter ("out.txt", "UTF-8");
        } catch (Exception e){}
        
        System.out.println ("CTC-20: Projeto 2");
        System.out.println ("Trabalho feito por:");
        System.out.println ("Caio Cesar Saldanha Maia Orejuela Kinelski");
        System.out.println ("Gabriela Nalim Tourinho\n");
        
        System.out.println ("Verificação com Grafos Aleatórios");
        for (int i=1; i<=10; i++){
            graph = new Graph (6, 10);
            
            System.out.println ("Grafo de teste " + i + ":");
            graph.println();
            
            hk.initialize(graph);
            System.out.println ("Solução por Held Karp: " + hk.TSP() + "\n");
        }
        
        System.out.println ("Teste de Desempenho Médio (10 execuções)");
        System.out.println ("A saída será gerada em um arquivo out.txt no formato");
        System.out.println ("adequado para a utilização em MATLAB.\n");
        
        System.out.println ("De 3 a 20 vértices (grafo completo):");
        for (int i=3; i<=20; i++){
            
            av_memory = 0.0;
            start = (new Date()).getTime();
            
            for (int j=1; j<=10; j++){
                graph = new Graph (i, (i*(i-1))/2);
                hk.initialize(graph);
                hk.TSP();
                av_memory += hk.getAllocatedMemory();
            }
            
            end = (new Date()).getTime();
            av_time = (end - start)/10.0;
            av_memory /= 10.0;
            
            System.out.print (i + " vértices: ");
            System.out.print (av_time + " ms, ");
            System.out.println (av_memory + " bytes");
        }
        
        System.out.println ("\nDe 1 a 120 arestas (16 vértices):");
        for (int i=1; i<=120; i++){
            
            start = (new Date()).getTime();
            
            for (int j=1; j<=10; j++){
                graph = new Graph(16, i);
                hk.initialize(graph);
                hk.TSP();
            }
            
            end = (new Date()).getTime();
            av_time = (end - start)/10.0;
            
            System.out.println (i + " arestas: " + av_time + " ms");
            
        }
        
        if (writer != null)
            writer.close();
    }
}
