/**
 * @author yangshuai
 * @Description: 抽象队列实现相同逻辑的接口
 * @date 2020/6/22 5:51 下午
 */
public abstract class AbstractDeque<T> implements Deque<T> {

    protected int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
