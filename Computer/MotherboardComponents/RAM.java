package Computer.MotherboardComponents;

import Computer.MicroComponents.LatchMatrix;

/*
 */
public class RAM {

	  // Overall READ and WRITE enable toggles for the RAM module, related to the toggles of the LatchMatrix object defined
	  boolean READ_EN;
	  boolean WRITE_EN;

	  public static final int noOfBits = 8; // number of bits in each address


	  LatchMatrix[] latchMatrices;

	  public boolean[] address; // an adddress for the 8 bit no stored over 8 latch matrices
	  public boolean[] eight_bit_num; // the 8 bit no in the address

	  public RAM () {
		    latchMatrices = new LatchMatrix[noOfBits];
		    address = new boolean[4];
		    READ_EN = WRITE_EN = false;
		    eight_bit_num = new boolean[noOfBits];
		    for ( int i = 0 ; i < latchMatrices.length ; i++ ) {
			      latchMatrices[i] = new LatchMatrix ();

		    }
	  }

	  // functions

	  public void READ () {
		    if ( READ_EN ) {
			      latchMatrices[0].address[0] = address[0];
			      latchMatrices[0].address[1] = address[1];
			      latchMatrices[0].address[2] = address[2];
			      latchMatrices[0].address[3] = address[3];

			      latchMatrices[1].address[0] = address[0];
			      latchMatrices[1].address[1] = address[1];
			      latchMatrices[1].address[2] = address[2];
			      latchMatrices[1].address[3] = address[3];

			      latchMatrices[2].address[0] = address[0];
			      latchMatrices[2].address[1] = address[1];
			      latchMatrices[2].address[2] = address[2];
			      latchMatrices[2].address[3] = address[3];

			      latchMatrices[3].address[0] = address[0];
			      latchMatrices[3].address[1] = address[1];
			      latchMatrices[3].address[2] = address[2];
			      latchMatrices[3].address[3] = address[3];

			      latchMatrices[4].address[0] = address[0];
			      latchMatrices[4].address[1] = address[1];
			      latchMatrices[4].address[2] = address[2];
			      latchMatrices[4].address[3] = address[3];

			      latchMatrices[5].address[0] = address[0];
			      latchMatrices[5].address[1] = address[1];
			      latchMatrices[5].address[2] = address[2];
			      latchMatrices[5].address[3] = address[3];

			      latchMatrices[6].address[0] = address[0];
			      latchMatrices[6].address[1] = address[1];
			      latchMatrices[6].address[2] = address[2];
			      latchMatrices[6].address[3] = address[3];

			      latchMatrices[7].address[0] = address[0];
			      latchMatrices[7].address[1] = address[1];
			      latchMatrices[7].address[2] = address[2];
			      latchMatrices[7].address[3] = address[3];

			      latchMatrices[0].READ ();
			      eight_bit_num[0] = latchMatrices[0].bit;

			      latchMatrices[1].READ ();
			      eight_bit_num[1] = latchMatrices[1].bit;

			      latchMatrices[2].READ ();
			      eight_bit_num[2] = latchMatrices[2].bit;

			      latchMatrices[3].READ ();
			      eight_bit_num[3] = latchMatrices[3].bit;

			      latchMatrices[4].READ ();
			      eight_bit_num[4] = latchMatrices[4].bit;

			      latchMatrices[5].READ ();
			      eight_bit_num[5] = latchMatrices[5].bit;

			      latchMatrices[6].READ ();
			      eight_bit_num[6] = latchMatrices[6].bit;

			      latchMatrices[7].READ ();
			      eight_bit_num[7] = latchMatrices[7].bit;
		    }
	  }

