import java.util.Stack;
public class IDAStar {
	private Stack<Frame> stack = new Stack<Frame>();
	private int statesExpanded = 0;
	private boolean finished = false;
	private Frame solutionFrame;
	private int row;
	private int coloumn;

	
	public IDAStar(int row, int coloumn) {
		this.row = row;
		this.coloumn = coloumn;
	}
	public void solve(int[] puzzle){
	
		int depth = 0;
		long startTime = System.currentTimeMillis();
		Frame root = new Frame( puzzle, this.row, this.coloumn);
		depth = root.getHeuristic();
		while(!this.finished){
			stack.push(root);
			idaStar(depth,startTime);
			depth+=2;
		}
		System.out.println("Number of Nodes expaned: " + this.statesExpanded);
		long duration = (System.currentTimeMillis()-startTime) / 1;
		System.out.println("Time Taken: " + Long.toString(duration) + "ms");
	}
//Solving IDAStar 
	public void idaStar(int depthlimit, long startTime){
	
		while(!stack.isEmpty() && System.currentTimeMillis()-startTime<=300000000){
		Frame state = stack.pop();
		if(state.checkSolution()){
				this.finished = true;
				this.solutionFrame = state;
				stack.clear();
			}	
			if(depthlimit >= state.getHeuristic()){
 			if(state.isParentParent()){
					stack = state.getChildren(stack);
					this.statesExpanded++;
				}
			}
		}
		if(System.currentTimeMillis()-startTime>300000000)
		{
			System.out.println("No solution");
		}
	}
//Print the Solution	
	public void printSolution() {		
		Stack<Frame> solutionStack = new Stack<Frame>();
		Frame state = this.solutionFrame;	
		while(state != null) {
			solutionStack.push(state);
			state = state.getParent();
		}
		System.out.print("Moves: ");
		while(!solutionStack.isEmpty()) {
			
			Frame sSate = solutionStack.pop();
			if( sSate.getMoveDirection() == Frame.MoveDirection.LEFT ) {
				System.out.print("L");
			}else if( sSate.getMoveDirection() == Frame.MoveDirection.RIGHT ) {
				System.out.print("R");
			}else if( sSate.getMoveDirection() == Frame.MoveDirection.UP ) {
				System.out.print("U");
			}else if( sSate.getMoveDirection() == Frame.MoveDirection.DOWN ) {
				System.out.print("D");
			}else if( sSate.getMoveDirection() == Frame.MoveDirection.ROOT ) {
			}	
			}		
		long aftermem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("\nMemory used: " + (aftermem/1024)+"kb");
		
	}
}
