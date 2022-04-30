import java.awt.*;

class Rectangle {// 一个Rectangle类
    double width, length;
    double area;

    Rectangle(double w, double len) {
        width = w;
        length = len;
    }

    public double getArea() {
        area = width * length;
        return area;
    }
}

//自定义一个实现Comparable接口的Rectangle类的子类（可比较）
class ComparableRectangle extends Rectangle implements Comparable<ComparableRectangle>{
    public ComparableRectangle(double width, double height){
        super(width,height);
    }

    @Override
    public int compareTo(ComparableRectangle o){
        if(getArea() > o.getArea()){
            return 1;
        }else if(getArea() < o.getArea()){
            return -1;
        }else return 0;
    }

    @Override
    public String toString(){
        return super.toString() + " Area: " + getArea();
    }
}

public class SortRectangles {
    public static void main(String[] args){
        ComparableRectangle[] rectangles = {
                new ComparableRectangle(3.4,5.4),
                new ComparableRectangle(13.24,55.4),
                new ComparableRectangle(7.4,35.4),
                new ComparableRectangle(1.4,25.4)
        };
        java.util.Arrays.sort(rectangles); //就能使用sort方法排序了
        for(Rectangle rectangle: rectangles){
            System.out.print(rectangle + " ");
            System.out.println();
        }
    }
}