import java.util.Collection;
//MyList接口
public interface MyList<E> extends Collection<E> {
    // 指定位置添加
    public void add(int index, E e);
    // 返回索引处元素
    public E get(int index);
    // 返回表中第一个匹配元素的索引
    public int indexOf(Object e);
    // 返回表中最后一个匹配元素的索引
    public int lastIndexOf(E e);
    //移除并返回
    public E remove(int index);
    //替换
    public E set(int index, E e);

    @Override
    public default boolean add(E e){
        add(size(), e);
        return true;
    }

    @Override
    public default boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public default boolean remove(Object e){
        if(indexOf(e) >= 0){
            remove(indexOf(e));
            return true;
        }else return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c){
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c){
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c){
        return true;
    }

    @Override
    public default boolean retainAll(Collection<?> c){
        return true;
    }

    @Override
    public default Object[] toArray(){
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array){
        return null;
    }
}