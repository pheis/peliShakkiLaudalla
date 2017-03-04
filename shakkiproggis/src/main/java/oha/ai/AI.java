/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.ai;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import oha.shakkiproggis.Board;
import oha.shakkiproggis.MoveValidator;
import oha.shakkiproggis.PawnPromoChooser;
import oha.shakkiproggis.PromoTarget;

/**
 * This is AI object. does moves on a chess board.
 * @author pyry
 */
public class AI {
	private final MoveValidator mv;
	private final PawnPromoChooser ppc;
	/**
	 * Default constructor. makes new object.
	 * @param moveMaster Movevalidator object. contains the pieces that AI will move.
	 * @param pcX PawnPromotionTarget chooser. Mutable object. Needed for choosing pawn promotion target.
	 */
	public AI(MoveValidator moveMaster, PawnPromoChooser pcX) {
		mv = moveMaster;
		this.ppc = pcX;
	}
	
	/**
	 * Makes a move.
	 * @return true if it was possible to move. false when mate or stalemate.
	 */
	public boolean move() {
		
		ArrayList<Board> list = mv.listMoves();
		
		int n = list.size();
		
		//System.out.println(n);
		
		//int i = rn.nextInt() % n;
		
		this.ppc.set(PromoTarget.QUEEN);
		
		if (!list.isEmpty()) {
			int i = ThreadLocalRandom.current().nextInt(0, n);
			mv.move(list.get(i).lastMove);
			return true;
		} else {

			return false;
		}
	}
	/**
	 * Makes a move. This one always chooses the first move from the list. Deterministic. Handy for testing.
	 */

	public void fmove() {
		
		ArrayList<Board> list = mv.listMoves();
		
		int n = list.size();
		
		//System.out.println(n);
		
		//int i = rn.nextInt() % n;
		
		int i = ThreadLocalRandom.current().nextInt(0, n);
		
		
		
		
		mv.move(list.get(0).toString());
		
	
	}
}
