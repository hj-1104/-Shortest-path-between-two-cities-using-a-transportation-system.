import java.util.Scanner;

public class Dijkstra {

    private static final int INFINITY = 9999;
    private static final int MAX = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] G = new int[MAX][MAX];
        String[] placeNames = new String[MAX];
        int n, u;

        System.out.print("Enter number of places: ");
        n = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        System.out.println("\nEnter the names of the places:");
        for (int i = 0; i < n; i++) {
            System.out.print("Place " + i + ": ");
            placeNames[i] = scanner.nextLine();
        }

        System.out.println("\nEnter the distance of places from each other:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                G[i][j] = scanner.nextInt();
            }
        }

        System.out.print("\nEnter the starting place point (index): ");
        u = scanner.nextInt();

        dijkstra(G, placeNames, n, u);

        scanner.close();
    }

    public static void dijkstra(int[][] G, String[] placeNames, int n, int startnode) {
        int[][] cost = new int[MAX][MAX];
        int[] distance = new int[MAX];
        int[] pred = new int[MAX];
        int[] visited = new int[MAX];
        int count, mindistance, nextnode;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (G[i][j] == 0) {
                    cost[i][j] = INFINITY;
                } else {
                    cost[i][j] = G[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            distance[i] = cost[startnode][i];
            pred[i] = startnode;
            visited[i] = 0;
        }

        distance[startnode] = 0;
        visited[startnode] = 1;
        count = 1;

        while (count < n - 1) {
            mindistance = INFINITY;
            nextnode = -1;

            for (int i = 0; i < n; i++) {
                if (distance[i] < mindistance && visited[i] == 0) {
                    mindistance = distance[i];
                    nextnode = i;
                }
            }

            visited[nextnode] = 1;

            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    if (mindistance + cost[nextnode][i] < distance[i]) {
                        distance[i] = mindistance + cost[nextnode][i];
                        pred[i] = nextnode;
                    }
                }
            }
            count++;
        }

        for (int i = 0; i < n; i++) {
            if (i != startnode) {
                System.out.println("\nDistance to " + placeNames[i] + " = " + distance[i]);
                System.out.print("Path = " + placeNames[i]);
                int j = i;
                do {
                    j = pred[j];
                    System.out.print(" <- " + placeNames[j]);
                } while (j != startnode);
                System.out.println();
            }
        }
    }
}
