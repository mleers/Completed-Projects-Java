import java.util.Arrays;

public interface Vehicle {
	public class Speed {
		private int sprinter_ts = 110; //top speed of sprinter van
		
		public class printSpeedSprinter {  //inner class for retrieving speed variable
			 public int getSpeedSprinter() { //speed retrieval method getSpeed
				System.out.print("The top speed of the Sprinter van is: ");
				return sprinter_ts;
			}  
		}  //end of sprinter top speed inner class
	}	 //end of speed class
	
	public class Color {  //colors of vehicles
		private String coupe_color = "periwinkle";  //custom color for coupes
				
	public class printAllColors {
		public String getAllColors() {
			String colors[] = new String[4]; //array of colors
			colors[0] = "red";
			colors[1] = "blue";
			colors[2] = "white";
			colors[3] = "green";
			System.out.print("Color of all vehicles are ");
			return Arrays.toString(colors);  //converting array objects to string to print
	}		
}
	
	public class printColorCoupe {
		public String getColorCoupe() {
			System.out.print("Color of coupe is ");
			return coupe_color;
		}	 
	}	
}	

public class Van implements Vehicle {
	protected int van_weight = 6000;  //bestows defined weight of all vans in class Van
	
	public class printVanWeight {
		public int getVanWeight() {
			System.out.print("Van weight is: ");
			return van_weight;
		}
	}
}

public class Sprinter extends Van { 
	 double sprinter_height = 94.5;  //instance variable
	 int sprinter_weight = van_weight;  //sees 6000 weight 
	 
	 public class printSprinterWeight {
			public int getSprinterWeight() {
				System.out.print("Sprinter weight is: ");
				return sprinter_weight;
		}
	} 
}

public class Car implements Vehicle {
	public int rowsFound() {
		int rows = 2;  //local variable
		System.out.println("Number of seat rows in a car is: "+ rows);
		return rows;
	}
}

public class Coupe extends Car {
	public int rowsFound() {  //method overriding of existing functionality in Car class
		int rows = 1;
		super.rowsFound();  //points to Car rowsFound
		System.out.println("Number of seat rows in a coupe is: "+ rows);
		return rows;
	}
}

	public static void main(String[] args) {
		
		Van v = new Van();  //vehicle types by instance variable
		Car c = new Car();
		Coupe cc = new Coupe();  //sub types by instance variable
		Sprinter s = new Sprinter();
		
		Speed speed = new Speed(); //instantiate "outer" Speed class
		Speed.printSpeedSprinter ps = speed.new printSpeedSprinter();  //instantiate "inner" printSpeedSprinter class
		
		Color color = new Color();
		Color.printColorCoupe pc = color.new printColorCoupe();
		
		Color.printAllColors pac = color.new printAllColors();
		
		Van.printVanWeight pvw = v.new printVanWeight();
		Sprinter.printSprinterWeight psw = s.new printSprinterWeight();
		
		System.out.println(cc instanceof Car);  //Coupe is an instance of Car
		System.out.println(c instanceof Coupe);  //Car is not an instance of Coupe
		System.out.println(v instanceof Vehicle);  //Van is an instance of Vehicle
		System.out.println(s instanceof Van);  //Sprinter is an instance of Van
		System.out.println(s instanceof Vehicle);  //Sprinter is an instance of Vehicle as well
		
		System.out.println(ps.getSpeedSprinter());  //use instantiated inner class to access method to print private variable
		System.out.println(pvw.getVanWeight());  
		System.out.println(psw.getSprinterWeight());  //Sprinter is same weight as Van class after inheritance
		System.out.println("Sprinter height is: "+ s.sprinter_height);  
		System.out.println(pac.getAllColors());
		System.out.println(pc.getColorCoupe());
		//c.rowsFound() not needed due to super keyword
		cc.rowsFound();  //invoking a superclass version of an overridden method in subclass Coupe
	}
}
