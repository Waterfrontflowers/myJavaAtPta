package 第二次博客.统计Java程序中关键词的出现次数7_4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ginger
 * @date 2021/11/11
 */


public class Main {

    /**
     * abstract、assert、boolean、break、byte、case、catch、
     * char、class、const、continue、default、do、double、else、
     * enum、extends、false、final、finally、float、
     * for、goto、if、implements、import、instanceof、
     * int、interface、long、native、new、null、package、
     * private、protected、public、return、short、static、
     * strictfp、super、switch、synchronized、this、throw、
     * throws、transient、true、try、void、volatile、while
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner input=new Scanner(System.in);

        String a;
        StringBuilder ss=new StringBuilder();
        Map<String, Integer> map= new HashMap<>();
        String []key= { "abstract","assert","boolean","break","byte","case","catch",
                "char","class","const","continue","default","do","double","else",
                "enum","extends","false","final","finally","float",
                "for","goto","if","implements","import","instanceof",
                "int","interface","long","native","new","null","package",
                "private","protected","public","return","short","static",
                "strictfp","super","switch","synchronized","this","throw",
                "throws","transient","true","try","void","volatile","while"

        };
        int i = 0;
        while (true){
            a=input.nextLine();
            if("exit".equals(a)) {
                i--;
                break;
            }
            if(a.matches("(.*)//(.*)")) {
                i++;
                String[] b =a.split("//");
                ss.append(b[0]).append(" ");
            }
            else {
                i--;
                ss.append(a).append(" ");
            }
        }

        int count;
        String s=ss.toString();

        String rule = "\"(.*?)\"";
        Pattern p=Pattern.compile(rule);
        Matcher m=p.matcher(s);
        while(m.find()){
            s=s.replace(m.group()," ");
            p=Pattern.compile(rule);
            m=p.matcher(s);
            i++;
        }
        String rule2 = "/\\**(.*?)/";
        p=Pattern.compile(rule2);
        m=p.matcher(s);
        while(m.find()){
            s=s.replace(m.group()," ");
            m=p.matcher(s);
            i++;
        }
        if(s.isEmpty()) {
            System.out.println("Wrong Format");
            System.exit(0);
        }
        s=s.replace("["," ");
        s=s.replace("]"," ");
        s=s.replace("-","a");
        s=s.replace("*","a");
        s=s.replace("/","a");
        s=s.replace("+","a");
        s=s.replace(">","a");
        s=s.replace("=","a");
        s=s.replace("!","a");
        s=s.replace(":","a");
        s=s.replace("\\","a");
        s= s.replaceAll("[^a-zA-Z]", " ");

        String []s1=s.split("[ ']");
        for (String value : s1) {
            for (int j = 0; j < key.length; j++) {
                if (value.equals(key[j])) {
                    map.put(key[j], 0);
                }
            }
        }
        for (String value : s1) {
            for (int j = 0; j < key.length; j++) {
                if (value.equals(key[j])) {
                    count = map.get(key[j]);
                    map.put(key[j], count + 1);
                }
            }
        }
        Set<String> set=map.keySet();
        Object[] arr=set.toArray();
        Arrays.sort(arr);
        for(Object k:arr){
            System.out.println(map.get(k)+"\t"+k);
        }
    }
}
