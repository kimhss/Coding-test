class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = {};
        int tmp = 0;
        for(int i = 0; i < num_list.length / 2; i++) {
            tmp = num_list[i];
            num_list[i] = num_list[num_list.length - i - 1];
            num_list[num_list.length - i - 1] = tmp;
        }
        
        return num_list;
    }
}