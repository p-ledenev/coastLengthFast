import java.io.*;
import java.util.*;

/**
 * Created by ledenev.p on 29.07.2015.
 */
public class Runner {

    public static Square square;

    public static void main(String[] args) throws Throwable {

//        Date dateStart = new Date();

        BufferedReader input;

        if (args.length > 0)
            input = new BufferedReader(new FileReader(args[0]));
        else
            input = new BufferedReader(new InputStreamReader(System.in));

        square = new Square(input);

//        Date dateEnd = new Date();
//        System.out.println("Time reading: " + (dateEnd.getTime() - dateStart.getTime()) / 1000. + " sec.");

        Stack<Integer> stack = fillStack();

        int coastLength = 0;
        while (!stack.isEmpty()) {

            int index = stack.pop();

            if (square.isVisited(index))
                continue;

            coastLength += square.lengthFor(index);
            if (square.isWater(index))
                square.visited(index);

            pushNeighbors(stack, index);
        }

//        dateEnd = new Date();
//        System.out.println("Time execution: " + (dateEnd.getTime() - dateStart.getTime()) / 1000. + " sec.");

        System.out.println(coastLength);
    }

    private static void pushNeighbors(Stack<Integer> stack, int index) {

        int left = square.getLeftNeighborFor(index);
        square.addTo(stack, left);

        int right = square.getRightNeighborFor(index);
        square.addTo(stack, right);

        int top = square.getTopNeighborFor(index);
        square.addTo(stack, top);

        int bottom = square.getBottomNeighborFor(index);
        square.addTo(stack, bottom);
    }

    public static Stack<Integer> fillStack() {
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < square.getColumnsAmount(); i++)
            stack.push(square.indexFor(0, i));

        for (int i = 0; i < square.getColumnsAmount(); i++)
            stack.push(square.indexFor(square.getRowsAmount() - 1, i));

        for (int i = 1; i < square.getRowsAmount() - 1; i++)
            stack.push(square.indexFor(i, 0));

        for (int i = 1; i < square.getRowsAmount() - 1; i++)
            stack.push(square.indexFor(i, square.getColumnsAmount() - 1));

        return stack;
    }


}
