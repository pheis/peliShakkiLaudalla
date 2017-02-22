package oha.shakkiproggis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

		PawnPromoChooser pc1 = new PawnPromoChooser();
		PawnPromoChooser pc2 = new PawnPromoChooser();
		

		MoveValidator mv = new MoveValidator(pc1, pc2);
		//board.piecesToStartingFormation();
		
		//ArrayList<Board> bs = board.listPossibleMoves();
		
		boolean t1 = mv.move("E2E4");
		boolean t2 = mv.move("A7A5");
		boolean t3 = mv.move("E4E5");
		boolean t4 = mv.move("D7D5");
		boolean t5 = mv.move("E5D6");
		

		
		assertTrue(t1);
		assertTrue(t2);
		assertTrue(t3);
		assertTrue(t4);
		assertTrue(t5);
		
		assertTrue(mv.lastBoard().my.sqPtMap.size() == 15);
		
		//int howManyPieces = b6.myPieces.size();
		
		//assertEquals(15, b6.my.sqPtMap.size());
	
	}
	@Test
	public void blackEnPass() {
	
		PawnPromoChooser pc1 = new PawnPromoChooser();
		PawnPromoChooser pc2 = new PawnPromoChooser();
		
		
		MoveValidator mv = new MoveValidator(pc1, pc2);
		
		boolean t1 = mv.move("A2A4");
		boolean t2 = mv.move("D7D5");
		boolean t3 = mv.move("B2B4");
		boolean t4 = mv.move("D5D4");
		boolean t5 = mv.move("E2E4");
		boolean t6 = mv.move("D4E3");
		
		assertTrue(t1);
		assertTrue(t2);
		assertTrue(t3);
		assertTrue(t4);
		assertTrue(t5);
		
		assertTrue(mv.lastBoard().my.sqPtMap.size() == 15);
		
	
	}
}
