package 第一次博客.求下一天7_4;

/**
 * @author Ginger
 * @date 2021/9/21
 */

public class Main {
    public static void main(String[] args){
        java.util.Scanner s = new java.util.Scanner(System.in);
        //java.util.Formatter f = new java.util.Formatter(System.out);
        int year = s.nextInt();
        int month = s.nextInt();
        int day = s.nextInt();
        if(!checkInputValidity(year,month,day)){
            System.out.print("Wrong Format");
            return;
        }

        nextDate(year,month,day);

    }

    public static boolean isLeapYear(int year) //判断year是否为闰年，返回boolean类型；
    {
        if(year % 4 == 0 && year % 100 != 0 ||year%400 == 0){
            return true;
        }
        return false;
    }

    public static boolean checkInputValidity(int year, int month, int day)//判断输入日期是否合法，返回布尔值
    {
        if(year < 1820 || year > 2020){
            return false;
        }
        if(month<1 || month>12){
            return false;
        }
        if(day<1 || day>31){
            return false;
        }
        if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day>30){
                return false;
            }
        }
        if(isLeapYear(year) && month==2){
            if(day>29){
                return false;
            }
        }
        if(!isLeapYear(year) && month==2){
            if(day>28){
                return false;
            }
        }
        return true;
    }

    public static void nextDate(int year, int month, int day)  //求输入日期的下一天
    {
        day++;
        while(!checkInputValidity(year,month,day)){
            day++;
            if(day>31){
                month++;
                day = 1;
            }
            if(month>12){
                year++;
                month = 1;
            }
        }
        System.out.print("Next date is:" + year + "-" + month + "-" + day);
    }

}
