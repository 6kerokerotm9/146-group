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
        int index = 0;
        for(ArrayList<AnyType> a : matrix) {
            if(a.get(0).equals(t)) {
                break;
            }
            index++;
        }
        matrix.get(index).add(s);
    }
    
    public ArrayList<AnyType> getEdges(AnyType t) { // returns the edges (adjacencies) of the given node
        ArrayList<AnyType> temp = new ArrayList<>(); //temp array to hold the edges
        for(ArrayList<AnyType> a : matrix) {
            if(a.get(0).equals(t)) {
                for(int i=1; i<a.size(); i++) { //populate the array with all the indexes after index 0 as 0 is the node
                    temp.add(a.get(i)); 
                }
            }
        }
        return temp;
    }

    public ArrayList<AnyType> getVertices() { //function returns all the vertices in the graph
        ArrayList<AnyType> temp = new ArrayList<>(); //temporary array that holds the vertices
        for(ArrayList<AnyType> a : matrix) {
            temp.add(a.get(0));
        }
        return temp;
    }
    
    public boolean showConnectivity(AnyType t, AnyType u) {
        ArrayList<AnyType> temp = getEdges(t);
        ArrayList<AnyType> temp2 = getEdges(u);
        return (temp.contains(u) && temp2.contains(t));
    }

    public void remove(AnyType t) {
        int index = 0;
        for(ArrayList<AnyType> a : matrix) {
            if(a.get(0).equals(t)) {
                break;
            }
            index++;
        }
        
        if(index >= matrix.size()) {
            return;
        }

        for(ArrayList<AnyType> a : matrix) {
            if(a.contains(t)) {
                a.remove(t);
            }
        }
        matrix.remove(matrix.get(index));
    }


    public void printVertices() {
        ArrayList<AnyType> temp = new ArrayList<>(); //temporary array that holds the vertices
        for(ArrayList<AnyType> a : matrix) {
            for(AnyType e : a) {
                System.out.println(e);
            }
        }
        System.out.println();
    }
    
    public String printEdges(AnyType t) { //function that prints out the edges of a given node
        String temp = ""; //will hold the names of the edges
        int index = 0;
        for(ArrayList<AnyType> a : matrix) {
            if(a.get(0).equals(t)) {
                break;
            }
            index++;
        }
        
        if(index >= matrix.size()) {
            return "";
        }
        
        temp += matrix.get(index).get(0) + ": ";
        for(int i=1; i<matrix.get(index).size(); i++) { //populate the array with all the indexes after index 0 as 0 is the node
            temp +=  matrix.get(index).get(i) + ", ";
        }
        if(temp.length() > 0) {
            temp = temp.substring(0, temp.length() -2); //remove the commas at the end 
        }
        return temp;
    }
}
