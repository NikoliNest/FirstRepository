package NesterovAlorythm4;

import java.util.Objects;package NesterovAlorythm4;

public interface IteratorRL {
    //int position = 0;
    //int size = getSize(this);
    Node current = null;


    int getSize(DoubleRelatedList doubleRelatedList);
    void  reset(DoubleRelatedList doubleRelatedList);
    void  next(DoubleRelatedList doubleRelatedList);
    void previous(DoubleRelatedList doubleRelatedList);
    public Node getCurrent();
    boolean atEnd(DoubleRelatedList doubleRelatedList);
    DoubleRelatedList insertAfter(DoubleRelatedList doubleRelatedList, String name, int age);
    DoubleRelatedList insertBefore(DoubleRelatedList doubleRelatedList, String name, int age);
    DoubleRelatedList deleteCurrent(DoubleRelatedList doubleRelatedList);
}




public class DoubleRelatedList implements IteratorRL {

    private Node head;
    private Node tail;


    //    reset() вЂ“ РїРµСЂРµРјРµС‰РµРЅРёРµ РІ РЅР°С‡Р°Р»Рѕ СЃРїРёСЃРєР°;
//    next() вЂ“ РїРµСЂРµРјРµС‰РµРЅРёРµ РёС‚РµСЂР°С‚РѕСЂР° Рє СЃР»РµРґСѓСЋС‰РµРјСѓ СЌР»РµРјРµРЅС‚Сѓ;
//    getCurrent() вЂ“ РїРѕР»СѓС‡РµРЅРёРµ СЌР»РµРјРµРЅС‚Р°, РЅР° РєРѕС‚РѕСЂС‹Р№ СѓРєР°Р·С‹РІР°РµС‚ РёС‚РµСЂР°С‚РѕСЂ;
//    atEnd() вЂ“ РІРѕР·РІСЂР°С‰Р°РµС‚ true, РµСЃР»Рё РёС‚РµСЂР°С‚РѕСЂ РЅР°С…РѕРґРёС‚СЃСЏ РІ РєРѕРЅС†Рµ СЃРїРёСЃРєР°;
//    insertAfter() вЂ“ РІСЃС‚Р°РІРєР° СЌР»РµРјРµРЅС‚Р° РїРѕСЃР»Рµ РёС‚РµСЂР°С‚РѕСЂР°;
//    insertBefore() вЂ“ РІСЃС‚Р°РІРєР° СЌР»РµРјРµРЅС‚Р° РґРѕ РёС‚РµСЂР°С‚РѕСЂР°;
//    deleteCurrent() вЂ“ СѓРґР°Р»РµРЅРёРµ СЌР»РµРјРµРЅС‚Р° РІ С‚РµРєСѓС‰РµР№ РїРѕР·РёС†РёРё РёС‚РµСЂР°С‚РѕСЂР°.
    DoubleRelatedList doubleRelatedList = this;
    int position;
    int size;// = getSize(this);
    Node current;

    public int getSize(DoubleRelatedList doubleRelatedList) {// Возвращает размер списка. Работает.
        Node tailRL = new Node();
        tailRL = doubleRelatedList.tail;
        Node headRL = new Node();
        headRL = doubleRelatedList.head;
        DoubleRelatedList tmpRL = new DoubleRelatedList();
        size = 1;
        while (!headRL.equals(tailRL)) {
            tmpRL.push(tailRL.getName(), tailRL.getAge());
            if (tailRL.prev == null) break;
            tailRL = tailRL.prev;
            size++;
        }
        return size;
    }

    public void reset(DoubleRelatedList doubleRelatedList) { // Перемещение в начало списка. Работает.
        current = new Node();
        current = doubleRelatedList.head;
        position = 0;
    }

    public void resetTail(DoubleRelatedList doubleRelatedList) { // Перемещение в начало списка. Работает.
        current = new Node();
        current = doubleRelatedList.tail;
        position = getSize(this);
    }

    public void next(DoubleRelatedList doubleRelatedList) { // Перемещение к следующему элементу
        if (position < (getSize(this) - 1)) {
            current = getCurrent();
            current = current.next;
            position++;
            System.out.println(current.toString());
        }
    }

    public void previous(DoubleRelatedList doubleRelatedList) { // Перемещение к следующему элементу. Работает.
        if (position > 0) {
            current = getCurrent();
            current = current.prev;
            position--;
            System.out.println(current.toString());
        }
    }

    public Node getCurrent() {      // Возвращает текущий элемент. Работает
        return current;
    }

