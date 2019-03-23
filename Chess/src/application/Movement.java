package application;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Movement {

	// Create the Image Nodes for the possible Moves
	static Image selection = new Image("file:Sprites/SelectedBlue.png");
	static ImageView[] posibleMoveVertical = new ImageView[64];
	static ImageView[] posibleMoveHorizontal = new ImageView[64];
	static ImageView[] posibleMoveHorse = new ImageView[16];
	static ImageView[] posibleMoveBishop = new ImageView[64];

	// Lets move the Pawn
	public static Piece movePawn(Piece selectedPiece, int index, int column, int row, int team) {

		SelectedView.deleteAllPiecesMove();
		// Print the Possible Moves
		printPosibleMovesPawn(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team);

		// Change its vertical Movement to one
		selectedPiece.setVerticalMovement(1);

		// Return the Piece with the Changes
		return selectedPiece;
	}

	public static Piece moveTower(Piece selectedPiece, int index, int column, int row, int team) {

		SelectedView.deleteAllPiecesMove();

		// Print the Possible Moves
		printPosibleMovesTower(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team, false, false);

		// Return the Piece with the Changes
		return selectedPiece;
	}

	public static Piece moveHorse(Piece selectedPiece, int index, int column, int row, int team) {

		SelectedView.deleteAllPiecesMove();

		// Print the Possible Moves
		printPosibleMovesHorse(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team);

		// Return the Piece with the Changes
		return selectedPiece;
	}

	public static Piece moveBishop(Piece selectedPiece, int index, int column, int row, int team) {

		SelectedView.deleteAllPiecesMove();

		// Print the Possible Moves
		printPosibleMovesBishop(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team, false);

		// Return the Piece with the Changes
		return selectedPiece;
	}

	public static Piece moveQueen(Piece selectedPiece, int index, int column, int row, int team) {
		SelectedView.deleteAllPiecesMove();

		// Print the Possible Moves
		printPosibleMovesBishop(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team, true);
		// Print the Possible Moves
		printPosibleMovesTower(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team, false, true);

		// Return the Piece with the Changes
		return selectedPiece;
	}

	public static Piece moveKing(Piece selectedPiece, int index, int column, int row, int team) {
		SelectedView.deleteAllPiecesMove();

		// Print the Possible Moves
		printPosibleMovesTower(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team, true, false);
		// Print the Possible Moves
		printPosibleMovesBishop(column, row, selectedPiece.getVerticalMovement(), selectedPiece.getHorizontalMovement(),
				selectedPiece.getTitledMovement(), index, team, false);

		// Return the Piece with the Changes
		return selectedPiece;
	}

	// Print the Possible Moves
	private static void printPosibleMovesPawn(int realPosColumn, int realPosRow, int verticalMove, int horizontalMove,
			int tiltedMove, int index, int team) {

		// Create the nodes
		for (int i = 0; i < posibleMoveVertical.length; i++) {

			posibleMoveVertical[i] = new ImageView(selection);
		}

		// If the white team is up
		if (team == 0) {

			// Set the Position of the new Column
			int newPosColumn = realPosColumn + horizontalMove;

			// Print Each position between the piece and the number of cells he can walk
			for (int j = 1; j <= verticalMove; j++) {

				// Keep how many cell will it walk
				int newPosRow = realPosRow + j;

				// Add each possible cell (Image node)
				Main.board.add(posibleMoveVertical[j], newPosColumn, realPosRow + j);

				// Check if you can it someone (you sum the VerticalMove to not overwrite the
				// positions of the "possible move" sprites)
				// And you Sum one to the real row as well
				checkForEatingPawn(newPosColumn, realPosRow + 1, j + verticalMove, team, tiltedMove, index);

				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardVertical(newPosColumn, newPosRow, j, team)) {

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going
					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();
					break;
				}

				// When CLicked it will call the MovePieces Function
				posibleMoveVertical[j].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRow, 0,
						posibleMoveVertical.length, "Pawn", false));

			}

			// If the Black team is up
		} else {

			// Set the Position of the new Column
			int newPosColumn = realPosColumn + horizontalMove;

			// Print Each position between the piece and the number of cells he can walk
			for (int j = 1; j <= verticalMove; j++) {

				// Keep how many cell will it walk
				int newPosRow = realPosRow - j;

				// Add each possible cell (Image node)
				Main.board.add(posibleMoveVertical[j], newPosColumn, newPosRow);
				SelectedView.deleteAllPiecesMove();
				// Check if you can it someone (you sum the VerticalMove to not overwrite the
				// positions of the "possible move" sprites)
				// And you Subtract one to the real row as well
				checkForEatingPawn(newPosColumn, realPosRow - 1, j + verticalMove, team, tiltedMove, index);

				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardVertical(newPosColumn, newPosRow, j, team)) {

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going
					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();
					break;
				}

				// When CLicked it will call the MovePieces Function
				posibleMoveVertical[j].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRow, 1,
						posibleMoveVertical.length, "Pawn", false));

			}

		}

	}

	// Print the Possible Moves
	private static void printPosibleMovesTower(int realPosColumn, int realPosRow, int verticalMove, int horizontalMove,
			int tiltedMove, int index, int team, boolean isKing, boolean isQueen) {

		// Create the nodes Vertical
		for (int i = 0; i < posibleMoveVertical.length; i++) {

			posibleMoveVertical[i] = new ImageView(selection);
		}

		// Create the nodes Horizontal
		for (int i = 0; i < posibleMoveHorizontal.length; i++) {

			posibleMoveHorizontal[i] = new ImageView(selection);
		}

		// If the white team is up
		if (team == 0) {

			// ----TOWER VERTICAL-------//

			// Vertical Front
			// Print Each position between the piece and the number of cells he can walk
			for (int j = 1; j <= verticalMove; j++) {

				// Keep how many cell will it walk
				int newPosRow = realPosRow + j;

				// Add each possible cell (Image node)
				Main.board.add(posibleMoveVertical[j], realPosColumn, newPosRow);
				SelectedView.deleteAllPiecesMove();
				// When CLicked it will call the MovePieces Function
				posibleMoveVertical[j].setOnMouseClicked(event -> movePieces(index, realPosColumn, newPosRow, 0,
						posibleMoveVertical.length, "Tower", isQueen));

	

				if (isKing) {

					System.out.println("Break");
					checkBoardVertical(realPosColumn, realPosRow + 1, j, team);
					checkForEatingTower(realPosColumn, realPosRow, j, team, index, false, false);
					break;
				}

				checkForEatingTower(realPosColumn, realPosRow, j + 1, team, index, false, isQueen);
				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardVertical(realPosColumn, newPosRow, j, team)) {

					// This means it don't have any possible moves
					if (j > 1) {
						// Give back the ability of moving
						SelectedView.deleteAllPiecesMove();
					}

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going

					break;
				}

				checkForEatingTower(realPosColumn, newPosRow, j + 1, team, index, false, isQueen);

			}

			// Vertical Backwards

			// This is the index of the array
			int dontOverloadV = 16;

			int sumSaverV = 1;
			// Subtract one from current position until you get to zero
			for (int i = realPosRow; i > 0; i--) {

				int newPosRowNegative = realPosRow - sumSaverV;
				// Add Possible Moves
				Main.board.add(posibleMoveVertical[dontOverloadV], realPosColumn, newPosRowNegative);
				// Add clickable function
				posibleMoveVertical[dontOverloadV].setOnMouseClicked(event -> movePieces(index, realPosColumn,
						newPosRowNegative, 0, posibleMoveHorizontal.length, "Tower", isQueen));
				dontOverloadV++;
				sumSaverV++;

				if (isKing) {

					System.out.println("Not Working");
					if (checkBoardVertical(realPosColumn, realPosRow - 1, 16, team)) {

						checkForEatingTower(realPosColumn, realPosRow, dontOverloadV, team, index, false, isQueen);

					}

					break;
				}

				// Delete the first One if there큦 something in front
				if (checkBoardVertical(realPosColumn, realPosRow - 1, 16, team)) {

					// Break the cycle, this means it wont be able to move
					System.out.println("No moves in Vertical Backwards");
					checkForEatingTower(realPosColumn, realPosRow, dontOverloadV + 1, team, index, false, isQueen);

					break;

				}
				// Check if your not putting a sprite into a Row you can't go
				if (checkBoardVertical(realPosColumn, newPosRowNegative - 1, dontOverloadV, team)) {

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going
					checkForEatingTower(realPosColumn, newPosRowNegative, dontOverloadV + 1, team, index, false,
							isQueen);
					break;
				}

			}

			// ----TOWER HORIZONTAL-------//
			// Print Each position between the piece and the number of cells he can walk

			// Horizontal East
			for (int j = 1; j <= horizontalMove; j++) {

				// Keep how many cell will it walk
				int newPosColumn = realPosColumn + j;

				// Add each possible cell (Image node)

				Main.board.add(posibleMoveHorizontal[j], newPosColumn, realPosRow);
				// When CLicked it will call the MovePieces Function
				posibleMoveHorizontal[j].setOnMouseClicked(event -> movePieces(index, newPosColumn, realPosRow, 0,
						posibleMoveHorizontal.length, "Tower", isQueen));

				SelectedView.deleteAllPiecesMove();

				checkForEatingTower(realPosColumn, realPosRow, j + 1, team, index, true, isQueen);

				if (isKing) {

					System.out.println("Break");
					checkBoardHorizontal(newPosColumn, realPosRow, j, team);
					break;
				}
				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardHorizontal(newPosColumn, realPosRow, j, team)) {

					// This means it don't have any possible moves
					if (j > 1) {

						SelectedView.deleteAllPiecesMove();
					}

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going

					break;
				}
				checkForEatingTower(newPosColumn, realPosRow, j + 1, team, index, true, isQueen);

			}

			// Horizontal West

			// This is the index of the array
			int dontOverload = 16;

			int sumSaver = 1;
			// Subtract one from current position until you get to zero
			for (int i = realPosColumn; i > 0; i--) {

				int newPosColumnNegative = realPosColumn - sumSaver;
				// Add Possible Moves
				Main.board.add(posibleMoveHorizontal[dontOverload], newPosColumnNegative, realPosRow);
				// Add clickable function
				posibleMoveHorizontal[dontOverload].setOnMouseClicked(event -> movePieces(index, newPosColumnNegative,
						realPosRow, 0, posibleMoveHorizontal.length, "Tower", isQueen));
				dontOverload++;
				sumSaver++;

				// Delete the first One if there큦 something in front
				if (checkBoardHorizontal(realPosColumn - 1, realPosRow, 16, team)) {

					checkForEatingTower(realPosColumn, realPosRow, dontOverload + 1, team, index, true, isQueen);
					// Break the cycle, this means it wont be able to move
					System.out.println("No moves in Horizontal West");
					break;

				}

				if (isKing) {

					System.out.println("Reaches?");
					checkBoardHorizontal(newPosColumnNegative - 1, realPosRow, dontOverload, team);
					break;
				}

				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardHorizontal(newPosColumnNegative - 1, realPosRow, dontOverload, team)) {

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going
					checkForEatingTower(newPosColumnNegative, realPosRow, dontOverload + 1, team, index, true, isQueen);
					break;
				}

				// checkForEatingTower(newPosColumnNegative-1, realPosRow, dontOverload+1, team,
				// index, true );

				// When CLicked it will call the MovePieces Function
				// posibleMoveHorizontal[dontOverload].setOnMouseClicked(event ->
				// movePieces(index, realPosColumn-1, realPosRow, 0,
				// posibleMoveHorizontal.length));
				// posibleMoveHorizontal[dontOverload].setOnMouseClicked(event ->
				// movePieces(index, newPosColumnNegative-1, realPosRow, 0,
				// posibleMoveHorizontal.length));
			}

			// If the Black team is up
		} else {

			// ----TOWER VERTICAL-------//
			// Print Each position between the piece and the number of cells he can walk
			for (int j = 1; j <= verticalMove; j++) {

				// Keep how many cell will it walk
				int newPosRow = realPosRow - j;

				if (newPosRow == -1) {
					break;
				}
				// Add each possible cell (Image node)
				Main.board.add(posibleMoveVertical[j], realPosColumn, newPosRow);

				// When CLicked it will call the MovePieces Function
				posibleMoveVertical[j].setOnMouseClicked(event -> movePieces(index, realPosColumn, newPosRow, 1,
						posibleMoveVertical.length, "Tower", isQueen));
				SelectedView.deleteAllPiecesMove();

				if (isKing) {

					System.out.println("Break");
					if (checkBoardVertical(realPosColumn, realPosRow - 1, j, team)) {

						checkForEatingTower(realPosColumn, realPosRow, j, team, index, false, isQueen);

					}

					break;
				}

				//checkForEatingTower(realPosColumn, realPosRow, j + 1, team, index, false, isQueen);
				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardVertical(realPosColumn, newPosRow, j, team)) {

					// This means it don't have any possible moves
					if (j > 1) {

						SelectedView.deleteAllPiecesMove();
					}

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going

					break;
				}

				checkForEatingTower(realPosColumn, newPosRow, j + 1, team, index, false, isQueen);

			}

			// Vertical Backwards
			// This is the index of the array
			int dontOverloadV = 16;

			int sumSaverV = 1;
			// Subtract one from current position until you get to zero
			for (int i = realPosRow; i > -8; i--) {

				int newPosRowNegative = realPosRow + sumSaverV;
				// Add Possible Moves
				Main.board.add(posibleMoveVertical[dontOverloadV], realPosColumn, newPosRowNegative);
				// Add clickable function
				posibleMoveVertical[dontOverloadV].setOnMouseClicked(event -> movePieces(index, realPosColumn,
						newPosRowNegative, 1, posibleMoveHorizontal.length, "Tower", isQueen));
				dontOverloadV++;
				sumSaverV++;

				if (isKing) {

					if (checkBoardVertical(realPosColumn, realPosRow + 1, 16, team)) {
						System.out.println("Not Working");
						checkForEatingTower(realPosColumn, realPosRow, dontOverloadV, team, index, false, isQueen);

					}
					break;

				}

				// Delete the first One if there큦 something in front
				if (checkBoardVertical(realPosColumn, realPosRow + 1, 16, team)) {

					checkForEatingTower(realPosColumn, realPosRow, dontOverloadV + 1, team, index, false, isQueen);
					// Break the cycle, this means it wont be able to move
					System.out.println("No moves in Vertical Backwards");
					break;

				}
				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardVertical(realPosColumn, newPosRowNegative + 1, dontOverloadV, team)) {

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going
					checkForEatingTower(realPosColumn, newPosRowNegative, dontOverloadV + 1, team, index, false,
							isQueen);
					break;
				}

			}

			// ----TOWER HORIZONTAL-------//

			// Horizontal East
			// Print Each position between the piece and the number of cells he can walk
			for (int j = 1; j <= horizontalMove; j++) {

				// Keep how many cell will it walk
				int newPosColumn = realPosColumn + j;

				// Add each possible cell (Image node)
				Main.board.add(posibleMoveHorizontal[j], newPosColumn, realPosRow);
				SelectedView.deleteAllPiecesMove();

				// When CLicked it will call the MovePieces Function
				posibleMoveHorizontal[j].setOnMouseClicked(event -> movePieces(index, newPosColumn, realPosRow, 1,
						posibleMoveHorizontal.length, "Tower", isQueen));

				checkForEatingTower(realPosColumn, realPosRow, j + 1, team, index, true, isQueen);
				if (isKing) {

					System.out.println("Break");
					checkBoardHorizontal(newPosColumn, realPosRow, j, team);
					break;
				}

				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardHorizontal(newPosColumn, realPosRow, j, team)) {

					// This means it don't have any possible moves
					if (j > 1) {

						SelectedView.deleteAllPiecesMove();
					}

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going

					break;
				}

				checkForEatingTower(newPosColumn, realPosRow, j + 1, team, index, true, isQueen);

			}

			// Horizontal West

			// This is the index of the array
			int dontOverload = 16;

			int sumSaver = 1;
			// Subtract one from current position until you get to zero
			for (int i = realPosColumn; i > 0; i--) {

				int newPosColumnNegative = realPosColumn - sumSaver;
				// Add Possible Moves
				Main.board.add(posibleMoveHorizontal[dontOverload], newPosColumnNegative, realPosRow);
				// Add clickable function
				posibleMoveHorizontal[dontOverload].setOnMouseClicked(event -> movePieces(index, newPosColumnNegative,
						realPosRow, 1, posibleMoveHorizontal.length, "Tower", isQueen));
				dontOverload++;
				sumSaver++;

				// Delete the first One if there큦 something in front
				if (checkBoardHorizontal(realPosColumn - 1, realPosRow, 16, team)) {

					checkForEatingTower(realPosColumn, realPosRow, dontOverload + 1, team, index, true, isQueen);
					// Break the cycle, this means it wont be able to move
					System.out.println("No moves in Horizontal West");
					break;

				}

				if (isKing) {

					System.out.println("Reaches?");
					checkBoardHorizontal(newPosColumnNegative - 1, realPosRow, dontOverload, team);
					break;
				}

				// Check if your not putting a sprite into a Row you can't go as a Pawn
				if (checkBoardHorizontal(newPosColumnNegative - 1, realPosRow, dontOverload, team)) {

					// If the first Cell in front of the piece is blocked it doesn't make sense to
					// keep going
					checkForEatingTower(newPosColumnNegative, realPosRow, dontOverload + 1, team, index, true, isQueen);
					break;
				}

			}

		}

	}

	private static void printPosibleMovesHorse(int realPosColumn, int realPosRow, int verticalMove, int horizontalMove,
			int tiltedMove, int index, int team) {

		// Create the nodes Horizontal
		for (int i = 0; i < posibleMoveHorse.length; i++) {

			posibleMoveHorse[i] = new ImageView(selection);
		}

		// ------------TEAM WHITE---------
		if (team == 0) {

			// North Options
			if (realPosRow > 1) {

				int newPosRow = realPosRow - 2;
				int newPosColumnWest = realPosColumn - 1;
				int newPosColumnEast = realPosColumn + 1;
				// Hardcode sorry
				SelectedView.deleteAllPiecesMove();
				if (realPosColumn > 0) {

					Main.board.add(posibleMoveHorse[0], newPosColumnWest, newPosRow);
					posibleMoveHorse[0].setOnMouseClicked(event -> movePieces(index, newPosColumnWest, newPosRow, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumnWest, newPosRow, 0, team)) {

						checkForEatingHorses(newPosColumnWest, newPosRow, 0, team, index);

					}

				}

				Main.board.add(posibleMoveHorse[1], newPosColumnEast, newPosRow);
				posibleMoveHorse[1].setOnMouseClicked(event -> movePieces(index, newPosColumnEast, newPosRow, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumnEast, newPosRow, 1, team)) {

					checkForEatingHorses(newPosColumnEast, newPosRow, 1, team, index);

				}

			}

			// West Options
			if (realPosColumn > 1) {

				int newPosColumn = realPosColumn - 2;
				int newPosRowNorth = realPosRow - 1;
				int newPosRowSouth = realPosRow + 1;

				// Hardcode sorry
				if (realPosRow != 0) {

					Main.board.add(posibleMoveHorse[2], newPosColumn, newPosRowNorth);
					posibleMoveHorse[2].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowNorth, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumn, newPosRowNorth, 2, team)) {

						checkForEatingHorses(newPosColumn, newPosRowNorth, 2, team, index);
					}

				}

				Main.board.add(posibleMoveHorse[3], newPosColumn, newPosRowSouth);
				posibleMoveHorse[3].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowSouth, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumn, newPosRowSouth, 3, team)) {

					checkForEatingHorses(newPosColumn, newPosRowSouth, 3, team, index);
				}

			}

			// South Options
			if (realPosRow > -10) {

				int newPosRow = realPosRow + 2;
				int newPosColumnWest = realPosColumn - 1;
				int newPosColumnEast = realPosColumn + 1;
				// Hardcode sorry
				if (realPosColumn > 0) {
					Main.board.add(posibleMoveHorse[4], newPosColumnWest, newPosRow);
					posibleMoveHorse[4].setOnMouseClicked(event -> movePieces(index, newPosColumnWest, newPosRow, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumnWest, newPosRow, 4, team)) {

						checkForEatingHorses(newPosColumnWest, newPosRow, 4, team, index);

					}
				}

				Main.board.add(posibleMoveHorse[5], newPosColumnEast, newPosRow);
				posibleMoveHorse[5].setOnMouseClicked(event -> movePieces(index, newPosColumnEast, newPosRow, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumnEast, newPosRow, 5, team)) {

					checkForEatingHorses(newPosColumnEast, newPosRow, 5, team, index);

				}

			}

			// West Options
			if (realPosColumn > -10) {

				int newPosColumn = realPosColumn + 2;
				int newPosRowNorth = realPosRow - 1;
				int newPosRowSouth = realPosRow + 1;

				// Hardcode sorry
				if (realPosRow != 0) {

					Main.board.add(posibleMoveHorse[6], newPosColumn, newPosRowNorth);
					posibleMoveHorse[6].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowNorth, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumn, newPosRowNorth, 6, team)) {

						checkForEatingHorses(newPosColumn, newPosRowNorth, 6, team, index);

					}

				}

				Main.board.add(posibleMoveHorse[7], newPosColumn, newPosRowSouth);
				posibleMoveHorse[7].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowSouth, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumn, newPosRowSouth, 7, team)) {

					checkForEatingHorses(newPosColumn, newPosRowSouth, 7, team, index);
				}

			}

			// -----------TEAM BLACK-----------
		} else {

			// North Options
			if (realPosRow > 1 && realPosColumn > 0) {

				int newPosRow = realPosRow - 2;
				int newPosColumnWest = realPosColumn - 1;
				int newPosColumnEast = realPosColumn + 1;
				// Hardcode sorry
				Main.board.add(posibleMoveHorse[0], newPosColumnWest, newPosRow);
				posibleMoveHorse[0].setOnMouseClicked(event -> movePieces(index, newPosColumnWest, newPosRow, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumnWest, newPosRow, 0, team)) {

					checkForEatingHorses(newPosColumnWest, newPosRow, 0, team, index);

				}

				Main.board.add(posibleMoveHorse[1], newPosColumnEast, newPosRow);
				posibleMoveHorse[1].setOnMouseClicked(event -> movePieces(index, newPosColumnEast, newPosRow, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumnEast, newPosRow, 1, team)) {

					checkForEatingHorses(newPosColumnEast, newPosRow, 1, team, index);

				}

			}

			// West Options
			if (realPosColumn > 1) {

				int newPosColumn = realPosColumn - 2;
				int newPosRowNorth = realPosRow - 1;
				int newPosRowSouth = realPosRow + 1;

				// Hardcode sorry
				if (realPosRow != 0) {

					Main.board.add(posibleMoveHorse[2], newPosColumn, newPosRowNorth);
					posibleMoveHorse[2].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowNorth, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumn, newPosRowNorth, 2, team)) {

						checkForEatingHorses(newPosColumn, newPosRowNorth, 2, team, index);
					}

				}

				Main.board.add(posibleMoveHorse[3], newPosColumn, newPosRowSouth);
				posibleMoveHorse[3].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowSouth, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumn, newPosRowSouth, 3, team)) {

					checkForEatingHorses(newPosColumn, newPosRowSouth, 3, team, index);
				}

			}

			// South Options
			if (realPosRow > -10) {

				int newPosRow = realPosRow + 2;
				int newPosColumnWest = realPosColumn - 1;
				int newPosColumnEast = realPosColumn + 1;
				// Hardcode sorry
				if (realPosColumn > 0) {
					Main.board.add(posibleMoveHorse[4], newPosColumnWest, newPosRow);
					posibleMoveHorse[4].setOnMouseClicked(event -> movePieces(index, newPosColumnWest, newPosRow, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumnWest, newPosRow, 4, team)) {

						checkForEatingHorses(newPosColumnWest, newPosRow, 4, team, index);

					}
				}

				Main.board.add(posibleMoveHorse[5], newPosColumnEast, newPosRow);
				posibleMoveHorse[5].setOnMouseClicked(event -> movePieces(index, newPosColumnEast, newPosRow, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumnEast, newPosRow, 5, team)) {

					checkForEatingHorses(newPosColumnEast, newPosRow, 5, team, index);

				}

			}

			// West Options
			if (realPosColumn > -10) {

				int newPosColumn = realPosColumn + 2;
				int newPosRowNorth = realPosRow - 1;
				int newPosRowSouth = realPosRow + 1;

				// Hardcode sorry
				if (realPosRow != 0) {

					Main.board.add(posibleMoveHorse[6], newPosColumn, newPosRowNorth);
					posibleMoveHorse[6].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowNorth, team,
							posibleMoveVertical.length, "Horse", false));
					if (checkBoardHorses(newPosColumn, newPosRowNorth, 6, team)) {

						checkForEatingHorses(newPosColumn, newPosRowNorth, 6, team, index);

					}

				}

				Main.board.add(posibleMoveHorse[7], newPosColumn, newPosRowSouth);
				posibleMoveHorse[7].setOnMouseClicked(event -> movePieces(index, newPosColumn, newPosRowSouth, team,
						posibleMoveVertical.length, "Horse", false));
				if (checkBoardHorses(newPosColumn, newPosRowSouth, 7, team)) {

					checkForEatingHorses(newPosColumn, newPosRowSouth, 7, team, index);
				}

			}

		}
	}

	private static void printPosibleMovesBishop(int realPosColumn, int realPosRow, int verticalMove, int horizontalMove,
			int tiltedMove, int index, int team, boolean isQueen) {

		// Create the nodes
		for (int i = 0; i < posibleMoveBishop.length; i++) {

			posibleMoveBishop[i] = new ImageView(selection);
		}

		// --WHITE TEAM---
		if (team == 0) {

			// Northeast

			// Check if it's not at the start of the board
			if (realPosRow != 0) {

				for (int i = 1; i < tiltedMove; i++) {

					// Subtract and Add the Columns and Row according to northeast
					int newPosColumn = realPosColumn + i;
					int newPosRow = realPosRow - i;

					if (newPosRow == -1) {

						break;

					}

					int posibleMoveIndex = 0 + i;
					Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
					SelectedView.deleteAllPiecesMove();
					// When CLicked it will call the MovePieces Function
					posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
							newPosRow, 0, posibleMoveVertical.length, "Bishop", isQueen));
					if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
						checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
						System.out.println("There's someting blocking");
						break;
					}

				}

			}

			// Southeast

			// Check if it's not at the start of the board

			for (int i = 1; i < tiltedMove; i++) {

				// Subtract and Add the Columns and Row according to northeast
				int newPosColumn = realPosColumn + i;
				int newPosRow = realPosRow + i;

				int posibleMoveIndex = 8 + i;
				Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
				SelectedView.deleteAllPiecesMove();
				// When CLicked it will call the MovePieces Function
				posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
						newPosRow, 0, posibleMoveVertical.length, "Bishop", isQueen));
				if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
					checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
					System.out.println("There's someting blocking");
					break;
				}

			}

			// Northwest

			// Check if it's not at the start of the board

			if (realPosRow != 0) {

				for (int i = 1; i < tiltedMove; i++) {

					// Subtract and Add the Columns and Row according to northeast
					int newPosColumn = realPosColumn - i;
					int newPosRow = realPosRow - i;

					if (newPosColumn == -1 || newPosRow == -1) {

						break;

					}

					int posibleMoveIndex = 16 + i;
					Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
					SelectedView.deleteAllPiecesMove();
					// When CLicked it will call the MovePieces Function
					posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
							newPosRow, 0, posibleMoveVertical.length, "Bishop", isQueen));
					if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
						checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
						System.out.println("There's someting blocking");
						break;
					}

				}

			}

			// Southwest

			// Check if it's not at the start of the board

			for (int i = 1; i < tiltedMove; i++) {

				// Subtract and Add the Columns and Row according to northeast
				int newPosColumn = realPosColumn - i;
				int newPosRow = realPosRow + i;

				if (newPosColumn == -1) {

					break;

				}

				int posibleMoveIndex = 24 + i;
				Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
				SelectedView.deleteAllPiecesMove();
				// When CLicked it will call the MovePieces Function
				posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
						newPosRow, 0, posibleMoveVertical.length, "Bishop", isQueen));
				if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
					checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
					System.out.println("There's someting blocking");
					break;
				}

			}

			// --BLACK TEAM---
		} else {

			// Northeast

			// Check if it's not at the start of the board
			if (realPosRow != 0) {

				for (int i = 1; i < tiltedMove; i++) {

					// Subtract and Add the Columns and Row according to northeast
					int newPosColumn = realPosColumn + i;
					int newPosRow = realPosRow - i;

					if (newPosRow == -1) {

						break;

					}

					int posibleMoveIndex = 0 + i;
					Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
					SelectedView.deleteAllPiecesMove();
					// When CLicked it will call the MovePieces Function
					posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
							newPosRow, 1, posibleMoveVertical.length, "Bishop", isQueen));
					if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
						checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
						System.out.println("There's someting blocking");
						break;
					}

				}

			}

			// Southeast

			// Check if it's not at the start of the board

			for (int i = 1; i < tiltedMove; i++) {

				// Subtract and Add the Columns and Row according to northeast
				int newPosColumn = realPosColumn + i;
				int newPosRow = realPosRow + i;

				int posibleMoveIndex = 8 + i;
				Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
				SelectedView.deleteAllPiecesMove();
				// When CLicked it will call the MovePieces Function
				posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
						newPosRow, 1, posibleMoveVertical.length, "Bishop", isQueen));
				if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {

					System.out.println("There's someting blocking");
					break;
				}

			}

			// Northwest

			// Check if it's not at the start of the board

			if (realPosRow != 0) {

				for (int i = 1; i < tiltedMove; i++) {

					// Subtract and Add the Columns and Row according to northeast
					int newPosColumn = realPosColumn - i;
					int newPosRow = realPosRow - i;

					if (newPosColumn == -1 || newPosRow == -1) {

						break;

					}

					int posibleMoveIndex = 16 + i;
					Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
					SelectedView.deleteAllPiecesMove();
					// When CLicked it will call the MovePieces Function
					posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
							newPosRow, 1, posibleMoveVertical.length, "Bishop", isQueen));
					if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
						checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
						System.out.println("There's someting blocking");
						break;
					}

				}

			}

			// Southwest

			// Check if it's not at the start of the board

			for (int i = 1; i < tiltedMove; i++) {

				// Subtract and Add the Columns and Row according to northeast
				int newPosColumn = realPosColumn - i;
				int newPosRow = realPosRow + i;

				if (newPosColumn == -1) {

					break;

				}

				int posibleMoveIndex = 24 + i;
				Main.board.add(posibleMoveBishop[posibleMoveIndex], newPosColumn, newPosRow);
				SelectedView.deleteAllPiecesMove();
				// When CLicked it will call the MovePieces Function
				posibleMoveBishop[posibleMoveIndex].setOnMouseClicked(event -> movePieces(index, newPosColumn,
						newPosRow, 1, posibleMoveVertical.length, "Bishop", isQueen));
				if (checkBoardTilted(newPosColumn, newPosRow, posibleMoveIndex, team)) {
					checkForEatingBishop(newPosColumn, newPosRow, posibleMoveIndex, team, index, isQueen);
					System.out.println("There's someting blocking");
					break;
				}

			}
		}

	}

	// Print the Pieces once the player has chosen where to put them
	private static void movePieces(int index, int Column, int Row, int team, int posibleMoveImage, String type,
			boolean isQueen) {

		if (team == 0) {

			Main.board.getChildren().remove(Creator.piecesWhite[index].getSpriteNode());
			Main.board.add(Creator.piecesWhite[index].getSpriteNode(), Column, Row);

			Creator.piecesWhite[index]
					.setPositionCol(GridPane.getColumnIndex(Creator.piecesWhite[index].getSpriteNode()));
			Creator.piecesWhite[index].setPositionRow(GridPane.getRowIndex(Creator.piecesWhite[index].getSpriteNode()));

			for (int k = 0; k < posibleMoveImage; k++) {

				Main.board.getChildren().remove(posibleMoveVertical[k]);
				Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				if (k < 8) {

					Main.board.getChildren().remove(posibleMoveHorse[k]);

				}
				Main.board.getChildren().remove(posibleMoveBishop[k]);

			}

			if (type.equals("Pawn")) {

				if (Row == 7) {

					System.out.println("You've been Promoted");

					Main.pool(index, team);

				}

			}

			// Check for the king
			if (type.equals("Tower")) {

				printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, false);

				if (isQueen) {

					printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveBishop[k]);

					}

				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveVertical[k]);
					Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				}
			}

			if (type.equals("Bishop")) {

				printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

				if (isQueen) {


					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveVertical[k]);
						Main.board.getChildren().remove(posibleMoveHorizontal[k]);
						
					}
					
