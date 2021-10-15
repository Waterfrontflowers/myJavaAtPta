package 判断三角形类型7_8;

/**
 * @author Ginger
 * @date 2021/9/6
 */

public class Main {
    public static void main(String[] args){
        java.util.Scanner s = new java.util.Scanner(System.in);
        //java.util.Formatter f = new java.util.Formatter(System.out);
        double[] a = new double[3];
        for(int i = 0 ; i < 3 ; i++){
            a[i] = s.nextDouble();
            if(a[i] < 1 || a[i] > 200){
                System.out.print("Wrong Format");
                return;
            }
        }
        if(a[0] + a[1] <= a[2] || a[0] + a[2] <= a[1] ||a[1] + a[2] <= a[0]){
            System.out.print("Not a triangle");
        }
        else if(a[0] == a[1] && a[0] == a[2] && a[1] == a[2] ){
            System.out.print("Equilateral triangle");
        }
        else if(Math.abs(Math.pow(a[0],2) + Math.pow(a[1],2) - Math.pow(a[2],2)) < 0.0001 || Math.abs(Math.pow(a[0],2) + Math.pow(a[2],2) - Math.pow(a[1],2)) <0.0001 || Math.abs(Math.pow(a[1],2) + Math.pow(a[2],2) - Math.pow(a[0],2)) < 0.0001){
            if(a[0] == a[1] || a[0] == a[2] || a[1] == a[2]){
                System.out.print("Isosceles right-angled triangle");
            }
            else {
                System.out.print("Right-angled triangle");
            }
        }
        else if(a[0] == a[1] || a[0] == a[2] || a[1] == a[2]){
            System.out.print("Isosceles triangle");
        }
        else {
            System.out.print("General triangle");
        }
    }
}
