package Computer.MicroComponents;

/**
 * A Latch Matrix is an array of Gated Latches which can be used to store multiple latches at a defined address when many of then are bundled together. Using a Latch Matrix greatly reduces the number of wires used in real life and the if-else statements in this code when compared to Registers!
 */
public class LatchMatrix {

	  public boolean[] address; // storing the address of a bit in the matrix (first 2 rows and next 2 columns)
	  public boolean bit; // The bit to store or write a value to any latches

	  //we are sorry but you cant change the size of the RAM which it is running, if that would be possible wouldnt it be cool?!
	  public static final int ROWS = 4;
	  public static final int COLUMNS = 4;

	  GatedLatch[][] latches; // each element in the latch matrix is a gated latch

	  // Overall READ and WRITE enable toggles for the LatchMatrix
	  boolean READ_EN;
	  boolean WRITE_EN;

	  public LatchMatrix () {
		    address = new boolean[4];
		    latches = new GatedLatch[ROWS][COLUMNS];
		    bit = false;

		    READ_EN = false;
		    WRITE_EN = false;

		    for ( int i = 0 ; i < latches.length ; i++ ) {
			      for ( int j = 0 ; j < latches[0].length ; j++ ) {
					latches[i][j] = new GatedLatch ();

			      }

		    }
	  }

	  // Functions that make the LatchMatrix alive and not a dead object! NOTE: We have tried to keep the useless variables like local variables of a function to a minimum cause theres nothing like that in real life memory, we dont wanna use memory to build a memory, WTH!

	  // Reads a bit from the given address in binary and stores it to the bit variable in this class for access
	  public void READ () {
		    if ( READ_EN ) {
			      if ( !address[0] && !address[1] && !address[2] && !address[3] ) {
					bit = latches[0][0].bit;
			      }
			      else if ( !address[0] && !address[1] && !address[2] && address[3] ) {
					bit = latches[0][1].bit;
			      }
			      else if ( !address[0] && !address[1] && address[2] && !address[3] ) {
					bit = latches[0][2].bit;
			      }
			      else if ( !address[0] && !address[1] && address[2] && address[3] ) {
					bit = latches[0][3].bit;
			      }
			      else if ( !address[0] && address[1] && !address[2] && !address[3] ) {
					bit = latches[1][0].bit;
			      }
			      else if ( !address[0] && address[1] && !address[2] && address[3] ) {
					bit = latches[1][1].bit;
			      }
			      else if ( !address[0] && address[1] && address[2] && !address[3] ) {
					bit = latches[1][2].bit;
			      }
			      else if ( !address[0] && address[1] && address[2] && address[3] ) {
					bit = latches[1][3].bit;
			      }
			      else if ( address[0] && !address[1] && !address[2] && !address[3] ) {
					bit = latches[2][0].bit;
			      }
			      else if ( address[0] && !address[1] && !address[2] && address[3] ) {
					bit = latches[2][1].bit;
			      }
			      else if ( address[0] && !address[1] && address[2] && !address[3] ) {
					bit = latches[2][2].bit;
			      }
			      else if ( address[0] && !address[1] && address[2] && address[3] ) {
					bit = latches[2][3].bit;
			      }
			      else if ( address[0] && address[1] && !address[2] && !address[3] ) {
					bit = latches[3][0].bit;
			      }
			      else if ( address[0] && address[1] && !address[2] && address[3] ) {
					bit = latches[3][1].bit;
			      }
			      else if ( address[0] && address[1] && address[2] && !address[3] ) {
					bit = latches[3][2].bit;
			      }
			      else if ( address[0] && address[1] && address[2] && address[3] ) {
					bit = latches[3][3].bit;
			      }
		    }
	  }

