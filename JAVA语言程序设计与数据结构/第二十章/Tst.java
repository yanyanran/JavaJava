public class Tst {
    public static void main(String args[]){
        double[][] x = {{3, 1}, {2, -1}, {2, 0}};
        java.util.Arrays.sort(x, (e1, e2) -> (int)(e1[1] - e2[1]));
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }

    }
}