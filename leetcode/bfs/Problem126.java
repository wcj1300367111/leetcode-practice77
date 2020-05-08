```
import java.util.*;

public class Problem126 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String   endWord = "cog";
        List<List<String>> list = new ArrayList<>();
        List<String> wordList  = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}) ;
        list= findLadders(beginWord, endWord, wordList);
        System.out.println(list);
    }
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dicts = new HashSet<>();
        for (String s : wordList) {
            dicts.add(s);
        }
        if (!dicts.contains(endWord)) {
            return res;
        }
        Set<String> visited = new HashSet<>();
        Queue<List<String>> queue = new ArrayDeque<>();
        List<String> begin = new ArrayList<>();
        begin.add(beginWord);
        queue.add(begin);
        visited.add(beginWord);
        boolean flag = false;
        while (!queue.isEmpty()&&!flag) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String word = path.get(path.size() - 1);
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    for (char ch = 'a'; ch < 'z'; ch++) {
                        chars[j]=ch;
                        if (temp == ch) {
                            continue;
                        }
                        String string = new String(chars);
                        if (dicts.contains(string) && !visited.contains(string)) {
                            // 生成新的路径
                            List<String> pathList = new ArrayList<>(path);
                            pathList.add(string);
                            if (string.equals(endWord)) {
                                flag = true;
                                res.add(pathList);
                            }
                            // 将该路径添加到该层队列中
                            queue.add(pathList);
                            // 将该单词添加到该层已访问的单词集合中
                            subVisited.add(string);
                        }
                    }
                    chars[j] = temp;
                }
            }
            visited.addAll(subVisited);
        }
            return res;
    }
}
```
