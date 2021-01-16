package Computer;



import Computer.MotherboardComponents.RAM;

/**
 * PURPOSE: Build multiple RAM and GPU and blah blah objects
 * and store all that as static variables for easy access;
 */
public class Motherboard {

    // OUR motherboard supports 2 oopsRAM.
	  // FOR THE USER: We've only provided one
	  // WHY? Coz we want more fkn money! (THIS is how apple does its business today)

	  public static RAM oopsRAM1;
	  public static RAM oppsRAM2;

	  public static void init () {
		    
		    oopsRAM1 = new RAM ();
	  }

}
