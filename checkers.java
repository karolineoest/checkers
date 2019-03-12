import java.util.Scanner;

//Create exception for invalid moves
class InvalidMove extends Exception {
private static final long serialVersionUID = -1654512517547585580L;
public InvalidMove(String message) {
	//Display relevant message
	super(message);
}
}
//Create class for the player
class Player{
	//the name of the player
	public String name;
	public Player(String name) {
		this.name = name;
}
}

//Clas for positions consisting of x and y coordinates
class Position{
	//get x or y coordinate
	public int x;
	public int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//method to check if a position is in bounds
	public void validate() throws InvalidMove{
		if(x<0|x>7|y<0|y>7) {
			//throw exception if out of bounds
			throw new InvalidMove("Error! Coordinates out of bounds.");
			
		}

	}
	
}

//class for the board
class Board{
	//define the start board
	public String[][]board = {{"  ","1 ","  ","1 ","  ","1 ","  ","1 "},{"1 ","  ","1 ","  ","1 ","  ","1 ","  "},{"  ","1 ","  ","1 ","  ","1 ","  ","1 "},{"  ","  ","  ","  ","  ","  ","  ","  "},{"  ","  ","  ","  ","  ","  ","  ","  "},{"2 ","  ","2 ","  ","2 ","  ","2 ","  "},{"  ","2 ","  ","2 ","  ","2 ","  ","2 "},{"2 ","  ","2 ","  ","2 ","  ","2 ","  "}};
	//method to check of a certain piece is standing on a given position
	public void checkPiece(String s,Position p) throws InvalidMove {
		if (board[p.y][p.x]!=s) {
//throw exception if piece is not standing on the position
			throw new InvalidMove("Error! no piece of yours standing on the given coordinates.");
		}
	}
	//method to move from one position to another
	public void move(Position cpos,Position npos)throws InvalidMove{
		//the piece standing on the starting position
		String cpiece = board[cpos.y][cpos.x];
		//if it is a piece from player number 2
		if (board[npos.y][npos.x]!= "1 "&board[npos.y][npos.x]!="2 "&  Math.abs(npos.x-cpos.x) == 1 ){
			if((cpiece=="2 "&npos.y-cpos.y == -1)|(cpiece=="1 "&npos.y-cpos.y == 1)) {
				board[npos.y][npos.x]= ""+cpiece;
				board[cpos.y][cpos.x]= "  ";	
		}
			else{
				throw new InvalidMove("Error. This position cannot be moved to");
				}
		}
			//throw exception if move is invalid
		else{
			throw new InvalidMove("Error. This position cannot be moved to");
			}
	}
		//method to print the board
	public void print() {
		System.out.println("  0 1 2 3 4 5 6 7 ");
		System.out.println("+----------------+");
		for (int i=0; i<8; i++) {
			System.out.println(i+"|"+board[i][0]+board[i][1]+board[i][2]+board[i][3]+board[i][4]+board[i][5]+board[i][6]+board[i][7]+"|");
		}
		System.out.println("+----------------+");
		System.out.println("  0 1 2 3 4 5 6 7 ");
	}
}

//Play checkers
public class checkers {
	public static void main(String[] args)throws InvalidMove{
		//create players
		Player p1 = new Player("1 ");
		Player p2 = new Player("2 ");
		//create board
		Board b = new Board();
		//start scanner
		Scanner s = new Scanner(System.in);
		//variable for the current player
		Player p;
		//check whose turn it is
		for(int i=1;i<10000;i++) {
			b.print();
			if (i%2==0) {
				System.out.println("Turn of player:"+ p2.name);
				p=p2;
				
			}else {System.out.println("Turn of player: "+ p1.name);p=p1;}
			//variable for the current position
			Position cp;
			while(true) {
			//get coordinates to move from
				System.out.println("Enter coordinate of piece to move: ");
				System.out.println("Enter X: ");
				int cx = s.nextInt();
				System.out.println("Enter Y: ");
				int cy = s.nextInt();
				cp = new Position(cx,cy);
				//check validity
				try {
					cp.validate();
					b.checkPiece(p.name, cp);
					break;
			//catch exception
			}catch (InvalidMove inm) {
					System.out.println(inm.getMessage());
				}
			}
			while (true){
				//get coordinates to move to
				System.out.println("Enter coordinate to move to: ");
				System.out.println("Enter X: ");
				int nx = s.nextInt();
				System.out.println("Enter Y: ");
				int ny = s.nextInt();
				Position np = new Position(nx,ny);
				//validate new position
				try {
				np.validate();
				b.move(cp, np);
				//Proceed to next round if move is valid
				break;
				//catch exception
				}catch (InvalidMove inm){
					System.out.println(inm.getMessage());
				}
	}

}
}
}
