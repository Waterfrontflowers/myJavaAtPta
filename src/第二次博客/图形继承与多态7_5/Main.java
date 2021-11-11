package 第二次博客.图形继承与多态7_5;

import java.util.*;

/**
 * @author Ginger
 * @date 2021/11/11
 */


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n1,n2,n3;
        n1 = s.nextInt();
        n2 = s.nextInt();
        n3 = s.nextInt();
        if(n1 < 0 || n2 < 0 ||n3 < 0){
            System.out.println("Wrong Format");
            System.exit(0);
        }
        ArrayList<Shape> arrayList = new ArrayList<>();
        while(n1-- > 0){
            double r = s.nextDouble();
            Shape shape = new Circle(r);
            if(!shape.validate()){
                System.out.println("Wrong Format");
                System.exit(0);
            }
            arrayList.add(shape);
        }
        while(n2-- > 0){
            double width = s.nextDouble();
            double length = s.nextDouble();
            Shape shape = new Rectangle(width,length);
            if(!shape.validate()){
                System.out.println("Wrong Format");
                System.exit(0);
            }
            arrayList.add(shape);
        }
        while(n3-- > 0){
            double side1 = s.nextDouble();
            double side2 = s.nextDouble();
            double side3 = s.nextDouble();
            Shape shape = new Triangle(side1,side2,side3);
            if(!shape.validate()){
                System.out.println("Wrong Format");
                System.exit(0);
            }
            arrayList.add(shape);
        }
        double sum = 0;
        System.out.println("Original area:");
        for (Shape shape : arrayList) {
            System.out.printf("%.2f ", shape.getArea());
            sum += shape.getArea();
        }
        System.out.printf("\nSum of area:%.2f\n", sum);
        //Arrays.sort(arrayList.);
        arrayList.sort((o1, o2) -> (int) (o1.getArea() - o2.getArea()));
        System.out.println("Sorted area:");
        for (Shape shape : arrayList) {
            System.out.printf("%.2f ", shape.getArea());
        }
        System.out.printf("\nSum of area:%.2f\n", sum);
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
        return width >= 0 && length >= 0;
    }

    public Rectangle(double width,double length){
        this.width = width;
        this.length = length;
    }
}

class Triangle extends Shape{
    private final double side1;
    private final double side2;
    private final double side3;
    @Override
    public double getArea(){
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public boolean validate(){
        return side1 >= 0 && side2 >= 0 && side3 >= 0 && side1 + side2 > side3 && side2 + side3 > side1 && side1 + side3 > side2;
    }

    public Triangle(double side1,double side2,double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
}