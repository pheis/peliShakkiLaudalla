/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import oha.shakkiproggis.Square;
import oha.shakkiproggis.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import oha.shakkiproggis.Game;

/**
 * This is the gui for the program.
 * @author pyry
 */
public class ChessGUI extends Application {
	int random = ThreadLocalRandom.current().nextInt(0, 2);	
	Imageload il = new Imageload();
	
	
	// promoChooserin kaa pitää ziigata kun implementoin värin valinnan.
	
	//PawnPromoChooser pc1 = new PawnPromoChooser();
	//PawnPromoChooser pc2 = new PawnPromoChooser();
	
	//MoveValidator mv = new MoveValidator(pc1, pc2);
	
	//AI ai2 = new AI(mv, pc1);
	
	//AI ai = new AI(mv, pc2);
	
	
	public Game game = new Game(false, false);
	
	int startX, startY, endX, endY;
	public boolean doingMove = false;
	public static final int SQUARESIZE = 80;
	public static final int HEIGHT = 8;
	public static final int WIDTH = 8;
	private Group squares = new Group();
	private Group pieces = new Group();
	private ArrayList<ChessPiece> piecesList = new ArrayList<>();
	
	Parent board;
	
	private Parent createContent() {
		
		Pane root = new Pane();
		

		root.setPrefSize(WIDTH * SQUARESIZE, HEIGHT * SQUARESIZE);	

	

		root.getChildren().addAll(squares, pieces);
		
		
		root.setOnMousePressed((MouseEvent event) ->
		{
			if (doingMove == false) {
				startX = (int) Math.floor(event.getSceneX() / SQUARESIZE);
				startY = 7 - (int) Math.floor((event.getSceneY() - root.getLayoutY()) / SQUARESIZE);
				doingMove = true;
			}			
		});		
		root.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (doingMove == true) {
					endX = (int) Math.floor(event.getSceneX() / SQUARESIZE);
					endY = 7 - (int) Math.floor((event.getSceneY() - root.getLayoutY()) / SQUARESIZE);
					int startSq = startX + (8 * startY);
					int endSq = endX + (8 * endY);
					if (0 <= startSq && startSq <= 63 && 0 <= endSq && endSq <= 63) {
						
						Square a = Square.values()[startSq];
						Square b = Square.values()[endSq];
						
						Move move = new Move(a, b);
						
						boolean t = false;
						t = game.move(move);
						
						if (t) updateContent();
						
						gameOver();
						
						if (t && !game.mate() && !game.draw()) {	
							game.aiMove();
							updateContent();
							gameOver();
						}
					}
				}
				doingMove = false;
			}
		});
		
		
		
		
		
		
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				ChessSquare sq = new ChessSquare((i + j) % 2 == 0, i, j);
				game.mv.getMyPieces()
								.keySet()
								.forEach(p ->
								{
									int gX = coordToGuiCoordX(p);
									int gY = coordToGuiCoordY(p);
									ChessPiece piece;
									piece = new ChessPiece(gX, gY, game.mv.getMyPieces().get(p), game.mv.iAmWhite(), il);
									piecesList.add(piece);
									pieces.getChildren().add(piece);
								});

				game.mv.getEnemyPieces()
								.keySet()
								.forEach(p ->
								{		
									int gX = coordToGuiCoordX(p);
									int gY = coordToGuiCoordY(p);
									ChessPiece piece;
									piece = new ChessPiece(gX, gY, game.mv.getEnemyPieces().get(p), !game.mv.iAmWhite(), il);
									piecesList.add(piece);
									pieces.getChildren().add(piece);
								});
				
				squares.getChildren().add(sq);
			}
		}
		return root;
	}
	public void updateContent() {
		piecesList.forEach(p -> this.pieces.getChildren().remove(p));
		piecesList.removeAll(piecesList);
		game.mv.getMyPieces().keySet()
						.forEach(p ->
						{
							int gX = coordToGuiCoordX(p);
							int gY = coordToGuiCoordY(p);
							ChessPiece piece;
							piece = new ChessPiece(gX, gY, game.mv.getMyPieces().get(p), game.mv.iAmWhite(), il);
							piecesList.add(piece);
							pieces.getChildren().add(piece);
						});
				
				
		game.mv.getEnemyPieces().keySet()
						.forEach(p ->
						{
							int gX = coordToGuiCoordX(p);
							int gY = coordToGuiCoordY(p);
							ChessPiece piece;
							piece = new ChessPiece(gX, gY, game.mv.getEnemyPieces().get(p), !game.mv.iAmWhite(), il);
							piecesList.add(piece);
							pieces.getChildren().add(piece);
						});
		
	}
	private int transformGuiCoordToBcoord(double x, double y) {
		int newX = (int) Math.floor(x / SQUARESIZE);
		int newY = 7 -  (int) Math.floor(y / SQUARESIZE);
		return newX + (8 * newY);
	}
	private int coordToGuiCoordX(int bCoord) {
		int x;
		x = bCoord % 8;
		return x;
	}	
	private int coordToGuiCoordY(int bCoord) {
		// FIX THIS
		int y;
		y = 7 - (bCoord / 8);
		return y;
	}
	private void gameOver() {
		boolean mate = this.game.mate();
		boolean draw = this.game.draw();
		if (mate) {
// root.getChildren().add(text);
			final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
           // dialog.initOwner();
            VBox dialogVbox = new VBox(20);
			
			Text t = new Text();
			t.setText("MATE");
			t.setFont(Font.font("Serif", FontWeight.BOLD, 100));
			t.setFill(Color.RED); 
			t.setCache(true);
			
			
            dialogVbox.getChildren().add(t);
            Scene dialogScene = new Scene(dialogVbox, 400, 100);
            dialog.setScene(dialogScene);
            dialog.show();
		}
		if (draw) {
			final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
           // dialog.initOwner();
            VBox dialogVbox = new VBox(20);
			
			Text t = new Text();
			t.setText("DRAW");
			t.setFont(Font.font("Serif", FontWeight.BOLD, 100));
			t.setFill(Color.GREEN); 
			t.setCache(true);
			
			
            dialogVbox.getChildren().add(t);
            Scene dialogScene = new Scene(dialogVbox, 400, 100);
            dialog.setScene(dialogScene);
			dialog.show();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		Scene scene = new Scene(new VBox());
		
		this.board = createContent();
		
		MenuBar menuBar = new MenuBar();
 
        // --- Menu new game
        Menu menuNewGame = new Menu("New Game");
		
		MenuItem humVsAi = new MenuItem("Human vs Computer");
		MenuItem aiVsHum = new MenuItem("Computer vs Human");
		MenuItem humVsHum = new MenuItem("Human vs Human");
		
		humVsAi.setOnAction((ActionEvent t) -> {
			game = new Game(true, true);
			updateContent();
		});
		aiVsHum.setOnAction((ActionEvent t) -> {
			game = new Game(false, true);
			updateContent();
		});
		humVsHum.setOnAction((ActionEvent t) -> {
			game = new Game(false, false);
			updateContent();
		});


		menuNewGame.getItems().addAll(humVsAi, aiVsHum, humVsHum);
		
		// --- Menu PPC 
        Menu menuPPC = new Menu("Pawn Promotion");
		
		MenuItem setQueen = new MenuItem("Queen");
		MenuItem setRook = new MenuItem("rook");
		MenuItem setBishop = new MenuItem("bishop");
		MenuItem setKnight= new MenuItem("knight");
		
		setQueen.setOnAction((ActionEvent t) -> {
			this.game.pptToQueen();
			//updateContent();
		});
		setRook.setOnAction((ActionEvent t) -> {
			this.game.pptToRook();
			//updateContent();
		});
		setBishop.setOnAction((ActionEvent t) -> {
			this.game.pptToBishop();
		});
		setKnight.setOnAction((ActionEvent t) -> {
			this.game.pptToKnight();
		});
 
		menuPPC.getItems().addAll(setQueen, setRook, setBishop, setKnight);

 
        menuBar.getMenus().addAll(menuNewGame, menuPPC);
 
 
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, this.board);


			
		primaryStage.setTitle("Very High Quality Chess Program");	
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

