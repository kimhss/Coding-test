import java.util.*;

class Solution {
    static String[][] tickets;
    static boolean[] visited;
    static List<String> route;
    
    public String[] solution(String[][] tickets) {
        
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        
        this.tickets = tickets;
        visited = new boolean[tickets.length];
        route = new ArrayList<>();
        
        route.add("ICN");
        find("ICN", 0);
 
        return route.toArray(new String[0]);
    }
    
    public static boolean find(String target, int count) {
        
        // 모든 티켓을 사용했다면 성공
        if (count == tickets.length) {
            return true;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) continue;
            
            if (!tickets[i][0].equals(target)) continue;
            
            // 티켓 사용
            visited[i] = true;
            route.add(tickets[i][1]);
            
            // 다음 공항 탐색
            if (find(tickets[i][1], count + 1)) {
                return true;
            }
            
            // 실패했다면 현상복구
            visited[i] = false;
            route.remove(route.size() - 1);
            
        }
        
        return false;
    }
}