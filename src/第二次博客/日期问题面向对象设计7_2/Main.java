package 第二次博客.日期问题面向对象设计7_2;

/**
 * @author Ginger
 * @date 2021/11/11
 */


public class Main {

    public static void main(String[] args) {
        final int next = 1,
                Previous = 2,
                daysOfDates = 3;
        java.util.Scanner s = new java.util.Scanner(System.in);
        int choice = s.nextInt();
        int year1,month1,day1,n,year2,month2,day2;
        year1 = s.nextInt();
        month1 = s.nextInt();
        day1 = s.nextInt();
        DateUtil date1 = new DateUtil(year1,month1,day1);
        DateUtil date2;
        if (choice == next){
            n = s.nextInt();
            if(date1.checkInputValidity() && n >=0){
                System.out.println(date1.getNextNDays(n).showDate());
            }
            else{
                System.out.println("Wrong Format");
            }
        }
        else if(choice == Previous){
            n = s.nextInt();
            if(date1.checkInputValidity() && n >=0){
                System.out.println(date1.getPreviousNDays(n).showDate());
            }
            else{
                System.out.println("Wrong Format");
            }
        }
        else if(choice == daysOfDates){
            year2 = s.nextInt();
            month2 = s.nextInt();
            day2 = s.nextInt();
            date2 = new DateUtil(year2,month2,day2);
            if(date1.checkInputValidity() && date2.checkInputValidity()){
                System.out.println(date1.getDayOfDates(date2));
            }
            else{
                System.out.println("Wrong Format");
            }
        }
        else{
            System.out.println("Wrong Format");
        }

        //System.out.println(date.showDate());

    }

}

class Year{
    private int value;

    public Year(int value){
        this.value = value;
    }

    public Year(){

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isLeapYear() {
        return this.value % 4 == 0 && this.value % 100 != 0 || this.value % 400 == 0;
    }

    public boolean validate(){
        return this.value <= 2050 && this.value >= 1900;
    }

    public void yearIncrement(){
        this.value++;
    }

    public void yearReduction(){
        this.value--;
    }
}

class Month{
    private int value;
    private Year year;

    public Month(int yearValue,int monthValue){
        this.value = monthValue;
        this.year = new Year(yearValue);
    }

    public Month(){

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public void resetMin(){
        this.value = 1;
    }

    public void resetMax(){
        this.value = 12;
    }

    public boolean validate(){
        return this.value <= 12 && this.value >= 1;
    }

    public void monthIncrement(){
        this.value++;
    }

    public void monthReduction(){
        this.value--;
    }
}

class Day{
    private int value;
    private Month month;
    int[] monMaxNum = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};

    public Day(int yearValue,int monthValue,int dayValue){
        this.value = dayValue;
        this.month = new Month(yearValue,monthValue);
    }

    public Day(){

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public void isLeap(){
        monMaxNum[2] = 29;
    }

    public void isNotLeap(){
        monMaxNum[2] = 28;
    }

    public void resetMin(){
        this.value = 1;
    }

    public void resetMax(){
        this.value = monMaxNum[month.getValue()];
    }

    public boolean validate(){
        final int maxMonth = 12;
        if(month.getValue()> maxMonth ||month.getValue() <1){
            return false;
        }
        return this.value <= monMaxNum[month.getValue()] && this.value >= 1;
    }

    public void dayIncrement(){
        this.value++;
    }

    public void dayReduction(){
        this.value--;
    }
}

class DateUtil {
    private Day day;

    public DateUtil(int year, int month, int day) {
        this.day = new Day(year, month, day);
    }

    public DateUtil() {

    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public boolean checkInputValidity() {
        return day.validate() && day.getMonth().validate() && day.getMonth().getYear().validate();
    }

    /**
     * @param date 日期
     * @return 若传入date更大返回true
     */
    public boolean compareDates(DateUtil date) {
        if (this.day.getMonth().getYear().getValue() < date.getDay().getMonth().getYear().getValue()) {
            return true;
        } else if (this.day.getMonth().getYear().getValue() == date.getDay().getMonth().getYear().getValue()) {
            if (this.day.getMonth().getValue() < date.getDay().getMonth().getValue()) {
                return true;
            } else if (this.day.getMonth().getValue() == date.getDay().getMonth().getValue()) {
                return this.day.getValue() < date.getDay().getValue();
            }
        }
        return false;
    }

    public boolean equalTwoDates(DateUtil date) {
        if (this.day.getMonth().getYear().getValue() == date.getDay().getMonth().getYear().getValue()) {
            if (this.day.getMonth().getValue() == date.getDay().getMonth().getValue()) {
                return this.day.getValue() == date.getDay().getValue();
            }
        }
        return false;
    }

    public String showDate(){
        return day.getMonth().getYear().getValue() + "-" + day.getMonth().getValue() + "-" +day.getValue();
    }

    public DateUtil getNextNDays(int n){
        DateUtil newDate = new DateUtil(day.getMonth().getYear().getValue(),day.getMonth().getValue(),day.getValue());
        while (n > 0){
            if(newDate.getDay().getMonth().getYear().isLeapYear()){
                newDate.getDay().isLeap();
            }
            else{
                newDate.getDay().isNotLeap();
            }
            newDate.getDay().dayIncrement();
            if(!newDate.getDay().validate()){
                newDate.getDay().resetMin();
                newDate.getDay().getMonth().monthIncrement();
            }
            if(!newDate.getDay().getMonth().validate()){
                newDate.getDay().getMonth().resetMin();
                newDate.getDay().getMonth().getYear().yearIncrement();
            }
            if(newDate.getDay().validate() && newDate.getDay().getMonth().validate()){
                n--;
            }
        }
        return newDate;
    }

    public DateUtil getPreviousNDays(int n){
        DateUtil newDate = new DateUtil(day.getMonth().getYear().getValue(),day.getMonth().getValue(),day.getValue());
        while (n > 0){
            if(newDate.getDay().getMonth().getYear().isLeapYear()){
                newDate.getDay().isLeap();
            }
            else{
                newDate.getDay().isNotLeap();
            }
            newDate.getDay().dayReduction();
            if(!newDate.getDay().validate()){
                newDate.getDay().getMonth().monthReduction();
                if(!newDate.getDay().getMonth().validate()){
                    newDate.getDay().getMonth().getYear().yearReduction();
                    newDate.getDay().getMonth().resetMax();
                }
                newDate.getDay().resetMax();

            }
            if(!newDate.getDay().getMonth().validate()){
                newDate.getDay().getMonth().getYear().yearReduction();
                newDate.getDay().getMonth().resetMax();
            }
            if(newDate.getDay().validate() && newDate.getDay().getMonth().validate()){
                n--;
            }
        }
        return newDate;
    }

    public int getDayOfDates(DateUtil date){
        int n = 0;
        DateUtil newDate = new DateUtil(day.getMonth().getYear().getValue(),day.getMonth().getValue(),day.getValue());
        if(!newDate.compareDates(date)){
            DateUtil temp = date;
            date = newDate;
            newDate = temp;
        }
        while (!newDate.getNextNDays(n).equalTwoDates(date)){
            n++;
        }
        return n;
    }

}