import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
 
public class Main {
	
	public static long N;
	public static long answer = 0;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Long.parseLong(st.nextToken());
    	
    	binarySearch();
    	System.out.println(answer);
    }
    
    public static void binarySearch() {
    	long start = 0;
    	long end = N;
    	
    	while(start <= end) {
    		long middle = ( start + end ) / 2;
    		    		
    		if( Math.pow(middle, 2) >= N ) {
    			answer = middle;
    			end = middle - 1;
    		} else  {
    			start = middle + 1;
    		}
    	}
    }
 
}