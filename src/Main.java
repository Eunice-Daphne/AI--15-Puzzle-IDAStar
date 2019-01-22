
public class Main {
	public static long beforemem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	
	public static void main(String[] args) {
	int[] puzzle = new int[16];
	int k = 0;
	for(int i=0; i<16; i++){
			String s = args[k];
			if(s.equals("_"))
					puzzle[i] = 0;
			else
				puzzle[i] = Integer.parseInt(s);
			k++;
		
	}
	IDAStar idasolver = new IDAStar(4,4);
	idasolver.solve(puzzle);
	idasolver.printSolution();
}
}
