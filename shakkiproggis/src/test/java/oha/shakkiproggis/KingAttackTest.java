/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;
import java.util.EnumSet;
import java.util.stream.Collectors;
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
public class KingAttackTest {
	
	public KingAttackTest() {
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
	 public void kinginLiikkeet() {
	 	Board b;		
		b = new Board();
		EnumSet<Square> attacks = EnumSet.noneOf(Square.class);
		attacks.add(Square.B1);
		attacks.add(Square.A2);
		attacks.add(Square.B2);
		assertEquals(attacks, b.kingAttacks(Square.A1)
			.collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class))));
	 }
}

