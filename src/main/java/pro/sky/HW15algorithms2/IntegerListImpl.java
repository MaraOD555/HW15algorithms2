package pro.sky.HW15algorithms2;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private final Integer[] repository; // поле - хранилище строк в массиве
    private int size; // поле - размер массива

    public IntegerListImpl() { // конструктор пустой, задаем количество элементов
        repository = new Integer[10];
    }

    public IntegerListImpl(int initSize) { // через конструктор задаем стартовый размер массива
        repository = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        checkSize();
        checkItem(item);
        repository[size++] = item;
        return item;// чтобы видеть что добавлено
    }

    @Override
    public Integer add(int index, Integer item) {
        checkSize();
        checkItem(item);
        checkIndex(index);
        if (index == size){
            repository[size++] = item; //сразу увеличиваем размер, т.к. проверки выполнены выше
            return item;
        }
        System.arraycopy(repository, index, repository, index + 1, index - size); // сдвиг всех элементов
        repository[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        repository[index] = item; //затираем значение, новое добавляем в ячейку
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        int index = indexOf(item); // находим индекс элемента
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = repository[index]; // здесь мы берем элемент, который будем удалять
        if(index != size) {
            System.arraycopy(repository, index + 1, repository, index, size - index); // сдвиг влево
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1; // если значение отличное от дефолтного,
        // то элемент находится внутри нашей коллекции
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if(repository[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size-1; i >= 0; i--) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index); // проверяем интервал
        return repository[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {

        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() { //обнуляем
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(repository, size);
    }

    private void checkItem (Integer item){ // проверка на null объекта
        if (item == null){
            throw new NullItemException();
        }
    }
    private void checkSize (){ // проверка size
        if (size == repository.length){
            throw new FullRepositoryException();
        }
    }
    private void checkIndex (int index){ // проверка индекса
        if (index < 0 || index > size){
            throw new InvalidIndexException();
        }
    }
}
