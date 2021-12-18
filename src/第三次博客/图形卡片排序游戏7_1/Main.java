package 第三次博客.图形卡片排序游戏7_1;

import java.util.*;

/**
 * @author Ginger
 * @date 2021/11/5
 */

public class Main {
    final static int circle = 1, rectangle = 2, triangle = 3, trapezoid = 4;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        ArrayList<Shape> arrayList = new ArrayList<>();
        n = s.nextInt();
        while (n!=0){
            if(n == circle){
                arrayList.add(new Circle());
            }
            else if(n == rectangle){
                arrayList.add(new Rectangle());
            }
            else if(n == triangle){
                arrayList.add(new Triangle());
            }
            else if(n == trapezoid){
                arrayList.add(new Trapezoid());
            }
            else{
                System.out.println("Wrong Format");
                return;
            }
            n = s.nextInt();
        }

        for(Shape i : arrayList){
            if(i instanceof Circle){
                ((Circle)i).setRadius(s.nextDouble());
                if(!i.validate()){
                    System.out.println("Wrong Format");
                    return;
                }
            }
            else if(i instanceof Rectangle){
                ((Rectangle)i).setLength(s.nextDouble());
                ((Rectangle)i).setWidth(s.nextDouble());
                if(!i.validate()){
                    System.out.println("Wrong Format");
                    return;
                }
            }
            else if(i instanceof Triangle){
                ((Triangle)i).setSide1(s.nextDouble());
                ((Triangle)i).setSide2(s.nextDouble());
                ((Triangle)i).setSide3(s.nextDouble());
                if(!i.validate()){
                    System.out.println("Wrong Format");
                    return;
                }
            }
            else if(i instanceof Trapezoid){
                ((Trapezoid)i).setTop(s.nextDouble());
                ((Trapezoid)i).setBottom(s.nextDouble());
                ((Trapezoid)i).setHigh(s.nextDouble());
                if(!i.validate()){
                    System.out.println("Wrong Format");
                    return;
                }
            }
        }

        double sum = 0;

        System.out.println("The original list:");
        for(Shape i : arrayList){
            if(i instanceof Circle) {
                System.out.printf("Circle:%.2f ",i.getArea());
                sum += i.getArea();
            }
            else if(i instanceof Rectangle) {
                System.out.printf("Rectangle:%.2f ",i.getArea());
                sum += i.getArea();
            }
            else if(i instanceof Triangle) {
                System.out.printf("Triangle:%.2f ",i.getArea());
                sum += i.getArea();
            }
            else if(i instanceof Trapezoid) {
                System.out.printf("Trapezoid:%.2f ",i.getArea());
                sum += i.getArea();
            }
        }

        arrayList.sort(Shape::compareTo);

        System.out.println("\nThe sorted list:");
        for(Shape i : arrayList){
            if(i instanceof Circle) {
                System.out.printf("Circle:%.2f ",i.getArea());
            }
            else if(i instanceof Rectangle) {
                System.out.printf("Rectangle:%.2f ",i.getArea());
            }
            else if(i instanceof Triangle) {
                System.out.printf("Triangle:%.2f ",i.getArea());
            }
            else if(i instanceof Trapezoid) {
                System.out.printf("Trapezoid:%.2f ",i.getArea());
            }
        }

        System.out.printf("\nSum of area:%.2f",sum);

    }
}

abstract class Shape implements Comparable{
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

    @Override
    public int compareTo(Object o){
        if(this.getArea()<((Shape)o).getArea()){
            return 1;
        }
        else if(this.getArea()>((Shape)o).getArea()){
            return -1;
        }
        else{
            return 0;
        }
    }

}

class Circle extends Shape{
    private double radius;
    @Override
    public double getArea(){
        return Math.PI * radius * radius;
    }

    @Override
    public boolean validate(){
        return radius > 0;
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(double radius){
        this.radius = radius;
    }

    public Circle(){

    }

}

class Rectangle extends Shape{
    private double width;
    private double length;
    @Override
    public double getArea(){
        return width * length;
    }

    @Override
    public boolean validate(){
        return width >= 0 && length >= 0;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }

    public Rectangle(){

    }
}



class Triangle extends Shape{
    private double side1;
    private double side2;
    private double side3;
    @Override
    public double getArea(){
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public boolean validate(){
        return side1 >= 0 && side2 >= 0 && side3 >= 0 && side1 + side2 > side3 && side2 + side3 > side1 && side1 + side3 > side2;
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    public Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Triangle(){

    }
}

class Trapezoid extends Shape{
    private double top;
    private double bottom;
    private double high;
    @Override
    public double getArea(){
        return (top + bottom) * high / 2.0;
    }

    @Override
    public boolean validate(){
        return top >= 0 && bottom >= 0 && high >= 0 ;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public Trapezoid(double top, double bottom, double high){
        this.top = top;
        this.bottom = bottom;
        this.high = high;
    }

    public Trapezoid(){

    }
}