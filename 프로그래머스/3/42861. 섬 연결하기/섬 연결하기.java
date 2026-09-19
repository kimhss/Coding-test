import java.util.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        
        // 처음에는 자기가 자기의 부모
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 크루스칼을 위해 최소 비용순으로 정렬
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int price = costs[i][2];
            
            if (find(a) != find(b)) {
                union(a, b);
                answer += price;
            }
        }
        
        return answer;
    }
    
    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return find(parent[x]);
    }
    
    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        parent[rootB] = rootA;
    }
}