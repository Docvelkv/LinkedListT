// реализовать метод разворота LinkedList (строка 222)

public class LinkedListT<T extends Comparable<T>> {
    private Node root;
    private int size;

    /**
     * Добавление элемента в конец списка
     * @param value значение
     */
    public void add(T value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(value);
        size++;
    }

    /**
     * Добавление элемента по указанному индексу
     * @param index индекс нового элемента
     * @param value значение нового элемента
     */
    public void addAt(int index, T value) {
        if (index == 0) {
            Node newNode = new Node(value);
            newNode.next = root;
            root = newNode;
            size++;
            return;
        }
        Node prev = getNode(index - 1);
        Node newNode = new Node(value);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    /**
     * Добавление элементов с сортировкой по значению
     * @param value значение нового элемента
     */
    public void addSorted(T value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return;
        }
        if (root.value.compareTo(value) >= 0) {
            Node newNode = new Node(value);
            newNode.next = root;
            root = newNode;
            size++;
            return;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value.compareTo(value) >= 0) {
                Node newNode = new Node(value);
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                size++;
                return;
            }
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(value);
        size++;
    }

    /**
     * Удаление первого в списке элемента с указанным значением
     * @param value значение элемента
     * @return Если элемент с таким значением есть-true, иначе-false
     */
    public boolean remove(T value) {
        if (root == null) return false;
        if (root.value.compareTo(value) == 0) {
            root = root.next;
            size--;
            return true;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value.compareTo(value) == 0) {
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Удаление элемента по индексу
     * @param index индекс удаляемого элемента
     */
    public void removeAt(int index) {
        if (index == 0) {
            root.next = root.next.next;
            size--;
            return;
        }
        Node prevNode = this.getNode(index - 1);
        prevNode.next = prevNode.next.next;
        size--;
    }

    /**
     * Удаление всех элементов с указанным значением
     * @param value значение
     * @return количество удалённых элементов
     */
    public int removeAll(T value) {
        int oldSize = size;
        if (root == null) return 0;
        while (root != null && root.value.compareTo(value) == 0) {
            root = root.next;
            size--;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value.compareTo(value) == 0) {
                currentNode.next = currentNode.next.next;
                size--;
            } else currentNode = currentNode.next;
        }
        return oldSize - size;
    }

    /**
     * Получение элемента по индексу
     * @param index индекс элемента
     * @return Node
     */
    private Node getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }

    /**
     * Получение значения элемента по индексу
     * @param index индекс элемента
     * @return T
     */
    public T getValue(int index) {
        return this.getNode(index).value;
    }

    /**
     * Установка нового значения по индексу
     * @param index индекс
     * @param value новое значение
     */
    public void setValue(int index, T value) {
        getNode(index).value = value;
    }

    /**
     * Обмен значений двух элементов по их индексам
     * @param index1 индекс первого элемента
     * @param index2 индекс второго элемента
     */
    public void swap(int index1, int index2) {
        if (index1 == index2) return;
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) return;
        Node currentNode = root;
        Node node1 = null;
        Node node2 = null;
        for (int i = 0; currentNode != null; i++) {
            if (i == index1) node1 = currentNode;
            else if (i == index2) node2 = currentNode;
            if (node2 != null && node1 != null) break;
            currentNode = currentNode.next;
        }
        assert node1 != null;
        T temp = node1.value;
        assert node2 != null;
        node1.value = node2.value;
        node2.value = temp;
    }

    /**
     * Быстрая сортировка списка
     */
    public void quickSort() {
        quickSort(0, size - 1);
    }

    /**
     * Реализация быстрой сортировки
     * @param leftBorder индекс крайнего левого элемента
     * @param rightBorder индекс крайнего правого элемента
     */
    private void quickSort(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        T pivot = getValue((leftMarker + rightMarker) / 2);
        while (leftMarker <= rightMarker) {
            while (getValue(leftMarker).compareTo(pivot) < 0) leftMarker++;
            while (getValue(rightMarker).compareTo(pivot) > 0) rightMarker--;
            if (leftMarker <= rightMarker) {
                swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }
        if (leftMarker < rightBorder) quickSort(leftMarker, rightBorder);
        if (leftBorder < rightMarker) quickSort(leftBorder, rightMarker);
    }

    /**
     * Разворот списка
     */
    public void reverse(){
        if (size > 1) {
            int n = size - 1;
            for (int i = 0; i <= n / 2; i++) {
                swap(i, n - i);
            }
        }
    }

    /**
     * Вывод списка в консоль
     */
    public void print() {
        Node currentNode = root;
        System.out.print("[ ");
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
        System.out.print("]  size: " + size);
    }

    /**
     * Класс Node(элемент)
     */
    private class Node {
        T value;
        Node next;

        /**
         * Конструктор(элемент со значением)
         * @param value значение
         */
        Node(T value) {
            this.value = value;
        }
    }
}
