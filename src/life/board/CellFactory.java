package life.board;

import java.util.ArrayList;
import java.util.List;

/**
 */
class CellFactory
{
    private CellFactory() {}

    public static List<Cell> createCellsForGameBoard(GameBoard board)
    {
        return createListOfCells(board);
    }

    private static List<Cell> createListOfCells(GameBoard board)
    {
        List<Cell> cells = new ArrayList<Cell>();
        for (int row = 1; row <= board.totalRows; row++)
        {
            for (int column = 1; column <= board.totalColumns; column++)
            {
                cells.add(new Cell(row, column));
            }
        }
        return makeCellsNeighbourAware(board, cells);
    }

    private static List<Cell> makeCellsNeighbourAware(GameBoard board, List<Cell> cells)
    {
        Cell[][] cellArray = new Cell[board.totalRows][board.totalColumns];
        for (Cell cell : cells)
        {
            cellArray[cell.getRow() - 1][cell.getColumn() - 1] = cell;
        }

        for (Cell cell : cells)
        {
            for (int row = startIndex(cell.getRow()); row <= endRow(cell.getRow(), board); row++)
            {
                for (int column = startIndex(cell.getColumn()); column <= endColumn(cell.getColumn(), board); column++)
                {
                    if (!(cell.getRow() == row && cell.getColumn() == column))
                    {
                        addCellListener(cellArray, cell, row, column);
                    }
                }
            }
        }

        return cells;
    }

    private static int endColumn(int cellColumn, GameBoard board)
    {
        return (board.totalColumns == cellColumn ? board.totalColumns : cellColumn + 1);
    }

    private static int endRow(int cellRow, GameBoard board)
    {
        return (board.totalRows == cellRow ? board.totalRows : cellRow + 1);
    }

    private static int startIndex(int idx)
    {
        return (idx == 1 ? 1 : idx - 1);
    }

    private static void addCellListener(Cell[][] cells, Cell cell, int row, int column)
    {
        cell.addCellListener(cells[row - 1][column - 1]);
    }
}
