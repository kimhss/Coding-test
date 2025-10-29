import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer> num = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            num.put(genres[i], num.getOrDefault(genres[i], 0) + plays[i]);
            
            if (!music.containsKey(genres[i])) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music.put(genres[i], map);
            } else {
                music.get(genres[i]).put(i, plays[i]);
            }
        }
        
        List<String> keySet = new ArrayList(num.keySet());
        // num 해시맵의 키셋(장르명)을 리스트로 변환 (정렬하기 위해)
        keySet.sort((s1, s2) -> Integer.compare(num.get(s2), num.get(s1)));
        /*
            리스트를 재생 횟수에 따라 내림차순으로 정렬
            s1과 s2는 keySet에서 가져온 두 개의 장르명.
            num.get(s1)과 num.get(s2)는 각각 장르 s1과 s2의 총 재생 횟수를 num 해시맵에서 조회.
            num.get(s2) - num.get(s1)는 s2의 재생 횟수에서 s1의 재생 횟수를 빼는 것.

            결과가 양수라면 s2가 s1보다 더 많이 재생된 것이므로, s2를 s1보다 앞에 위치 (내림차순).
            결과가 음수라면 s1이 s2보다 더 많이 재생되어, s1을 s2보다 앞에 위치.
            결과가 0이면 두 장르의 재생 횟수가 같다는 의미 => 두 요소의 순서는 변경되지 않는다.
        */
        
        for(String key : keySet) {
            HashMap<Integer, Integer> map = music.get(key);
            List<Integer> genre_key = new ArrayList(map.keySet());
            
            genre_key.sort((s1, s2) -> {
                if (map.get(s2).equals(map.get(s1))) {
                    return s1 - s2; // 재생수 같으면 고유번호 낮은 순
                } else {
                    return map.get(s2) - map.get(s1); // 재생수 내림차순
                }
            }); // 각 장르의 HashMap에서 키셋(노래 고유 번호)을 가져와 재생 횟수에 따라 내림차순으로 정렬
            
            answer.add(genre_key.get(0));
            if(genre_key.size() > 1)
                answer.add(genre_key.get(1));
            // 각 장르에서 재생 횟수가 가장 높은 노래 두 개를 answer 리스트에 추가
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
        
    }
}