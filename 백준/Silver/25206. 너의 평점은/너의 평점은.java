import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //      BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        int n=20;
//        String[] input ;
        HashMap<String, Double> gradeTb = new HashMap<>();
        gradeTb.put("A+",4.5);
        gradeTb.put("A0",4.0);
        gradeTb.put("B+",3.5);
        gradeTb.put("B0",3.0);
        gradeTb.put("C+",2.5);
        gradeTb.put("C0",2.0);
        gradeTb.put("D+",1.5);
        gradeTb.put("D0",1.0);
        gradeTb.put("F",0.0);
        gradeTb.put("P",-1.0);

        Double total = 0.0;
        Double gradeSum = 0.0;

        while(n-- > 0){
            String inp = br.readLine();
            String[] data = inp.split(" ");
//            System.out.println(Arrays.toString(data));

            Double score = gradeTb.get(data[2]);
            if(score.equals(gradeTb.get("P"))) continue;

            Double grade = Double.parseDouble(data[1]);

            total += (score*grade);
            gradeSum+=grade;
        }

        System.out.printf("%.6f%n",total/gradeSum);


    }
}
