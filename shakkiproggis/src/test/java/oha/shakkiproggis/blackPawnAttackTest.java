/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;
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
public class blackPawnAttackTest {
	
	public blackPawnAttackTest() {
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
	public void onTheEdgeofTheBoardTest() {
		
		EnumMap<Square, Piece> mp = new EnumMap<>(Square.class);
		
		mp.put(Square.B2, Piece.BPAWN);
		
		mp.put(Square.E1, Piece.KING);
		
		
		EnumMap<Square, Piece> ep = new EnumMap<>(Square.class);
		
		ep.put(Square.F8, Piece.KING);
		
		ep.put(Square.C1, Piece.KNIGHT);
		ep.put(Square.A1, Piece.KNIGHT);
		ep.put(Square.B1, Piece.KNIGHT);
		
		boolean[] ts = new boolean[7];
		
		for (int i = 0; i < 7;i++) {
			ts[i] = true;
		}
		
		ts[0] = false;
		
		Optional<Square> epp = Optional.empty();
		
		String n = "F7F8";
		
		Board b = new Board(mp , ep, ts, epp, n);
		
		ArrayList<Board> nBoards = b.listPossibleMoves();
		StringBuilder sb = new StringBuilder();
		nBoards.forEach(x -> sb.append(x.toString()));
		String ans = sb.toString();
		
		String corr = "E1D1E1F1E1F2B2A1B2C1";
		
		
		assertEquals(corr, ans);
	
		
	}
}
