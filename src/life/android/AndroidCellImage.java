package life.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import life.board.Cell;
import life.board.CellListener;
import life.board.GameBoard;
import life.board.TickListener;

import java.util.HashMap;
import java.util.Map;

public class AndroidCellImage implements CellListener
{
    private final SurfaceHolder surfaceHolder;
    private final Cell cell;
    private final float[] pixelArray = new float[2 * pixelsSquarePerCell * pixelsSquarePerCell];
    private int ticksInState;
    public static int pixelsSquarePerCell = 4;
    public static final Map<Integer, Integer> colourTransitions = new HashMap<Integer, Integer>();


    class Pixel
    {
        final int x;
        final int y;

        Pixel(int row, int col)
        {
            this.x = (cell.getRow() - 1) * pixelsSquarePerCell + row;
            this.y = (cell.getColumn() - 1) * pixelsSquarePerCell + col;
        }

        @Override
        public String toString()
        {
            return "Pixel [" + x + ", " + y + "]";
        }
    }

    AndroidCellImage(Cell cell, SurfaceHolder surfaceHolder, GameBoard board)
    {
        this.cell = cell;
        this.surfaceHolder = surfaceHolder;
        cell.addCellListener(this);
        setPixels();
        board.addTickListener(new TickListener()
        {
            public void boardHasTicked()
            {
                boardTicker();
            }
        });
    }

    void boardTicker()
    {
        ticksInState++;
        if (cell.isAlive() && colourTransitions.keySet().contains(ticksInState))
            paintPixels(colourTransitions.get(ticksInState));
    }

    public void listenedToCellHasComeToLife()
    {
        paintPixels(Color.GREEN);
        ticksInState = 0;
    }

    public void listenedToCellHasDied()
    {
        paintPixels(Color.BLACK);
        ticksInState = 0;
    }

    private void setPixels()
    {
        int idx = 0;
        for (int row = 0; row < pixelsSquarePerCell; row++)
        {
            for (int col = 0; col < pixelsSquarePerCell; col++)
            {
                Pixel pixel = new Pixel(row, col);
                pixelArray[idx++] = pixel.x;
                pixelArray[idx++] = pixel.y;
            }
        }
    }

    private void paintPixels(int rgb)
    {
        Paint paint = new Paint();
        paint.setColor(rgb);
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawPoints(pixelArray, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
