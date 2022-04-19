public class MyPoint {
    private double x, y;
    public MyPoint(){
        this.x = 0;
        this.y = 0;
    }
    public MyPoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double distance(MyPoint myPoint){
        return Math.sqrt((x - myPoint.x) * (x - myPoint.x) + (y - myPoint.y) * (y - myPoint.y));
    }
    public double distance(double x, double y) {
        MyPoint myPoint = new MyPoint(x, y);
        return distance(myPoint);
    }
    public static double distance(MyPoint myPoint1, MyPoint myPoint2) {
        return myPoint1.distance(myPoint2);
    }

    public static void main(String[] args) {
        MyPoint myPoint1 = new MyPoint();
        MyPoint myPoint2 = new MyPoint(10,30.5);
        System.out.println(myPoint1.distance(myPoint2));
        System.out.println(myPoint1.distance(10,30.5));
        System.out.println(MyPoint.distance(myPoint1,myPoint2));
    }
}