import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = {};
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < quiz.length; i++) {
            String[] arr = quiz[i].split(" ");
            int sum = Integer.parseInt(arr[0]);
            for(int j = 1; j < arr.length; j++) {
                if (arr[j].equals("+"))
                    sum += Integer.parseInt(arr[j + 1]);
                else if (arr[j].equals("-"))
                    sum -= Integer.parseInt(arr[j + 1]);
                else if (arr[j].equals("=")) {
                    if (sum == Integer.parseInt(arr[j + 1]))
                        list.add("O");
                    else list.add("X");
                }
            }
        }
        answer = list.toArray(new String[0]);
        return answer;
    }
}