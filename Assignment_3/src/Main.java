import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Enter your marks: ");

        Scanner scanner=new Scanner(System.in);
        int marks=scanner.nextInt();
        String result;

        if(marks>=90 && marks<=100 )
        {
            result= "A" +":" + "Well done";
        }

        else if (marks>=80 && marks<90) {
            result= "B" +":" + "Well done";
        }
        else if (marks>=70 && marks<80) {
            result= "C" +":" + "Needs improvement";
        }
        else if (marks>=60 && marks<70) {
            result= "D" +":" + "Needs improvement";
        }
        else{
            result= "F" +":" + "Failed";
        }
        System.out.println(result);
    }
}