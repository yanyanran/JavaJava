abstract class Animal{
    public void sleep(){
        System.out.println("我趴着睡");
    }
    public abstract void eat();
}

class Dog extends Animal{
    public Dog(){
        super();
    }

    @Override
    public void eat(){
        System.out.println("我实现了父类方法，狗吃肉");
    }
}

class Cat extends Animal{
    public Cat(){
        super();
    }

    @Override
    public void eat(){
        System.out.println("我实现了父类方法，猫吃鱼");
    }
}
public class TestAnimal {
    public static void main(String[] args){
        Animal a1 = new Dog();
        a1.sleep();
        a1.eat();
        System.out.println("----------------------");
        Animal a2 = new Cat();
        a2.sleep();
        a2.eat();
    }
}