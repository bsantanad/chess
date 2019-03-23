package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece {

	private String pieceIndex;
	private int team;
	private int positionCol;
	private int positionRow;
	private Image sprite;
	private ImageView spriteNode;
	private int verticalMovement;
	private int horizontalMovement;
	private int titledMovement;
	private boolean firstTurn = true;

	public Piece(String index, Image sprite, int team) {

		setPieceIndex(index);
		setSprite(sprite);
		setTeam(team);

		// Create the Node for the Sprite
		ImageView tmp = new ImageView(sprite);
		setSpriteNode(tmp);

	}

	public boolean isFirstTurn() {
		return firstTurn;
	}

	public void setFirstTurn(boolean firstTurn) {
		this.firstTurn = firstTurn;
	}

	public String getPieceIndex() {
		return pieceIndex;
	}

	public void setPieceIndex(String pieceIndex) {
		this.pieceIndex = pieceIndex;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public ImageView getSpriteNode() {
		return spriteNode;
	}

	public void setSpriteNode(ImageView spriteNode) {
		this.spriteNode = spriteNode;
	}

	public int getPositionCol() {
		return positionCol;
	}

	public void setPositionCol(int positionCol) {
		this.positionCol = positionCol;
	}

	public int getPositionRow() {
		return positionRow;
	}

	public void setPositionRow(int positionRow) {
		this.positionRow = positionRow;
	}

	public int getVerticalMovement() {
		return verticalMovement;
	}

	public void setVerticalMovement(int verticalMovement) {
		this.verticalMovement = verticalMovement;
	}

	public int getHorizontalMovement() {
		return horizontalMovement;
	}

	public void setHorizontalMovement(int horizontalMovement) {
		this.horizontalMovement = horizontalMovement;
	}

	public int getTitledMovement() {
		return titledMovement;
	}

	public void setTitledMovement(int titledMovement) {
		this.titledMovement = titledMovement;
	}

}
