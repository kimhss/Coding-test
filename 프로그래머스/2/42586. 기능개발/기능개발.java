import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        
        for(int i = 0; i < speeds.length; i++) {
            q.add((int)Math.ceil((100.0-progresses[i])/speeds[i]));
        }
        
        while 
            (!q.isEmpty()) {
            int min = q.poll();
            int count = 1;
            
            while (!q.isEmpty() && q.peek() <= min) {
                q.poll();
                count++;
            }
            list.add(count);
        }
        answer = list.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}