package Computer.CPU;

/* Evaluation blocks are labelled
 * TO check a specific command evaluation, search the file for "<CMND>:"
 *
 * UPDATES:
 *
 * ADDED proper support for oopsRAM, search oopsRAM
 * ADDED new commands
 * REMOVED useless comments
 * ADDED new useless comments (like this one and the last one)
 * ADDED a class called MotherBoard, no idea if we need it
 * ADDED ALU invocation
 */

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
 *
 * >> Operations
 *
 * ADD----------1000 REG REG
 * EQUALITY-----1101 REG REG
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
import Computer.MicroComponents.GatedLatch;
import Computer.Motherboard;

public class OOPS extends Motherboard {


	;//<editor-fold defaultstate="collapsed" desc="REGISTERS and CONNECTORS">

	;// REGISTERS
	private static Register instruction;
	private static Register instruction_address;
	private static Register A;	//REGR address 00
	private static Register B;	//REGR address 01
	private static Register C;	//REGR address 10
	private static Register D;	//REGR address 11

	// CONNECTIONS
	private static boolean[] OPCode;	  // The OPCODE. This connects to the ALU
	public static boolean[] data_address;  // GOES TO RAM, Sole purpose is to help fetch data to registers. Nothing else
	private static boolean[] reg1_address;  // 1st Register 2bit address for ALU
	private static boolean[] reg2_address;  // 2nd Register 2bit address for ALU

	// ALU FLAGS
	public static boolean NEGATIVE;	  // When output is negative, this is true
	public static boolean EQUAL;		  // If outcome of comparison is equality (relational operation)
	public static boolean OVERFLOW;	  // Overflow bit

	;//</editor-fold>

	;/* REGISTER array. THIS CAN BE USED BY ALU TO TARGET SPECIFIC RESISTORS
	  * THE CU CAN USE IT ONLY WITH LARGER INSTRUCTION CODES
	  */
	private static Register[][] REGR;


	;//<editor-fold defaultstate="collapsed" desc="ArithmeticAndLogicUnit">

