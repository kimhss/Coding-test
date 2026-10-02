import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> IN = new HashMap<>();
        
        Map<String, Integer> results = new HashMap<>();
                                        
        int baseTime = fees[0]; // 기본 시간
        int baseFee = fees[1];  // 기본 요금
        int unitTime = fees[2];  // 단위 시간
        int unitFee = fees[3];  // 단위 요금
        
        int size = records.length;
        
        for (int i = 0; i < records.length; i++) {
            String[] str = records[i].split(" ");
            
            String time = str[0];
            String number = str[1];
            String type = str[2];
            
            switch(type) {
                case "IN":
                    IN.put(number, time);
                    break;
                    
                case "OUT":
                    int result = calc(IN.get(number), time);
                    results.put(number, results.getOrDefault(number, 0) + result);
                    IN.remove(number);
                    break;
            }
        }
        
        for (String number : IN.keySet()) {
            int result = calc(IN.get(number), "23:59");
            results.put(number, results.getOrDefault(number, 0) + result);
        } 
        
        List<String> list = new ArrayList<>(results.keySet());
        Collections.sort(list);
        
        int[] answer = new int[list.size()];
        int idx = 0;
        
        for (String number : list) {
            int total = results.get(number);
            
            if (total <= baseTime) {
                answer[idx] = baseFee;
            }
            
            else {
                int diff = total - baseTime;
                answer[idx] = baseFee + ((diff + unitTime - 1) / unitTime) * unitFee;
            }
            
            idx++;
        }
        
        return answer;
    }
    
    static int calc(String in, String out) {
        int inHour = Integer.parseInt(in.split(":")[0]) * 60;
        int inMin = Integer.parseInt(in.split(":")[1]);
        int inResult = inHour + inMin;
        
        int outHour = Integer.parseInt(out.split(":")[0]) * 60;
        int outMin = Integer.parseInt(out.split(":")[1]);
        int outResult = outHour + outMin;
        
        return outResult - inResult;
        
    } 
}