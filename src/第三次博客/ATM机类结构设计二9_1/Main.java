package 第三次博客.ATM机类结构设计二9_1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ginger
 * @date 2021/11/5
 */

public class Main {
    static final int CARD = 1, PASSWORD = 2, ATM = 3, MONEY = 4;
    public static void main(String[] args) {
        HashMap<String,Card> card= new HashMap<>(15);
        HashMap<String,Atm> atm = new HashMap<>(10);
        init(card,atm);
        String line;
        Scanner scanner = new Scanner(System.in);
        do{
            line = scanner.nextLine();
            line = line.replaceAll(" +"," ");
            String rule = "(\\d{19}) (\\d{8}) (\\d{2}) (((-?[1-9]\\d*)|(0))(\\.\\d{0,2})?)";
            Pattern pattern = Pattern.compile(rule);
            Matcher matcher = pattern.matcher(line);
            Card c;
            Atm a;
            if(matcher.matches()){
                if(card.get(matcher.group(CARD)) == null){
                    System.out.println("Sorry,this card does not exist.");
                    continue;
                }

                c = card.get(matcher.group(CARD));

                if(!c.checkPassword(matcher.group(PASSWORD))){
                    System.out.println("Sorry,your password is wrong.");
                    continue;
                }

                if(atm.get(matcher.group(ATM)) == null){
                    System.out.println("Sorry,the ATM's id is wrong.");
                    continue;
                }

                a = atm.get(matcher.group(ATM));
                double money = Double.parseDouble(matcher.group(MONEY));
                if(money < 0){
                    c.getBankAccount().deposit(money * -1);
                    System.out.printf("业务：存款 " + c.getBankAccount().getUserName() + "在" + a.getBankName() + "的" + a.getNumber() + "号ATM机上存款¥%.2f\n",money * -1);
                    System.out.printf("当前余额为¥%.2f\n",c.getBankAccount().getBalance());
                }
                else{
                    if(c.getBankAccount().withdrawal(money,a)){
                        System.out.printf("业务：取款 " + c.getBankAccount().getUserName() + "在" + a.getBankName() + "的" + a.getNumber() + "号ATM机上取款¥%.2f\n",money);
                        System.out.printf("当前余额为¥%.2f\n",c.getBankAccount().getBalance());
                    }
                    else {
                        System.out.println("Sorry,your account balance is insufficient.");
                    }
                }
            }
            else{
                rule = "(\\d{19})";
                pattern = Pattern.compile(rule);
                matcher = pattern.matcher(line);
                if(matcher.matches()){
                    System.out.printf("业务：查询余额 ¥%.2f\n",card.get(matcher.group(1)).getBankAccount().getBalance());
                }
                else {
                    System.exit(0);
                }
            }
        }while (!line.matches("#"));

    }

    public static void init(HashMap<String,Card> hashMap,HashMap<String,Atm> atm){
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

        bankAccount = new BankAccount("3640000010045442002","中国建设银行","张三丰",true,50000,0.05);
        hashMap.put("6640000010045442002",new Card("6640000010045442002",bankAccount));
        hashMap.put("6640000010045442003",new Card("6640000010045442003",bankAccount));
        bankAccount = new BankAccount("3640000010045441009","中国工商银行","令狐冲",true,50000,0.05);
        hashMap.put("6640000010045441009",new Card("6640000010045441009",bankAccount));
        bankAccount = new BankAccount("3630000010033431001","中国农业银行","乔峰",true,50000,0.05);
        hashMap.put("6630000010033431001",new Card("6630000010033431001",bankAccount));
        bankAccount = new BankAccount("3630000010033431008","中国农业银行","洪七公",true,50000,0.05);
        hashMap.put("6630000010033431008",new Card("6630000010033431008",bankAccount));

        atm.put("01",new Atm("01","中国建设银行",0.02,"中国银联"));
        atm.put("02",new Atm("02","中国建设银行",0.02,"中国银联"));
        atm.put("03",new Atm("03","中国建设银行",0.02,"中国银联"));
        atm.put("04",new Atm("04","中国建设银行",0.02,"中国银联"));
        atm.put("05",new Atm("05","中国工商银行",0.03,"中国银联"));
        atm.put("06",new Atm("06","中国工商银行",0.03,"中国银联"));
        atm.put("07",new Atm("07","中国农业银行",0.04,"中国银联"));
        atm.put("08",new Atm("08","中国农业银行",0.04,"中国银联"));
        atm.put("09",new Atm("09","中国农业银行",0.04,"中国银联"));
        atm.put("10",new Atm("10","中国农业银行",0.04,"中国银联"));
        atm.put("11",new Atm("11","中国农业银行",0.04,"中国银联"));

    }
}

class Card{
    private String cardId;
    private String password = "88888888";
    private BankAccount bankAccount;

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public Card(){

    }

    public Card(String cardId, BankAccount bankAccount){
        this.cardId = cardId;
        this.bankAccount = bankAccount;
    }

    public String getCardId() {
        return cardId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}

class BankAccount{
    private String accountId;
    private String bankName;
    private String userName;
    private boolean credit = false;
    private double cashWithdrawalMax;
    private double cashWithdrawalPoundage;
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
     * @param money 取款金额 atm 所使用的atm机
     * @return 成功true 失败false
     */
    public boolean withdrawal(double money,Atm atm){
        double poundage = 0;
        if(!atm.bankCheck(bankName)) {
            poundage += atm.interBankPoundage(money);
        }
        if(credit && this.balance <= money){
            if(this.balance >= 0){
                poundage += cashWithdrawalPoundage(-1 * (this.balance - money));
            }
            else{
                poundage += cashWithdrawalPoundage(money);
            }
        }

        if(underCashWithdrawalMax(balance - money - poundage)){
            this.balance -= money + poundage;
            return true;
        }
        return false;
    }

    public boolean underCashWithdrawalMax(double money){
        return money >= this.cashWithdrawalMax;
    }

    public double cashWithdrawalPoundage(double money){
        return money * this.cashWithdrawalPoundage;
    }

    public BankAccount(){

    }

    public BankAccount(String accountId,String bankName,String userName){
        this.accountId = accountId;
        this.bankName = bankName;
        this.userName = userName;
    }

    public BankAccount(String accountId,String bankName,String userName,boolean credit,double cashWithdrawalMax,double cashWithdrawalPoundage){
        this.accountId = accountId;
        this.bankName = bankName;
        this.userName = userName;
        this.credit = credit;
        if(this.credit) {
            this.cashWithdrawalMax = -1 * cashWithdrawalMax;
            this.cashWithdrawalPoundage = cashWithdrawalPoundage;
        }
        else {
            this.cashWithdrawalMax = 0;
            this.cashWithdrawalPoundage = 0;
        }
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
    private double interBankPoundage;
    private String institutionName;

    public Atm(){

    }

    public Atm(String number,String bankName,double interBankPoundage,String institutionName){
        this.number = number;
        this.bankName = bankName;
        this.interBankPoundage = interBankPoundage;

        this.institutionName = institutionName;
    }

    public boolean bankCheck(String bankName){
        return this.bankName.equals(bankName);
    }

    public double interBankPoundage(double money){
        return money * this.interBankPoundage;
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