import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
Preorder : 전위
Inorder : 중위
Postoder: 후위
 */
public class Main {

    public static class Node{
        char value;
        Node left;
        Node right;
        Node(char value){
            this.value = value;
        }
    }
    static StringBuilder sb;
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node((char) ('A'+i));
        }


        char root, left, right;
        int idx;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            root = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);

            idx = root - 'A';
            if (left != '.') nodes[idx].left  = nodes[left-'A'];
            if (right != '.') nodes[idx]. right = nodes[right - 'A'];
        }

        preorder(nodes[0]);
        sb.append("\n");

        inorder(nodes[0]);
        sb.append("\n");

        postorder(nodes[0]);

        System.out.println(sb);

}

    private static void postorder(Node node) {
        if(node == null) return;

        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);

    }

    private static void inorder(Node node) {
        if(node == null) return;

        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);

    }

    private static void preorder(Node node) {
        if(node == null) return;

        sb.append(node.value);
        preorder(node.left);
        preorder(node.right);
    }
}
