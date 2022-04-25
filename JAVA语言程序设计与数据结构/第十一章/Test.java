class A {
    public void show(A obj){
        System.out.println("A and A");
    }

    //重载
    public void show(D obj){
        System.out.println("A and D");
    }
}

//链状继承
class B extends A {
    public void show(A obj){
        System.out.println("B and A");
    } //重写

    public void show(B obj){
        System.out.println("B and B");
    }

    public void show(E obj){
        System.out.println("B and E");
    }
}

class C extends B{
}

class D extends B{
}

class E{
}

public class Test {
    public static void main(String[] args){
        A a1 = new A();
        A a2 = new B();

        B b = new B();
        C c = new C();
        D d = new D();
        E e = new E();

        /*函数参数的自动类型转换（子类自动转父类）*/
        a1.show(b);  /*A and A*/
        a1.show(c);  /*A and A*/
        a1.show(d);  /*A and D*/

        /*(方法匹配和方法绑定)
        先是在类A里面进行方法匹配，运行时再到类B里面看看有没有重写，发现只有一个public void show(A obj)重写了，那么就只有这个方法是调用B的方法，其他都调用A的方法
        * */
        a2.show(b);  /*B and A*/
        a2.show(c);  /*B and A*/
        a2.show(d);  /*A and D*/

        //a2.show(e);//这句编译不过
        /*原因:编译阶段，方法匹配不成功。因为A里面没有show(E obj)的，而且也没有show(O obj)的。（其中O是E的父类型）
        转回实际类型：B a2 = (B)a2; a2.show(e)就没问题了*/

        b.show(b);  /*B and B*/
        b.show(c);  /*B and B*/

        /*由于继承的原因，其实A中的非private方法都被B继承了，所以B自己找不到方法的，会去父类A里找*/
        b.show(d);  /*A and D*/

        b.show(e);  /*B and E*/
    }
}