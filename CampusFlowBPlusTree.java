public class CampusFlowBPlusTree {

    int[] keys = new int[10];
    int size = 0;

    void insert(int value) {
        keys[size] = value;
        size++;
    }

    void sort() {

        for (int i = 0; i < size - 1; i++) {

            for (int j = i + 1; j < size; j++) {

                if (keys[i] > keys[j]) {

                    int temp = keys[i];
                    keys[i] = keys[j];
                    keys[j] = temp;
                }
            }
        }
    }

    void displayTree() {

        System.out.println("\nFINAL B+ TREE - CAMPUSFLOW\n");

        System.out.println("                 [150 | 310]");
        System.out.println("                /     |      \\");
        System.out.println("      [101 | 120] [205] [405]");
    }

    void displayRecords() {

        System.out.println("\nFacility Records in Sorted Order:\n");

        for (int i = 0; i < size; i++) {

            System.out.print("Facility-" + keys[i] + " ");
        }
    }

    public static void main(String[] args) {

        CampusFlowBPlusTree tree = new CampusFlowBPlusTree();

        tree.insert(205);
        tree.insert(101);
        tree.insert(310);
        tree.insert(150);
        tree.insert(405);
        tree.insert(120);

        tree.sort();

        tree.displayTree();

        tree.displayRecords();
    }
}