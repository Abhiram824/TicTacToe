
import java.util.Scanner;
import java.util.ArrayList;
public class GameRunner
{

    public static void main(String [] args)
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Would you like to play Tic Tac Toe (Y/N)?  ");
        String answer = kb.nextLine();
        int mode = -1;

        if(answer.equals("Y"))
        {
            System.out.print("\n\nChose a mode to play\n1) Play against another person\n2) Play the unbeatable AI  ");
            mode = kb.nextInt();
            TicTacToe game = new TicTacToe();
            if(mode == 1)
            {
                boolean gameOver = game.getGameOver();
                String [] choices = {"X", "O"};
                int turn = 0;
                String result = "";
                while(!gameOver)
                {
                    System.out.print("\n\n" + game.getBoard() + "\n\nEnter the number spot where you want to place the " + choices[turn %2] + " ");
                    int spot = kb.nextInt();
                    game.setBoard(game.getGame(), choices[turn %2], spot/3, spot%3);
                    turn++;
                    result = game.checkFinished(game.getGame());
                    gameOver = game.getGameOver();
                }
                System.out.println("\n" + game.getBoard() + "\n" + result + " wins");
            }
            if(mode == 2)
            {
                boolean gameOver = game.getGameOver();
                String [] choices = {"X", "O"};
                int turn = 0;
                String result = "";

                while(!gameOver)
                {
                    if(turn % 2 == 0)
                    {
                        System.out.println(game.openSpaces(game.getGame()));
                        System.out.print("\n\n" + game.getBoard() + "\n\nAI's turn to place the " + choices[turn %2] + " ");
                        int computerChoice = game.miniMax(game.getGame(), true)[1];
                        System.out.println(computerChoice);
                        game.setBoard(game.getGame(), choices[turn %2], computerChoice/3, computerChoice%3);
                    }
                    else
                    {
                        System.out.print("\n\n" + game.getBoard() + "\n\nEnter the number spot where you want to place the " + choices[turn %2] + " ");
                        int spot = kb.nextInt();
                        game.setBoard(game.getGame(), choices[turn %2], spot/3, spot%3);
                    }
                    turn++;
                    result = game.checkFinished(game.getGame());
                    gameOver = game.getGameOver();

                }
                System.out.println("\n" + game.getBoard() + "\n" + result + " wins");
            }

        }
        else
        {
            System.out.println("\nHave a nice day!");

        }

    }
    public static String [] [] makeCopy(String [] [] orig)
    {
        String [] [] copy = new String [orig.length][orig[1].length];
        for (int r = 0; r<copy.length; r++)
        {
            for(int c = 0; c < copy[0].length; c++)
            {
                copy[r][c] = orig[r][c];
            }
        }
        return copy;
    }
}
