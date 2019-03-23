package application;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SelectedView {

	// Receive Info from Piece
	public static void getPieceInfo(Piece selectedPiece, int index) {

		deleteAllPiecesMove();

		int Column = selectedPiece.getPositionCol();
		int Row = selectedPiece.getPositionRow();
		String type = selectedPiece.getPieceIndex();
		int team = selectedPiece.getTeam();

		// Check what type of movement will it take
		switch (type) {

		case ("Pawn"):

			if (team == 0) {

				Creator.piecesWhite[index] = Movement.movePawn(selectedPiece, index, Column, Row, team);

			} else {

				Creator.piecesBlack[index] = Movement.movePawn(selectedPiece, index, Column, Row, team);

			}

			break;

		case ("Tower"):

			if (team == 0) {

				Creator.piecesWhite[index] = Movement.moveTower(selectedPiece, index, Column, Row, team);

			} else {

				Creator.piecesBlack[index] = Movement.moveTower(selectedPiece, index, Column, Row, team);

			}
			break;

		case ("Horse"):

			if (team == 0) {

				Creator.piecesWhite[index] = Movement.moveHorse(selectedPiece, index, Column, Row, team);

			} else {

				Creator.piecesBlack[index] = Movement.moveHorse(selectedPiece, index, Column, Row, team);

			}
			break;

		case ("Bishop"):

			if (team == 0) {

				Creator.piecesWhite[index] = Movement.moveBishop(selectedPiece, index, Column, Row, team);

			} else {

				Creator.piecesBlack[index] = Movement.moveBishop(selectedPiece, index, Column, Row, team);

			}
			break;

		case ("Queen"):

			if (team == 0) {

				Creator.piecesWhite[index] = Movement.moveQueen(selectedPiece, index, Column, Row, team);

			} else {

				Creator.piecesBlack[index] = Movement.moveQueen(selectedPiece, index, Column, Row, team);

			}
			break;

		case ("King"):

			if (team == 0) {

				Creator.piecesWhite[index] = Movement.moveKing(selectedPiece, index, Column, Row, team);

			} else {

				Creator.piecesBlack[index] = Movement.moveKing(selectedPiece, index, Column, Row, team);

			}
			break;
		}
	}

	/*
	 * 
	 * OPERATIONAL FUNCTIONS()
	 * 
	 * 
	 * 
	 */

	// Give all White pieces the ability of moving
	public static void createAllWhitePiecesMove() {

		for (int i = 0; i < 16; i++) {

			int index = i;

			// Sends Info of the piece
			Creator.piecesWhite[i].getSpriteNode()
					.setOnMouseClicked(event -> getPieceInfo(Creator.piecesWhite[index], index));

		}

	}

	// Give all Black pieces the ability of moving
	public static void createAllBlackPiecesMove() {

		for (int i = 0; i < 16; i++) {

			int index = i;

			// Sends Info of the piece
			Creator.piecesBlack[i].getSpriteNode()
					.setOnMouseClicked(event -> getPieceInfo(Creator.piecesBlack[index], index));

		}

	}

	// Remove all pieces the ability of moving
	public static void deleteAllPiecesMove() {

		for (int i = 0; i < 16; i++) {

			Creator.piecesWhite[i].getSpriteNode()
					.setOnMouseClicked(event -> System.out.println("Piece Already Selected"));

			Creator.piecesBlack[i].getSpriteNode()
					.setOnMouseClicked(event -> System.out.println("Piece Already Selected"));

		}

	}

}
