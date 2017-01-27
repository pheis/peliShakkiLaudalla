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
public class diagonalleftTest {
	
	public diagonalleftTest() {
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
	// @Test
	// public void hello() {}
		@Test public void dtestL() {
		Board b;		
		b = new Board();
		
		EnumSet<Square> sq = EnumSet.noneOf(Square.class);
		
		b.allSquaresOnBoard()
			.filter((Square x) -> (b.onSameLeftDiagonal(Square.A1, x)))
			.forEach(sq::add);
		
		
		EnumSet<Square> corr = EnumSet.noneOf(Square.class);
		
		corr.add(Square.A1);
		
		assertEquals(corr, sq);
		
	}
}
