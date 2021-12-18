package 第三次博客.ATM机类结构设计一8_1;

import java.awt.image.CropImageFilter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ginger
 * @date 2021/11/5
 */

public class Main {

    public static void main(String[] args) {
        HashMap<String,Card> card= new HashMap<>(15);
        HashMap<String,Atm> atm = new HashMap<>(10);
        init(card,atm);
        String line;
        Scanner scanner = new Scanner(System.in);
        line = scanner.nextLine();
        while (!line.matches("#")){
            line = line.replaceAll(" +"," ");
            String rule = "(\\d{19}) (\\d{8}) (\\d{2}) (((-?[1-9]{1}\\d*)|(0{1}))(\\.\\d{0,2})?)";
            Pattern pattern = Pattern.compile(rule);
            Matcher matcher = pattern.matcher(line);
            Card c;
            Atm a;
            if(matcher.matches()){
                if(card.get(matcher.group(1)) != null){
                    c = card.get(matcher.group(1));
                    if(c.getPassword().equals(matcher.group(2))){
                        if(atm.get(matcher.group(3)) != null){
                            a = atm.get(matcher.group(3));
                            if(a.getBankName().equals(c.getBankAccount().getBankName())){
                                double money = Double.parseDouble(matcher.group(4));
                                if(money < 0){
                                    c.getBankAccount().deposit(money * -1);
                                    System.out.printf(c.getBankAccount().getUserName() + "在" + a.getBankName() + "的" + a.getNumber() + "号ATM机上存款¥%.2f\n",money * -1);
                                    System.out.printf("当前余额为¥%.2f\n",c.getBankAccount().getBalance());
                                }
                                else{
                                    if(c.getBankAccount().withdrawal(money)){
                                        System.out.printf(c.getBankAccount().getUserName() + "在" + a.getBankName() + "的" + a.getNumber() + "号ATM机上取款¥%.2f\n",money);
                                        System.out.printf("当前余额为¥%.2f\n",c.getBankAccount().getBalance());
                                    }
                                    else {
                                        System.out.println("Sorry,your account balance is insufficient.");
                                    }
                                }
                            }
                            else {
                                System.out.println("Sorry,cross-bank withdrawal is not supported.");
                            }
                        }
                        else {
                            System.out.println("Sorry,the ATM's id is wrong.");
                        }
                    }
                    else {
                        System.out.println("Sorry,your password is wrong.");
                    }
                }
                else{
                    System.out.println("Sorry,this card does not exist.");
                }

            }
            else{
                rule = "(\\d{19})";
                pattern = Pattern.compile(rule);
                matcher = pattern.matcher(line);
                if(matcher.matches()){
                    System.out.printf("¥%.2f\n",card.get(matcher.group(1)).getBankAccount().getBalance());
                }
                else {
                    System.exit(0);
                }
            }
            line = scanner.nextLine();
        }

    }

    public static void init(HashMap hashMap,HashMap atm){
        BankAccount bankAccount = new BankAccount("3217000010041315709","中国建设银行","杨过");
        hashMap.put("6217000010041315709",new Card("6217000010041315709",bankAccount));
        hashMap.put("6217000010041315715",new Card("6217000010041315715",bankAccount));
        bankAccount = new BankAccount("3217000010041315715","中国建设银行","杨过");
        hashMap.put("6217000010041315718",new Card("6217000010041315718",bankAccount));
        bankAccount = new BankAccount("3217000010051320007","中国建设银行","郭靖");
        hashMap.put("6217000010051320007",new Card("6217000010051320007",bankAccount));
        bankAccount = new BankAccount("3222081502001312389","中国工商银行","张无忌");
        hashMap.put("6222081502001312389",new Card("6222081502001312389",bankAccount));
        bankAccount = new BankAccount("3222081502001312390","中国工商银行","张无忌");
        hashMap.put("6222081502001312390",new Card("6222081502001312390",bankAccount));
        bankAccount = new BankAccount("3222081502001312399","中国工商银行","张无忌");
        hashMap.put("6222081502001312399",new Card("6222081502001312399",bankAccount));
        hashMap.put("6222081502001312400",new Card("6222081502001312400",bankAccount));
        bankAccount = new BankAccount("3222081502051320785","中国工商银行","韦小宝");
        hashMap.put("6222081502051320785",new Card("6222081502051320785",bankAccount));
        bankAccount = new BankAccount("3222081502051320786","中国工商银行","韦小宝");
        hashMap.put("6222081502051320786",new Card("6222081502051320786",bankAccount));

        atm.put("01",new Atm("01","中国建设银行","中国银联"));
        atm.put("02",new Atm("02","中国建设银行","中国银联"));
        atm.put("03",new Atm("03","中国建设银行","中国银联"));
        atm.put("04",new Atm("04","中国建设银行","中国银联"));
        atm.put("05",new Atm("05","中国工商银行","中国银联"));
        atm.put("06",new Atm("06","中国工商银行","中国银联"));

    }
}

class Card{
    private String cardId;
    private String password = "88888888";
    private BankAccount bankAccount;

    public Card(){

    }

    public Card(String cardId, BankAccount bankAccount){
        this.cardId = cardId;
        this.bankAccount = bankAccount;
    }

    public String getCardId() {
        return cardId;
    }

    public String getPassword() {
        return password;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}

class BankAccount{
    private String accountId;
    private String bankName;
    private String userName;
    private double balance = 10000;

    /**
     *
     * @param money 存款金额
     */
    public void deposit(double money){
        this.balance += money;
    }

    /**
     *
     * @param money 取款金额
     * @return 成功true 失败false
     */
    public boolean withdrawal(double money){
        if(money <= this.balance){
            this.balance -= money;
            return true;
        }
        return false;
    }

    public BankAccount(){

    }

    public BankAccount(String accountId,String bankName,String userName){
        this.accountId = accountId;
        this.bankName = bankName;
        this.userName = userName;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getBankName() {
        return bankName;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }


}

class Atm{
    private String number;
    private String bankName;
    private String institutionName;

    public Atm(){

    }

    public Atm(String number,String bankName,String institutionName){
        this.number = number;
        this.bankName = bankName;
        this.institutionName = institutionName;
    }

    public String getNumber() {
        return number;
    }

    public String getBankName() {
        return bankName;
    }

    public String getInstitutionName() {
        return institutionName;
    }
}