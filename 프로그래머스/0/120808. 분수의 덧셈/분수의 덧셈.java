class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int numer, denom;

        numer = numer1 * denom2 + numer2 * denom1;
        denom = denom1 * denom2;
        
        // 분모분자의 최대공약수 구해서 나누기
        int max = 1;
        for(int i = 2; i <= numer && i <= denom; i++) {
            if (numer % i == 0 && denom % i == 0) {
                max = i;
            }   
        }
        
        numer = numer / max;
        denom = denom / max;
        
        int[] answer = {numer, denom};
        return answer;
    }
}