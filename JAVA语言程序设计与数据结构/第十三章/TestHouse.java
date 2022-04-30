class House implements Cloneable,Comparable<House> {
    private int id;
    private double area;
    private java.util.Date whenBuild;

    public House(int id, double area){
        this.id = id;
        this.area = area;
        whenBuild = new java.util.Date();
    }

    public int getId() {
        return id;
    }
    public double getArea() {
        return area;
    }
    public java.util.Date getWhenBuild() {
        return whenBuild;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
            //return super.clone();
        House houseClone = (House)super.clone();
        houseClone.whenBuild = (java.util.Date)(whenBuild.clone());
        return houseClone;
    }

    @Override
    public int compareTo(House o){
        if(area > o.area) return 1;
        else if(area < o.area) return -1;
        else return 0;
    }
}

public class TestHouse {
    public static void main(String[] args) throws CloneNotSupportedException {
        House house1 = new House(1, 1750.50);
        House house2 = (House)house1.clone();
    }
}