/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Optional;
import java.util.TreeSet;
import oha.gui.ChessGUI;
import javafx.application.Application;
import oha.ai.AI;
import oha.shakkiproggis.Piece.Bishop;
import oha.shakkiproggis.Piece.BlackPawn;
import oha.shakkiproggis.Piece.King;
import oha.shakkiproggis.Piece.Knight;
import oha.shakkiproggis.Piece.PieceObj;
import oha.shakkiproggis.Piece.PieceT;
import oha.shakkiproggis.Piece.Queen;
import oha.shakkiproggis.Piece.Rook;
import oha.shakkiproggis.Piece.WhitePawn;

/**
 *
 * @author pyry
 */
public class Main {
	
	public static void main(String [] args) {
		
	

		//System.out.println(Square.rankPlus(Square.A2));

		
		Application.launch(ChessGUI.class, args);
		
	
		
		//TextUI ui = new TextUI();
		
		
	}
	
}