	  // Overwrites a bit to a location of the existing matrix
	  public void WRITE () {
		    if ( WRITE_EN ) {
			      if ( !address[0] && !address[1] && !address[2] && !address[3] ) {
					latches[0][0].bit = bit;
			      }
			      else if ( !address[0] && !address[1] && !address[2] && address[3] ) {
					latches[0][1].bit = bit;
			      }
			      else if ( !address[0] && !address[1] && address[2] && !address[3] ) {
					latches[0][2].bit = bit;
			      }
			      else if ( !address[0] && !address[1] && address[2] && address[3] ) {
					latches[0][3].bit = bit;
			      }
			      else if ( !address[0] && address[1] && !address[2] && !address[3] ) {
					latches[1][0].bit = bit;
			      }
			      else if ( !address[0] && address[1] && !address[2] && address[3] ) {
					latches[1][1].bit = bit;
			      }
			      else if ( !address[0] && address[1] && address[2] && !address[3] ) {
					latches[1][2].bit = bit;
			      }
			      else if ( !address[0] && address[1] && address[2] && address[3] ) {
					latches[1][3].bit = bit;
			      }
			      else if ( address[0] && !address[1] && !address[2] && !address[3] ) {
					latches[2][0].bit = bit;
			      }
			      else if ( address[0] && !address[1] && !address[2] && address[3] ) {
					latches[2][1].bit = bit;
			      }
			      else if ( address[0] && !address[1] && address[2] && !address[3] ) {
					latches[2][2].bit = bit;
			      }
			      else if ( address[0] && !address[1] && address[2] && address[3] ) {
					latches[2][3].bit = bit;
			      }
			      else if ( address[0] && address[1] && !address[2] && !address[3] ) {
					latches[3][0].bit = bit;
			      }
			      else if ( address[0] && address[1] && !address[2] && address[3] ) {
					latches[3][1].bit = bit;
			      }
			      else if ( address[0] && address[1] && address[2] && !address[3] ) {
					latches[3][2].bit = bit;
			      }
			      else if ( address[0] && address[1] && address[2] && address[3] ) {
					latches[3][3].bit = bit;
			      }
		    }
	  }

	  // When you read enable the LatchMatrix, the GatedLatch at the specified address becomes read enabled too this happens through a multiplexer
	  public void ENABLE_READ () {
		    READ_EN = true;
		    if ( !address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[0][0].READ_EN = true;
		    }
		    else if ( !address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[0][1].READ_EN = true;
		    }
		    else if ( !address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[0][2].READ_EN = true;
		    }
		    else if ( !address[0] && !address[1] && address[2] && address[3] ) {
			      latches[0][3].READ_EN = true;
		    }
		    else if ( !address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[1][0].READ_EN = true;
		    }
		    else if ( !address[0] && address[1] && !address[2] && address[3] ) {
			      latches[1][1].READ_EN = true;
		    }
		    else if ( !address[0] && address[1] && address[2] && !address[3] ) {
			      latches[1][2].READ_EN = true;
		    }
		    else if ( !address[0] && address[1] && address[2] && address[3] ) {
			      latches[1][3].READ_EN = true;
		    }
		    else if ( address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[2][0].READ_EN = true;
		    }
		    else if ( address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[2][1].READ_EN = true;
		    }
		    else if ( address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[2][2].READ_EN = true;
		    }
		    else if ( address[0] && !address[1] && address[2] && address[3] ) {
			      latches[2][3].READ_EN = true;
		    }
		    else if ( address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[3][0].READ_EN = true;
		    }
		    else if ( address[0] && address[1] && !address[2] && address[3] ) {
			      latches[3][1].READ_EN = true;
		    }
		    else if ( address[0] && address[1] && address[2] && !address[3] ) {
			      latches[3][2].READ_EN = true;
		    }
		    else if ( address[0] && address[1] && address[2] && address[3] ) {
			      latches[3][3].READ_EN = true;
		    }

	  }

	  public void DISABLE_READ () {
		    READ_EN = false;
		    if ( !address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[0][0].READ_EN = false;
		    }
		    else if ( !address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[0][1].READ_EN = false;
		    }
		    else if ( !address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[0][2].READ_EN = false;
		    }
		    else if ( !address[0] && !address[1] && address[2] && address[3] ) {
			      latches[0][3].READ_EN = false;
		    }
		    else if ( !address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[1][0].READ_EN = false;
		    }
		    else if ( !address[0] && address[1] && !address[2] && address[3] ) {
			      latches[1][1].READ_EN = false;
		    }
		    else if ( !address[0] && address[1] && address[2] && !address[3] ) {
			      latches[1][2].READ_EN = false;
		    }
		    else if ( !address[0] && address[1] && address[2] && address[3] ) {
			      latches[1][3].READ_EN = false;
		    }
		    else if ( address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[2][0].READ_EN = false;
		    }
		    else if ( address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[2][1].READ_EN = false;
		    }
		    else if ( address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[2][2].READ_EN = false;
		    }
		    else if ( address[0] && !address[1] && address[2] && address[3] ) {
			      latches[2][3].READ_EN = false;
		    }
		    else if ( address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[3][0].READ_EN = false;
		    }
		    else if ( address[0] && address[1] && !address[2] && address[3] ) {
			      latches[3][1].READ_EN = false;
		    }
		    else if ( address[0] && address[1] && address[2] && !address[3] ) {
			      latches[3][2].READ_EN = false;
		    }
		    else if ( address[0] && address[1] && address[2] && address[3] ) {
			      latches[3][3].READ_EN = false;
		    }

	  }

