import java.util.Scanner;
import java.util.Random;
import java.lang.Exception;

class MyException extends Exception
{
    MyException(String message)
    {
        super(message);
    }
}

interface Account
{
    void CreateAcc();
    void Deposit();
    void Withdraw();
}

class BankProperties
{
    int acctype,year=1,ssn=0,accnum;
    float intr;
    String name="";
    String loc="";
    Scanner in = new Scanner(System.in);
    Random rnd = new Random();
    double temp=0.0,bal=0.0;

}

class Bank extends BankProperties implements Account
{


    void getInfo()
    {
        try
        {

            System.out.print("Enter Name: ");
            name=in.nextLine();


            System.out.print("Enter SSN: ");
            ssn=in.nextInt();

            in.nextLine();
            System.out.print("Enter Location: ");
            loc=in.nextLine();

            System.out.println("Mention account type:\n1.Savings (5% intr )\t2.Current (6% intr) ");
            acctype=in.nextInt();
            switch(acctype)
            {
                case 1:
                    System.out.println("Enter the initial amount of deposit:");
                    temp=in.nextFloat();
                    if(temp<0)
                    {
                        System.out.println("Invalid Amount\nTry again:\n");
                        System.out.println("Enter the initial amount of deposit:");
                        temp=in.nextFloat();
                    }
                    Deposit(temp);
                    System.out.println("Enter no of years:");
                    year=in.nextInt();
                    if(year<=0)
                    {
                        System.out.println("Invalid year\n Try again.");
                        System.out.println("Enter no of years:");
                        year=in.nextInt();
                    }

                    break;

                case 2:
                    System.out.println("Enter the initial amount of deposit:");
                    temp=in.nextFloat();
                    if(temp<0)
                    {
                        System.out.println("Invalid Amount\nTry again:\n");
                        System.out.println("Enter the initial amount of deposit:");
                        temp=in.nextFloat();
                    }
                    Deposit(temp);
                    break;

                default: System.out.println("Invalid Option");
            } //Switch


        }
        catch(Exception e)
        {
            System.out.println("Inbuilt Exception --> "+e);
            System.exit(0);
        }

    }//getInfo

    public void CreateAcc()
    {
        try
        {
            getInfo();
            System.out.println("\nAccount Successfully Created!");
            accnum=rnd.nextInt(1000)+1;
            System.out.println("Hello "+name+" your account no is " +accnum+".\n");
        }
        catch(Exception e)
        {
            System.out.println("Fatal Error");
        }
    }

    void Deposit(double temp) // Initial Deposit
    {
        try
        {
            if(temp>500)
            {
                bal+=temp;
                System.out.println("SUCCESSFULLY CREDITED");
            }
            else
            {
                throw new MyException("Minimum Deposit Violation");
            }
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
            System.out.println("TRANSACTION FAILURE");
            System.exit(0);
        }

    }

    public void Deposit() // regular deposit
    {

        System.out.println("Enter the amount to be deposited :");
        temp=in.nextFloat();
        try
        {
            if(temp>0)
            {
                bal+=temp;
                System.out.println("SUCCESSFULLY CREDITED");
            }
            else
            {
                throw new MyException("INVALID AMOUNT EXCEPTION");
            }
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
            System.out.println("TRANSACTION FAILURE");
        }

    }

    public void Withdraw()// regular deposit
    {

        System.out.println("Enter the amount to be withdrawn :");
        temp=in.nextFloat();
        if(temp<=0)
        {
            System.out.println("Invalid Amount Error.");
            System.exit(0);
        }

        try
        {

            if(temp>2500)
            {
                bal-=temp;
                System.out.println("SUCCESSFULLY DEBITED");
            }
            else
            {
                throw new MyException("INSUFFICIENT FUND EXCEPTION");
            }
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
            System.out.println("TRANSACTION FAILURE");
        }

    }
    void Interest()
    {
        try
        {
            if("".equals(name)&&(ssn==0))
            {
                throw new MyException("ACCOUNT NOT FOUND EXCEPTION");
            }
            else
            {
                if(acctype==1)
                {
                    bal+=year*bal*0.5;
                    System.out.println("5% interest added");
                }
                else if(acctype==2)
                {
                    bal+=year*bal*0.03;
                    System.out.println("3% interest added");
                }
            }
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
            System.out.println("TRANSACTION FAILURE");
        }
    }

    void CheckBal()
    {
        System.out.println("Balance:"+bal);
    }

}


class BankAccountExc
{
    public static void main(String[]args) throws MyException
    {
        Scanner in = new Scanner(System.in);
        Bank b = new Bank();

        System.out.println("\n\n\t----Bank Transaction Menu----");
        try
        {
            while(true)
            {
                System.out.println("1.Create Account\t2.Check Balance \n3.Deposit\t\t4.Withdraw\n5.Interest\t\t6.Exit.");
                System.out.println("Enter your choice below:::::::::");
                int ch = in.nextInt();
                switch(ch)
                {
                    case 1:
                        b.CreateAcc();
                        break;

                    case 2:
                        b.CheckBal();
                        break;

                    case 3:
                        b.Deposit();
                        break;

                    case 4:
                        b.Withdraw();
                        break;

                    case 5:
                        b.Interest();
                        break;

                    case 6:
                        System.exit(0);
                        break;

                    default: System.out.println("Invalid Option");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("SELF THROWN EXCEPTION IS--->"+e);
        }
    }
}