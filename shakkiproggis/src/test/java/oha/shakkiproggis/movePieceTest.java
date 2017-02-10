/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.ArrayList;
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
public class movePieceTest {
	
	public movePieceTest() {
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
	public void hello() {
		
		Board board = new Board();
		board.piecesToStartingFormation();
		
		//ArrayList<Board> bs = board.listPossibleMoves();
		
		Board b2 = board.makeAmove(Square.A2, Square.A4).get();
		
		String a = "A2A4";
		
		assertEquals(a,b2.toString());
	
	}
}
