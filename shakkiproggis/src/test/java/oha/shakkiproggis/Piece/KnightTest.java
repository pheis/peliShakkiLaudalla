package oha.shakkiproggis.Piece;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import oha.shakkiproggis.Piece.PieceT;
import oha.shakkiproggis.Board;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.Collectors;
import oha.shakkiproggis.Board;
import oha.shakkiproggis.MoveValidator;
import oha.shakkiproggis.PawnPromoChooser;
import oha.shakkiproggis.PieceGroup;
import oha.shakkiproggis.Square;

/**
 *
 * @author pyry
 */
public class KnightTest {
	
	public KnightTest() {
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
	public void knightsAttacks() {
		
		PawnPromoChooser pc1 = new PawnPromoChooser();
		PawnPromoChooser pc2 = new PawnPromoChooser();
		
		MoveValidator mv = new MoveValidator(pc1, pc2);

		boolean[] t = new boolean[]{true,true,true,true,true,true,true};
		
		t[0] = (t[0]) ? mv.move("B1C3") : false; // true
		t[1] = (t[1]) ? mv.move("B8B5") : false; // false
		t[2] = (t[2]) ? mv.move("B8C6") : false; // true
		t[3] = (t[3]) ? mv.move("C3E2") : false; // false
		t[4] = (t[4]) ? mv.move("C3D5") : false; // true
		t[5] = (t[5]) ? mv.move("G8F6") : false; // true
		t[6] = (t[6]) ? mv.move("D5F6") : false; // true
		
		


		//ArrayList<Board> nBoards = b.listPossibleMoves();
		//StringBuilder sb = new StringBuilder();
		//nBoards.forEach(x -> sb.append(x.toString()));
		//String ans = sb.toString();
		
		//String corr = "A1C2A1B3E1D1E1F1E1D2E1E2E1F2";
		
		boolean corr[] = new boolean[]{true,false,true,false,true,true,true};
		
		
		assertTrue(corr[0] == t[0]);
		assertTrue(corr[1] == t[1]);
		assertTrue(corr[2] == t[2]);
		assertTrue(corr[3] == t[3]);
		assertTrue(corr[4] == t[4]);
		assertTrue(corr[5] == t[5]);
		assertTrue(corr[6] == t[6]);
	}
}
