package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Creator {

	// Pieces of both Teams
	public static Piece[] piecesBlack = new Piece[16];
	public static Piece[] piecesWhite = new Piece[16];

	/*
	 * 
	 * Teams:
	 * 
	 * White=0; Black=1;
	 * 
	 */

	// Assign Each Piece a Name, Sprite and the Team their are on
	public static void allocator() {

		// Minor to 8 because it'll be the Pawns
		for (int i = 0; i < 8; i++) {

			piecesBlack[i] = new Pawn("Pawn", spriteAllocator("PawnB"), 1);
			piecesBlack[i].setHorizontalMovement(0);
			piecesBlack[i].setVerticalMovement(2);
			piecesBlack[i].setTitledMovement(1);

			piecesWhite[i] = new Pawn("Pawn", spriteAllocator("PawnW"), 0);
			piecesWhite[i].setHorizontalMovement(0);
			piecesWhite[i].setVerticalMovement(2);
			piecesWhite[i].setTitledMovement(1);

		}

		for (int i = 8; i < 16; i++) {

			if (i == 8 || i == 15) {

				piecesBlack[i] = new Tower("Tower", spriteAllocator("TowerB"), 1);
				piecesBlack[i].setHorizontalMovement(8);
				piecesBlack[i].setVerticalMovement(8);
				piecesBlack[i].setTitledMovement(0);

				piecesWhite[i] = new Tower("Tower", spriteAllocator("TowerW"), 0);
				piecesWhite[i].setHorizontalMovement(8);
				piecesWhite[i].setVerticalMovement(8);
				piecesWhite[i].setTitledMovement(0);

			}

			if (i == 9 || i == 14) {

				piecesBlack[i] = new Horse("Horse", spriteAllocator("HorseB"), 1);

				piecesWhite[i] = new Horse("Horse", spriteAllocator("HorseW"), 0);

			}

			if (i == 10 || i == 13) {

				piecesBlack[i] = new Bishop("Bishop", spriteAllocator("BishopB"), 1);
				piecesBlack[i].setHorizontalMovement(0);
				piecesBlack[i].setVerticalMovement(0);
				piecesBlack[i].setTitledMovement(8);

				piecesWhite[i] = new Bishop("Bishop", spriteAllocator("BishopW"), 0);
				piecesWhite[i].setHorizontalMovement(0);
				piecesWhite[i].setVerticalMovement(0);
				piecesWhite[i].setTitledMovement(8);

			}

		}

		// Create the Queens
		piecesBlack[12] = new Queen("Queen", spriteAllocator("QueenB"), 1);
		piecesBlack[12].setHorizontalMovement(8);
		piecesBlack[12].setVerticalMovement(8);
		piecesBlack[12].setTitledMovement(8);

		piecesWhite[12] = new Queen("Queen", spriteAllocator("QueenW"), 0);
		piecesWhite[12].setHorizontalMovement(8);
		piecesWhite[12].setVerticalMovement(8);
		piecesWhite[12].setTitledMovement(8);

		// Create the Kings
		piecesBlack[11] = new King("King", spriteAllocator("KingB"), 1);
		piecesBlack[11].setHorizontalMovement(1);
		piecesBlack[11].setVerticalMovement(1);
		piecesBlack[11].setTitledMovement(2);

		piecesWhite[11] = new King("King", spriteAllocator("KingW"), 0);
		piecesWhite[11].setHorizontalMovement(1);
		piecesWhite[11].setVerticalMovement(1);
		piecesWhite[11].setTitledMovement(2);

	}

	// Give Each Created Piece an Sprite
	private static Image spriteAllocator(String type) {

		switch (type) {

		// Pawn Sprite
		case ("PawnB"):

			Image blackPawn = new Image("file:Sprites/Pieces/Black/BlackPawn.png");

			return blackPawn;

		case ("PawnW"):

			Image whitePawn = new Image("file:Sprites/Pieces/White/WhitePawn.png");

			return whitePawn;

		// Tower Sprite
		case ("TowerB"):

			Image blackTower = new Image("file:Sprites/Pieces/Black/BlackTower.png");

			return blackTower;

		case ("TowerW"):

			Image whiteTower = new Image("file:Sprites/Pieces/White/WhiteTower.png");

			return whiteTower;

		// Horse Sprite
		case ("HorseB"):

			Image blackHorse = new Image("file:Sprites/Pieces/Black/BlackHorse.png");

			return blackHorse;

		case ("HorseW"):

			Image whiteHorse = new Image("file:Sprites/Pieces/White/WhiteHorse.png");

			return whiteHorse;

		// Bishop Sprite
		case ("BishopB"):

			Image blackBishop = new Image("file:Sprites/Pieces/Black/BlackBishop.png");

			return blackBishop;

		case ("BishopW"):

			Image whiteBishop = new Image("file:Sprites/Pieces/White/WhiteBishop.png");

			return whiteBishop;

		// Queen Sprite
		case ("QueenB"):

			Image blackQueen = new Image("file:Sprites/Pieces/Black/BlackQueen.png");

			return blackQueen;

		case ("QueenW"):

			Image whiteQueen = new Image("file:Sprites/Pieces/White/WhiteQueen.png");

			return whiteQueen;

		// Kings
		case ("KingB"):

			Image blackKing = new Image("file:Sprites/Pieces/Black/BlackKing.png");

			return blackKing;

		case ("KingW"):

			Image whiteKing = new Image("file:Sprites/Pieces/White/WhiteKing.png");

			return whiteKing;

		}

		return null;

	}

	public static void reset() {

		for (int i = 0; i < piecesWhite.length; i++) {

			Main.board.getChildren().remove(piecesWhite[i].getSpriteNode());
			Main.board.getChildren().remove(piecesBlack[i].getSpriteNode());
		}

		allocator();
		SelectedView.deleteAllPiecesMove();
		SelectedView.createAllWhitePiecesMove();

		// Fill the Board
		for (int i = 0; i < 8; i++) {

			int j = i + 8;

			// Paint the White Pieces
			Main.board.add(Creator.piecesWhite[j].getSpriteNode(), i, 0);
			Main.board.add(Creator.piecesWhite[i].getSpriteNode(), i, 1);

			// Set the Position of Each Piece
			Creator.piecesWhite[j].setPositionCol(GridPane.getColumnIndex(Creator.piecesWhite[j].getSpriteNode()));
			Creator.piecesWhite[j].setPositionRow(GridPane.getRowIndex(Creator.piecesWhite[j].getSpriteNode()));

			Creator.piecesWhite[i].setPositionCol(GridPane.getColumnIndex(Creator.piecesWhite[i].getSpriteNode()));
			Creator.piecesWhite[i].setPositionRow(GridPane.getRowIndex(Creator.piecesWhite[i].getSpriteNode()));

			// Paint the Black Pieces
			Main.board.add(Creator.piecesBlack[j].getSpriteNode(), i, 7);
			Main.board.add(Creator.piecesBlack[i].getSpriteNode(), i, 6);

			// Set the Position of Each Piece
			Creator.piecesBlack[j].setPositionCol(GridPane.getColumnIndex(Creator.piecesBlack[j].getSpriteNode()));
			Creator.piecesBlack[j].setPositionRow(GridPane.getRowIndex(Creator.piecesBlack[j].getSpriteNode()));

			Creator.piecesBlack[i].setPositionCol(GridPane.getColumnIndex(Creator.piecesBlack[i].getSpriteNode()));
			Creator.piecesBlack[i].setPositionRow(GridPane.getRowIndex(Creator.piecesBlack[i].getSpriteNode()));

		}

	}
}
