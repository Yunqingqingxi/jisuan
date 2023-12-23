package test;

import java.util.*;

class Graph {
    private int V;
    private LinkedList<Integer>[] adjList;

    public Graph(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
    }

    public void DFS(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                DFS(neighbor, visited);
            }
        }
    }

    public void DFSTraversal() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; ++i) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);

        // 添加边
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        System.out.println("图的深度优先遍历结果：");
        graph.DFSTraversal();
    }
}

