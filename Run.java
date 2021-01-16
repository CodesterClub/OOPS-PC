
import Computer.CPU.OOPS;
import Computer.IO.InputStream;
import Computer.IO.OutputStream;
import Computer.Motherboard;

/** COMMAND LIST
 *
 * CMND---------4BIT 4BIT
 *
 * HALT---------0000 0000
 *
 * >> Loading from RAM to REGR
 *
 * LOAD_A-------0001 ADDR
 * LOAD_B-------0010 ADDR
 * LOAD_C-------0011 ADDR
 * LOAD_D-------0100 ADDR
 *
 * >> Flow of control
 *
 * JUMP---------0101 0000
 * JUMP_NEG-----0110 0000
 * GOTO---------0111 ADDR
 * JUMP_EQUAL---1101 REG REG
 *
 * >> Operations
 *
 * ADD----------1000 REG REG
 * SUBTRACT-----1110 REG REG
 *
 * >> Following READS from REGR and writes to RAM
 *
 * READ_A-------1001 ADDR
 * READ_B-------1010 ADDR
 * READ_C-------1011 ADDR
 * READ_D-------1100 ADDR
 *
 * >> Special ALU commands
 *
 * RESET_FLAGS--1111 0000
 *
 */

public class Run {

	public static void main ( String[] args ) {
		boolean O = false;

		// Motherboard connection, initialising components!
		Motherboard.init ();

		boolean[] RAM_Input;
		boolean[] RAM_Address;

		// RAM loader codes:

		LOAD_VALUE_13_to_RAM_5:

		RAM_Address = new boolean[] { O, !O, O, !O }; //0101
		RAM_Input = new boolean[] { O, O, O, O, !O, !O, O, !O };
		InputStream.loadRAM (RAM_Address, RAM_Input);

		LOAD_VALUE_7_to_RAM_6:

		RAM_Address = new boolean[] { O, !O, !O, O }; //0110
		RAM_Input = new boolean[] { O, O, O, O, O, !O, !O, !O };
		InputStream.loadRAM (RAM_Address, RAM_Input);


		// CPU execution codes:
		LOAD_A_from_RAM_5:

		RAM_Address = new boolean[] { O, O, O, O }; //0000
		RAM_Input = new boolean[] { O, O, O, !O, O, !O, O, !O };
		InputStream.loadRAM (RAM_Address, RAM_Input);

		LOAD_B_from_RAM_6:

		RAM_Address = new boolean[] { O, O, O, !O }; //0001
		RAM_Input = new boolean[] { O, O, !O, O, O, !O, !O, O };
		InputStream.loadRAM (RAM_Address, RAM_Input);

		SUB_A_B:

		RAM_Address = new boolean[] { O, O, !O, O }; //0010
		RAM_Input = new boolean[] { !O, !O, !O, O, O, O, O, !O };
		InputStream.loadRAM (RAM_Address, RAM_Input);

		READ_B_to_RAM_7:

		RAM_Address = new boolean[] { O, O, !O, !O }; //0011
		RAM_Input = new boolean[] { !O, O, !O, O, O, !O, !O, !O };
		InputStream.loadRAM (RAM_Address, RAM_Input);

		HALT:

		RAM_Address = new boolean[] { O, !O, O, O }; //0100
		RAM_Input = new boolean[] { O, O, O, O, O, O, O, O };
		InputStream.loadRAM (RAM_Address, RAM_Input);

		// Invokes CU, equivalent to giving power to CPU
		OOPS.ControlUnit ();

		// OUTPUT PARAMETERS

		// Output from RAM_7
		RAM_Address = new boolean[] { O, !O, !O, !O }; //0111
		OutputStream.ReadRAM (RAM_Address);
	}

}
