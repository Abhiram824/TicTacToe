import java.util.ArrayList;
import java.lang.Integer;

public class TicTacToe
{
    private String [][] game;
    private boolean gameOver;
    private int leastScenarios;



    public TicTacToe()
    {
        game = new String [][]{{"0", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"}};
        gameOver = false;
        leastScenarios = Integer.MAX_VALUE;
    }
    public boolean getGameOver()
    {
        return gameOver;
    }
    public void setBoard( String [] [] board, String symbol, int row, int col)
    {
        board[row][col] = symbol;
    }
    public String [] [] getGame()
    {
        return game;
    }
    public String  getBoard()
    {
        String space = "  ";
        String board = "";
        for(int r = 0; r< game.length; r++)
        {
            for(int c = 0; c < game[0].length; c++)
            {
                board += game[r][c] + space;
            }
            board += "\n";
        }

        return board;
    }
    public int makePos(boolean pos, int num)
    {
        if(pos)
            return Math.abs(num);
        return Math.abs(num) * -1;
    }

    public ArrayList <String>  openSpaces(String [][] board)
    {
        ArrayList <String> openSpaces = new ArrayList <>();
        for(int r = 0; r< board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(!board[r][c].equals("X")  && !board[r][c].equals("O"))
                {
                    String str = board[r][c];
                    openSpaces.add(str);
                }
            }
        }
        return openSpaces;
    }

    public  String checkFinished(String [][] board)
    {
        if(board[0][0].equals(board[1][1]) && board[2][2].equals(board[1][1]) || (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])))
        {
            gameOver  = true;
            return board[1][1];
        }


        for(int r = 0; r < game.length; r++)
        {
            if(board[r][0].equals(board[r][1]) && board[r][1].equals(board[r][2]))
            {
                gameOver = true;
                return board[r][1];
            }
            if(board[0][r].equals(board[1][r]) && board[2][r].equals(board[1][r]))
            {
                gameOver = true;
                return board[1][r];
            }

        }
        if(openSpaces(board).size() == 0)
        {
            gameOver = true;
            return "Tie";
        }
        gameOver = false;
        return "Not Over";
    }

    public int vals (String [] [] board)
    {
        if(checkFinished(board).equals("X"))
            return 1;
        if(checkFinished(board).equals("O"))
            return -1;
        return 0;
    }

    public int [] miniMax(String [] [] board, boolean maximizing)//, int scenarios)
    {
        //scenarios++;
        checkFinished(board);
        if(gameOver)
        {
            int [] result = {vals(board), 0,};// scenarios};
            return result;
        }
        String symbol = "";
        String optimalPos = "";
        int optimalVal;

        if(maximizing)
        {
            optimalVal  = -1000;
            symbol = "X";
        }
        else
        {
            optimalVal  = 1000;
            symbol = "O";
        }
        ArrayList <String> potentialMoves = openSpaces(board);
        for(String item: potentialMoves)
        {
            String [] [] copy = makeCopy(board);
            setBoard(copy, symbol, Integer.parseInt(item)/3, Integer.parseInt(item)%3);
            int minMax = miniMax(copy, !maximizing)[0]; // scenarios);
            //int minMax = miniMaxArray[0];
            //int numScenarios = miniMaxArray[2];
            //numScenarios /= 100;
            if(maximizing &&  minMax > optimalVal)// && leastScenarios > numScenarios )
            {
                optimalVal = minMax;
                optimalPos = item;
                //leastScenarios = numScenarios;
            }
            if(!maximizing && minMax < optimalVal)// && leastScenarios > numScenarios)
            {
                optimalVal = minMax;
                optimalPos = item;
                //leastScenarios = numScenarios;
            }
            //scenarios = 0;

        }

        return new int[]{optimalVal, Integer.parseInt(optimalPos)};// scenarios};
    }
    public String [] [] makeCopy(String [] [] orig)
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
