/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import oha.ai.AI;
import java.util.*;

import java.util.HashMap;
import oha.shakkiproggis.MoveValidator;
import oha.shakkiproggis.PawnPromoChooser;

/**
 * Text ui. ugh.
 * @author pyry
 */
public class TextUI {
	//private Board b1;
	public MoveValidator mv;
	
	public PawnPromoChooser pc1;
	public PawnPromoChooser pc2;
	//private char[] ui;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";	
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	/**
	 * constructor. yeps.
	 */
	public TextUI() {
		mv = new MoveValidator(pc1, pc2);
	}
	
	
	/**
	 * Prints some text.
	 */
	public void printUI() {
		
		HashMap<Integer, String> myPieces = mv.getMyPieceStrings();		
		HashMap<Integer, String> enemyPieces = mv.getEnemyPieceStrings();	
		for (int j = 0; j < 8; j++) {
			for (int i = 56 - (8 * j); i <= 63 - (8 * j); i++) {		
				if (myPieces.containsKey(i)) {
					System.out.print(ANSI_BLUE + " " + myPieces.get(i).substring(0, 2) + " " + ANSI_RESET);
				} else if (enemyPieces.containsKey(i)) {
					System.out.print(ANSI_RED + " " + enemyPieces.get(i).substring(0, 2) + " " + ANSI_RESET);
				} else {
					System.out.print(" .. ");
				}
				//System.out.print(" "+this.ui[i]+" ");
			}
			System.out.printf("\n\n");
		}	
	}
	/**
	 * lets play. 
	 */
	public void play() {
		Scanner sc = new Scanner(System.in);
		AI ai = new AI(mv, pc2);
		String input;
		boolean t;
		ai.move();
		while (true) {
			printUI();
			System.out.println("Anna liike");
			input = sc.nextLine();
			t = mv.move(input);
			if (t) {
				ai.move();
			}
			System.out.println(t);	
		}
	}
	

	
	
}
