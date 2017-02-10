/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import oha.ai.AI;
import oha.gui.ChessGUI;
import  oha.shakkiproggis.Piece;

import java.util.*;

import java.util.stream.Stream;
import javafx.application.Application;

/**
 *
 * @author pyry
 */
public class Main {
	
	public static void main(String [] args) {
		
	/*	Board board = new Board();
		
		board.piecesToStartingFormation();
		
		//System.out.println(board.whitePawnMoves(Square.D2));
		
		//System.out.println(board.movesOfThisPiece(Square.B1));
		
		//board.printPieces();
		
	//	board.mySquares()
	///		.forEach(z -> System.out.println(z));
				
				
			//.forEach(x -> System.out.println("" + y + "" + x));
		
		//ArrayList<Board> bs = board.listPossibleMoves();
		
		//bs.forEach(b -> System.out.println(b));
		
		
		Board b2 = board.makeAmove(Square.A2, Square.A4).get();
		Board b3 = b2.makeAmove(Square.D7, Square.D5).get();
		Board b4 = b3.makeAmove(Square.B2, Square.B4).get();
		Board b5 = b4.makeAmove(Square.D5, Square.D4).get();
		Board b6 = b5.makeAmove(Square.E2, Square.E4).get();
		
		System.out.println("");
		System.out.println("EN pass");
		//System.out.println(b4.enpassEmerges(Square.D7, Square.D5));
		System.out.println(b5.enpassantablePawn.isPresent());
		System.out.println("");
		//b5.movesOfThisPiece(Square.E5)
		//	.forEach(x -> System.out.println(x));
		
		
		//MoveValidator zorbas = new MoveValidator
		
		MoveValidator mv = new MoveValidator();
		
		AI ai = new AI(mv);
		boolean t;
			
		mv.movePiece("E2E3");
		ai.fmove();
		mv.movePiece("F1E2");
		ai.fmove();
		mv.movePiece("G1F3");
		ai.fmove();
		t = mv.movePiece("E1G1");
		System.out.println(t);
		
		
		

		
		
		
		
EnumMap<Square, Piece> mp = new EnumMap<>(Square.class);
		mp.put(Square.E1, Piece.KING);
		mp.put(Square.A1, Piece.QUEEN);
		
		EnumMap<Square, Piece> ep = new EnumMap<>(Square.class);
		
		ep.put(Square.F8, Piece.KING);
		ep.put(Square.A6, Piece.WPAWN);
		
		
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
		System.out.println(ans);
		
		
		*/
		
		
		Application.launch(ChessGUI.class, args);
		
		//TextUI ui = new TextUI();
		
		//ui.printUI();
		
		//ui.play();
		
		//chessGUI gui = new chessGUI();
		
		//chessGUI.launch(args);
		
		//ArrayList<Board> bs = b5.listPossibleMoves();
		//bs.forEach(b -> System.out.println(b));
			
			
		//Board b6 = b5.makeAmove(Square.E5, Square.D4).get();
		
		
		//System.out.println(board.possible());
		
		//Board b2 = board.makeAmove(Square.A2, Square.A4);
		
		
		//b2.mySquares().forEach(x -> System.out.println(x));
		
		//System.out.println(b2.enemyPieces);
		//System.out.println(b2.myPieces);
		
		//System.out.println(b2.possible());
		
 		//bs.forEach(x -> System.out.println(x.possible()));
		
		
		
		//boards.forEach(x -> x.printPieces());
		
		
		//Stream<Square> mySquares = board.mySquares();
		//mySquares.forEach(x->System.out.println(""+x));
		
			
		//Stream<Square> pieces = board.allSquaresWithAPiece();
		//pieces.forEach(x->System.out.println(""+x));
		
		//System.out.println(board.knightAttacks(Square.A1));
		
		//System.out.println(board.bishopAttacks(Square.A1));
	
		/*Lauta lauta = new Lauta();
		
		lauta.asetaNappulatAloituspaikoille();
		//lauta.tulostaLauta();

		lauta.tulostaOmat();
		//
		
		lauta.tulostaNappulat();
		//
		MoveValidator siirrot = new MoveValidator(lauta);
		
		
		lauta.printtaaHepo(24);
		lauta.printtaaHepo(63);

		lauta.pManha(24,18);
			

		lauta.psamaR(24,17);
		lauta.psamaR(24,28);
		lauta.psamaL(24,17);
		lauta.psamaL(24,0);*/
	
	}
	
}
