# Threadlocal原理机制
* 每个线程都会维护一个ThreadLocal.ThreadLocalMap私有对象
* ThreadLocalMap内部维护了一个Entry对象,属性value就是单个数据的值,Key为线程对象,map持有Entry[]数组,通过Entry来存储所有数据
* 所以ThreadLocalMap存储的值都是当前线程私有的
```java
public class ThreadlocalDemo {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(Integer.valueOf(23));

        new Thread(new ThreadlocalDemoThread()).start();

        System.out.println("hi, i am man thread "+threadLocal.get());
    }

    static class ThreadlocalDemoThread implements Runnable{

        @Override
        public void run() {
            System.out.println("hi , i am thread " + threadLocal.get());
        }
    }
}
```
* ThreadlocalDemoThread线程不能获取主线程的threadLocal里面的数据
# InheritableThreadLocal原理机制
* 重写了三个方法
```java
protected T childValue(T parentValue) {
        return parentValue;
    }
ThreadLocalMap getMap(Thread t) {
       return t.inheritableThreadLocals;
    }
void createMap(Thread t, T firstValue) {
        t.inheritableThreadLocals = new ThreadLocalMap(this, firstValue);
    }
```
* 调用get()方法时,未重写会调用父类的方法
```java
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```
* 可以看出getMap是调用子类的方法
* new MyThread()时会调用ThreadLocal.createInheritedMap(parent.inheritableThreadLocals)把父线程的局部变量copy到子线程中去
```java

private ThreadLocalMap(ThreadLocalMap parentMap) {
    Entry[] parentTable = parentMap.table;
    int len = parentTable.length;
    setThreshold(len);
    table = new Entry[len];
 
    for (int j = 0; j < len; j++) {
        Entry e = parentTable[j];
        if (e != null) {
            @SuppressWarnings("unchecked")
            ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
            if (key != null) {
                Object value = key.childValue(e.value);
                Entry c = new Entry(key, value);
                int h = key.threadLocalHashCode & (len - 1);
                while (table[h] != null)
                    h = nextIndex(h, len);
                    table[h] = c;
                    size++;
                }
            }
        }
    }
```
* key.childValue(e.value)是调用子类重写的方法,返回的是父线程的value
* 所有子线程拥有了父线程的值,不过必须是在父线程已经有值得情况
* 子线程对本地变量的修改不会影响到父线程
