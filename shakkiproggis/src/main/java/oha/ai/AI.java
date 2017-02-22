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

/**
 *
 * @author pyry
 */
public class AI {
	private final MoveValidator mv;
	private final PawnPromoChooser pcX;
	
	public AI(MoveValidator moveMaster, PawnPromoChooser pcX){
		mv = moveMaster;
		this.pcX = pcX;
	}
	
	/**
	 * Makes a move
	 * @return true if it was possible to move. false when mate or stalemate.
	 */
	
	public boolean move() {
		
		ArrayList<Board> list = mv.listMoves();
		
		int n = list.size();
		
		//System.out.println(n);
		
		//int i = rn.nextInt() % n;
		
		if (!list.isEmpty()) {
			int i = ThreadLocalRandom.current().nextInt(0, n);
			mv.move(list.get(i).lastMove);
			return true;
		} else {

			return false;
		}
	}

	public void fmove() {
		
		ArrayList<Board> list = mv.listMoves();
		
		int n = list.size();
		
		//System.out.println(n);
		
		//int i = rn.nextInt() % n;
		
		int i = ThreadLocalRandom.current().nextInt(0, n);
		
		
		
		
		mv.move(list.get(0).toString());
		
	
	}
}
