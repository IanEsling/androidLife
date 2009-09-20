package life;

import static junit.framework.Assert.assertEquals;
import life.board.Cell;
import life.board.GameBoard;
import life.ui.LifeRunner;
import org.junit.Test;

/**
 */
public class TestRunner
{
    private LifeRunner testee;

    @Test
    public void liveCellCountWhenRandomising()
    {
        testee = new LifeRunner();
        testee.setRandomCellsAlive();
        assertEquals(((GameBoard.defaultRows * GameBoard.defaultColumns) / 100 * testee.percentageOfBoardCells), numberOfLiveCells());
    }

    int numberOfLiveCells()
    {
        int liveCells = 0;
        for (Cell cell : testee.getBoard().getCells())
        {
            if (cell.isAlive()) liveCells++;
        }
        return liveCells;
    }
}
