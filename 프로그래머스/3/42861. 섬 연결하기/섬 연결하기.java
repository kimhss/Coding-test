import java.util.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        
        // 1. 처음에는 각자 자기 자신이 대표
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 2. 다리를 비용순으로 정렬
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        // 3. 싼 다리부터 확인
        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int price = cost[2];
            
            // 4. 서로 다른 그룹이면 연결
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