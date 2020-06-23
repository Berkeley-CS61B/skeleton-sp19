

import java.util.NoSuchElementException;

/**
 * @author yangshuai
 * @date 2020/6/19 4:29 下午
 * @description 环形数组实现的队列
 */
public class ArrayDeque<T> extends AbstractDeque<T> {

    /**
     * 队列底层数组，存储队列元素
     */
    private Object[] data;

    /**
     * 队列头部指针
     */
    private int head = 0;

    /**
     * 队列尾部指针
     */
    private int tail = 0;


    public ArrayDeque() {
        this.data = new Object[8];
    }

    /**
     * 添加到队列头部
     *
     * @param item
     */
    @Override
    public void addFirst(T item) {
        head = (head - 1 + data.length) % data.length;
        data[head] = item;
        size++;
        if (head == tail) {
            resize(data.length);
        }

    }


    /**
     * 队列内部数组扩容
     * 大小为原来的1.5倍
     *
     * @param oldCap
     */
    private void resize(int oldCap) {
        Object[] temp = new Object[oldCap + (oldCap >> 1)];
        for (int i = 0; i < size; i++) {
            temp[i] = data[(head + i) % data.length];
        }
        data = temp;
        head = 0;
        tail = size;
    }

    /**
     * 添加到队列尾部
     *
     * @param item
     */
    @Override
    public void addLast(T item) {
        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
        if (head == tail) {
            resize(data.length);
        }
    }

    /**
     * 从头开始打印队列元素
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[(head + i) % data.length] + " ");
        }
        System.out.println();
    }

    /**
     * 移除队列头部元素
     *
     * @return
     */
    @Override
    public T removeFirst() {
        Object item = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return (T) item;
    }

    /**
     * 移除队列尾部元素
     *
     * @return
     */
    @Override
    public T removeLast() {
        tail = (tail - 1 + data.length) % data.length;
        Object item = data[tail];
        data[tail] = null;
        size--;
        return (T) item;
    }

    /**
     * 返回指定索引元素
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return (T) data[(head + index) % data.length];
    }
}
