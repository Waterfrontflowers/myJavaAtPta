package 定义日期类7_2;

/**
 * @author Ginger
 * @date 2021/9/27
 */

public class Main {
    public static void main(String[] args){
        java.util.Scanner s = new java.util.Scanner(System.in);
        //java.util.Formatter f = new java.util.Formatter(System.out);
        int year = s.nextInt();
        int month = s.nextInt();
        int day = s.nextInt();
        Date da = new Date(year,month,day);
        if(!da.checkInputValidity(year,month,day)){
            System.out.print("Date Format is Wrong");
            return;
        }

        da.getNextDate(year,month,day);

    }





}

class Date{
    private int year,month,day;
    final int[] mon_maxnum = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};

    public Date(){

    }

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isLeapYear(int year) //判断year是否为闰年，返回boolean类型；
    {
        if(year % 4 == 0 && year % 100 != 0 ||year%400 == 0){
            return true;
        }
        return false;
    }

    public boolean checkInputValidity(int year, int month, int day)//判断输入日期是否合法，返回布尔值
    {
        if(year < 1900 || year > 2000){
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

    public void getNextDate(int year, int month, int day)  //求输入日期的下一天
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
        System.out.print("Next day is:" + year + "-" + month + "-" + day);
    }
}