package Test;


import WeightedGraph.Graph;

import java.awt.image.ImagingOpException;
import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String args[]){
        // find data files
        String filePath = "data/agency.txt";
        try {
            File f = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(f));
            while(br.ready())
                System.out.println(br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
//        Graph graph = new Graph(f);
//        System.out.println(graph);
//
//        graph.addEdge("6", "5", 5);
//        System.out.println(graph);
//        System.out.println(graph.isConnected());
//        System.out.println(graph.hasCircle());
//
//        try {
//            System.out.println(graph.PrimMST("1"));
//            System.out.println(graph.Dijkstra("1"));
//            System.out.println();
//            System.out.println(graph.Kruskal());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
