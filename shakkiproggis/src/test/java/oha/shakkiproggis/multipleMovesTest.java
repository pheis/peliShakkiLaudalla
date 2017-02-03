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
public class multipleMovesTest {
	
	public multipleMovesTest() {
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
		
		Board b2 = board.makeAmove(Square.E2, Square.E4).get();
		Board b3 = b2.makeAmove(Square.D7, Square.D5).get();
		Board b4 = b3.makeAmove(Square.E4, Square.D5).get();
		String a = "E4 -> D5";
		
		//int howManyPieces = b4.myPieces.size();
		
		assertEquals(15,b4.myPieces.size());
	
	}
}

