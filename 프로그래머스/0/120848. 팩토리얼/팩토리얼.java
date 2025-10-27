class Solution {
    public int solution(int n) {
        int i = 0;
        int fac = 1;
        
        while(fac <= n) {
            fac *= (i + 1);
            i++;
        }
        
        return i - 1;
    }
}