	;// ALU (For Bangalis: Eta khabar nohe)
	public static void ArithmeticAndLogicUnit () {
		ALU:
		;
		// Just for clarity, We created X, Y for 1st regr, P, Q for 2nd
		// It simlpy stores indexes of REGR[][] for easy register access
		int P = reg1_address[0] ? 1 : 0;
		int Q = reg1_address[1] ? 1 : 0;
		int X = reg2_address[0] ? 1 : 0;
		int Y = reg2_address[1] ? 1 : 0;

		// 1000 ADDITION
		if ( OPCode[0] & !OPCode[1] & !OPCode[2] & !OPCode[3] ) {
			ADDITION:
			;
			// Inner connection, carry in/out and one more connection to prevent circuit loop
			boolean Cin = false, Cout = false, buffer;

			for ( int i = REGR[X][Y].REGISTER.length - 1 ; i >= 0 ; --i ) {
				buffer = REGR[P][Q].REGISTER[i].bit ^ REGR[X][Y].REGISTER[i].bit ^ Cin;
				Cout = REGR[P][Q].REGISTER[i].bit & REGR[X][Y].REGISTER[i].bit | Cin & ( REGR[P][Q].REGISTER[i].bit ^ REGR[X][Y].REGISTER[i].bit );
				REGR[X][Y].REGISTER[i].bit = buffer;
				Cin = Cout;
			}

			OVERFLOW = Cout;
		}
		// 1101 EQUALITY
		else if ( OPCode[0] & OPCode[1] & !OPCode[2] & OPCode[3] ) {
			JUMP_EQUAL:

			EQUAL = ( ( REGR[P][Q].REGISTER[0].bit == REGR[X][Y].REGISTER[0].bit )
				& ( REGR[P][Q].REGISTER[1].bit == REGR[X][Y].REGISTER[1].bit )
				& ( REGR[P][Q].REGISTER[2].bit == REGR[X][Y].REGISTER[2].bit )
				& ( REGR[P][Q].REGISTER[3].bit == REGR[X][Y].REGISTER[3].bit )
				& ( REGR[P][Q].REGISTER[4].bit == REGR[X][Y].REGISTER[4].bit )
				& ( REGR[P][Q].REGISTER[5].bit == REGR[X][Y].REGISTER[5].bit )
				& ( REGR[P][Q].REGISTER[6].bit == REGR[X][Y].REGISTER[6].bit )
				& ( REGR[P][Q].REGISTER[7].bit == REGR[X][Y].REGISTER[7].bit ) );

		}
		// 1110 SUBTRACT
		else if ( OPCode[0] & OPCode[1] & OPCode[2] & !OPCode[3] ) {
			SUBTRACT:
			;
			boolean carry;

			// This variable is not needed in a circuit
			boolean buffer_bit;

			// 1's Complementing the 2nd number
			for ( int i = 7 ; i >= 0 ; i-- ) {
				REGR[X][Y].REGISTER[i].bit = !REGR[X][Y].REGISTER[i].bit;
			}
			// 2's Complementing the 2nd number
			carry = true;
			for ( int i = 7 ; i >= 0 ; i-- ) {

				buffer_bit = REGR[X][Y].REGISTER[i].bit;
				REGR[X][Y].REGISTER[i].bit = buffer_bit ^ carry;
				carry = buffer_bit & carry;
			}

			/* System.out.print ("\n\nSUBTRACT From = ");
			 * for ( GatedLatch var : REGR[P][Q].REGISTER ) {
			 * System.out.print (var.bit ? 1 : 0);
			 * }
			 * System.out.print ("\n2's Complement = ");
			 * for ( GatedLatch var : REGR[X][Y].REGISTER ) {
			 * System.out.print (var.bit ? 1 : 0);
			 * }
			 * System.out.println ();
			 */

			// Adding the 2's complement using the ALU adder
			carry = false;
			OPCode[0] = true;
			OPCode[1] = false;
			OPCode[2] = false;
			OPCode[3] = false;
			ArithmeticAndLogicUnit ();

			if ( !OVERFLOW ) {

				NEGATIVE = true;

				/* 2's Complementing the result since if there's no overflow, it means a
				 * larger number was subtracted from a smaller
				 */
				carry = false;
				for ( int i = 7 ; i >= 0 ; i-- ) {
					REGR[X][Y].REGISTER[i].bit = !REGR[X][Y].REGISTER[i].bit;
				}

				carry = true;
				for ( int i = 7 ; i >= 0 ; i-- ) {

					buffer_bit = REGR[X][Y].REGISTER[i].bit;
					REGR[X][Y].REGISTER[i].bit = buffer_bit ^ carry;
					carry = buffer_bit & carry;
				}
			}
			OVERFLOW = false;
		}
		// 1111 RESET
		else if ( OPCode[0] & OPCode[1] & OPCode[2] & OPCode[3] ) {
			RESET:
			NEGATIVE = false;
			EQUAL = false;
			OVERFLOW = false;
		}
	}


	;//</editor-fold>


	;//<editor-fold defaultstate="collapsed" desc="CONTOL UNIT">

