import java.util.ArrayList;
import java.util.Collection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public class TestIterator {
    public static void main(String[] args){
        Collection<String> collection = new ArrayList<>();
        collection.add("NNn");
        collection.add("AAn");
        collection.add("DDn");
        collection.add("MMm");

        Iterator<String> iterator = collection.iterator(); //迭代器
        while(iterator.hasNext()){
            //返回下一个元素的大写形式（遍历）
            System.out.println(iterator.next().toUpperCase() + "");
        }
        System.out.println();
    }
}