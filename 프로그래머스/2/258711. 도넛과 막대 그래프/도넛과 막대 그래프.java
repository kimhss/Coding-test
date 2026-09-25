class Solution {
    static int[] in;
    static int[] out;
    
    public int[] solution(int[][] edges) {
        in = new int[1000001];
        out = new int[1000001];
        
        int maxNode = 0;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            out[from]++;
            in[to]++;
            
            maxNode = Math.max(maxNode, Math.max(from, to));
        }
        
        int start = 0;
        int donut = 0;
        int bar = 0;
        int eight = 0;
        
        for (int i = 1; i <= maxNode; i++) {
            
            // 정점
            if (out[i] >= 2 && in[i] == 0) {
                start = i;
            }
            
            // 막대 마지막 정점
            else if (out[i] == 0 && in[i] > 0) {
                bar++;
            }
            
            // 8자
            else if (out[i] == 2 && in[i] >= 2) {
                eight++;
            }
        }
        
        donut = out[start] - bar - eight;
        
        int[] answer = {start, donut, bar, eight};
        return answer;
    }
}