// Graph Representation - Campus Network (M3 - CO3)
import java.util.*;

public class CampusGraph {

    // Adjacency List using HashMap
    private Map<String, List<String>> adjList = new HashMap<>();

    public CampusGraph() {
        adjList = new HashMap<>();
    }

    // Add a building/node to the campus graph
    public void addBuilding(String building) {
        adjList.putIfAbsent(building, new ArrayList<>());
    }

    // Add undirected pathway between two buildings
    public void addPathway(String src, String dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // undirected
    }

    // Display the graph
    public void displayGraph() {
        System.out.println("Campus Graph (Adjacency List):");
        for (String building : adjList.keySet()) {
            System.out.println(building + " --> " + adjList.get(building));
        }
    }

    public Map<String, List<String>> getAdjList() {
        return adjList;
    }

    public static void main(String[] args) {
        CampusGraph campus = new CampusGraph();

        // Add campus buildings
        campus.addBuilding("Library");
        campus.addBuilding("Admin Block");
        campus.addBuilding("Canteen");
        campus.addBuilding("CSE Dept");
        campus.addBuilding("Hostel");
        campus.addBuilding("Sports Ground");

        // Add pathways
        campus.addPathway("Library", "Admin Block");
        campus.addPathway("Library", "CSE Dept");
        campus.addPathway("Admin Block", "Canteen");
        campus.addPathway("CSE Dept", "Hostel");
        campus.addPathway("Canteen", "Sports Ground");
        campus.addPathway("Hostel", "Sports Ground");

        campus.displayGraph();
    }
}