interface Edible {//设计一个Edible接口来明确一个对象是否是可食用的
    public abstract String howToEat();//接口中方法格式固定：public abstract
}

abstract class Animal2 {//抽象类Animal
    public abstract String sound();//抽象方法（动物叫声）
}

class Chicken extends Animal2 implements Edible {//鸡肉类继承抽象的Animal类并实现Edible接口以表明小鸡是可食用的
    @Override
    public String sound() {
        return "Chicken:cock-a-doodle-doo";
    }//重写sound方法

    @Override
    public String howToEat() {
        return "Chicken:Fry it";
    }//重写howToEat方法
}
class Tiger extends Animal2{//老虎类继承抽象的Animal类
    @Override
    public  String sound(){
        return "Tiger:RROOAARR";
    }//重写sound方法
}
abstract class Fruit implements Edible{//抽象类Fruit实现接口Edible，因为它不实现howToEat方法，所以Fruit类必须是抽象的

}
class Apple extends Fruit {//苹果类继承Fruit类（Fruit的子类必须实现howToEat方法）
    @Override
    public String howToEat(){
        return "Make apple cider";
    }//重写howToEat方法
}
class Orange extends Fruit{//橘子类继承水果类（Fruit的子类必须实现howToEat方法）
    @Override
    public String howToEat(){
        return "Orange:Make orange juice";
    }//重写howToEat方法
}

public class TestEdible {
    public static void main(String[] args) {
        Object[] objects={new Chicken(),new Tiger(),new Orange()};//创建对象数组
        for (int i=0;i<objects.length;i++){
            if (objects[i]instanceof Edible)
                System.out.println(((Edible)objects[i]).howToEat());
            if (objects[i]instanceof Animal2)
                System.out.println(((Animal2)objects[i]).sound());
        }
    }
}