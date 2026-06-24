import java.util.*;

public class CampusFlowCO5 {

    private final int buildings;
    private final LinkedList<Integer>[] campusMap;

    CampusFlowCO5(int buildings) {
        this.buildings = buildings;
        campusMap = new LinkedList[buildings];

        for (int i = 0; i < buildings; i++) {
            campusMap[i] = new LinkedList<>();
        }
    }

    void addPath(int building1, int building2) {
        campusMap[building1].add(building2);
        campusMap[building2].add(building1);
    }

    void displayCampusMap() {
        System.out.println("===== CAMPUS BUILDING NETWORK =====");

        for (int i = 0; i < buildings; i++) {
            System.out.print("Building " + i + " connected to: ");

            for (int next : campusMap[i]) {
                System.out.print(next + " ");
            }

            System.out.println();
        }
    }

    void findNearestAvailableFacility(int startBuilding,
                                     boolean[] availableFacility) {

        boolean[] visited = new boolean[buildings];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(startBuilding);
        visited[startBuilding] = true;

        System.out.println("\n===== NEAREST AVAILABLE FACILITY USING BFS =====");

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (availableFacility[current]) {
                System.out.println("Nearest available facility is Building "
                        + current);
                return;
            }

            for (int next : campusMap[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        System.out.println("No available facility found.");
    }

    void allocateRoomsGreedy(int[] start, int[] finish) {
        System.out.println("\n===== GREEDY CLASSROOM BOOKING ALLOCATION =====");

        int selected = 0;
        System.out.println("Allocated Slot 1: "
                + start[0] + " AM to " + finish[0] + " AM");

        for (int i = 1; i < start.length; i++) {
            if (start[i] >= finish[selected]) {
                System.out.println("Allocated Slot " + (i + 1) + ": "
                        + start[i] + " AM to " + finish[i] + " AM");

                selected = i;
            }
        }
    }

    void demandPatternLCS(String firstPattern,
                          String secondPattern) {

        int m = firstPattern.length();
        int n = secondPattern.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (firstPattern.charAt(i - 1)
                        == secondPattern.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i][j - 1]
                    );
                }
            }
        }

        System.out.println("\n===== FACILITY DEMAND PATTERN USING LCS =====");
        System.out.println("Pattern 1: " + firstPattern);
        System.out.println("Pattern 2: " + secondPattern);
        System.out.println("Longest Common Demand Pattern Length: "
                + dp[m][n]);
    }

    public static void main(String[] args) {

        CampusFlowCO5 campus = new CampusFlowCO5(7);

        campus.addPath(0, 1);
        campus.addPath(0, 2);
        campus.addPath(1, 3);
        campus.addPath(1, 4);
        campus.addPath(2, 5);
        campus.addPath(5, 6);

        campus.displayCampusMap();

        boolean[] availableFacility = {
                false, false, false, false, true, false, false
        };

        campus.findNearestAvailableFacility(0, availableFacility);

        int[] startTime = {9, 10, 11, 12, 14, 15};
        int[] finishTime = {10, 11, 12, 14, 15, 16};

        campus.allocateRoomsGreedy(startTime, finishTime);

        campus.demandPatternLCS("LABRARY", "LIBRARY");
    }
}