    public boolean atEnd(DoubleRelatedList doubleRelatedList) { // в конце ли находится итератор
        if (position == (getSize(this) - 1)) return true;
        else return false;
    }

    public DoubleRelatedList insertAfter(DoubleRelatedList doubleRelatedList, String name, int age) { // Работает
        int prevPos = position;
        DoubleRelatedList tmpRL = new DoubleRelatedList();
        reset(doubleRelatedList);
        for (int i = getSize(this); i > prevPos; i--) {
            current = doubleRelatedList.pop();
            previous(doubleRelatedList);
            tmpRL.push(current.getName(), current.getAge());
        }
        tmpRL.push(name, age);
        resetTail(doubleRelatedList);
        for (int i = prevPos-1; i > 0; i--) {
            tmpRL.push(current.getName(), current.getAge());
            previous(doubleRelatedList);
        }
        tmpRL.push(current.getName(), current.getAge());
        reset(tmpRL);
        doubleRelatedList = tmpRL;
        position = prevPos;
        System.out.println("Position set to " + position);
        return doubleRelatedList;
    }

    public DoubleRelatedList insertBefore(DoubleRelatedList doubleRelatedList, String name, int age) { // Работает
        previous(doubleRelatedList);
        return this.insertAfter(doubleRelatedList, name, age);
    }

    public DoubleRelatedList deleteCurrent(DoubleRelatedList doubleRelatedList) {
        Node tmp = getCurrent();
        int prevPos = position;
        DoubleRelatedList tmpRL = new DoubleRelatedList();
        reset(doubleRelatedList);
        for (int i = getSize(doubleRelatedList); i > prevPos; i--) {
            current = doubleRelatedList.pop();
            tmpRL.push(current.getName(), current.getAge());
            previous(doubleRelatedList);
        }
        resetTail(doubleRelatedList);
        for (int i = prevPos-2; i > 0; i--) {
            tmpRL.push(current.getName(), current.getAge());
            previous(doubleRelatedList);
        }
        tmpRL.push(current.getName(), current.getAge());
        reset(tmpRL);
        this.doubleRelatedList = tmpRL;
        System.out.println("  drl " + doubleRelatedList.toString());
        System.out.println("  tmpRL " + tmpRL.toString() + " размер"  + getSize(tmpRL));
        position = prevPos;
        System.out.println("Position set to " + position);
        return tmpRL;
    }


    public DoubleRelatedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(String name, int age) {
        Node n = new Node(name, age);
        n.next = head;

        if (head == null)
            tail = n;
        else
            head.prev = n;

        head = n;
    }

    public Node pop() {
        if (this.isEmpty()) return null;
        Node temp = tail;
        tail.prev.next = null;
        tail = tail.prev;
        return temp;
    }

    public boolean find(String name, int age) {
        Node n = new Node(name, age);
        Node current = head;
        while (!current.equals(n)) {
            if (current.next == null) return false;
            else current = current.next;
        }
        return true;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current);
            current = current.next;
            sb.append((current == null) ? " ]" : ", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DoubleRelatedList drl = new DoubleRelatedList();
        drl.push("Ivan1", 10);
        drl.push("Ivan2", 20);
        drl.push("Ivan3", 30);
        drl.push("Ivan4", 40);
        drl.push("Ivan5", 50);
        drl.push("Ivan6", 60);
        drl.push("Ivan7", 70);
        drl.push("Ivan8", 80);

        System.out.println("Список: " + drl.toString());

        System.out.println("Размер: " + drl.getSize(drl));

        drl.reset(drl);
        drl.next(drl);
        drl.next(drl);
        drl.next(drl);
        drl.next(drl);

        drl.next(drl);
        drl = drl.insertBefore(drl, "Коля3", 35);
        System.out.println(drl.toString() + " Размер: " + drl.getSize(drl));

        drl.reset(drl);
        drl.next(drl);
        drl.next(drl);
        drl = drl.deleteCurrent(drl);

        System.out.println(drl.toString() + " Размер: " + drl.getSize(drl));
    }

}

package NesterovAlorythm4;

        import java.util.Objects;

public class Node {

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private String name;
    private int age;

    Node next;
    Node prev;

    public Node(String name, int age) {
        this.name = name;
        this.age = age;
        next = null;
        prev = null;
    }

    public Node() {
        this.name = null;
        this.age = 0;
        next = null;
        prev = null;
    }

    @Override
    public String toString() {
        return String.format("(Name: %s, age: %d)", name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return age == node.age && Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }
}