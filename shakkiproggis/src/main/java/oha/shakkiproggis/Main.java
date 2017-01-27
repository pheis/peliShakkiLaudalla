/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import  oha.shakkiproggis.Piece;
import  oha.shakkiproggis.Square;

import java.util.stream.Stream;

/**
 *
 * @author pyry
 */
public class Main {
	
	public static void main(String [] args) {
		
		Board board = new Board();
		
		board.piecesToStartingFormation();
		
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
