package 一元多项式求导7_3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ginger
 * @date 2021/9/27
 */

public class Main {
    public static void main(String[] args){
        java.util.Scanner s = new java.util.Scanner(System.in);
        //java.util.Formatter f = new java.util.Formatter(System.out);
        String str = s.nextLine();
        Polynomial p = new Polynomial(str);
        //System.out.println(p.getPolynomial());
        if(!p.regular()){
            System.out.println("Wrong Format");
            return;
        }
        p.derivative();
        p.print();
    }
}

class Polynomial{
    private String polynomial;
    private long[] coefficient = new long[100];
    private long[] index = new long[100];
    private int cnt = 0;

    public Polynomial(String polynomial){
        this.polynomial = polynomial.replace(" ","");
    }

    public String getPolynomial() {
        return polynomial;
    }

    public boolean regular(){
        String rule = "((-)?\\d\\d*)?(\\*)?(-)?(x)?(\\^)?((-)?\\d\\d*)?";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(polynomial);
        while(matcher.find()){
            if(matcher.group().length()>0) {
                //System.out.println(matcher.group());
                String rule1 = "((-)?\\d\\d*)(\\*)(-)?(x)(\\^)((-)?\\d\\d*)";
                Pattern pattern1 = Pattern.compile(rule1);
                Matcher matcher1 = pattern1.matcher(matcher.group());
                if (matcher1.find()) {
                    //System.out.println("ALL");
                    int add = 0;
                    for (int i = 0;matcher.group().charAt(i)!='*';i++){
                        add++;
                    }
                    String s = matcher.group().substring(0,add);
                    coefficient[cnt] = Long.valueOf(s);
                    s = matcher.group().substring(add+3,matcher.group().length());
                    index[cnt] = Long.valueOf(s);
                    // System.out.println(coefficient[cnt] + " " + index[cnt]);
                    if(coefficient[cnt] == 0 || index[cnt] == 0){
                        return false;
                    }
                    cnt++;
                    continue;
                }

                String rule2 = "((-)?\\d\\d*)(\\*)(-)?(x)(?!(\\^)((-)?\\d\\d*))";
                Pattern pattern2 = Pattern.compile(rule2);
                Matcher matcher2 = pattern2.matcher(matcher.group());
                if (matcher2.find()) {
                    //System.out.println("幂为一");
                    int add = 0;
                    for (int i = 0;matcher.group().charAt(i)!='*';i++){
                        add++;
                    }
                    String s = matcher.group().substring(0,add);
                    coefficient[cnt] = Long.valueOf(s);
                    index[cnt] = 1;
                    //System.out.println(coefficient[cnt] + " " + index[cnt]);
                    if(coefficient[cnt] == 0){
                        return false;
                    }
                    cnt++;
                    continue;
                }


                if (matcher.group().charAt(0) == 'x' || matcher.group().charAt(0) == '-' && matcher.group().charAt(1) == 'x') {
                    //System.out.println("系数为正负一");
                    int add = 0;
                    for (int i = 0;matcher.group().charAt(i)!='x';i++){
                        add++;
                    }
                    String s = matcher.group().substring(0,add);
                    if(matcher.group().charAt(0) == 'x'){
                        coefficient[cnt] = 1;
                    }
                    else {
                        coefficient[cnt] = -1;
                    }

                    s = matcher.group().substring(add+2,matcher.group().length());
                    if(!s.isEmpty()) {
                        index[cnt] = Long.valueOf(s);
                    }
                    //System.out.println(coefficient[cnt] + " " + index[cnt]);
                    if(coefficient[cnt] == 0){
                        return false;
                    }
                    cnt++;
                    continue;
                }

            }
        }
        return true;
    }

    public void derivative(){
        for(int i = 0 ; i <cnt ; i++) {
            coefficient[i] *= index[i];
            index[i]--;
        }
    }

    public void print(){
        int out = 0;
        for(int i = 0; i < cnt;i++){
            if(out!=0 && coefficient[i]>0&&index[i]!=0){
                System.out.print("+");
            }
            if(coefficient[i]==0){
                continue;
            }
            else if(coefficient[i]==-1){
                System.out.print("-");
            }
            else if(coefficient[i]!=1){
                System.out.print(coefficient[i]);
            }
            if(index[i]==0){
                if(coefficient[i]==-1){
                    System.out.print("1");
                }
                else if(coefficient[i]==1){
                    System.out.print("1");
                }
                out++;
                continue;
            }
            else if(index[i]==1){
                if(coefficient[i] != 1 || coefficient[i]!= -1) {
                    System.out.print("*x");
                    out++;
                }
                else {
                    System.out.print("x");
                    out++;
                }
            }
            else {
                System.out.print("*x^"+index[i]);
                out++;
            }

        }
        if(out==0){
            System.out.print("0");
        }
    }
}

