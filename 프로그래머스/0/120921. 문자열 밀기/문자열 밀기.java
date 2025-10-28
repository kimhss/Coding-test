class Solution {
    public int solution(String A, String B) {
        String copy = B.repeat(2);
        return copy.indexOf(A);
    }
}