//					printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, isQueen);
//					
//					for (int k = 0; k < posibleMoveImage; k++) {
//
//						Main.board.getChildren().remove(posibleMoveVertical[k]);
//						Main.board.getChildren().remove(posibleMoveHorizontal[k]);
//						
//					}
				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveBishop[k]);

				}
			}

			if (type.equals("Horse")) {

				printPosibleMovesHorse(Column, Row, 0, 0, 0, index, team);

				for (int k = 0; k < 8; k++) {

					Main.board.getChildren().remove(posibleMoveHorse[k]);

				}
			}

			// Switch Turn
			SelectedView.deleteAllPiecesMove();
			SelectedView.createAllBlackPiecesMove();

		} else {

			if (type.equals("Pawn")) {

				if (Row == 0) {

					System.out.println("You've been Promoted");

					Main.pool(index, team);

				}

			}

			Main.board.getChildren().remove(Creator.piecesBlack[index].getSpriteNode());
			Main.board.add(Creator.piecesBlack[index].getSpriteNode(), Column, Row);

			Creator.piecesBlack[index]
					.setPositionCol(GridPane.getColumnIndex(Creator.piecesBlack[index].getSpriteNode()));
			Creator.piecesBlack[index].setPositionRow(GridPane.getRowIndex(Creator.piecesBlack[index].getSpriteNode()));

			for (int k = 0; k < posibleMoveImage; k++) {

				Main.board.getChildren().remove(posibleMoveVertical[k]);
				Main.board.getChildren().remove(posibleMoveHorizontal[k]);

				if (k < 8) {

					Main.board.getChildren().remove(posibleMoveHorse[k]);

				}

				Main.board.getChildren().remove(posibleMoveBishop[k]);

			}

			// Check for the king
			if (type.equals("Tower")) {

				printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, false);

				if (isQueen) {

					printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveBishop[k]);

					}

				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveVertical[k]);
					Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				}
			}

			if (type.equals("Bishop")) {

				printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

				if (isQueen) {

					
					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveVertical[k]);
						Main.board.getChildren().remove(posibleMoveHorizontal[k]);
					}
					
