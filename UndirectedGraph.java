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
        ArrayList<AnyType> temp = matrix.get(index);
        temp.add(s);
    }
}
