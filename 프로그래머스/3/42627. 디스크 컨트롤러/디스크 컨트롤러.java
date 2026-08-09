import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        // 요청 시점 기준 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // 소요 시간 기준 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );
        
        int now = 0;
        int index = 0;
        int count = 0;
        int total = 0;
        
        while (count < jobs.length) {
            
            // 현재 시간까지 들어온 작업 전부 pq에 넣기
            while (index < jobs.length &&
              jobs[index][0] <= now) {
            
                pq.offer(jobs[index]);
                index++;
            }
            
            // 처리할 수 있는 작업이 있다면
            if (!pq.isEmpty()) {
                
                int[] job  = pq.poll();
                
                now += job[1];
                
                total += now - job[0];
                
                count++;
            }
            
            // 현재 처리할 작업이 없다면
            else {
                now = jobs[index][0];
            }
        }
        
        return total / jobs.length;
    }
}