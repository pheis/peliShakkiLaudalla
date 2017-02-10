/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.ai;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import oha.shakkiproggis.Board;
import oha.shakkiproggis.MoveValidator;

/**
 *
 * @author pyry
 */
public class AI {
	public MoveValidator mv;
	
	public AI(MoveValidator zorro) {
		mv = zorro;
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
			mv.movePiece(list.get(i).toString());
			return true;
		} else {
			System.out.println("GAME HAS ENDED");
			System.out.println("YOU WON!");
			return false;
		}
	}

	public void fmove() {
		
		ArrayList<Board> list = mv.listMoves();
		
		int n = list.size();
		
		//System.out.println(n);
		
		//int i = rn.nextInt() % n;
		
		int i = ThreadLocalRandom.current().nextInt(0, n);
		
		
		
		
		mv.movePiece(list.get(0).toString());
		
	
	}
}
