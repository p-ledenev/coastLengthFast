import java.io.*;
import java.util.*;

/**
 * Created by ledenev.p on 27.07.2015.
 */
public class Square {

    private List<String> lines;

    private int rowsAmount;
    private int columnsAmount;

    private int[] data;

    public int getRowsAmount() {
        return rowsAmount;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    public Square(BufferedReader reader) throws Throwable {

        String[] params = reader.readLine().split(" ");
        rowsAmount = Integer.parseInt(params[0]) + 2;
        columnsAmount = Integer.parseInt(params[1]) + 2;

        lines = new ArrayList<String>();

        lines.add(new String(new char[columnsAmount]).replace("\0", "0"));

        String line;
        while ((line = reader.readLine()) != null)
            lines.add("0" + line + "0");

        lines.add(new String(new char[columnsAmount]).replace("\0", "0"));

        fillData();
    }

    private void fillData() throws Throwable {

        data = new int[rowsAmount * columnsAmount];

        int k = 0;
        for (int i = 0; i < rowsAmount; i++) {

            String line = lines.get(i);
            for (int j = 0; j < columnsAmount; j++) {
                data[k++] = Integer.parseInt(Character.toString(line.charAt(j)));
            }
        }
    }

    public int indexFor(int row, int column) {
        return row * columnsAmount + column;
    }

    public int getTopNeighborFor(int index) {
        return index - columnsAmount;
    }

    public int getBottomNeighborFor(int index) {
        return index + columnsAmount;
    }

    public int getLeftNeighborFor(int index) {

        if (index % columnsAmount == 0)
            return -1;

        return index - 1;
    }

    public int getRightNeighborFor(int index) {

        if ((index + 1) % columnsAmount == 0)
            return -1;

        return index + 1;
    }

    public int lengthFor(int index) {

        if (data[index] == 1)
            return 0;

        int length = 0;

        int i = getLeftNeighborFor(index);
        if (exist(i) && isLand(i))
            length++;

        i = getRightNeighborFor(index);
        if (exist(i) && isLand(i))
            length++;

        i = getTopNeighborFor(index);
        if (exist(i) && isLand(i))
            length++;

        i = getBottomNeighborFor(index);
        if (exist(i) && isLand(i))
            length++;

        return length;
    }

    private boolean isLand(int i) {
        return data[i] == 1;
    }

    public void visited(int index) {
        data[index] = -1;
    }

    public boolean isWater(int i) {
        return data[i] == 0;
    }

    public void addTo(Stack<Integer> stack, int index) {

        if (!exist(index) || !isWater(index) || isVisited(index))
            return;

        stack.push(index);
    }

    public boolean exist(int index) {
        return index >= 0 && index < rowsAmount * columnsAmount;
    }

    public boolean isVisited(int index) {
        return data[index] == -1;
    }
}
