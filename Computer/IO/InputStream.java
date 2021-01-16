package Computer.IO;

import Computer.Motherboard;

public class InputStream extends Motherboard{

	  public static void loadRAM (boolean[] RAM_Address, boolean[] RAM_Input){

		    oopsRAM1.ENABLE_WRITE ();

		    oopsRAM1.address = RAM_Address;
		    oopsRAM1.eight_bit_num = RAM_Input;
		    oopsRAM1.WRITE ();

		    oopsRAM1.DISABLE_WRITE ();
	  }
}
