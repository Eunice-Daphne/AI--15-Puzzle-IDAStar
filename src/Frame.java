import java.util.Stack;

public class Frame {
	public enum MoveDirection{
		ROOT, UP, DOWN, LEFT, RIGHT
	}

	private int[] puzzle;
	private int heuristic;
	private int row;
	private int col;
	private int deep;
	private Frame parent;
	private MoveDirection moveDirection = MoveDirection.ROOT;


	public Frame(int[] puzzle, int row, int col){
		this.puzzle = new int[row*col];
		this.col = col;
		this.row = row;
		this.puzzle = puzzle;
		this.heuristic = calculateTotManhanttanDist(this);
	}


	private Frame(Frame parentFrame){
		this.puzzle = parentFrame.puzzle.clone();
		this.col = parentFrame.col;
		this.row = parentFrame.row;
		this.deep = parentFrame.deep + 1;
	}

//Check for the Solution state
	public boolean checkSolution(){

		boolean success = true;

		for(int i=0; i<puzzle.length-1; i++){
			if(puzzle[i] != i+1){
				success = false;
				break;
			}
		}
		return success;
	}
	public Stack<Frame> getChildren(Stack<Frame> queue){

		int x = getX( getBlankPosition() );
		int y = getY( getBlankPosition() );
		//blank is left top
		if((x == 0) && (y == 0)){
			queue.add(moveRight(x, y));
			queue.add(moveDown(x, y));
			//blank is right top
		} else if((x == row-1) && (y == 0)){
			queue.add(moveLeft(x, y));
			queue.add(moveDown(x, y));
			//blank is top
		} else if(y == 0){
			queue.add(moveLeft(x, y));
			queue.add(moveRight(x, y));
			queue.add(moveDown(x, y));
			//blank is left bottom
		} else if((x == 0) && (y == col-1)){
			queue.add(moveRight(x, y));
			queue.add(moveUp(x, y));
			//blank is right bottom
		} else if((x == row-1) && (y == col-1)){
			queue.add(moveLeft(x, y));
			queue.add(moveUp(x, y));
			//blank is bottom
		} else if(y == col-1){
			queue.add(moveLeft(x, y));
			queue.add(moveRight(x, y));
			queue.add(moveUp(x, y));
			//blank is left
		} else if(x == 0){
			queue.add(moveDown(x, y));
			queue.add(moveUp(x, y));
			queue.add(moveRight(x, y));
			//blank is right
		} else if(x == row-1){
			queue.add(moveDown(x, y));
			queue.add(moveUp(x, y));
			queue.add(moveLeft(x, y));
			// blank is in the middle
		} else{
			queue.add(moveDown(x, y));
			queue.add(moveUp(x, y));
			queue.add(moveRight(x, y));
			queue.add(moveLeft(x, y));
		}
		return queue;
	}


	public int getHeuristic() {
		return heuristic;
	}
//check if parent node
	public boolean isParentParent(){
		boolean expand = false;
		if(this.deep > 1){
			if(!Equal.isEqual(this.parent.parent.puzzle,this.puzzle)){
				expand = true;
			}
		}else{
			expand = true;
		}
		return expand;
	}

	private Frame moveRight(int x, int y){		
		return swap(x, y, x+1, y, MoveDirection.RIGHT);
	}

	private Frame moveLeft(int x, int y){
		return swap(x, y, x-1, y, MoveDirection.LEFT);
	}

	private Frame moveDown(int x, int y){
		return swap(x, y, x, y+1, MoveDirection.DOWN);
	}

	private Frame moveUp(int x, int y){
		return swap(x, y, x, y-1, MoveDirection.UP);
	}

	private int getX(int bufferPos){
		return bufferPos % row;
	}

	private int getY(int bufferPos){
		return bufferPos / col;
	}

	private int getPosition(int x, int y){
		return y*row + x;
	}

	private int getBlankPosition(){
		int pos = 0;
		for(int i=0; i<puzzle.length; i++){
			if(puzzle[i] == 0){
				pos = i;
				break;
			}
		}
		return pos;
	}


	private Frame swap(int fromX, int fromY, int toX, int toY, MoveDirection move){

		Frame child = new Frame(this);
		child.parent = this;
		int toN = child.puzzle[getPosition(toX, toY)];
		int fromN = child.puzzle[getPosition(fromX, fromY)];
		//swap node
		child.puzzle[getPosition(toX, toY)] = fromN;
		child.puzzle[getPosition(fromX, fromY)] = toN;
		child.moveDirection = move;
		child.heuristic = calculateTotManhanttanDist(child);

		return child;
	}

//Get position of the node
	private int getNodePosition(int node, int[] buffer){
		int pos = -1;
		for(int i=0; i<buffer.length; i++){
			if(node == buffer[i]){
				pos = i;
				break;
			}
		}
		return pos;
	}

//calculate Manhanttan Distance
	private int calculateManhanttanDistance(int node, int[] buffer){

		int heuristic = 0;

		if(node != 0){
			int bufferPos = getNodePosition(node, buffer);

			int xIst = getX(bufferPos);
			int yIst = getY(bufferPos);

			int xSoll = getX(node-1);
			int ySoll = getY(node-1);

			heuristic = Math.abs(xIst - xSoll) + Math.abs(yIst - ySoll);
		}
		return heuristic;
	}

	private int calculateTotManhanttanDist(Frame Frame){

		int childHeuristic = 0;

		for(int i=0; i<Frame.puzzle.length; i++){
			childHeuristic = childHeuristic + calculateManhanttanDistance(Frame.puzzle[i], Frame.puzzle);
		}

		return childHeuristic + Frame.deep;
	}
	
	public MoveDirection getMoveDirection() {
		return this.moveDirection;
	}
	
	public int[] getPuzzle() {
		return this.puzzle;
	}
	
	public Frame getParent() {
		return this.parent;
	}
}
