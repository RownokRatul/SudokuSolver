import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

public class SudokuSolver {

    private int empty = 0;
    ArrayList<ArrayList<Integer>> plate;

    ArrayList<TreeSet<Integer>> horizontal;
    ArrayList<TreeSet<Integer>> vertical;
    ArrayList<TreeSet<Integer>> cubes;

    public SudokuSolver(int arr[][]) {

        plate = new ArrayList<ArrayList<Integer>>(9);
        horizontal = new ArrayList<TreeSet<Integer>>(9);
        vertical = new ArrayList<TreeSet<Integer>>(9);
        cubes = new ArrayList<TreeSet<Integer>>(9);

        for(int i=0;i<9;i++) {
            ArrayList<Integer> temp = new ArrayList<>(9);
            TreeSet<Integer> tempTree = new TreeSet<Integer>();
            for(int j=0;j<9;j++) {
                temp.add(arr[i][j]);
                tempTree.add(arr[i][j]);
            }
            horizontal.add(tempTree);
            plate.add(temp);
        }

        for(int i=0;i<9;i++) {
            TreeSet<Integer> tempTree = new TreeSet<Integer>();
            for(int j=0;j<9;j++) {
                tempTree.add(arr[j][i]);
            }
            vertical.add(tempTree);
        }

        for(int i=0;i<9;i += 3) {
            TreeSet<Integer> temp1 = new TreeSet<>();
            TreeSet<Integer> temp2 = new TreeSet<>();
            TreeSet<Integer> temp3 = new TreeSet<>();

            for (int j = i; j < i + 3; j++) {
                for (int k = 0; k < 9; k++) {
                    if (k < 3) temp1.add(arr[j][k]);
                    else if (k >= 3 && k < 6) temp2.add(arr[j][k]);
                    else temp3.add(arr[j][k]);
                }
            }
            cubes.add(temp1);
            cubes.add(temp2);
            cubes.add(temp3);
        }

    }

    public void solve() {
        recurrentSolve(0,-1);
    }

    private boolean recurrentSolve(int prevR,int prevC) {

        int r = -1,c = -1;
        for(int i = prevR;i<9;i++) {
            for(int j = 0;j<9;j++) {
                if(plate.get(i).get(j) == empty) {
                    r = i;
                    c = j;
                    break;
                }
            }
            if(r != -1) {
                break;
            }
        }

        if(r == -1 && c == -1) {
            return true;
        }

        for(int num = 1; num<10 ; num++) {
            if(checkVertical(vertical.get(c),num) && checkHorizontal(horizontal.get(r),num) && checkCubes(cubes.get(findCubePos(r,c)),num)) {
                plate.get(r).set(c,num);
                vertical.get(c).add(num);
                horizontal.get(r).add(num);
                cubes.get(findCubePos(r,c)).add(num);
                boolean state = recurrentSolve(r,c);
                if(state) {
                    return true;
                }
                vertical.get(c).remove(num);
                horizontal.get(r).remove(num);
                cubes.get(findCubePos(r,c)).remove(num);
                plate.get(r).set(c,empty);
            }
        }
        return false;
    }

    private int findCubePos(int r,int c) {
        return (r/3)*3+(c/3);
    }

    private boolean checkVertical(TreeSet<Integer> s, int i) {
        return s.contains(i) == false;
    }

    private boolean checkHorizontal(TreeSet<Integer> s, int i) {
        return s.contains(i) == false;
    }

    private boolean checkCubes(TreeSet<Integer> s, int i) {
        return s.contains(i) == false;
    }

    public void print() {
        for(ArrayList<Integer> i:plate) {
            for(Integer j:i) {
                System.out.print(j+"  ");
            }
            System.out.println();
        }
    }

}
