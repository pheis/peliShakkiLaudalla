/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis.Piece;
import oha.shakkiproggis.pieces.Rook;
import oha.shakkiproggis.pieces.PieceObj;
import oha.shakkiproggis.pieces.Knight;
import oha.shakkiproggis.pieces.King;
import oha.shakkiproggis.pieces.Bishop;
import oha.shakkiproggis.pieces.WhitePawn;
import oha.shakkiproggis.pieces.Queen;
import oha.shakkiproggis.pieces.BlackPawn;
import oha.shakkiproggis.pieces.PieceT;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import oha.shakkiproggis.Board;
import oha.shakkiproggis.Board;
import oha.shakkiproggis.Move;
import oha.shakkiproggis.MoveValidator;
import oha.shakkiproggis.PawnPromoChooser;
import oha.shakkiproggis.PieceGroup;
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
public class RookSlidingAttackTest {
	
	public RookSlidingAttackTest() {
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
		
		EnumMap<PieceT, PieceObj> pTpMap1 = new EnumMap<>(PieceT.class);
		pTpMap1.put(PieceT.KING, new King());
		pTpMap1.put(PieceT.QUEEN, new Queen());
		pTpMap1.put(PieceT.ROOK, new Rook());
		pTpMap1.put(PieceT.BISHOP, new Bishop());
		pTpMap1.put(PieceT.KNIGHT, new Knight());
	    pTpMap1.put(PieceT.WPAWN, new WhitePawn());
	
		EnumMap<PieceT, PieceObj> pTpMap2 = new EnumMap<>(PieceT.class);
		pTpMap2.put(PieceT.KING, new King());
		pTpMap2.put(PieceT.QUEEN, new Queen());
		pTpMap2.put(PieceT.ROOK, new Rook());
		pTpMap2.put(PieceT.BISHOP, new Bishop());
		pTpMap2.put(PieceT.KNIGHT, new Knight());
		pTpMap2.put(PieceT.BPAWN, new BlackPawn());
		
		EnumMap<Square, PieceT> sqPtMap1 = new EnumMap<>(Square.class);
		sqPtMap1.put(Square.B7, PieceT.KING);
		sqPtMap1.put(Square.D5, PieceT.ROOK);
		
		
		EnumMap<Square, PieceT> sqPtMap2 = new EnumMap<>(Square.class);
		sqPtMap2.put(Square.E8, PieceT.KING);
		sqPtMap2.put(Square.B5, PieceT.BPAWN);
		sqPtMap2.put(Square.F5, PieceT.BPAWN);
		sqPtMap2.put(Square.D3, PieceT.BPAWN);
	

		 		
		PawnPromoChooser pc1 = new PawnPromoChooser();
		PawnPromoChooser pc2 = new PawnPromoChooser();
		
		boolean[] castling = new boolean[]{false,false};
		Optional<Square> epp = Optional.empty();	
		
		PieceGroup pg1 = new PieceGroup(sqPtMap1, pTpMap1, Square.B7, castling, epp, pc1);
		PieceGroup pg2 = new PieceGroup(sqPtMap2, pTpMap2, Square.E8, castling, epp, pc2);
		
		Board board = new Board(pg1, pg2, true, new Move(Square.A1, Square.A2));
		
		MoveValidator mv = new MoveValidator(board);
		
		TreeSet<String> posMoves = new TreeSet<>();
		mv.listMoves().stream().map(x -> x.toString())
					.filter(s -> s.startsWith("D5"))
					.forEach(s -> posMoves.add(s));
		
		
		TreeSet<String> corr = new TreeSet<>();
		corr.add("D5B5");
		corr.add("D5C5");
		corr.add("D5D3");
		corr.add("D5D4");
		corr.add("D5D6");
		corr.add("D5D7");
		corr.add("D5D8");
		corr.add("D5E5");
		corr.add("D5F5");
		
		assertTrue(corr.equals(posMoves));


	}
}
