class Circle2D{
    private double x;
    private double y;
    private double radius;
    Circle2D(){
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }

    Circle2D(double x,double y,double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }

    public double getPerimeter(){
        return Math.PI * 2 * radius;
    }

    //x，y在圆内，返回true
    public boolean contains(double x,double y){
        return Math.pow((x - this.x),2) + Math.pow((y - this.y),2) < Math.pow((radius - this.radius),2);
    }

    //给定圆在圆内，返回true
    public boolean contains(Circle2D circle) {
        return Math.pow((circle.x - this.x), 2) + Math.pow((circle.y - this.y), 2) <= Math
                .abs(circle.radius - this.radius);
    }

    //两圆重合，返回true
    public boolean overlaps(Circle2D circle) {
        return Math.pow((circle.x - this.x), 2) + Math.pow((circle.y - this.y), 2) > Math
                .abs(circle.radius - this.radius)
                && Math.pow((circle.x - this.x), 2) + Math.pow((circle.y - this.y), 2) < circle.radius + this.radius;
    }

public class TestCircle2D {
    public static void main(String[] args) {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        System.out.println("The c1's area is " + c1.getArea());
        System.out.println("The c1's perimeter is " + c1.getPerimeter());
        System.out.println("The points contains is " + c1.contains(3, 3));
        System.out.println("The circle contains is " + c1.contains(new Circle2D(4, 5, 10.5)));
        System.out.println("The circle contains is " + c1.contains(new Circle2D(3, 5, 2.3)));
    }
}
}