	;// Control unit
	public static void ControlUnit () {

		CONTROLUNIT:

		// REGISTERS
		instruction = new Register (8);
		instruction_address = new Register (4);
		A = new Register (8);
		B = new Register (8);
		C = new Register (8);
		D = new Register (8);

		// CONNECTIONS
		OPCode = new boolean[4];
		data_address = new boolean[4];
		reg1_address = new boolean[2];
		reg2_address = new boolean[2];


		// The register array
		REGR = new Register[][] { { A, B }, { C, D } };

		while ( true ) {

			System.out.println ();
			System.out.print ("CALL " + n + ": ");

			System.out.print ("RAM: ");
			for ( GatedLatch var : instruction_address.REGISTER ) {
				System.out.print (var.bit ? 1 : 0);
			}

			// Reading 8BIT INSTRUCTION

			instruction_address.READ_EN (true);
			instruction.WRITE_EN (true);
			oopsRAM1.ENABLE_READ ();

			oopsRAM1.address = instruction_address.Read ();
			oopsRAM1.READ ();
			instruction.Write (oopsRAM1.eight_bit_num);

			oopsRAM1.DISABLE_READ ();
			instruction.WRITE_EN (false);
			instruction_address.READ_EN (false);

			// OPCODE extraction
			for ( int i = 0 ; i < OPCode.length ; i++ ) {
				OPCode[i] = instruction.REGISTER[i].bit;
			}

			System.out.print (" OP: ");
			for ( boolean var : OPCode ) {
				System.out.print (var ? 1 : 0);
			}

			// COMMAND EVALUATION
			if ( !OPCode[0] ) {
					// POS1
				// POS1 = 0
				if ( !OPCode[1] ) {
						  // POS2
					// POS2 = 0
					if ( !OPCode[2] ) {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							HALT:
							System.out.println ("\n\nHALTED_AT_" + n);
							// 0000 HALT
							break;
						} // POS3 = 1
						else {
							LOAD_A:
								      // 0001 LOAD_A
							// LOADING 8BIT DATA TO REG_A

							A.WRITE_EN (true);
							oopsRAM1.ENABLE_READ ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set address, read data from RAM, write to register
							oopsRAM1.address = data_address;
							oopsRAM1.READ ();

							A.Write (oopsRAM1.eight_bit_num);

							oopsRAM1.DISABLE_READ ();
							A.WRITE_EN (false);
						}
						// END: POS3

					}
					// POS2 = 1
					else {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							LOAD_B:
								      // 0010 LOAD_B
							// LOADING 8BIT DATA TO REG_B

							B.WRITE_EN (true);
							oopsRAM1.ENABLE_READ ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;


							// Set address, read data from RAM, write to register
							oopsRAM1.address = data_address;
							oopsRAM1.READ ();

							B.Write (oopsRAM1.eight_bit_num);

							oopsRAM1.DISABLE_READ ();
							B.WRITE_EN (false);

						} // POS3 = 1
						else {
							LOAD_C:
								      // 0011 LOAD_C
							// LOADING 8BIT DATA TO REG_C

							C.WRITE_EN (true);
							oopsRAM1.ENABLE_READ ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set address, read data from RAM, write to register
							oopsRAM1.address = data_address;
							oopsRAM1.READ ();

							C.Write (oopsRAM1.eight_bit_num);

							oopsRAM1.DISABLE_READ ();
							C.WRITE_EN (false);

						}
						// END: POS3
					}
					// END: POS2
				}
				// POS1 = 1
				else {
						  // POS2
					// POS2 = 0
					if ( !OPCode[2] ) {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							LOAD_D:
								      // 0100 LOAD_D
							// LOADING 8BIT DATA TO REG_D

							D.WRITE_EN (true);
							oopsRAM1.ENABLE_READ ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set address, read data from RAM, write to register
							oopsRAM1.address = data_address;
							oopsRAM1.READ ();

							D.Write (oopsRAM1.eight_bit_num);

							oopsRAM1.DISABLE_READ ();
							D.WRITE_EN (false);

						}
						// POS3 = 1
						else {
							JUMP:
							;
							// Adding from 2nd index, skips next statement
							boolean carry;

							// This variable is not needed in a circuit
							boolean buffer_address_bit;

							buffer_address_bit = instruction_address.REGISTER[3].bit;
							instruction_address.REGISTER[3].bit = buffer_address_bit ^ false;
							carry = buffer_address_bit & false;

							buffer_address_bit = instruction_address.REGISTER[2].bit;
							instruction_address.REGISTER[2].bit = buffer_address_bit ^ true;
							carry = buffer_address_bit & true;

							buffer_address_bit = instruction_address.REGISTER[1].bit;
							instruction_address.REGISTER[1].bit = buffer_address_bit ^ carry;
							carry = buffer_address_bit & carry;

							buffer_address_bit = instruction_address.REGISTER[0].bit;
							instruction_address.REGISTER[0].bit = buffer_address_bit ^ carry;
							carry = buffer_address_bit & carry;
						}
						// END: POS3
					}
					// POS2 = 1
					else {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							JUMP_NEG:
							;
							// 0110 JUMP_NEG
							if ( NEGATIVE ) {

								// Adding from 2nd index, skips next statement
								boolean carry;

								// This variable is not needed in a circuit
								boolean buffer_address_bit;

								buffer_address_bit = instruction_address.REGISTER[3].bit;
								instruction_address.REGISTER[3].bit = buffer_address_bit ^ false;
								carry = buffer_address_bit & false;

								buffer_address_bit = instruction_address.REGISTER[2].bit;
								instruction_address.REGISTER[2].bit = buffer_address_bit ^ true;
								carry = buffer_address_bit & true;

								buffer_address_bit = instruction_address.REGISTER[1].bit;
								instruction_address.REGISTER[1].bit = buffer_address_bit ^ carry;
								carry = buffer_address_bit & carry;

								buffer_address_bit = instruction_address.REGISTER[0].bit;
								instruction_address.REGISTER[0].bit = buffer_address_bit ^ carry;
								carry = buffer_address_bit & carry;
							}
						} // POS3 = 1
						else {
							GOTO:
							// 0111 GOTO

							instruction.WRITE_EN (true);
							oopsRAM1.ENABLE_READ ();

							// TAKING NEW ADDRESS FROM GOTO COMMAND
							System.arraycopy (instruction.REGISTER, 4, instruction_address.REGISTER, 0, 4);

							//Reading instruction from address
							oopsRAM1.READ ();
							instruction.Write (oopsRAM1.eight_bit_num);

							instruction.WRITE_EN (true);
							oopsRAM1.DISABLE_READ ();
						}
						// END: POS3
					}
					// END: POS2
				}
				// END: POS1
			}
			else {
					// POS1
				// POS1 = 0
				if ( !OPCode[1] ) {
						  // POS2
					// POS2 = 0
					if ( !OPCode[2] ) {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							ADD:
							// 1000 ADD

							// Connection from instruction register to ALU, carrying addresses of two registers
							reg1_address[0] = instruction.REGISTER[4].bit;
							reg1_address[1] = instruction.REGISTER[5].bit;
							reg2_address[0] = instruction.REGISTER[6].bit;
							reg2_address[1] = instruction.REGISTER[7].bit;

							ALU_INVOCATION:
							ArithmeticAndLogicUnit ();
						} // POS3 = 1
						else {
							READ_A:
							// 1001 READ_A

							A.READ_EN (true);
							oopsRAM1.ENABLE_WRITE ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set RAM address, read data, write data
							oopsRAM1.address = data_address;
							oopsRAM1.eight_bit_num = A.Read ();
							oopsRAM1.WRITE ();

							oopsRAM1.DISABLE_WRITE ();
							A.READ_EN (false);
						}
						// END: POS3
					}
					// POS2 = 1
					else {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							READ_B:
							// 1010 READ_B

							B.READ_EN (true);
							oopsRAM1.ENABLE_WRITE ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set RAM address, read data, write data
							oopsRAM1.address = data_address;
							oopsRAM1.eight_bit_num = B.Read ();
							oopsRAM1.WRITE ();

							oopsRAM1.DISABLE_WRITE ();
							B.READ_EN (false);
						}
						// POS3 = 1
						else {
							READ_C:
							// 1010 READ_C

							C.READ_EN (true);
							oopsRAM1.ENABLE_WRITE ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set RAM address, read data, write data
							oopsRAM1.address = data_address;
							oopsRAM1.eight_bit_num = C.Read ();
							oopsRAM1.WRITE ();

							oopsRAM1.DISABLE_WRITE ();
							C.READ_EN (false);
						}
						// END: POS3
					}
					// END: POS2
				}
				// POS1 = 1
				else {
						  // POS2
					// POS2 = 0
					if ( !OPCode[2] ) {
							    // POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							READ_D:
							// 1010 READ_D

							D.READ_EN (true);
							oopsRAM1.ENABLE_WRITE ();

							// Last 4-Bits of the instruction (all but OPCode)
							data_address[0] = instruction.REGISTER[4].bit;
							data_address[1] = instruction.REGISTER[5].bit;
							data_address[2] = instruction.REGISTER[6].bit;
							data_address[3] = instruction.REGISTER[7].bit;

							// Set RAM address, read data, write data
							oopsRAM1.address = data_address;
							oopsRAM1.eight_bit_num = D.Read ();
							oopsRAM1.WRITE ();

							oopsRAM1.DISABLE_WRITE ();
							D.READ_EN (false);
						}
						// POS3 = 1
						else {
							JUMP_EQUAL:
							;
							    // 1101 JUMP_EQUAL

							// Connection from instruction register to ALU, carrying addresses of two registers
							reg1_address[0] = instruction.REGISTER[4].bit;
							reg1_address[1] = instruction.REGISTER[5].bit;
							reg2_address[0] = instruction.REGISTER[6].bit;
							reg2_address[1] = instruction.REGISTER[7].bit;

							ALU_INVOCATION:
							ArithmeticAndLogicUnit ();

							if ( EQUAL ) {
								// Adding from 2nd index, skips next statement
								boolean carry;

								// This variable is not needed in a circuit
								boolean buffer_address_bit;

								buffer_address_bit = instruction_address.REGISTER[3].bit;
								instruction_address.REGISTER[3].bit = buffer_address_bit ^ false;
								carry = buffer_address_bit & false;

								buffer_address_bit = instruction_address.REGISTER[2].bit;
								instruction_address.REGISTER[2].bit = buffer_address_bit ^ true;
								carry = buffer_address_bit & true;

								buffer_address_bit = instruction_address.REGISTER[1].bit;
								instruction_address.REGISTER[1].bit = buffer_address_bit ^ carry;
								carry = buffer_address_bit & carry;

								buffer_address_bit = instruction_address.REGISTER[0].bit;
								instruction_address.REGISTER[0].bit = buffer_address_bit ^ carry;
								carry = buffer_address_bit & carry;
							}
						}
						// END: POS3
					}
					// POS2 = 1
					else {
						// POS3
						// POS3 = 0
						if ( !OPCode[3] ) {
							// 1110

							// Connection from instruction register to ALU, carrying addresses of two registers
							reg1_address[0] = instruction.REGISTER[4].bit;
							reg1_address[1] = instruction.REGISTER[5].bit;
							reg2_address[0] = instruction.REGISTER[6].bit;
							reg2_address[1] = instruction.REGISTER[7].bit;

							ALU_INVOCATION:
							ArithmeticAndLogicUnit ();
						}
						// POS3 = 1
						else {
							// 1111
							System.err.println ("1111 UNSUPPORTED");
							for ( GatedLatch REGISTER : instruction.REGISTER ) {
								REGISTER.bit = false;
							}
						}
						// END: POS3
					}
					// END: POS2
				}
				// END: POS1
			}

			// Adding 1 bit to last instruction address. Goes to next instruction
			boolean carry;

			// This variable is not needed in a circuit
			boolean buffer_address_bit;

			buffer_address_bit = instruction_address.REGISTER[3].bit;
			instruction_address.REGISTER[3].bit = buffer_address_bit ^ true;
			carry = buffer_address_bit & true;

			buffer_address_bit = instruction_address.REGISTER[2].bit;
			instruction_address.REGISTER[2].bit = buffer_address_bit ^ carry;
			carry = buffer_address_bit & carry;

			buffer_address_bit = instruction_address.REGISTER[1].bit;
			instruction_address.REGISTER[1].bit = buffer_address_bit ^ carry;
			carry = buffer_address_bit & carry;

			buffer_address_bit = instruction_address.REGISTER[0].bit;
			instruction_address.REGISTER[0].bit = buffer_address_bit ^ carry;
			carry = buffer_address_bit & carry;

			n++;
		}
	}

	static int n = 0;
	//</editor-fold>

}
