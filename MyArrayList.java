package task1.list;

import java.util.*;

//Задание 1.2
public class MyArrayList <E> implements List<E> {

    private int capacity = 100;
    private Object [] mas = new Object[capacity];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.mas;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();

            try {
                MyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;

            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(mas, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(mas, size, a.getClass());
        System.arraycopy(mas, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        if(size() < capacity) {
            mas[size] = e;
            size++;
            return true;
        } else {
            resize();
            mas[size] = e;
            size++;
            return true;
        }
    }

    private void resize() {
        capacity += 50;
    }

    @Override
    public boolean remove(Object o) {
        if(o != null) {
            for(int index = 0; index < size; index++) {
                if(mas[index].equals(o)) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        while(mas.length < (size() + numNew)) {
            resize();
        }
        System.arraycopy(a, 0, mas, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        int numMoved = size - index;
        while(mas.length < (size() + numNew)) {
            resize();
        }
        System.arraycopy(mas, index , mas, index + numNew, numNew);
        System.arraycopy(a, 0, mas, index, numMoved);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b = false;
        Iterator<?> it = iterator();
        while(it.hasNext()) {
            if(c.contains(it.next())) {
                it.remove();
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean b = false;
        Iterator<?> it = iterator();
        while(it.hasNext()) {
            if(!c.contains(it.next())) {
                it.remove();
                b = true;
            }
        }
        return b;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            mas[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        return (E) mas[index];
    }

    @Override
    public E set(int index, E element) {
        E oldValue = (E) mas[index];
        mas[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if(index < size) {
            System.arraycopy(mas, index, mas, index + 1, size - index);
            mas[index] = element;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        E oldValue = (E) mas[index];

        if(index < size) {
            System.arraycopy(mas, index + 1, mas, index, size - index + 1);
            mas[size] = null;
            size--;
        } else {
            mas[index] = null;
            size--;
        }

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (mas[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(mas[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (mas[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(mas[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<?> that = (MyArrayList<?>) o;

        if (capacity != that.capacity) return false;
        if (size != that.size) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(mas, that.mas);

    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + Arrays.hashCode(mas);
        result = 31 * result + size;
        return result;
    }
}
