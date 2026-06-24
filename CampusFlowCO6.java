import java.util.Scanner;

public class CampusFlowCO6 {

    static final int MAX_BUILDINGS = 6;

    static String[] buildingNames = {
            "Main Gate",
            "Library",
            "Computer Lab",
            "Seminar Hall",
            "Auditorium",
            "Research Lab"
    };

    static int[][] campusMap = new int[MAX_BUILDINGS][MAX_BUILDINGS];

    static boolean[] visited = new boolean[MAX_BUILDINGS];

    // Adds a two-way path between buildings
    static void addPath(int source, int destination) {
        campusMap[source][destination] = 1;
        campusMap[destination][source] = 1;
    }

    // Creates CampusFlow campus network
    static void createCampusNetwork() {

        addPath(0, 1); // Main Gate - Library
        addPath(0, 2); // Main Gate - Computer Lab
        addPath(1, 3); // Library - Seminar Hall
        addPath(2, 4); // Computer Lab - Auditorium
        addPath(3, 5); // Seminar Hall - Research Lab
        addPath(4, 5); // Auditorium - Research Lab
    }

    // Displays all building connections
    static void displayCampusPaths() {

        System.out.println("\n===== CAMPUSFLOW BUILDING CONNECTIONS =====");

        for (int i = 0; i < MAX_BUILDINGS; i++) {

            System.out.print(buildingNames[i] + " connected to: ");

            for (int j = 0; j < MAX_BUILDINGS; j++) {

                if (campusMap[i][j] == 1) {
                    System.out.print(buildingNames[j] + " | ");
                }
            }

            System.out.println();
        }
    }

    // DFS traversal method
    static void dfs(int currentBuilding) {

        visited[currentBuilding] = true;

        System.out.println("Visited: " + buildingNames[currentBuilding]);

        for (int nextBuilding = 0;
             nextBuilding < MAX_BUILDINGS;
             nextBuilding++) {

            if (campusMap[currentBuilding][nextBuilding] == 1
                    && visited[nextBuilding] == false) {

                dfs(nextBuilding);
            }
        }
    }

    // Checks whether all buildings are connected
    static void checkCampusConnectivity() {

        for (int i = 0; i < MAX_BUILDINGS; i++) {
            visited[i] = false;
        }

        System.out.println("\n===== DFS CAMPUS CONNECTIVITY CHECK =====");

        dfs(0);

        boolean connected = true;

        for (int i = 0; i < MAX_BUILDINGS; i++) {

            if (visited[i] == false) {
                connected = false;
                break;
            }
        }

        if (connected == true) {
            System.out.println("\nResult: All campus buildings are connected.");
        } else {
            System.out.println("\nResult: Some buildings are not connected.");
        }
    }

    // Displays building details selected by user
    static void showBuildingDetails(Scanner scanner) {

        System.out.println("\n===== CAMPUS BUILDINGS =====");

        for (int i = 0; i < MAX_BUILDINGS; i++) {
            System.out.println(i + " - " + buildingNames[i]);
        }

        System.out.print("\nEnter Building Number: ");
        int buildingId = scanner.nextInt();

        if (buildingId >= 0 && buildingId < MAX_BUILDINGS) {
            System.out.println("\nSelected Building: " + buildingNames[buildingId]);
        } else {
            System.out.println("\nInvalid Building Number.");
        }
    }

    // Menu method
    static void showMenu() {

        System.out.println("\n========================================");
        System.out.println(" CAMPUSFLOW CAMPUS NETWORK APPLICATION");
        System.out.println("========================================");
        System.out.println("1. Display Campus Building Connections");
        System.out.println("2. Check Campus Connectivity using DFS");
        System.out.println("3. View Building Details");
        System.out.println("4. Exit");
    }

    // Main method
    public static void main(String[] args) {

        createCampusNetwork();

        int choice;

        try (Scanner scanner = new Scanner(System.in)) {

            do {
                showMenu();

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> displayCampusPaths();
                    case 2 -> checkCampusConnectivity();
                    case 3 -> showBuildingDetails(scanner);
                    case 4 -> System.out.println("\nThank you for using CampusFlow.");
                    default -> System.out.println("\nInvalid choice. Enter 1 to 4.");
                }

            } while (choice != 4);
        }
    }
}