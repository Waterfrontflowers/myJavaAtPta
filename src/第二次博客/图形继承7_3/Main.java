package 第二次博客.图形继承7_3;
import java.util.ArrayList;

/**
 * @author Ginger
 * @date 2021/11/11
 */


public class Main {
    final static int circle = 1,
            rectangle = 2,
            ball = 3,
            box = 4;
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        Output output = new Output();
        int type = s.nextInt();
        Shape shape = create(type,output);
        double[] value = new double[3];
        if(type == circle){
            value[0] = s.nextDouble();
        }
        else if(type == rectangle){
            value[0] = s.nextDouble();
            value[1] = s.nextDouble();
        }
        else if(type == ball){
            value[0] = s.nextDouble();
        }
        else if((type == box)){
            value[0] = s.nextDouble();
            value[1] = s.nextDouble();
            value[2] = s.nextDouble();
        }
        insertDate(type,shape,output,value);
        output.print();


    }

    public static Shape create(int type,Output output){

        Shape shape = null;
        if(type == circle){
            shape = new Circle(output);
        }
        else if(type == rectangle){
            shape = new Rectangle(output);
        }
        else if(type == ball){
            shape = new Ball(output);
        }
        else if(type == box){
            shape = new Box(output);
        }
        else{
            output.clean();
            output.add("Wrong Format");
            output.error();
        }
        return shape;
    }

    public static void insertDate(int type,Shape shape,Output output,double[] value){

        if(type == circle && !output.getError()){
            circle((Circle) shape, output, value);
        }
        else if(type == rectangle && !output.getError()){
            rectangle((Rectangle) shape, output, value);
        }
        else if(type == ball && !output.getError()){
            ball((Ball) shape, output, value);
        }
        else if(type == box && !output.getError()){
            box((Box) shape, output, value);
        }

    }

    public static void circle(Circle shape,Output output,double[] value){
        if (value[0] > 0) {
            shape.setRadius(value[0]);
            output.add(String.format("Circle's area:%.2f",shape.getArea()));
        }
        else{
            output.clean();
            output.add("Wrong Format");
            output.error();
        }
    }

    public static void rectangle(Rectangle shape,Output output,double[] value){
        if (value[0] > 0 && value[1] > 0) {
            shape.setWidth(value[0]);
            shape.setLength(value[1]);
            output.add(String.format("Rectangle's area:%.2f",shape.getArea()));
        }
        else{
            output.clean();
            output.add("Wrong Format");
            output.error();
        }
    }

    public static void ball(Ball shape,Output output,double[] value){
        if (value[0] > 0) {
            shape.setRadius(value[0]);
            output.add(String.format("Ball's surface area:%.2f",shape.getArea()));
            output.add(String.format("Ball's volume:%.2f",shape.getVolume()));
        }
        else{
            output.clean();
            output.add("Wrong Format");
            output.error();
        }
    }

    public static void box(Box shape,Output output,double[] value){
        if (value[0] > 0 && value[1] > 0 && value[2] > 0) {
            shape.setWidth(value[0]);
            shape.setLength(value[1]);
            shape.setHeight(value[2]);
            output.add(String.format("Box's surface area:%.2f",shape.getArea()));
            output.add(String.format("Box's volume:%.2f",shape.getVolume()));
        }
        else{
            output.clean();
            output.add("Wrong Format");
            output.error();
        }
    }

}

class Shape{
    public double getArea(){
        return .0;
    }

    public Shape(Output output){
        output.add("Constructing Shape");
    }
}

class Circle extends Shape{
    private double radius;

    @Override
    public double getArea(){
        return radius * radius * Math.PI;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(Output output){
        super(output);
        output.add("Constructing Circle");
    }
}

class Rectangle extends Shape{
    private double width;
    private double length;

    @Override
    public double getArea(){
        return width * length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Rectangle(Output output){
        super(output);
        output.add("Constructing Rectangle");
    }
}

class Ball extends Circle{
    @Override
    public double getArea(){
        return 4 * Math.PI * getRadius() * getRadius();
    }

    public double getVolume(){
        return 4 / 3.0 * Math.PI * Math.pow(getRadius(),3);
    }

    public Ball(Output output){
        super(output);
        output.add("Constructing Ball");
    }
}

class Box extends Rectangle{
    private double height;

    @Override
    public double getArea(){
        return 2 * (getLength() * getWidth() + getLength() * height + getWidth() * height);
    }

    public double getVolume(){
        return super.getArea() * height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Box(Output output){
        super(output);
        output.add("Constructing Box");
    }
}

class Output {
    private ArrayList<String> arrayList = new ArrayList<>();
    boolean error = false;

    public void add(String str){
        arrayList.add(str);
    }


    public void clean(){
        arrayList = new ArrayList<>();
    }

    public void print(){
        for (String s : arrayList) {
            System.out.println(s);
        }
    }

    public void error(){
        error = true;
    }

    public boolean getError(){
        return error;
    }
}