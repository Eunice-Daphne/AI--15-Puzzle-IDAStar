
public class Equal {
	public static boolean isEqual(int[] a, int[] b){
		boolean isEqual = true;
		for(int i=0; i<a.length; i++){
			if(a[i] != b[i]){
				isEqual = false;
			}
		}
		return isEqual;
	}
}
