import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;


public class TestElectricityBiller {
	ElectricityPlans plans = new ElectricityBiller();
	ElectricityBiller biller = new ElectricityBiller();

	@Test
	public void testPrintWelcomeMessage() {
		assertEquals(message, plans.welcome_msg(message));
	}
	String message = "Testing welcome message...";

	@Test 
	public void testRateA() {
		System.out.println("Testing rate a...");
		assert(plans.compute_a(.16375, 25) == 4.09375);
	}

	@Test
	public void testRateB() {
		System.out.println("Testing rate b...");
		assert(plans.compute_b(.22125, 450) == 99.5625);
	}
	
	@Test
	public void testRateC() {
		System.out.println("Testing rate c...");
		assert(plans.compute_c(.31955, 850) == 271.6175);
	}
	
	@Test
	public void testSurcharge() {
		System.out.println("Testing surcharge...");
		assert(plans.compute_surcharge(.31955, 1075) == 393.51625);
	}
	
	@Test
	public void testRateE() {
		System.out.println("Testing rate e...");
		assert(plans.compute_e(.13835, 2385, 1.20) == 395.9577);
	}
	
	@Test
	public void testRateF() {
		System.out.println("Testing rate f...");
		assert(plans.compute_f(.08665, 3100, 1.50) == 402.9225);
	}
	
	@Test
	public void testRateG() {
		System.out.println("Testing rate g...");
		assert(plans.compute_g(.07995, 4900, 1.85) == 724.74675);
	}
	
	
	@Test
	public void testComputeTransaction() {
		System.out.println("Testing balance deduction...");
		assert(plans.compute_transaction(225.7525, 2000) == 1774.2475);
	}
	
	
	@Test
	public void testInitialValuesNotNull() {
		assertNotNull(plans.high_usage_charge);
		System.out.println("Testing surcharge loaded...");
		
		assertNotNull(ElectricityPlans.balance);  //accessing static variable
		System.out.println("Testing balance loaded...");
	}
	
	@Test
	public void testInitialValuesNull() {
		assertNull(plans.rd);
	}
	
	@Test
	public void testInitialRateTypeNames() {
		assertSame(plans.rd, null);
		assertSame(plans.sb, "small business");
		assertSame(plans.id, "industrial");
		assertSame(plans.gv, "governmental");
		
		assertNotSame(plans.rd, plans.sb);
		assertNotSame(plans.rd, plans.id);
		assertNotSame(plans.rd, plans.gv);
		assertNotSame(plans.sb, plans.id);
		assertNotSame(plans.sb, plans.gv);
		assertNotSame(plans.id, plans.gv);
		
	}
	
	
	@BeforeClass
	public static void testRateValues() {
		double high_usage_charge = 50.00;
		double balance = 2000.00;
		
		double rate_a = .16375;
		double rate_b = .22125;
		double rate_c = .31955;
		
		double rate_e = .21835;  
		double rate_f = .25265;  
		double rate_g = .35565; 
		
		double desig_one = 1.00;  
		double desig_two = 1.20;  
		double desig_three = 1.50;  
		double desig_four = 1.85;  
		
		
		assertTrue (high_usage_charge == 50.00);
		assertTrue (balance == 2000.00);
		assertTrue (high_usage_charge < balance);
		
		assertTrue (rate_a < rate_b);
		assertTrue (rate_a < rate_c);
		assertTrue (rate_b < rate_c);
		
		assertTrue (rate_e < rate_f);
		assertTrue (rate_e < rate_g);
		assertTrue (rate_f < rate_g);
		
		assertFalse (rate_e < rate_a);
		assertFalse (rate_f < rate_b);
		assertFalse (rate_g < rate_c);
		
		
		assertNotNull(desig_one);
		assertNotNull(desig_two);
		assertNotNull(desig_three);
		assertNotNull(desig_four);
		
		assertTrue(desig_one < desig_two);
		assertTrue(desig_one < desig_three);
		assertTrue(desig_one < desig_four);
		assertTrue(desig_two < desig_three);
		assertTrue(desig_two < desig_four);
		assertTrue(desig_three < desig_four);
		
			
		System.out.println("In testRateValues... testing inital values.");
		
	}
	
}