	  public void ENABLE_WRITE () {
		    WRITE_EN = true;
		    if ( !address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[0][0].WRITE_EN = true;
		    }
		    else if ( !address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[0][1].WRITE_EN = true;
		    }
		    else if ( !address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[0][2].WRITE_EN = true;
		    }
		    else if ( !address[0] && !address[1] && address[2] && address[3] ) {
			      latches[0][3].WRITE_EN = true;
		    }
		    else if ( !address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[1][0].WRITE_EN = true;
		    }
		    else if ( !address[0] && address[1] && !address[2] && address[3] ) {
			      latches[1][1].WRITE_EN = true;
		    }
		    else if ( !address[0] && address[1] && address[2] && !address[3] ) {
			      latches[1][2].WRITE_EN = true;
		    }
		    else if ( !address[0] && address[1] && address[2] && address[3] ) {
			      latches[1][3].WRITE_EN = true;
		    }
		    else if ( address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[2][0].WRITE_EN = true;
		    }
		    else if ( address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[2][1].WRITE_EN = true;
		    }
		    else if ( address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[2][2].WRITE_EN = true;
		    }
		    else if ( address[0] && !address[1] && address[2] && address[3] ) {
			      latches[2][3].WRITE_EN = true;
		    }
		    else if ( address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[3][0].WRITE_EN = true;
		    }
		    else if ( address[0] && address[1] && !address[2] && address[3] ) {
			      latches[3][1].WRITE_EN = true;
		    }
		    else if ( address[0] && address[1] && address[2] && !address[3] ) {
			      latches[3][2].WRITE_EN = true;
		    }
		    else if ( address[0] && address[1] && address[2] && address[3] ) {
			      latches[3][3].WRITE_EN = true;
		    }
	  }

	  public void DISABLE_WRITE () {
		    WRITE_EN = false;
		    if ( !address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[0][0].WRITE_EN = false;
		    }
		    else if ( !address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[0][1].WRITE_EN = false;
		    }
		    else if ( !address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[0][2].WRITE_EN = false;
		    }
		    else if ( !address[0] && !address[1] && address[2] && address[3] ) {
			      latches[0][3].WRITE_EN = false;
		    }
		    else if ( !address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[1][0].WRITE_EN = false;
		    }
		    else if ( !address[0] && address[1] && !address[2] && address[3] ) {
			      latches[1][1].WRITE_EN = false;
		    }
		    else if ( !address[0] && address[1] && address[2] && !address[3] ) {
			      latches[1][2].WRITE_EN = false;
		    }
		    else if ( !address[0] && address[1] && address[2] && address[3] ) {
			      latches[1][3].WRITE_EN = false;
		    }
		    else if ( address[0] && !address[1] && !address[2] && !address[3] ) {
			      latches[2][0].WRITE_EN = false;
		    }
		    else if ( address[0] && !address[1] && !address[2] && address[3] ) {
			      latches[2][1].WRITE_EN = false;
		    }
		    else if ( address[0] && !address[1] && address[2] && !address[3] ) {
			      latches[2][2].WRITE_EN = false;
		    }
		    else if ( address[0] && !address[1] && address[2] && address[3] ) {
			      latches[2][3].WRITE_EN = false;
		    }
		    else if ( address[0] && address[1] && !address[2] && !address[3] ) {
			      latches[3][0].WRITE_EN = false;
		    }
		    else if ( address[0] && address[1] && !address[2] && address[3] ) {
			      latches[3][1].WRITE_EN = false;
		    }
		    else if ( address[0] && address[1] && address[2] && !address[3] ) {
			      latches[3][2].WRITE_EN = false;
		    }
		    else if ( address[0] && address[1] && address[2] && address[3] ) {
			      latches[3][3].WRITE_EN = false;
		    }
	  }

}
