import java.util.Arrays;

public class CampusFlowCO4 {

    // ================= HEAP SORT =================

    void heapSort(int[] usage) {
        int n = usage.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(usage, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = usage[0];
            usage[0] = usage[i];
            usage[i] = temp;

            heapify(usage, i, 0);
        }
    }

    void heapify(int[] usage, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && usage[left] > usage[largest]) {
            largest = left;
        }

        if (right < n && usage[right] > usage[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = usage[i];
            usage[i] = usage[largest];
            usage[largest] = temp;

            heapify(usage, n, largest);
        }
    }

    // ================= ACTIVITY SELECTION =================

    void selectClassroomBookings(int[] start, int[] finish) {
        System.out.println("\n===== CLASSROOM ALLOCATION USING ACTIVITY SELECTION =====");

        System.out.println("Selected Booking 1 : Start = " + start[0]
                + ", Finish = " + finish[0]);

        int lastSelected = 0;

        for (int i = 1; i < finish.length; i++) {
            if (start[i] >= finish[lastSelected]) {
                System.out.println("Selected Booking " + (i + 1)
                        + " : Start = " + start[i]
                        + ", Finish = " + finish[i]);

                lastSelected = i;
            }
        }
    }

    // ================= 0/1 KNAPSACK =================

    void selectInfrastructureProjects(int[] cost,
                                      int[] benefit,
                                      String[] projectNames,
                                      int budget) {

        int n = cost.length;
        int[][] dp = new int[n + 1][budget + 1];

        for (int i = 1; i <= n; i++) {
            for (int currentBudget = 0;
                 currentBudget <= budget;
                 currentBudget++) {

                if (cost[i - 1] <= currentBudget) {
                    dp[i][currentBudget] = Math.max(
                            benefit[i - 1]
                                    + dp[i - 1][currentBudget - cost[i - 1]],
                            dp[i - 1][currentBudget]
                    );
                } else {
                    dp[i][currentBudget] = dp[i - 1][currentBudget];
                }
            }
        }

        System.out.println("\n===== CAMPUS INFRASTRUCTURE SELECTION USING 0/1 KNAPSACK =====");
        System.out.println("Available Budget : " + budget + " Lakhs");
        System.out.println("Maximum Benefit Score : " + dp[n][budget]);

        System.out.println("\nSelected Projects:");

        int remainingBudget = budget;

        for (int i = n; i > 0; i--) {
            if (dp[i][remainingBudget] != dp[i - 1][remainingBudget]) {
                System.out.println(projectNames[i - 1]
                        + " | Cost = " + cost[i - 1]
                        + " Lakhs"
                        + " | Benefit = " + benefit[i - 1]);

                remainingBudget -= cost[i - 1];
            }
        }
    }

    public static void main(String[] args) {

        CampusFlowCO4 campus = new CampusFlowCO4();

        // Heap Sort: number of bookings per facility
        int[] facilityUsage = {85, 40, 120, 60, 95, 30, 75};

        System.out.println("===== FACILITY USAGE BEFORE HEAP SORT =====");
        System.out.println(Arrays.toString(facilityUsage));

        campus.heapSort(facilityUsage);

        System.out.println("\n===== FACILITY USAGE AFTER HEAP SORT =====");
        System.out.println(Arrays.toString(facilityUsage));

        // Activity Selection: booking start and finish times
        int[] startTime = {1, 3, 0, 5, 8, 5};
        int[] finishTime = {2, 4, 6, 7, 9, 9};

        campus.selectClassroomBookings(startTime, finishTime);

        // 0/1 Knapsack: campus development projects
        String[] projects = {
                "Computer Lab Upgrade",
                "Library Digital System",
                "Smart Classroom Setup",
                "Sports Complex Repair",
                "Solar Power Installation"
        };

        int[] cost = {4, 3, 5, 2, 6};
        int[] benefit = {8, 6, 10, 4, 12};

        int budget = 10;

        campus.selectInfrastructureProjects(
                cost,
                benefit,
                projects,
                budget
        );
    }
}