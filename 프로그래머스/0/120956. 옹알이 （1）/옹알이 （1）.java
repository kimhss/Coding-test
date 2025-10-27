class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] pron = {"aya", "ye", "woo", "ma"};
        
        for(String str : babbling) {
            for(String p : pron) {
                if (str.contains(p)) {
                    str = str.replace(p, " ");
                }
            }
            str = str.replace(" ", "");
            if(str.equals("")) answer++;
        }
        
        return answer;
    }
}