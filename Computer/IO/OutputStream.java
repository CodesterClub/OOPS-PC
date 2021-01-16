package Computer.IO;

import Computer.CPU.OOPS;
import Computer.Motherboard;

public class OutputStream extends Motherboard{
	  public static void ReadRAM (boolean[] RAM_Address) {

		    oopsRAM1.ENABLE_READ ();

		    oopsRAM1.address = RAM_Address;
		    oopsRAM1.READ ();

		    System.out.print ("\nRESULT = ");

		    // RESULT output
		    System.out.print (oopsRAM1.eight_bit_num[0]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[1]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[2]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[3]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[4]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[5]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[6]?1:0);
		    System.out.print (oopsRAM1.eight_bit_num[7]?1:0);

		    System.out.println ("\n");

		    // FLAGS output
		    System.out.println ("NEG:\t\t" + OOPS.NEGATIVE);
		    System.out.println ("EQUAL:\t\t" + OOPS.EQUAL);
		    System.out.println ("OVERFLOW:\t" + OOPS.OVERFLOW);

		    oopsRAM1.DISABLE_READ ();
	  }
}
