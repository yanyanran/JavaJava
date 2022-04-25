class Animal{
    public void shout(){
        System.out.println("叫了一声！");
    }
}

class Dog extends Animal{
    public void shout(){
        System.out.println("旺旺旺！");
    }
    public void seeDoor(){
        System.out.println("看门中....");
    }
}

class Cat extends Animal{
    public void shout(){
        System.out.println("喵喵喵喵！");
    }
}

public class TestAnimal {
    public static void main(String[] args){
        Animal a1 = new Cat(); // 向上自动转型
        animalCry(a1);
        Animal a2 = new Dog();
        animalCry(a2);

        Dog dog = (Dog)a2;  // 向下需强制类型转换
        dog.seeDoor();
    }

    // 有了多态，只需要让增加的这个类继承Animal类就可以了
    static void animalCry(Animal a) {
        a.shout();
    }
    /* 如果没有多态，就需要写很多重载的方法。
     * 每增加一种动物，就需要重载一种动物的喊叫方法，非常麻烦：
    static void animalCry(Dog d) {
        d.shout();
    }
    static void animalCry(Cat c) {
        c.shout();
    }*/
}

/*
喵喵喵喵！
旺旺旺！
看门中....*/
