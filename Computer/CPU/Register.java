package Computer.CPU;

import Computer.MicroComponents.GatedLatch;

public class Register {

	  // READ and WRITE enable wires
	  boolean READ_EN;
	  boolean WRITE_EN;

	  int SIZE_REGR = 8;

	  // Register latch array, 8BIT Register
	  GatedLatch[] REGISTER;

	  public Register (int SIZE_REGR) {

		    this.SIZE_REGR = SIZE_REGR;

		    REGISTER = new GatedLatch[SIZE_REGR];

		    for ( int i = 0 ; i < REGISTER.length ; i++ ) {
			      REGISTER[i] = new GatedLatch ();

		    }
	  }

	  // state = true / false, on or off. This state is decide by the CPU control unit
	  public void READ_EN ( boolean state ) {

		    READ_EN = state;

		    // READ ENABLING THE LATCHES: CODE FOR DEMO PURPOSE ONLY
		    for ( int i = 0 ; i < SIZE_REGR ; i++ ) {
			      REGISTER[i].READ_EN = READ_EN;
		    }
	  }

	  public void WRITE_EN ( boolean state ) {

		    WRITE_EN = state;

		    // WRITE ENABLING THE LATCHES: CODE FOR DEMO PURPOSE ONLY
		    for ( int i = 0 ; i < SIZE_REGR ; i++ ) {
			      REGISTER[i].WRITE_EN = WRITE_EN;
		    }
	  }

	  // Reading a register
	  public boolean[] Read () {

		    boolean[] data = new boolean[SIZE_REGR];

		    if ( READ_EN ) {
			      for ( int i = 0 ; i < data.length ; i++ ) {
					data[i] = REGISTER[i].bit;
			      }
		    }

		    return data;
	  }

	  public void Write ( boolean[] data ) {

		    if ( WRITE_EN ) {
			      for ( int i = 0 ; i < data.length ; i++ ) {
					REGISTER[i].bit = data[i];
			      }
		    }
	  }

}
