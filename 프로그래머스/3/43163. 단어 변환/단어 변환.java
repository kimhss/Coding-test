class Solution {
    static boolean[] visited;
    static int MIN = Integer.MAX_VALUE;
    static String[] words2;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        words2 = words.clone();
        
        dfs(begin, target, 0);
        
        return MIN == Integer.MAX_VALUE ? 0 : MIN;
    }
    
    public void dfs(String word, String target, int depth) {
        if (word.equals(target)) {
            MIN = Math.min(MIN, depth);
            return;
        }
        
        // 이미 구한 최솟값보다 깊어졌다면 더 탐색할 필요 없음
        if (depth >= MIN) return;
                
        for(int i = 0; i < words2.length; i++) {
            if (visited[i]) continue;
            
            if (changeable(word, words2[i])) {
                visited[i] = true;
                dfs(words2[i], target, depth + 1);
                visited[i] = false;
            }
        }
        
    }
    
    public boolean changeable(String word1, String word2) {
        int count = 0;
        
        for(int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                count++;
        }
        
        return count == 1;
    }
}