/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.ArrayList;
import static oha.shakkiproggis.Square.*;
import oha.shakkiproggis.pieces.PieceT;
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
public class GameTest {
	
	public GameTest() {
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
	public void movesWork() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();
		moves.add(new Move(H2, H4));
	
		moves.add(new Move(D7, D5));
		
		moves.add(new Move(H4, H5));
	
		moves.add(new Move(C8, F5));
		
		moves.add(new Move(H5, H6));
		
		moves.add(new Move(D8, D6));
		
		moves.add(new Move(H6, G7));
		
		moves.add(new Move(B8, C6));
		
		moves.add(new Move(G7, H8));
		
		moves.add(new Move(E8, C8));
		
		boolean t = true;
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
			
			t = t ? !game.mate() : t;
			t = t ? !game.draw() : t;
		}
		assertTrue(t);
		
	}
	@Test
	public void PawnPromoTest() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();

		moves.add(new Move(H2, H4));
	
		moves.add(new Move(D7, D5));
		
		moves.add(new Move(H4, H5));
	
		moves.add(new Move(C8, F5));
		
		moves.add(new Move(H5, H6));
		
		moves.add(new Move(D8, D6));
		
		moves.add(new Move(H6, G7));
		
		moves.add(new Move(B8, C6));
		
		moves.add(new Move(G7, H8));
		
		moves.add(new Move(E8, C8));
		
		
		
		boolean t = true;
		
		game.pptToKnight();
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
		}
		
		
		
		PieceT maybeHorse = game.mv.lastBoard().my.sqPtMap.get(H8);
		
		assertEquals(PieceT.KNIGHT, maybeHorse);
	}
	@Test
	public void PawnPromoBishopTest() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();

		moves.add(new Move(H2, H4));
	
		moves.add(new Move(D7, D5));
		
		moves.add(new Move(H4, H5));
	
		moves.add(new Move(C8, F5));
		
		moves.add(new Move(H5, H6));
		
		moves.add(new Move(D8, D6));
		
		moves.add(new Move(H6, G7));
		
		moves.add(new Move(B8, C6));
		
		moves.add(new Move(G7, H8));
		
		moves.add(new Move(E8, C8));
		
		
		
		boolean t = true;
		
		game.pptToBishop();
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
		}
		
		
		
		PieceT maybeBishop = game.mv.lastBoard().my.sqPtMap.get(H8);
		
		assertEquals(PieceT.BISHOP, maybeBishop);
	}
		@Test
	public void PawnPromoRookTest() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();

		moves.add(new Move(H2, H4));
	
		moves.add(new Move(D7, D5));
		
		moves.add(new Move(H4, H5));
	
		moves.add(new Move(C8, F5));
		
		moves.add(new Move(H5, H6));
		
		moves.add(new Move(D8, D6));
		
		moves.add(new Move(H6, G7));
		
		moves.add(new Move(B8, C6));
		
		moves.add(new Move(G7, H8));
		
		moves.add(new Move(E8, C8));
		
		
		
		boolean t = true;
		
		game.pptToRook();
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
		}
		
		
		
		PieceT maybeRook = game.mv.lastBoard().my.sqPtMap.get(H8);
		
		assertEquals(PieceT.ROOK, maybeRook);
	}
	
		@Test
	public void PawnPromoQueenTest() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();

		moves.add(new Move(H2, H4));
	
		moves.add(new Move(D7, D5));
		
		moves.add(new Move(H4, H5));
	
		moves.add(new Move(C8, F5));
		
		moves.add(new Move(H5, H6));
		
		moves.add(new Move(D8, D6));
		
		moves.add(new Move(H6, G7));
		
		moves.add(new Move(B8, C6));
		
		moves.add(new Move(G7, H8));
		
		moves.add(new Move(E8, C8));
		
		
		
		boolean t = true;
		
		//game.pptToRook();
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
		}
		
		
		
		PieceT maybeQueen = game.mv.lastBoard().my.sqPtMap.get(H8);
		
		assertEquals(PieceT.QUEEN, maybeQueen);
	}
	
	@Test
	public void MateTest() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();
		moves.add(new Move(E2, E4));
	
		moves.add(new Move(E7, E5));
		
		moves.add(new Move(D1, H5));
	
		moves.add(new Move(A7, A5));
		
		moves.add(new Move(F1, C4));
		
		moves.add(new Move(B7, B5));
		
		moves.add(new Move(H5, F7));
		
		boolean t = true;
		
		//game.pptToKnight();
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
		}
		
		assertTrue(game.mate());
	}
	@Test
	public void DrawByRepetitionTest() {
		Game game = new Game(true, false);
		ArrayList<Move> moves = new ArrayList<>();
		moves.add(new Move(G1, H3));
	
		moves.add(new Move(G8, H6));
		
		moves.add(new Move(H3, G1));
	
		moves.add(new Move(H6, G8));
		
		moves.add(new Move(G1, H3));
		
		moves.add(new Move(G8, H6));
		
		moves.add(new Move(H3, G1));
		
		moves.add(new Move(H6, G8));
		
		boolean t = true;
		
		//game.pptToKnight();
		
		for (Move m : moves) {
			t = t ? game.move(m) : t;
		}
		
		assertTrue(game.draw());
	}
}
