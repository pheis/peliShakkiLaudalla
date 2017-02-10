/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

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
public class moveAKnightTest {
	
	public moveAKnightTest() {
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
		@Test
	public void hello() {
		
		Board board = new Board();
		board.piecesToStartingFormation();
		
		//ArrayList<Board> bs = board.listPossibleMoves();
		
		Board b2 = board.makeAmove(Square.B1, Square.A3).get();
		
		String a = "B1A3";
		
		assertEquals(a,b2.toString());
	
	}
}
