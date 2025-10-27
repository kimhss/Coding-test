import java.util.*;

class Solution {
    public String solution(String[] id_pw, String[][] db) {
        String answer = "";
        String id = id_pw[0];
        String pw = id_pw[1];
        HashMap<String, String> map = new HashMap<>();
        
        for(int i = 0; i < db.length; i++) {
            map.put(db[i][0], db[i][1]);
        }
        
        if (map.containsKey(id)) {
            if (map.get(id).equals(pw)) {
                answer = "login";
            } else {
                answer = "wrong pw";
            }
        } 
        else {
            answer = "fail";
        }
        
        
        return answer;
    }
}