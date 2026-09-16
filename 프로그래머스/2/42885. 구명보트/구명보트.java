import java.util.*;

class Solution {
    
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int answer = 0;
        
        int left = 0;
        int right = people.length - 1;
        
        while(left <= right) {
            
            // 한 명만 남은 경우
            if (left == right) {
                answer++;
                break;
            }
            
            // 가장 가벼운 사람 +  가장 무거운 사람
            if (people[left] + people[right] <= limit) {
                left++;
            }
            
            // 가장 무거운 사람은 무조건 이번 보트에 탐
            right--;
            
            answer++;
        }
        
        return answer;
    }
}