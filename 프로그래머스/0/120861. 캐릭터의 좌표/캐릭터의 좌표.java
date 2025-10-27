class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int numRow = board[1];
        int numCol = board[0];
        int r = 0;
        int c = 0;
        for (String key : keyinput) {
            switch (key.charAt(0)) {
                case 'l':
                    c = Math.max(-numCol / 2, c - 1);
                    break;
                case 'r':
                    c = Math.min(numCol / 2, c + 1);
                    break;
                case 'd':
                    r = Math.max(-numRow / 2, r - 1);
                    break;
                case 'u':
                    r = Math.min(numRow / 2, r + 1);
                    break;
            }
            System.out.println(r + " " + c);
        }
        return new int[]{c, r};
    }
}