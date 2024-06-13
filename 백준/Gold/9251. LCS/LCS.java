import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static String inp1,inp2;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        inp1 = bf.readLine();
        inp2 = bf.readLine();
        char[] a = new char[inp1.length()+1];
        char[] b = new char[inp2.length()+1];
        for (int i = 0; i < inp1.length(); i++) {
            a[i+1] = inp1.charAt(i);
        }
        for (int i = 0; i < inp2.length(); i++) {
            b[i+1] = inp2.charAt(i);
        }

        System.out.println(lcs(a,b));
    }

    private static int lcs(char[] x, char[] y){
        int m,n;
        m = x.length;
        n = y.length;
        int[][] matrix = new int[m][n];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j <n; j++) {
                if(x[i]==y[j]){
                    matrix[i][j] = matrix[i-1][j-1]+1;
                }else{
                    matrix[i][j] = Math.max(matrix[i][j-1],matrix[i-1][j]);
                }
            }
        }
        return matrix[m-1][n-1];

    }

//    private static void printMatrix(int[][] matrix){
//        for (int[] ints : matrix) {
//            System.out.println(Arrays.toString(ints));
//        }
//    }
}

