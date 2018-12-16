package pack1;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

	private final int row = 3;
	private final int col = 3;
	private char[][] board = new char[row][col];
	boolean currentPlayerX = true;
	boolean turnOne = true;
	boolean isTieGame = false;
	Scanner s = new Scanner(System.in);

	public void startGame() {
		isTieGame = false;
		createBoard();
		playerX();
	}

	// create a 3x3 grid with a '-' filled in each slot
	private char[][] createBoard() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				board[r][c] = '-';
			}
		}
		return board;
	}

	// draws a 3x3 grid
	private void drawBoard() {
		for (int i = 0; i < row; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}

	// updates board based on whoever is playing
	// also checks for overlaps
	private void changeBoardState() {
		System.out.print("row: ");
		int tempRow = s.nextInt();
		System.out.print("col: ");
		int tempCol = s.nextInt();

		if (board[tempRow - 1][tempCol - 1] == '-') {
			if (currentPlayerX == true) {
				board[tempRow - 1][tempCol - 1] = 'X';
			} else {
				board[tempRow - 1][tempCol - 1] = 'Y';
			}
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Spot is already occupied!");
			if (currentPlayerX == true) {
				playerX();
			} else {
				playerY();
			}
		}
	}

	// player X properties
	private void playerX() {
		if (turnOne == true) {
			System.out.println("Player X, please give a coordinate (row, col) to place your first marker!");
		} else {
			System.out.println("Player X's Turn");
		}

		drawBoard();
		currentPlayerX = true;
		changeBoardState();
		turnOne = false;

		if (isGameOver() == false) {
			playerY();
		} else if (isGameOver() == true && isTieGame == true) {
			System.out.println("Cat Game!");
			newGame();
		} else if (isGameOver() == true && isTieGame == false) {
			System.out.println("Game Over! - Player X Wins!");
			newGame();
		}
	}

	// player Y properties
	private void playerY() {
		System.out.println("Player Y's Turn");

		drawBoard();
		currentPlayerX = false;
		changeBoardState();

		if (isGameOver() == false) {
			playerX();
		} else if (isGameOver() == true && isTieGame == true) {
			System.out.println("Cat Game!");
			drawBoard();
			newGame();
		} else if (isGameOver() == true && isTieGame == false) {
			System.out.println("Game Over! - Player Y Wins!");
			drawBoard();
			newGame();
		}
	}

	// game over properties
	private boolean isGameOver() {
		// check row similarity
		if ((board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != '-')
				|| (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != '-')
				|| (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != '-')) {
			return true;
		}
		// check column similarity
		else if ((board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != '-')
				|| (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != '-')
				|| (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != '-')) {
			return true;
		}
		// check diagonal similarity
		else if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '-')
				|| (board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != '-')) {
			return true;
		}
		// check if all slots are filled - Cat Game
		else if ((board[0][0] != '-') && (board[1][0] != '-') && (board[2][0] != '-') && (board[0][1] != '-')
				&& (board[1][1] != '-') && (board[2][1] != '-') && (board[0][2] != '-') && (board[1][2] != '-')
				&& (board[2][2] != '-')) {
			isTieGame = true;
			return true;
		} else {
			return false;
		}
	}

	// new game properties
	private void newGame() {
		System.out.println("New Game? (y/n)");
		String newGameChoice = s.next();
		if (newGameChoice.equals("y")) {
			System.out.println();
			startGame();
		} else {
			System.out.println("Thanks for playing!");
		}
	}
}
