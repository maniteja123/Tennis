package board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScoreBoard {
	
	private int[] game;
	private int[] set;
	private int[] match;
	private char[] players;

	private final int NO_OF_PLAYERS = 2;
	
	public ScoreBoard(char playerA, char playerB) {
		game = new int[2];
		set = new int[2];
		match = new int[2];
		players = new char[2];
		players[0] = playerA;
		players[1] = playerB;
	}

	private int getCurrentPlayer(char player) {
		if(player == players[0])
			return 0;
		return 1;
	}
	
	private void addPoint(int player) {
		game[player]++; 
		if((game[player] - game[1-player] >= 2) && game[player]>=4) {
			game[player] = game[1 - player] = 0;
			addGame(player);
		}
	}
	
	private void addGame(int player) {
		set[player]++;
		if((set[player] - set[1-player] >= 2) && set[player]>=6) {
			set[player] = set[1 - player] = 0;
			addSet(player);
		}
	}

	private void addSet(int player) {
		match[player]++;
	}

	public void updateScoreBoard(char point) {
		int player = getCurrentPlayer(point);
		addPoint(player);
	}
	
	public String getScoreBoard() {
		String s = new String("");
		for(int i=0; i<NO_OF_PLAYERS; i++) {
			s +=  players[i] +" " + convertGame(i, game[i]) + " " + Integer.toString(set[i]) + " " + Integer.toString(match[i]) +"\n";
		}	
		return s;
	}
	
	private String convertGame(int player, int score) {
		if(score<=2) return Integer.toString(15*score);
		if(game[player] > 3 && game[player] - game[1-player] == 1) return "Adv";
		if(game[player] >= 3 && game[1-player]<=game[player]) return Integer.toString(40);
		return "  ";
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer testCases = Integer.parseInt(br.readLine());
		for(int test=1; test <= testCases; test++) {
			String score = br.readLine();
			System.out.println("Test " + test);
			char[] scores = score.toCharArray();
			ScoreBoard scoreBoard = new ScoreBoard('A', 'B');
			for (char s : scores) {
				scoreBoard.updateScoreBoard(s);
				System.out.println(scoreBoard.getScoreBoard());
			}
		}
	}

}
