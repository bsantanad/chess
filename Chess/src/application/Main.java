	package application;
	
	import javafx.application.Application;
	import javafx.application.Platform;
	import javafx.geometry.Insets;
	import javafx.stage.Stage;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.scene.input.MouseEvent;
	import javafx.scene.layout.Background;
	import javafx.scene.layout.BackgroundImage;
	import javafx.scene.layout.BackgroundPosition;
	import javafx.scene.layout.BackgroundRepeat;
	import javafx.scene.layout.BackgroundSize;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.ColumnConstraints;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.RowConstraints;
	import javafx.scene.layout.StackPane;
	import javafx.scene.transform.Scale;
	import javafx.beans.value.ChangeListener;
	import javafx.beans.value.ObservableValue;
	import javafx.event.ActionEvent;
	
	public class Main extends Application {
	
		public static GridPane board = new GridPane();
	
		@Override
		public void start(Stage primaryStage) {
			try {
	
				primaryStage.setScene(BoardStage());
				primaryStage.setResizable(false);
				primaryStage.show();
	
				primaryStage.setOnCloseRequest(event -> Platform.exit());
				reset();
				displayPlayerReg();
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		public static void main(String[] args) {
			launch(args);
		}
	
		// Create the Board
		public Scene BoardStage() {
	
			// How to create an Image Node
	//		Image test = new Image("file:Sprites/Pieces/Black/BlackPawn.png");
	//		ImageView testone = new ImageView(test);
	//		board.add(testone, 0, 1);
	//		testone.setOnMouseEntered(event -> System.out.println("Well Done"));
	
			// Background
			Image chessBoardImage = new Image("file:Sprites/Board_3.png");
			BackgroundImage chessBoard = new BackgroundImage(chessBoardImage, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	
			board.setBackground(new Background(chessBoard));
	
			// Create the pieces
			Creator.allocator();
	
			// Column and Row Properties
			ColumnConstraints prefColumn = new ColumnConstraints(64);
			RowConstraints prefRow = new RowConstraints(64);
	
			board.getColumnConstraints().add(prefColumn); // column 0 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 1 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 2 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 3 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 4 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 5 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 6 is 64 wide
			board.getColumnConstraints().add(prefColumn); // column 7 is 64 wide
	
			board.getRowConstraints().add(prefRow); // Row 0 is 64 height
			board.getRowConstraints().add(prefRow); // Row 1 is 64 height
			board.getRowConstraints().add(prefRow); // Row 2 is 64 height
			board.getRowConstraints().add(prefRow); // Row 3 is 64 height
			board.getRowConstraints().add(prefRow); // Row 4 is 64 height
			board.getRowConstraints().add(prefRow); // Row 5 is 64 height
			board.getRowConstraints().add(prefRow); // Row 6 is 64 height
			board.getRowConstraints().add(prefRow); // Row 7 is 64 height
	
			// Fill the Board
			for (int i = 0; i < 8; i++) {
	
				int j = i + 8;
	
				// Paint the White Pieces
				board.add(Creator.piecesWhite[j].getSpriteNode(), i, 0);
				board.add(Creator.piecesWhite[i].getSpriteNode(), i, 1);
	
				// Set the Position of Each Piece
				Creator.piecesWhite[j].setPositionCol(GridPane.getColumnIndex(Creator.piecesWhite[j].getSpriteNode()));
				Creator.piecesWhite[j].setPositionRow(GridPane.getRowIndex(Creator.piecesWhite[j].getSpriteNode()));
	
				Creator.piecesWhite[i].setPositionCol(GridPane.getColumnIndex(Creator.piecesWhite[i].getSpriteNode()));
				Creator.piecesWhite[i].setPositionRow(GridPane.getRowIndex(Creator.piecesWhite[i].getSpriteNode()));
	
				// Paint the Black Pieces
				board.add(Creator.piecesBlack[j].getSpriteNode(), i, 7);
				board.add(Creator.piecesBlack[i].getSpriteNode(), i, 6);
	
				// Set the Position of Each Piece
				Creator.piecesBlack[j].setPositionCol(GridPane.getColumnIndex(Creator.piecesBlack[j].getSpriteNode()));
				Creator.piecesBlack[j].setPositionRow(GridPane.getRowIndex(Creator.piecesBlack[j].getSpriteNode()));
	
				Creator.piecesBlack[i].setPositionCol(GridPane.getColumnIndex(Creator.piecesBlack[i].getSpriteNode()));
				Creator.piecesBlack[i].setPositionRow(GridPane.getRowIndex(Creator.piecesBlack[i].getSpriteNode()));
	
			}
	
			// White Starts
			SelectedView.createAllWhitePiecesMove();
	
			Scene scene = new Scene(board, 500, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
			return scene;
	
		}
	
		private static Player[] players = new Player[2];
	
		public void displayPlayerReg() {
	
			for (int i = 0; i < players.length; i++) {
	
				players[i] = new Player();
	
			}
	
			Stage secondStage = new Stage();
			secondStage.setTitle("Add");
	
			// Scene
			StackPane secondaryLayout = new StackPane();
			Scene scene = new Scene(secondaryLayout, 480, 240);
	
			// Grid
			GridPane addScreen = new GridPane();
			addScreen.setPadding(new Insets(10, 10, 10, 10));
	
			// Add the Grid
			secondaryLayout.getChildren().add(addScreen);
	
			// Space between Labels and buttons
			addScreen.setHgap(10);
			addScreen.setVgap(10);
	
			TextField playerone = new TextField("White");
			TextField playertwo = new TextField("Black");
			players[0].setName(playerone.getText());
			players[1].setName(playertwo.getText());
	
			Button buttonAdd = new Button("Add");
	
			addScreen.add(playerone, 0, 1);
			addScreen.add(playertwo, 0, 2);
			addScreen.add(buttonAdd, 1, 1);
	
			buttonAdd.setOnAction((ActionEvent event) -> {
	
				players[0].setName(playerone.getText());
				players[1].setName(playertwo.getText());
				secondStage.close();
	
			});
	
			secondStage.setScene(scene);
			secondStage.show();
	
		}
	
		public static void displayWinner(int team) {
	
			Stage secondStage = new Stage();
			secondStage.setTitle("Winner");
	
			// Scene
			StackPane secondaryLayout = new StackPane();
			Scene scene = new Scene(secondaryLayout, 480, 240);
	
			// Grid
			GridPane addScreen = new GridPane();
			addScreen.setPadding(new Insets(10, 10, 10, 10));
	
			// Add the Grid
			secondaryLayout.getChildren().add(addScreen);
	
			// Space between Labels and buttons
			addScreen.setHgap(10);
			addScreen.setVgap(10);
	
			if (team == 0) {
	
				Label winner = new Label("The winner " + players[0].getName());
				addScreen.add(winner, 1, 5);
	
			} else {
	
				Label winner = new Label("The winner " + players[1].getName());
				addScreen.add(winner, 1, 5);
	
			}
	
			secondStage.setScene(scene);
			secondStage.show();
			secondStage.setOnCloseRequest(event -> Platform.exit());
	
		}
	
		public static void reset() {
	
			Stage secondStage = new Stage();
			secondStage.setTitle("Reset");
	
			// Scene
			StackPane secondaryLayout = new StackPane();
			Scene scene = new Scene(secondaryLayout, 200, 100);
	
			// Grid
			GridPane addScreen = new GridPane();
			addScreen.setPadding(new Insets(10, 10, 10, 10));
	
			// Add the Grid
			secondaryLayout.getChildren().add(addScreen);
	
			// Space between Labels and buttons
			addScreen.setHgap(10);
			addScreen.setVgap(10);
	
			Image button = new Image("file:Sprites/reset.png");
			ImageView reset = new ImageView(button);
			addScreen.add(reset, 0, 0);
			reset.setOnMouseClicked(event -> {
	
				Creator.reset();
	
			});
	
			secondStage.setScene(scene);
			secondStage.show();
	
		}
	
		public static void pool(int index, int team) {
	
			Stage secondStage = new Stage();
			secondStage.setTitle("Promotion Pool");
	
			// Scene
			StackPane secondaryLayout = new StackPane();
			Scene scene = new Scene(secondaryLayout, -1, -1);
	
			// Grid
			GridPane addScreen = new GridPane();
			addScreen.setPadding(new Insets(10, 10, 10, 10));
	
			// Add the Grid
			secondaryLayout.getChildren().add(addScreen);
	
			// Space between Labels and buttons
			addScreen.setHgap(10);
			addScreen.setVgap(10);
	
			if (team == 0) {
	
				Image queen = new Image("file:Sprites/Pieces/White/WhiteQueen.png");
				ImageView queenW = new ImageView(queen);
	
				Image bishop = new Image("file:Sprites/Pieces/White/WhiteBishop.png");
				ImageView bishopW = new ImageView(bishop);
	
				Image tower = new Image("file:Sprites/Pieces/White/WhiteTower.png");
				ImageView towerW = new ImageView(tower);
	
				Image knight = new Image("file:Sprites/Pieces/White/WhiteHorse.png");
				ImageView knightW = new ImageView(knight);
	
				addScreen.add(queenW, 0, 0);
				queenW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesWhite[index].getPositionCol();
					int actualRow = Creator.piecesWhite[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesWhite[index].getSpriteNode());
					Creator.piecesWhite[index] = new Queen("Queen", queen, team);
	
					Creator.piecesWhite[index].setHorizontalMovement(8);
					Creator.piecesWhite[index].setVerticalMovement(8);
					Creator.piecesWhite[index].setTitledMovement(8);
	
					Creator.piecesWhite[index].setPositionCol(actualColumn);
					Creator.piecesWhite[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesWhite[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
	
				});
	
				addScreen.add(bishopW, 1, 0);
				bishopW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesWhite[index].getPositionCol();
					int actualRow = Creator.piecesWhite[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesWhite[index].getSpriteNode());
					Creator.piecesWhite[index] = new Bishop("Bishop", bishop, team);
	
					Creator.piecesWhite[index].setHorizontalMovement(0);
					Creator.piecesWhite[index].setVerticalMovement(0);
					Creator.piecesWhite[index].setTitledMovement(8);
	
					Creator.piecesWhite[index].setPositionCol(actualColumn);
					Creator.piecesWhite[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesWhite[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
	
				});
				addScreen.add(towerW, 2, 0);
				towerW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesWhite[index].getPositionCol();
					int actualRow = Creator.piecesWhite[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesWhite[index].getSpriteNode());
					Creator.piecesWhite[index] = new Tower("Tower", tower, team);
	
					Creator.piecesWhite[index].setHorizontalMovement(8);
					Creator.piecesWhite[index].setVerticalMovement(8);
					Creator.piecesWhite[index].setTitledMovement(0);
	
					Creator.piecesWhite[index].setPositionCol(actualColumn);
					Creator.piecesWhite[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesWhite[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
				});
	
				addScreen.add(knightW, 3, 0);
				knightW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesWhite[index].getPositionCol();
					int actualRow = Creator.piecesWhite[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesWhite[index].getSpriteNode());
					Creator.piecesWhite[index] = new Horse("Horse", knight, team);
	
					Creator.piecesWhite[index].setHorizontalMovement(8);
					Creator.piecesWhite[index].setVerticalMovement(8);
					Creator.piecesWhite[index].setTitledMovement(8);
	
					Creator.piecesWhite[index].setPositionCol(actualColumn);
					Creator.piecesWhite[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesWhite[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
				});
	
			} else {
	
				Image queen = new Image("file:Sprites/Pieces/Black/BlackQueen.png");
				ImageView queenW = new ImageView(queen);
	
				Image bishop = new Image("file:Sprites/Pieces/Black/BlackBishop.png");
				ImageView bishopW = new ImageView(bishop);
	
				Image tower = new Image("file:Sprites/Pieces/Black/BlackTower.png");
				ImageView towerW = new ImageView(tower);
	
				Image knight = new Image("file:Sprites/Pieces/Black/BlackHorse.png");
				ImageView knightW = new ImageView(knight);
	
				addScreen.add(queenW, 0, 0);
				queenW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesBlack[index].getPositionCol();
					int actualRow = Creator.piecesBlack[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesBlack[index].getSpriteNode());
					Creator.piecesBlack[index] = new Queen("Queen", queen, team);
	
					Creator.piecesBlack[index].setHorizontalMovement(8);
					Creator.piecesBlack[index].setVerticalMovement(8);
					Creator.piecesBlack[index].setTitledMovement(8);
	
					Creator.piecesBlack[index].setPositionCol(actualColumn);
					Creator.piecesBlack[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesBlack[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
	
				});
	
				addScreen.add(bishopW, 1, 0);
				bishopW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesBlack[index].getPositionCol();
					int actualRow = Creator.piecesBlack[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesBlack[index].getSpriteNode());
					Creator.piecesBlack[index] = new Bishop("Bishop", bishop, team);
	
					Creator.piecesBlack[index].setHorizontalMovement(0);
					Creator.piecesBlack[index].setVerticalMovement(0);
					Creator.piecesBlack[index].setTitledMovement(8);
	
					Creator.piecesBlack[index].setPositionCol(actualColumn);
					Creator.piecesBlack[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesBlack[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
	
				});
				addScreen.add(towerW, 2, 0);
				towerW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesBlack[index].getPositionCol();
					int actualRow = Creator.piecesBlack[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesBlack[index].getSpriteNode());
					Creator.piecesBlack[index] = new Tower("Tower", tower, team);
	
					Creator.piecesBlack[index].setHorizontalMovement(8);
					Creator.piecesBlack[index].setVerticalMovement(8);
					Creator.piecesBlack[index].setTitledMovement(0);
	
					Creator.piecesBlack[index].setPositionCol(actualColumn);
					Creator.piecesBlack[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesBlack[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
				});
	
				addScreen.add(knightW, 3, 0);
				knightW.setOnMouseClicked(event -> {
	
					int actualColumn = Creator.piecesBlack[index].getPositionCol();
					int actualRow = Creator.piecesBlack[index].getPositionRow();
	
					board.getChildren().remove(Creator.piecesBlack[index].getSpriteNode());
					Creator.piecesBlack[index] = new Horse("Horse", knight, team);
	
					Creator.piecesBlack[index].setHorizontalMovement(8);
					Creator.piecesBlack[index].setVerticalMovement(8);
					Creator.piecesBlack[index].setTitledMovement(8);
	
					Creator.piecesBlack[index].setPositionCol(actualColumn);
					Creator.piecesBlack[index].setPositionRow(actualRow);
	
					board.add((Creator.piecesBlack[index].getSpriteNode()), actualColumn, actualRow);
					secondStage.close();
				});
	
			}
	
			secondStage.setScene(scene);
			secondStage.show();
	
		}
	
	}
