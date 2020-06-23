/**
 * @author yangshuai
 * @Description: 双向队列接口
 * @date 2020/6/22 4:36 下午
 */
public interface Deque<T> {

    /**
     * 添加到队列头部
     *
     * @param item
     */
    void addFirst(T item);

    /**
     * 添加到队列尾部
     *
     * @param item
     */
    void addLast(T item);

    /**
     * 队列为空，返回true，否则返回false
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 返回队列元素大小
     * @return
     */
    int size();


    /**
     * 从头打印双向队列
     */
    void printDeque();

    /**
     * 移除队列头部元素
     *
     * @return
     */
    T removeFirst();

    /**
     * 移除队列尾部元素
     *
     * @return
     */
    T removeLast();

    /**
     * 获得指定索引元素
     *
     * @param index
     * @return
     */
    T get(int index);


}
