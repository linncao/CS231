/**
 * AdjMatrixGraph.java
 * Adjacency matrix implementation of a undirected graph
 * CS231 Data structures and algorithms, Spring 2021
 * @author Hannah Wolfe, Oliver W. Layton, Zadia Codabux & Dale Skrien
 */
public class AdjMatrixGraph
{
  private int[][] neighbors;

  public AdjMatrixGraph(int numVertices)  {
      this.neighbors = new int[numVertices][numVertices];
  }

  public void join(int vertexA, int vertexB) {
      this.neighbors[vertexA][vertexB] = 1;
      this.neighbors[vertexB][vertexA] = 1;
  }

  // big-Oh notation: O(1)
  public boolean isATriangle(int v1, int v2, int v3) {
    if (this.neighbors[v1][v2] == 1 && this.neighbors[v1][v3] == 1
        && this.neighbors[v2][v1] == 1 && this.neighbors[v2][v3] == 1
        && this.neighbors[v3][v1] == 1 && this.neighbors[v3][v2] == 1) {
        return true;
    }
    return false;                                                                                                                                                                                                                                                                 
  }

  // For each vertex, print its neighbors
  public String toString()  {
    String returnStr = "";
    for (int i = 0; i < this.neighbors.length; i++) {
      for (int j = 0; j < this.neighbors[i].length; j++) {
          returnStr += this.neighbors[i][j] + " ";
      }
      returnStr += "\n";
    }
    return returnStr;
  }

  // test our Graph
  public static void main(String[] args) {
    AdjMatrixGraph graph = new AdjMatrixGraph(3);

    System.out.println(graph);

    graph.join(1, 2);
    graph.join(2, 0);
    // graph.join(0, 1);

    System.out.println(graph.isATriangle(0, 1, 2));
    // graph.join(2, 2);
    // graph.join(9, 9);
    // graph.join(0, 9);
    // graph.join(9, 0);

    System.out.println(graph);
  }
}

/* 
 3. a. Change to "this.neighbors[vertexA][vertexA] = 1;" and "this.neighbors[vertexB][vertexB] = 1;"
    b. Do the commands in the method's body again 
    c. Remove "this.neighbors[vertexB][vertexA] = 1;" so that it can connect A to B but not B to A
 */