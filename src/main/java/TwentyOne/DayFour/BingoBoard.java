package TwentyOne.DayFour;

import java.util.List;

public class BingoBoard {
    int size;
    Node[][] grid;

    public BingoBoard(int s) {
        this.size = s;
        grid = new Node[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++)
                grid[y][x] = new Node();
        }
    }

    public void light(int n) {
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                if (grid[y][x].value == n)
                    grid[y][x].lit = true;
    }

    public void setRow(int i, List<Integer> row) {
        for (int x = 0; x < row.size(); x++) {
            grid[i][x].value = row.get(x);
        }
    }

    public boolean check() {
        for (int x = 0; x < size; x++) {
            if (checkCol(x))
                return true;
        }
        for (int y = 0; y < size; y++) {
            if (checkRow(y))
                return true;
        }
        return false;
    }

    public boolean checkCol(int c) {
        for (int y = 0; y < size; y++) {
            if (!grid[y][c].lit)
                return false;
        }
        return true;
    }

    public boolean checkRow(int r) {
        for (int x = 0; x < size; x++) {
            if (!grid[r][x].lit)
                return false;
        }
        return true;
    }

    public String toString() {
        String output = "";
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                output += grid[y][x].lit ? "*" : " ";
                output += grid[y][x].value;
                output += grid[y][x].lit ? "*" : " ";
            }
            output += "\n";
        }
        return output;
    }

    public int sumUnlit() {
        int sum = 0;
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                if (!grid[y][x].lit)
                    sum += grid[y][x].value;
        return sum;
    }
}

