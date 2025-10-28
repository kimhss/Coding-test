class Solution {
    public String solution(String bin1, String bin2) {
        String answer = "";
        answer = Integer.toBinaryString(Integer.parseInt(bin1,2) + Integer.parseInt(bin2,2));
        // Integer.parseInt(bin1, 2)   ->  2진수형태의 bin1을 10진수로 변환
        // Integer.toBinaryString(A + B)  ->  10진수형태의  A와 B를 더한 값을 2진수로 변환.
        return answer;
    }
}