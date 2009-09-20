package life.board;

import life.rules.RuleHandler;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class GameBoard
{
    private final List<Cell> board;
    final int totalRows;
    final int totalColumns;
    public List<RuleHandler> rules = new ArrayList<RuleHandler>();
    private final List<TickListener> tickListeners = new ArrayList<TickListener>();
    public final static int defaultRows = 300, defaultColumns = 300;

    public GameBoard()
    {
        this(defaultRows, defaultColumns);
    }

    public GameBoard(int rows, int columns)
    {
        totalColumns = columns;
        totalRows = rows;
        board = CellFactory.createCellsForGameBoard(this);
        setRules();
    }

    public void addTickListener(TickListener listener)
    {
        tickListeners.add(listener);
    }

    protected void setRules()
    {
        rules = RuleFactory.allRules();
    }

    public List<Cell> getCells()
    {
        return board;
    }

    public void tick()
    {
        for (Cell cell : board)
        {
            applyRules(cell);
        }
        for (Cell cell : board)
        {
            cell.applyNewState();
        }
        for (TickListener listener : tickListeners)
        {
            listener.boardHasTicked();
        }
    }

    private void applyRules(Cell cell)
    {
        for (RuleHandler rule : rules)
        {
            if (rule.isEligible(cell))
            {
                rule.applyRule(cell);
            }
        }
    }

    public Cell getCell(Cell cell)
    {
        if (!board.contains(cell))
        {
            throw new CellNotFoundException(cell);
        }
        return board.get(board.indexOf(cell));
    }
}
