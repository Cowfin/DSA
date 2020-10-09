package ex6_1;

/**
   An interface that defines the abstract data type for an undirected
   graph whose vertices hold elements of type E
*/
import Chapter6.*;
import java.util.Set;

public interface GraphADT<E>
{
   // removes all vertices and edges from the graph
   public void clear();
   // returns true if the graph has no vertices nor edges
   public boolean isEmpty();
   // returns a view of the vertices as a Set
   public Set<Vertex<E>> vertexSet();
   // returns a view of the edges as a Set
   public Set<Edge<E>> edgeSet();
   // method which adds the graph as a subgraph into this graph
   public <F extends E> void addGraph(GraphADT<F> graph);
   // adds and returns a new isolated vertex to the graph
   public Vertex<E> addVertex(E element);
   // adds and returns a new undirected edge between two vertices
   public Edge<E> addEdge(Vertex<E> vertex0, Vertex<E> vertex1);
   // removes the specified vertex from the graph
   public <F> boolean removeVertex(Vertex<F> vertex);
   // removes the specified undirected edge from the graph
   public <F> boolean removeEdge(Edge<F> edge);
   // returns whether the specified vertex is in the graph
   public boolean containsVertex(Vertex<?> vertex);
   // returns whether the specified edge is in the graph
   public boolean containsEdge(Edge<?> edge);
}