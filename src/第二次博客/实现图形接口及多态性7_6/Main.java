package 第二次博客.实现图形接口及多态性7_6;

import java.util.*;

/**
 * @author Ginger
 * @date 2021/11/11
 */


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Shape> arrayList = new ArrayList<>();

        double r = s.nextDouble();
        Shape shape = new Circle(r);
        if(!shape.validate()){
            System.out.println("Wrong Format");
            System.exit(0);
        }
        arrayList.add(shape);

        double width = s.nextDouble();
        double length = s.nextDouble();
        shape = new Rectangle(width,length);
        if(!shape.validate()){
            System.out.println("Wrong Format");
            System.exit(0);
        }
        arrayList.add(shape);

        for (Shape sh : arrayList) {
            System.out.printf("%.2f\n", sh.getArea());
        }

    }
}

abstract class Shape{
    public double getArea(){
        return 0.0;
    }
    public boolean validate(){
        return false;
    }
    @Override
    public String toString(){
        return String.valueOf(getArea());
    }

}

class Circle extends Shape{
    private final double radius;
    @Override
    public double getArea(){
        return Math.PI * radius * radius;
    }

    @Override
    public boolean validate(){
        return radius > 0;
    }

    public Circle(double radius){
        this.radius = radius;
    }
}

class Rectangle extends Shape{
    private final double width;
    private final double length;
    @Override
    public double getArea(){
        return width * length;
    }

    @Override
    public boolean validate(){
        return width > 0 && length > 0;
    }

    public Rectangle(double width,double length){
        this.width = width;
        this.length = length;
    }
}
