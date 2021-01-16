package Computer.MicroComponents;

/**
 * A Gated latch can be thought of as a combination of logic gates having two inputs such that one of the inputs gets stored as the output when the other input has a particular value. On changing the value of the second input after this, causes no change to the output, or the combination can be said to have some memory! Its called a Latch because it latches onto a particular value
 */
public class GatedLatch {

	  public boolean bit; // stores the bit to be stored in the Latch
	  public boolean READ_EN; // Read enable toggle
	  public boolean WRITE_EN; // write enable toggle


	  //  THIS NON PARAMETERISED CONDITION DEFINES THE ABSOLUTE INITIAL CONDITION AFTER BOOT INSTANT
	  public GatedLatch () {
		    bit = false;
		    READ_EN = WRITE_EN = false;
	  }

	  // YOU CAN INITIALIZE THE GATED LATCH FROM A GIVEN CONDITION, THES COOL!
	  public GatedLatch ( boolean bit, boolean READ_EN, boolean WRITE_EN ) {
		    this.bit = bit;
		    this.READ_EN = READ_EN;
		    this.WRITE_EN = WRITE_EN;
	  }

}
