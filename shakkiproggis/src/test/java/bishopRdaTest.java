/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;
import java.util.EnumSet;
import oha.shakkiproggis.Board;
import oha.shakkiproggis.Square;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pyry
 */
public class bishopRdaTest {
	
	public bishopRdaTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void rdabishop() {
		Board b;		
		b = new Board();
		EnumSet<Square> attacks = EnumSet.noneOf(Square.class);
		
		b.piecesToStartingFormation();
		
		attacks.add(Square.A8);
		attacks.add(Square.B7);
		attacks.add(Square.C6);
		attacks.add(Square.D5);
		attacks.add(Square.E4);
		attacks.add(Square.F3);
		attacks.add(Square.G2);
		attacks.add(Square.A6);
		attacks.add(Square.C8);
		assertEquals(attacks, b.bishopAttacks(Square.B7));
	}
}
