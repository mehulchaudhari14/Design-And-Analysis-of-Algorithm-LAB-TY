import java.util.Arrays;

public class SimpleTSP {
    static final double INF = 1e12;
    static int N;
    static double[][] cost;   
    static double[][] timeM;  
    static boolean[] visited;
    static int[] bestPath;
    static double bestCost = INF;
    static double maxTotalTime;
    static double globalMinCost = INF;
    static double globalMinTime = INF;
    static int start = 0;

    static void dfs(int curr, int level, double curCost, double curTime, int[] path) {
        if (level == N) {
            if (cost[curr][start] >= INF) return;
            double totalCost = curCost + cost[curr][start];
            double totalTime = curTime + timeM[curr][start];
            if (totalTime <= maxTotalTime && totalCost < bestCost) {
                bestCost = totalCost;
                bestPath = Arrays.copyOf(path, N + 1);
                bestPath[N] = start;
            }
            return;
        }

      
        for (int j = 0; j < N; j++) {
            if (visited[j] || cost[curr][j] >= INF) continue;

            double nextCost = curCost + cost[curr][j];
         
            double lb = nextCost + (N - level - 1) * globalMinCost;
            if (lb >= bestCost) continue; 

            double nextTime = curTime + timeM[curr][j];
            double optimisticTime = nextTime + (N - level - 1) * globalMinTime;
            if (optimisticTime > maxTotalTime) continue; 

            visited[j] = true;
            path[level] = j;
            dfs(j, level + 1, nextCost, nextTime, path);
            visited[j] = false;
        }
    }

    public static void main(String[] args) {
     
        double[][] distance = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        double[][] fuelFactor = {
            {0, 1.1, 1.0, 1.3},
            {1.1, 0, 1.2, 1.0},
            {1.0, 1.2, 0, 1.1},
            {1.3, 1.0, 1.1, 0}
        };
        timeM = new double[][] {
            {0, 1.0, 1.2, 1.5},
            {1.0, 0, 2.0, 1.3},
            {1.2, 2.0, 0, 1.4},
            {1.5, 1.3, 1.4, 0}
        };

        N = distance.length;
        cost = new double[N][N];
        visited = new boolean[N];

        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) cost[i][j] = INF;
                else {
                    cost[i][j] = distance[i][j] * fuelFactor[i][j];
                    globalMinCost = Math.min(globalMinCost, cost[i][j]);
                    globalMinTime = Math.min(globalMinTime, timeM[i][j]);
                }
            }
        }

        maxTotalTime = 6.0; 
        start = 0;
        visited[start] = true;
        int[] path = new int[N + 1];
        path[0] = start;

        dfs(start, 1, 0.0, 0.0, path);

        if (bestPath == null) {
            System.out.println("No feasible route under time " + maxTotalTime);
        } else {
            System.out.println("Best cost: " + bestCost);
            System.out.println("Route:");
            for (int i = 0; i <= N; i++) {
                if (i > 0) System.out.print(" -> ");
                System.out.print(bestPath[i]);
            }
            System.out.println();
        }
    }
}
