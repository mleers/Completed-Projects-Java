import java.text.DecimalFormat;
import java.util.*;

class ElectricityPlans {
	DecimalFormat numberFormat = new DecimalFormat("#.00");  //limits double decimal places
	static double bill;
	double high_usage_charge = 50.00;
	static double balance = 2000.00;
	double remaining_balance = balance - bill;
	
	static double rate_a = .16375;  //residential rates (low user)
	static double rate_b = .22125;  
	static double rate_c = .31955;  
	
	static double rate_e = .13835;  //small business
	static double rate_f = .08665;  //industrial
	static double rate_g = .07995;  //governmental
	
	static double desig_one = 1.00;  // multipliers
	static double desig_two = 1.20;  //sb
	static double desig_three = 1.50;  //id
	static double desig_four = 1.85;  //gv
	
	String rd = null;
	String sb = "small business";
	String id = "industrial";
	String gv = "governmental";

	
	public String welcome_msg(String message) {
		System.out.println(message);
		return message;
	}
	
	public double compute_a(double rate_a, double hours) {
		bill = (rate_a)*(hours);
		System.out.println("Bill is: "+ numberFormat.format(bill));
		return bill;
	}
	
	public double compute_b(double rate_b, double hours) {
		bill = (rate_b)*(hours);
		System.out.println("Bill is: "+ numberFormat.format(bill));
		return bill;
	}
	
	public double compute_c(double rate_c, double hours) {
		bill = (rate_c)*(hours);
		System.out.println("Bill is: "+ numberFormat.format(bill));
		return bill;
	}
	
	public double compute_surcharge(double rate_c, double hours) {
		bill = ((rate_c)*(hours) + high_usage_charge);
		System.out.println("Bill with surcharge is: "+ numberFormat.format(bill));
		return bill;
	}
	
	public double compute_e(double rate_e, double hours, double desig_two) {
		bill = ((rate_e)*(hours)*(desig_two));
		System.out.printf("Your usage qualifies you for: %s rates.", sb);
		System.out.println();
		System.out.println("Bill is: "+ numberFormat.format(bill));
		return bill;
	}
	
	public double compute_f(double rate_f, double hours, double desig_three) {
		bill = ((rate_f)*(hours)*(desig_three));
		System.out.printf("Your usage qualifies you for: %s rates.", id);
		System.out.println();
		System.out.println("Bill is: "+ numberFormat.format(bill));
		return bill;
	}
	
	public double compute_g(double rate_g, double hours, double desig_four) {
		bill = ((rate_g)*(hours)*(desig_four));
		System.out.printf("Your usage qualifies you for: %s rates.", gv);
		System.out.println();
		System.out.println("Bill is: "+ numberFormat.format(bill));
		return bill;
	}
	
	
	public double compute_transaction(double bill, double balance) {
		remaining_balance = (balance) - (bill);  //bill includes any designations already
		
		if (balance >= bill) {
			System.out.println("Remaining balance is: "+ numberFormat.format(remaining_balance));
			return remaining_balance;
		}else if (balance < bill) {
			System.out.println("Not enough funds.");
		}
		return remaining_balance;
	}
}

public class ElectricityBiller extends ElectricityPlans{

	public static void main(String[] args) {
		ElectricityPlans plan = new ElectricityPlans();
		Random rand = new Random();
		
		plan.welcome_msg("Welcome to bill payer");
		
		//kWh input and logic
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter kWh for last term: ");
		while (!in.hasNextFloat()) {
			System.out.println("Only numbers allowed; re-enter; ");
			in.next();
		}
		float hours = in.nextFloat();
		while ((hours) < 0) {
			System.out.println("Only positive numbers allowed; re-enter: ");
			hours = in.nextFloat();
		}
		
		
		if (hours <= 150.00) {
			plan.compute_a(rate_a, hours);
		}else if  (150.00 < hours && hours <= 600.00) {
			plan.compute_b(rate_b, hours);
		}else if (600.00 < hours && hours <= 1000.00) {
			plan.compute_c(rate_c, hours);
		}else if (hours > 1000.00  && hours <= 1200.00) {
			plan.compute_surcharge(rate_c, hours);			//surcharged residential rate
		}else if (hours > 1200.00 && hours <= 2500.00) {
			plan.compute_e(rate_e, hours, desig_two);
		}else if (hours > 2500.00 && hours <= 4500.00) {
			plan.compute_f(rate_f, hours, desig_three);
		}else if (hours > 4500.00) {
			plan.compute_g(rate_g, hours, desig_four);
		}
		
		//bill pay input and logic
		
		Scanner pay = new Scanner(System.in);  //generates days left to pay bill
		int n = rand.nextInt(29) + 1;
		int selection;
		System.out.println("Would you like to pay your bill? Enter 1 for yes or 2 for no: ");

		selection = pay.nextInt();

		while (selection != 1 && selection != 2) {
			System.out.println("Only 1 or 2 allowed.");
			selection = pay.nextInt();
		}
		
		
		switch (selection) {
		case 1:
			plan.compute_transaction(bill, balance);
			if (balance < bill) {
				System.out.printf("Bill not payed.  You have %2d days to pay.", n);
			}
			else if( balance >= bill) {
				System.out.println("Bill payed");
			}
			break;
		case 2:
			System.out.printf("Bill not payed.  You have %2d days to pay.", n);
			break;
		}
		
	
		in.close();
		pay.close();
		
	}
}

