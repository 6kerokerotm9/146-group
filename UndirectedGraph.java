import java.util.ArrayList;

public class UndirectedGraph<AnyType> {
    private ArrayList<ArrayList<AnyType>> matrix; //2d array where the 0 index of each list is the original profile and the rest are friends
    
    public UndirectedGraph() {
        matrix = new ArrayList<>();
    }
    
    public void addVertex(AnyType t) {
        ArrayList<AnyType> temp = new ArrayList<>();
        temp.add(t);
        matrix.add(temp);
    }
    
    public void makeEdge(AnyType t, AnyType s) {
        int index = matrix.indexOf(t);
        matrix.get(index).add(s);
    }
    
    public ArrayList<AnyType> getVertices() { //function returns all the vertices in the graph
        ArrayList<AnyType> temp = new ArrayList<>(); //temporary array that holds the vertices
        for(ArrayList<AnyType> a : matrix) {
            temp.add(a.get(0));
        }
        return temp;
    }
    
    public void print() {
        ArrayList<AnyType> temp = new ArrayList<>(); //temporary array that holds the vertices
        for(ArrayList<AnyType> a : matrix) {
            System.out.println(a.get(0));
        }
    }
}
