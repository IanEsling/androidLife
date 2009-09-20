package life.android;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import life.board.Cell;
import life.board.GameBoard;

public class LifeView extends SurfaceView implements SurfaceHolder.Callback
{
    private boolean gameIsRunning;
    private GameBoard board;
    private SurfaceHolder surfaceHolder;
    public Integer percentageOfBoardCells = 10;

    public LifeView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        Log.i(this.getClass().getName(), "surface holder: " + surfaceHolder.toString());
        Log.i(this.getClass().getName(), "starting life view...");
        Canvas c = surfaceHolder.lockCanvas();
        Log.i(this.getClass().getName(), "creating board with canvas: " + c);
        Log.i(this.getClass().getName(), "board height: " + c.getHeight());
        Log.i(this.getClass().getName(), "board width: " + c.getWidth());
        board = new GameBoard(75, 100);
        setRandomCellsAlive();
        surfaceHolder.unlockCanvasAndPost(c);
        Log.i(this.getClass().getName(), "created board...");
        Log.i(this.getClass().getName(), "creating cell images...");
        createCellImages();
        gameIsRunning = true;
        new Thread(new GameRunner()).start();
    }

    private void createCellImages()
    {
        for (Cell cell : board.getCells())
        {
            new AndroidCellImage(cell, surfaceHolder, board);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {
    }

    class GameRunner implements Runnable
    {
        @SuppressWarnings({"CallToPrintStackTrace"})
        public void run()
        {
            Log.i(this.getClass().getName(), "starting to run...");
            while (gameIsRunning)
            {
                Log.i(this.getClass().getName(), "ticking board...");
                board.tick();
                try
                {
                    Thread.sleep(25);
                } catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void setRandomCellsAlive()
    {
        for (Cell cell : board.getCells())
        {
            cell.setAlive(false);
        }

        for (int i = 0; i < (board.getCells().size() / 100) * percentageOfBoardCells; i++)
        {
            boolean aliveCell = true;
            while (aliveCell)
            {
                Cell cell = getRandomCell(board);
                if (!cell.isAlive())
                {
                    cell.setAlive(true);
                    aliveCell = false;
                }
            }
        }
    }

    private static Cell getRandomCell(GameBoard board)
    {
        return board.getCells().get((int) (Math.random() * board.getCells().size()));
    }

}
