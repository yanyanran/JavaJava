import java.util.*;

public class CountOccurrenceOfWords {
    public static void main(String[] args){
        String text = "Good morning.Have a good Day" + "Have fun!";
        Map<String, Integer> map = new TreeMap<>();
        String[] words = text.split("[\\s+\\p{P}]");

        for(int i = 0; i < words.length; i++){
            String key = words[i].toLowerCase();

            if(key.length() > 0 ){
                if(!map.containsKey(key)){ //检测单词是否已经作为键存在
                    map.put(key,1); //如果没有，就将单词和初始统计次数（1）构成一个新条目存储到映射中
                }else {     //如果有，给单词的计数器+1
                    int value = map.get(key);
                    value ++;
                    map.put(key,value);
                }
            }
        }

        //按照出现次数递增顺序显示单词
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        //sort方法排序
        Collections.sort(entries, (entry1, entry2) -> {
            return entry1.getValue().compareTo(entry2.getValue());
        });
        for(Map.Entry<String,Integer> entry:entries){  //输出
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
