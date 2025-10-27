class Solution {
    public String solution(String polynomial) {
        String answer = "";
        String[] str = polynomial.split(" \\+ ");
        // " + "를 하면 +를 정규식으로 이해해서 공백 1개 이상<<으로 표현됨
        // 따라서 + 자체를 포함하기 위해서는 " //+ "로 해야됨
        
        int xSum = 0;
        int sum = 0;
        
        for(String s : str) {
            if (s.equals("x")) {
                xSum += 1;
            } else if (s.contains("x")) {
                xSum += Integer.parseInt(s.substring(0, s.length() - 1));
            } else if (!s.equals("+")) {
                sum += Integer.parseInt(s);
            }
        }
        
         if (xSum != 0 && sum == 0) {
            if (xSum == 1) {
                answer = "x";
            } else {
                answer = xSum + "x";
            }
        } 

        if (xSum != 0 && sum != 0) {
            if (xSum == 1) {
                answer = "x" + " + " + sum;
            } else {
                answer = xSum + "x" + " + " + sum;
            }
        }

        if (xSum == 0 && sum != 0) {
            answer = String.valueOf(sum);
        }

        return answer;
    }
}