import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        int  principal_amount=scanner.nextInt();
        int interest_rate=scanner.nextInt();

        String time=scanner.nextLine();


        if(time.endsWith("y"))
        {
            double timeInYear=Double.parseDouble(time.replace("y",""));
        }
        else if (time.endsWith("m"))
        {
            float timeInMonth=Float.parseFloat(time.replace("m",""));
            timeInYear= timeInMonth/12;
        }
        else
        {
            System.out.println("Invalid Input");
        }

        float Interest = (principal_amount * interest_rate * timeInYear) / 100;
    }
    }
