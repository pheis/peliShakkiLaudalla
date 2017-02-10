package oha.shakkiproggis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class enPassantTest {
	
	public enPassantTest() {
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
	public void whiteEnPass() {
	
		Board board = new Board();
		board.piecesToStartingFormation();
		
		//ArrayList<Board> bs = board.listPossibleMoves();
		
		Board b2 = board.makeAmove(Square.E2, Square.E4).get();
		Board b3 = b2.makeAmove(Square.A7, Square.A5).get();
		Board b4 = b3.makeAmove(Square.E4, Square.E5).get();
		Board b5 = b4.makeAmove(Square.D7, Square.D5).get();
		Board b6 = b5.makeAmove(Square.E5, Square.D6).get();
		//String a = "E4 -> D5";
		
		
		//int howManyPieces = b6.myPieces.size();
		
		assertEquals(15, b6.myPieces.size());
	
	}
	@Test
	public void blackEnPass() {
	
		Board board = new Board();
		board.piecesToStartingFormation();
		
		//ArrayList<Board> bs = board.listPossibleMoves();
		
		Board b2 = board.makeAmove(Square.A2, Square.A4).get();
		Board b3 = b2.makeAmove(Square.D7, Square.D5).get();
		Board b4 = b3.makeAmove(Square.B2, Square.B4).get();
		Board b5 = b4.makeAmove(Square.D5, Square.D4).get();
		Board b6 = b5.makeAmove(Square.E2, Square.E4).get();
		Board b7 = b6.makeAmove(Square.D4, Square.E3).get();
		//String a = "E4 -> D5";
		
		
		//int howManyPieces = b6.myPieces.size();
		
		assertEquals(15, b7.myPieces.size());

	
	}
}