	  public void WRITE () {
		    if ( WRITE_EN ) {
			      latchMatrices[0].address[0] = address[0];
			      latchMatrices[0].address[1] = address[1];
			      latchMatrices[0].address[2] = address[2];
			      latchMatrices[0].address[3] = address[3];

			      latchMatrices[1].address[0] = address[0];
			      latchMatrices[1].address[1] = address[1];
			      latchMatrices[1].address[2] = address[2];
			      latchMatrices[1].address[3] = address[3];

			      latchMatrices[2].address[0] = address[0];
			      latchMatrices[2].address[1] = address[1];
			      latchMatrices[2].address[2] = address[2];
			      latchMatrices[2].address[3] = address[3];

			      latchMatrices[3].address[0] = address[0];
			      latchMatrices[3].address[1] = address[1];
			      latchMatrices[3].address[2] = address[2];
			      latchMatrices[3].address[3] = address[3];

			      latchMatrices[4].address[0] = address[0];
			      latchMatrices[4].address[1] = address[1];
			      latchMatrices[4].address[2] = address[2];
			      latchMatrices[4].address[3] = address[3];

			      latchMatrices[5].address[0] = address[0];
			      latchMatrices[5].address[1] = address[1];
			      latchMatrices[5].address[2] = address[2];
			      latchMatrices[5].address[3] = address[3];

			      latchMatrices[6].address[0] = address[0];
			      latchMatrices[6].address[1] = address[1];
			      latchMatrices[6].address[2] = address[2];
			      latchMatrices[6].address[3] = address[3];

			      latchMatrices[7].address[0] = address[0];
			      latchMatrices[7].address[1] = address[1];
			      latchMatrices[7].address[2] = address[2];
			      latchMatrices[7].address[3] = address[3];

			      latchMatrices[0].bit = eight_bit_num[0];
			      latchMatrices[0].WRITE ();

			      latchMatrices[1].bit = eight_bit_num[1];
			      latchMatrices[1].WRITE ();

			      latchMatrices[2].bit = eight_bit_num[2];
			      latchMatrices[2].WRITE ();

			      latchMatrices[3].bit = eight_bit_num[3];
			      latchMatrices[3].WRITE ();

			      latchMatrices[4].bit = eight_bit_num[4];
			      latchMatrices[4].WRITE ();

			      latchMatrices[5].bit = eight_bit_num[5];
			      latchMatrices[5].WRITE ();

			      latchMatrices[6].bit = eight_bit_num[6];
			      latchMatrices[6].WRITE ();

			      latchMatrices[7].bit = eight_bit_num[7];
			      latchMatrices[7].WRITE ();

		    }
	  }

	  public void ENABLE_READ () {
		    READ_EN = true;

		    latchMatrices[0].ENABLE_READ ();
		    latchMatrices[1].ENABLE_READ ();
		    latchMatrices[2].ENABLE_READ ();
		    latchMatrices[3].ENABLE_READ ();
		    latchMatrices[4].ENABLE_READ ();
		    latchMatrices[5].ENABLE_READ ();
		    latchMatrices[6].ENABLE_READ ();
		    latchMatrices[7].ENABLE_READ ();
	  }

	  public void DISABLE_READ () {
		    READ_EN = false;

		    latchMatrices[0].DISABLE_READ ();
		    latchMatrices[1].DISABLE_READ ();
		    latchMatrices[2].DISABLE_READ ();
		    latchMatrices[3].DISABLE_READ ();
		    latchMatrices[4].DISABLE_READ ();
		    latchMatrices[5].DISABLE_READ ();
		    latchMatrices[6].DISABLE_READ ();
		    latchMatrices[7].DISABLE_READ ();
	  }

	  public void ENABLE_WRITE () {
		    WRITE_EN = true;

		    latchMatrices[0].ENABLE_WRITE ();
		    latchMatrices[1].ENABLE_WRITE ();
		    latchMatrices[2].ENABLE_WRITE ();
		    latchMatrices[3].ENABLE_WRITE ();
		    latchMatrices[4].ENABLE_WRITE ();
		    latchMatrices[5].ENABLE_WRITE ();
		    latchMatrices[6].ENABLE_WRITE ();
		    latchMatrices[7].ENABLE_WRITE ();
	  }

	  public void DISABLE_WRITE () {
		    WRITE_EN = false;

		    latchMatrices[0].DISABLE_WRITE ();
		    latchMatrices[1].DISABLE_WRITE ();
		    latchMatrices[2].DISABLE_WRITE ();
		    latchMatrices[3].DISABLE_WRITE ();
		    latchMatrices[4].DISABLE_WRITE ();
		    latchMatrices[5].DISABLE_WRITE ();
		    latchMatrices[6].DISABLE_WRITE ();
		    latchMatrices[7].DISABLE_WRITE ();
	  }

}
