import java.util.*;

class Process {
    boolean isMine;
    int priority;
    
    Process(int priority, boolean isMine) {
        this.priority = priority;
        this.isMine = isMine;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        List<Process> list = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            list.add(new Process(priorities[i], i == location));
        }
        
        int count = 0;
        while(!list.isEmpty()) {
            Process now = list.remove(0);
            
            boolean isPrintable = true;
            for(Process next : list) {
                if (now.priority < next.priority) {
                    isPrintable = false;
                    break;
                }
            }
            
            if(!isPrintable) {
                list.add(now);
                continue;
            }
            
            count++;
            if(now.isMine) break;
        }
        
        return count;
       
    }
}