//					printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, isQueen);
//					
//					for (int k = 0; k < posibleMoveImage; k++) {
//
//						Main.board.getChildren().remove(posibleMoveVertical[k]);
//						Main.board.getChildren().remove(posibleMoveHorizontal[k]);
//						
//					}
				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveBishop[k]);

				}
			}

			if (type.equals("Horse")) {

				printPosibleMovesHorse(Column, Row, 0, 0, 0, index, team);

				for (int k = 0; k < 8; k++) {

					Main.board.getChildren().remove(posibleMoveHorse[k]);

				}
			}

			// Switch Turn
			SelectedView.deleteAllPiecesMove();
			SelectedView.createAllWhitePiecesMove();

		}

		return;

	}

	// Check where you want to put a posibleMove and if there's a piece there don't
	public static boolean checkBoardVertical(int column, int row, int id, int team) {

		for (int index = 0; index < Creator.piecesBlack.length; index++) {

			// For White Team
			if (team == 0) {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite

				// Check Front
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveVertical[id]);
					System.out.println("This Piece can't move to the front");

					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();

					return true;
				}

				// For Black Team
			} else {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveVertical[id]);
					System.out.println("This Piece can't move to the front");

					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();

					return true;
				}

			}
		}

		return false;

	}

	public static boolean checkBoardHorizontal(int column, int row, int id, int team) {

		for (int index = 0; index < Creator.piecesBlack.length; index++) {

			// For White Team
			if (team == 0) {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite

				// Check Front
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveHorizontal[id]);
					System.out.println("This Piece can't move to Sidways");

					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();

					return true;

				}

				// For Black Team
			} else {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveHorizontal[id]);
					System.out.println("This Piece can't move to the front");

					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();

					return true;
				}

			}
		}

		return false;

	}

	// Check where you want to put a posibleMove and if there's a piece there don't
	public static boolean checkBoardTilted(int column, int row, int id, int team) {

		for (int index = 0; index < Creator.piecesBlack.length; index++) {

			// For White Team
			if (team == 0) {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite

				// Check Front
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveBishop[id]);
					System.out.println("This Piece can't move titled");

					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();

					return true;
				}

				// For Black Team
			} else {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveBishop[id]);
					System.out.println("This Piece can't move titled");

					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();

					return true;
				}

			}
		}

		return false;

	}

	public static void checkForEatingPawn(int column, int row, int id, int team, int howManySpaces, int willEatIndex) {

		for (int index = 0; index < Creator.piecesWhite.length; index++) {

			// For White Team
			if (team == 0) {

				int columnWest = column - howManySpaces;
				int columnEast = column + howManySpaces;

				// Check if there's a Piece a Pawn Could eat, and give that a Sprite --WEST
				if (getNodeFromGridPane(Main.board, columnWest, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Add that Sprite
					Main.board.add(posibleMoveVertical[id], columnWest, row);
					System.out.println("This can be eaten");
					// When CLicked it will call the EatPieces Function

					if (getNodeFromGridPane(Main.board, columnWest, row) == Creator.piecesBlack[11].getSpriteNode()) {

						System.out.println("Check");

					}
					isCheck(columnWest, row, team);
					posibleMoveVertical[id].setOnMouseClicked(event -> eatPiece(willEatIndex, columnWest, row, 0,
							posibleMoveVertical.length, "Pawn", false));
					SelectedView.deleteAllPiecesMove();
				}

				// Check if there's a Piece a Pawn Could eat, and give that a Sprite -- EAST
				if (getNodeFromGridPane(Main.board, columnEast, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Add that Sprite
					Main.board.add(posibleMoveVertical[id + 3], columnEast, row);
					System.out.println("This can be eaten");
					// When CLicked it will call the EatPieces Function
					isCheck(columnEast, row, team);
					posibleMoveVertical[id + 3].setOnMouseClicked(event -> eatPiece(willEatIndex, columnEast, row, 0,
							posibleMoveVertical.length, "Pawn", false));
					SelectedView.deleteAllPiecesMove();

				}

				// For Black Team
			} else {

				int columnWest = column - howManySpaces;
				int columnEast = column + howManySpaces;

				// Check if there's a Piece a Pawn Could eat, and give that a Sprite --WEST
				if (getNodeFromGridPane(Main.board, columnWest, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Add that Sprite
					Main.board.add(posibleMoveVertical[id], columnWest, row);
					System.out.println("This can be eaten");
					// When CLicked it will call the EatPieces Function
					isCheck(columnWest, row, team);
					posibleMoveVertical[id].setOnMouseClicked(event -> eatPiece(willEatIndex, columnWest, row, 1,
							posibleMoveVertical.length, "Pawn", false));
					SelectedView.deleteAllPiecesMove();
				}

				// Check if there's a Piece a Pawn Could eat, and give that a Sprite --EAST
				if (getNodeFromGridPane(Main.board, columnEast, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Add that Sprite
					Main.board.add(posibleMoveVertical[id + 3], columnEast, row);
					System.out.println("This can be eaten");
					// When CLicked it will call the EatPieces Function
					isCheck(columnEast, row, team);
					posibleMoveVertical[id + 3].setOnMouseClicked(event -> eatPiece(willEatIndex, columnEast, row, 1,
							posibleMoveVertical.length, "Pawn", false));
					SelectedView.deleteAllPiecesMove();

				}

			}

		}

	}

	public static void checkForEatingTower(int column, int row, int posibleMoveIndex, int team, int willEatIndex,
			boolean isHorizontal, boolean isQueen) {

		// Is Horizontal?
		// NO
		if (!isHorizontal) {

			if (team == 0) {

				for (int index = 0; index < Creator.piecesBlack.length; index++) {

					int checkFront = row + 1;
					int checkBack = row - 1;

					if (getNodeFromGridPane(Main.board, column, checkFront) == Creator.piecesBlack[index]
							.getSpriteNode()) {

						Main.board.add(posibleMoveVertical[posibleMoveIndex+1], column, checkFront);
						if(isCheck(column, checkFront, team)) {
							
							
							break;
						}

						posibleMoveVertical[posibleMoveIndex+1].setOnMouseClicked(event -> eatPiece(willEatIndex,
								column, checkFront, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}

					if (getNodeFromGridPane(Main.board, column, checkBack) == Creator.piecesBlack[index]
							.getSpriteNode()) {

						System.out.println("is getting");
						Main.board.add(posibleMoveVertical[posibleMoveIndex + 2], column, checkBack);
						if(isCheck(column, checkBack, team)) {
							
							break;
							
						}
						posibleMoveVertical[posibleMoveIndex + 2].setOnMouseClicked(event -> eatPiece(willEatIndex,
								column, checkBack, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}

				}
			} else {

				for (int index = 0; index < Creator.piecesWhite.length; index++) {

					int checkFront = row + 1;
					int checkBack = row - 1;

					if (getNodeFromGridPane(Main.board, column, checkFront) == Creator.piecesWhite[index]
							.getSpriteNode()) {

						Main.board.add(posibleMoveVertical[posibleMoveIndex + 1], column, checkFront);
							if (isCheck(column, checkFront, team)) {
	
								break;
							}
						posibleMoveVertical[posibleMoveIndex + 1].setOnMouseClicked(event -> eatPiece(willEatIndex,
								column, checkFront, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}

					if (getNodeFromGridPane(Main.board, column, checkBack) == Creator.piecesWhite[index]
							.getSpriteNode()) {

						Main.board.add(posibleMoveVertical[posibleMoveIndex + 2], column, checkBack);
						if(isCheck(column, checkBack, team)) {
							
							break;
							
						}
						posibleMoveVertical[posibleMoveIndex + 2].setOnMouseClicked(event -> eatPiece(willEatIndex,
								column, checkBack, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}
				}

			}

			// YES
		} else {

			if (team == 0) {

				for (int index = 0; index < Creator.piecesBlack.length; index++) {

					int west = column - 1;
					int east = column + 1;

					if (getNodeFromGridPane(Main.board, west, row) == Creator.piecesBlack[index].getSpriteNode()) {

						Main.board.add(posibleMoveHorizontal[posibleMoveIndex + 3], west, row);
						if(isCheck(west, row, team)) {
							
							System.out.println("Breaking");
							break;
							
						}
						posibleMoveHorizontal[posibleMoveIndex + 3].setOnMouseClicked(event -> eatPiece(willEatIndex,
								west, row, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}

					if (getNodeFromGridPane(Main.board, east, row) == Creator.piecesBlack[index].getSpriteNode()) {

						Main.board.add(posibleMoveHorizontal[posibleMoveIndex + 2], east, row);
						if(isCheck(east, row, team)) {
							
							System.out.println("Breaking");
							break;
							
						}
						posibleMoveHorizontal[posibleMoveIndex + 2].setOnMouseClicked(event -> eatPiece(willEatIndex,
								east, row, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}

				}
			} else {

				for (int index = 0; index < Creator.piecesWhite.length; index++) {

					int east = column + 1;
					int west = column - 1;

					if (getNodeFromGridPane(Main.board, east, row) == Creator.piecesWhite[index].getSpriteNode()) {

						Main.board.add(posibleMoveHorizontal[posibleMoveIndex + 3], east, row);
						if(isCheck(west, row, team)) {
							
							System.out.println("Breaking");
							break;
							
						}
						posibleMoveHorizontal[posibleMoveIndex + 3].setOnMouseClicked(event -> eatPiece(willEatIndex,
								east, row, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}

					if (getNodeFromGridPane(Main.board, west, row) == Creator.piecesWhite[index].getSpriteNode()) {

						Main.board.add(posibleMoveHorizontal[posibleMoveIndex + 2], west, row);
						if(isCheck(east, row, team)) {
							
							System.out.println("Breaking");
							break;
							
						}
						posibleMoveHorizontal[posibleMoveIndex + 2].setOnMouseClicked(event -> eatPiece(willEatIndex,
								west, row, team, posibleMoveHorizontal.length, "Tower", isQueen));

					}
				}

			}

		}

	}

	public static boolean checkBoardHorses(int column, int row, int id, int team) {

		for (int index = 0; index < Creator.piecesBlack.length; index++) {

			// For White Team
			if (team == 0) {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite

				// Check Front
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveHorse[id]);
					System.out.println("There's already a piece there");

					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();

					return true;
				}

				// For Black Team
			} else {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()
						|| getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.getChildren().remove(posibleMoveHorse[id]);
					System.out.println("There's already a piece there");

					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();

					return true;
				}

			}
		}

		return false;

	}

	public static boolean checkForEatingHorses(int column, int row, int id, int team, int willEatIndex) {

		for (int index = 0; index < Creator.piecesBlack.length; index++) {

			// For White Team
			if (team == 0) {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite

				// Check Front
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.add(posibleMoveHorse[id], column, row);
					System.out.println("You Can Eat Bro");
					isCheck(column, row, team);
					posibleMoveHorse[id].setOnMouseClicked(event -> eatPiece(willEatIndex, column, row, team,
							posibleMoveHorizontal.length, "Horse", false));

					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();

					return true;
				}

				// For Black Team
			} else {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.add(posibleMoveHorse[id], column, row);
					System.out.println("You Can Eat Bro");
					isCheck(column, row, team);
					posibleMoveHorse[id].setOnMouseClicked(event -> eatPiece(willEatIndex, column, row, team,
							posibleMoveHorizontal.length, "Horse", false));

					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();

					return true;
				}

			}

		}

		return false;

	}

	public static boolean checkForEatingBishop(int column, int row, int id, int team, int willEatIndex,
			boolean isQueen) {

		for (int index = 0; index < Creator.piecesBlack.length; index++) {

			// For White Team
			if (team == 0) {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite

				// Check
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesBlack[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.add(posibleMoveBishop[id], column, row);
					System.out.println("You Can Eat Bro");
					if(isCheck(column, row, team)) {
						
						break;
					}
					posibleMoveBishop[id].setOnMouseClicked(event -> eatPiece(willEatIndex, column, row, team,
							posibleMoveHorizontal.length, "Bishop", isQueen));

					// Give back the ability of moving
					SelectedView.createAllWhitePiecesMove();

					return true;
				}

				// For Black Team
			} else {

				// Check if there's already a piece were you want to put your "PosibleMove"
				// sprite
				if (getNodeFromGridPane(Main.board, column, row) == Creator.piecesWhite[index].getSpriteNode()) {

					// Remove that Sprite
					Main.board.add(posibleMoveBishop[id], column, row);
					System.out.println("You Can Eat Bro");
					if(isCheck(column, row, team)) {
						
						break;
					}
					posibleMoveBishop[id].setOnMouseClicked(event -> eatPiece(willEatIndex, column, row, team,
							posibleMoveHorizontal.length, "Bishop", isQueen));

					// Give back the ability of moving
					SelectedView.createAllBlackPiecesMove();

					return true;
				}

			}

		}

		return false;

	}

	// Eat a Piece
	private static void eatPiece(int index, int Column, int Row, int team, int posibleMoveImage, String type,
			boolean isQueen) {

		if (team == 0) {

			// Check if the king is dead
			if (getNodeFromGridPane(Main.board, Column, Row) == Creator.piecesBlack[11].getSpriteNode()) {

				Main.displayWinner(team);

			}

			// Piece to Eat
			Main.board.getChildren().remove(getNodeFromGridPane(Main.board, Column, Row));

			Main.board.getChildren().remove(Creator.piecesWhite[index].getSpriteNode());
			Main.board.add(Creator.piecesWhite[index].getSpriteNode(), Column, Row);

			Creator.piecesWhite[index]
					.setPositionCol(GridPane.getColumnIndex(Creator.piecesWhite[index].getSpriteNode()));
			Creator.piecesWhite[index].setPositionRow(GridPane.getRowIndex(Creator.piecesWhite[index].getSpriteNode()));

			for (int k = 0; k < posibleMoveImage; k++) {

				Main.board.getChildren().remove(posibleMoveVertical[k]);
				Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				Main.board.getChildren().remove(posibleMoveBishop[k]);

				posibleMoveVertical[k] = null;
				posibleMoveHorizontal[k] = null;
				posibleMoveBishop[k] = null;

			}

			for (int i = 0; i < posibleMoveHorse.length; i++) {

				Main.board.getChildren().remove(posibleMoveHorse[i]);
			}

			// Check for the king
			if (type.equals("Tower")) {

				printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, false);

				if (isQueen) {

					printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveBishop[k]);

					}

				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveVertical[k]);
					Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				}
			}

			if (type.equals("Bishop")) {

				printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

				if (isQueen) {

					//printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, false);
					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveVertical[k]);
						Main.board.getChildren().remove(posibleMoveHorizontal[k]);
					}
				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveBishop[k]);

				}
			}

			if (type.equals("Horse")) {

				printPosibleMovesHorse(Column, Row, 0, 0, 0, index, team);

				for (int k = 0; k < 8; k++) {

					Main.board.getChildren().remove(posibleMoveHorse[k]);

				}
			}

			// Switch Turn
			SelectedView.deleteAllPiecesMove();
			SelectedView.createAllBlackPiecesMove();

		} else {

			// Check if the king is dead
			if (getNodeFromGridPane(Main.board, Column, Row) == Creator.piecesWhite[11].getSpriteNode()) {

				Main.displayWinner(team);

			}

			Main.board.getChildren().remove(getNodeFromGridPane(Main.board, Column, Row));

			Main.board.getChildren().remove(Creator.piecesBlack[index].getSpriteNode());
			Main.board.add(Creator.piecesBlack[index].getSpriteNode(), Column, Row);

			Creator.piecesBlack[index]
					.setPositionCol(GridPane.getColumnIndex(Creator.piecesBlack[index].getSpriteNode()));
			Creator.piecesBlack[index].setPositionRow(GridPane.getRowIndex(Creator.piecesBlack[index].getSpriteNode()));

			for (int k = 0; k < posibleMoveImage; k++) {

				Main.board.getChildren().remove(posibleMoveVertical[k]);
				Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				Main.board.getChildren().remove(posibleMoveBishop[k]);

				posibleMoveVertical[k] = null;
				posibleMoveHorizontal[k] = null;

			}
			for (int i = 0; i < posibleMoveHorse.length; i++) {

				Main.board.getChildren().remove(posibleMoveHorse[i]);
			}

			// Check for the king
			if (type.equals("Tower")) {

				printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, false);

				if (isQueen) {

					printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveBishop[k]);

					}

				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveVertical[k]);
					Main.board.getChildren().remove(posibleMoveHorizontal[k]);
				}
			}

			if (type.equals("Bishop")) {

				printPosibleMovesBishop(Column, Row, 0, 0, 8, index, team, false);

				if (isQueen) {

					//printPosibleMovesTower(Column, Row, 8, 8, 0, index, team, false, false);
					for (int k = 0; k < posibleMoveImage; k++) {

						Main.board.getChildren().remove(posibleMoveVertical[k]);
						Main.board.getChildren().remove(posibleMoveHorizontal[k]);
					}
				}

				for (int k = 0; k < posibleMoveImage; k++) {

					Main.board.getChildren().remove(posibleMoveBishop[k]);

				}
			}

			if (type.equals("Horse")) {

				printPosibleMovesHorse(Column, Row, 0, 0, 0, index, team);

				for (int k = 0; k < 8; k++) {

					Main.board.getChildren().remove(posibleMoveHorse[k]);

				}
			}

			// Switch Turn
			SelectedView.deleteAllPiecesMove();
			SelectedView.createAllWhitePiecesMove();

			for (int i = 0; i < posibleMoveHorse.length; i++) {

				Main.board.getChildren().remove(posibleMoveHorse[i]);
			}

		}

		return;

	}

	private static boolean isCheck(int Column, int Row, int team) {

		Image checkText = new Image("file:Sprites/Chec1.png");
		ImageView checkTextNode = new ImageView(checkText);

	
		
		if (team == 0) {

			if (getNodeFromGridPane(Main.board, Column, Row) == Creator.piecesBlack[11].getSpriteNode()) {

				Main.board.add(checkTextNode, 0, 3);
				System.out.println("Check");
				checkTextNode.setOnMouseClicked(event -> Main.board.getChildren().remove(checkTextNode));


				
				return true;
			}

		} else {

			if (getNodeFromGridPane(Main.board, Column, Row) == Creator.piecesWhite[11].getSpriteNode()) {

				Main.board.add(checkTextNode, 0, 3);
				System.out.println("Check");
				checkTextNode.setOnMouseClicked(event -> Main.board.getChildren().remove(checkTextNode));
				

				
				return true;
			}

		}
		
		return false;

	}

	// Get a Node From the Board
	private static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

}
