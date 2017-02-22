/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;
import java.util.EnumSet;
import oha.shakkiproggis.Board;
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
public class diagonalTest {
	
	public diagonalTest() {
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


	@Test public void dtestR() {
		//Board b;		
		//b = new Board();
		
		EnumSet<Square> sq = EnumSet.noneOf(Square.class);
		
		Square.allSquares()
			.filter((Square x) -> (Square.onSameRightDiagonal(Square.A1, x)))
			.forEach(sq::add);
		
		
		EnumSet<Square> corr = EnumSet.noneOf(Square.class);
		
		corr.add(Square.A1);
		corr.add(Square.B2);
		corr.add(Square.C3);
		corr.add(Square.D4);
		corr.add(Square.E5);
		corr.add(Square.F6);
		corr.add(Square.G7);
		corr.add(Square.H8);
		
		assertEquals(corr, sq);
		
	}
}
