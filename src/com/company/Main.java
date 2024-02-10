package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // создаём пулл объектов
        ObjectPool objectPool = new ObjectPool();
        // создаём объект, вытаскиваем его
        myObject m = objectPool.get();
        // и релизим его
        objectPool.release(m);
    }
}

class myObject{}

// создаём паттерн Пулл объектов
// Он помогает держать большое кол-во объектов и следить за тем что используется
// что бы не создавать лишние сущности
class ObjectPool{
    List<myObject> free = new LinkedList<>();
    List<myObject> used = new LinkedList<>();

    // если свободных объектов нету, то он создаст, а иначе отдаст свободный
    public myObject get(){
        if (free.isEmpty()){
            myObject m1 = new myObject();
            free.add(m1);
            return m1;
        }else {
            myObject m2 = free.get(0);
            used.add(m2);
            free.remove(m2);
            return m2;
        }
    }

    // как только закончили работу с объектом, кидаем его в свободные
    public void release(myObject m){
        used.remove(m);
        free.add(m);
    }
}
