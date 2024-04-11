import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int [][]dataMatrix = new int[3][3];
        int player = 1;

        printMatrix(dataMatrix);

        for(int i=0;i<9;i++){
            System.out.println("Turn: Player " + player);
            dataMatrix = gameTurn(dataMatrix,player);
            printMatrix(dataMatrix);
            if(player==1){
                player++;
            }
            else{
                player--;
            }
            if(i>=4){
                int winner = winnerCheck(dataMatrix);
                if(winner!=0){
                    System.out.println("Player " + winner + " is winner!");
                    break;
                }
            }
        }
        if(winnerCheck(dataMatrix)==0){
            System.out.println("No one has won!");
        }
    }

    static int[][] gameTurn (int [][]datMat,int pl){
        Scanner scanner = new Scanner(System.in);

        int[] coordinates = new int[2];
        for(int i = 0; i<2;i++){
            if(i==0){
                while(true){
                    System.out.println("Enter the x coordinate:");
                    while(true) {
                        try {
                            coordinates[0] = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Input have to be number in range 1-3");
                            scanner.next();
                        }
                    }
                    if (coordinates[0] > 0 && coordinates[0] < 4) {
                        if (datMat[coordinates[0] - 1][0] == 0 || datMat[coordinates[0] - 1][1] == 0 || datMat[coordinates[0] - 1][2] == 0) {
                            break;
                        } else {
                            System.out.println("Invalid input, there is no empty game space in selected row.");
                        }

                    } else {
                        System.out.println("Invalid input, coordinate could be in range: 1-3");
                    }


                }

            }
            else{
                while(true){
                    System.out.println("Enter the y coordinate:");
                    while(true) {
                        try {
                            coordinates[1] = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Input have to be number in range 1-3");
                            scanner.next();
                        }
                    }

                    if(coordinates[1]>0&&coordinates[1]<4){
                        if(datMat[coordinates[0]-1][coordinates[1]-1]==0){
                            break;
                        }
                        else{
                            System.out.println("Invalid input, there is no empty game space in selected coordinates.");
                        }

                    }
                    else{
                        System.out.println("Invalid input, coordinate could be in range: 1-3");
                    }
                }

            }
        }

        if(pl == 1){
            datMat[coordinates[0]-1][coordinates[1]-1]=1;
        }
        else{
            datMat[coordinates[0]-1][coordinates[1]-1]=-1;
        }
        return datMat;
    }

    static void printMatrix(int[][]datMat){

        String head1 = "Coordinates" + "\t" + "| y: 1" + "\t" + "| y: 2" + "\t" + "| y: 3"+ "\t" + "|";
        String head2 = "------------|-----------------------|";

        System.out.println(head1);
        System.out.println(head2);

        for(int i=0;i<3;i++){
            System.out.println("\t x: "+ (i+1) + "\t" + "|" + "\t" + displayRes(datMat[i][0]) + "\t" + "|"+ "\t"
                    + displayRes(datMat[i][1]) + "\t" + "|"+ "\t" + displayRes(datMat[i][2]) + "\t" + "|");
            System.out.println("- - - - - - |-----------------------|");
        }
    }

    static String displayRes(int value){
        if(value == 0){
            return "";
        }
        else if(value == 1){
            return "X";
        }
        else if(value == -1){
            return "O";
        }
        else{
            return "-";
        }
    }

    static int winnerCheck(int[][]datMat){
        //column check
        for(int i=0;i<3;i++){
            int column = datMat[0][i] + datMat[1][i] + datMat[2][i];

            if (column == 3){
                // player 1 is winner
                return 1;

            }
            else if (column == -3){
                // player 2 is winner
                return 2;

            }

        }

        //row check
        for(int i=0;i<3;i++){
            int row = datMat[i][0] + datMat[i][1] + datMat[i][2];

            if (row == 3){
                // player 1 is winner
                return 1;

            }
            else if (row == -3){
                // player 2 is winner
                return 2;

            }

        }

        //diagonal check
        int diagonal1 = datMat[0][0] + datMat[1][1] + datMat[2][2];
        int diagonal2 = datMat[0][2] + datMat[1][1] + datMat[2][0];
        if(diagonal1 == 3 || diagonal2 == 3){
            // player 1 is winner
            return 1;
        }
        else if (diagonal1 == -3 || diagonal2 == -3){
            // player 2 is winner
            return 2;
        }


        // nobody is winner
        return 0;
    }
}