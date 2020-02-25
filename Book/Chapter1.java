import java.lang.ProcessBuilder.Redirect.Type;
import java.util.*; 

public class Chapter1 {
    ///1.1.1 Give the value of each of the following expressions:
    public static void e1_1_1() {
        var a = (0 + 15) /2;
        var b = 2.0e-6 * 10000000.1;
        var c = true && false || true && true;
        
        printTypeAndValue("a", a);
        printTypeAndValue("b", b);
        printTypeAndValue("c", c);
    }

    ///1.1.2 Give the type and value of each of the following expressions:
    public static void e1_1_2(){
        var a = (1+2.236)/2;
        var b = 1+2+3+4.0;
        var c = 4.1 >= 4;
        var d = 1+2+"3";

        printTypeAndValue("a", a);
        printTypeAndValue("b", b);
        printTypeAndValue("c", c);
        printTypeAndValue("d", d);
    }

    ///1.1.5 Write a code fragment that prints true if the double variables x and y are both
    ///strictly between 0 and 1 and false otherwise.
    public static void e1_1_5(Double x, Double y) {
        System.out.println(x > 0 && x < 1 && y > 0 && y < 1);
    }

    ///1.1.6 What does the following program print?
    public  static void e1_1_6() {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++)
        {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }

    ///1.1.11 Write a code fragment that prints the contents of a two-dimensional boolean
    ///array, using * to represent true and a space to represent false. Include row and column
    ///numbers.
    private static void e1_1_11() {
        //10 rows of 5 columns
        Boolean[][] grid = new Boolean[10][5];
        
        //init grid values
        for (var i = 0; i < grid.length; i++) {
            for (var j = 0; j < grid[i].length; j++) {
                grid[i][j] = ((i + j) % 2 == 0);
            }
        }

        for (var i = 0; i < grid.length; i++) {
            for (var j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.println("Row:" + i + " Col:" + j + " = *");
                } else {
                    System.out.println("Row:" + i + " Col:" + j + " = ");
                }

            }
        }
    }

    ///1.1.9 Write a code fragment that puts the binary representation of a positive integer N
    ///into a String s.
    private static void e1_1_9(int N) {
        //better version
        // String s = "";
        // for (int n = N; n > 0; n /= 2) {
        //     s = (n % 2) + s;
        // }

        //binary position
        // int position = BinarySearchForNextHighest(GetMultiplesOf(2, 10), N);
        // if (position < N) {
        //     while (position < N) {
        //         position *= 2;
        //     }
        // }
        //BinarySearchForNextHighest(GetMultiplesUpTo(2, N), N); was a fun exercise, but unnecessary

        int position = GetNextMultipleOf(2, N);
        String s = "";
        int rem = N;
        while (rem > 0 || position >= 1) {
            if (position > rem) {
                s += "0";
            } else {
                s += "1";
                rem -= position;
            }
            position /= 2;
        }
        
        System.out.println(s);
    }

    private static int GetNextMultipleOf(int multiplier, int target) {
        int current = multiplier;
        while (current < target) {
            current *= 2;            
        }

        return current;        
    }

    private static int[] GetMultiplesOf(int multiplier, int count) {
        int[] a = new int[count];
        for (int i = 0; i < count; i++) {
            int result = multiplier;
            if (i > 0) { result = multiplier * a[i-1]; }
            a[i] = result;
        }
        return a;
    }

    //a fun excersise 
    private static int BinarySearchForNextHighest(int[] a, int key) {
        var lo = 0;
        var hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > key) {
                hi = mid-1;
                if (a[hi] < key) {return a[mid];}
            }
            else if (a[mid] < key) {
                lo = mid+1;
                if (a[lo] > key) {return a[lo];}
            }
            else return a[mid];
        }        
        return a[hi];
    }

    ///1.1.13 Write a code fragment to print the transposition (rows and columns changed)
    ///of a two-dimensional array with M rows and N columns.
    private static void e1_1_13(int m, int n) {
        //since it's possible have rectangular matrix, I'm creating a new array to store the transposed array
        int[][] table = new int[m][n];

        for(var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                table[i][j] = i;
            }
        }

        System.out.println("Normal:");
        for(var i = 0; i < m; i++) {
            System.out.println("");
            for (var j = 0; j < n; j++) {
                System.out.print(table[i][j]);
                System.out.print(" ");
            }
        }

        int[][] tTable = new int[n][m];

        System.out.println("\nTranspose:");
        for(var i = 0; i < n; i++) {
            for (var j = 0; j < m; j++) {
                tTable[i][j] = table[j][i];
            }
        }

        System.out.println("\nTranspose:");
        for(var i = 0; i < tTable.length; i++) {
            if (i > 0) {System.out.println("");}
            for (var j = 0; j < tTable[i].length; j++) {
                System.out.print(tTable[i][j]);
                System.out.print(" ");
            }
        }
    }

    ///1.1.14 Write a static method lg() that takes an int value N as argument and returns
    ///the largest int not larger than the base-2 logarithm of N. Do not use Math.
    private static void lg(int N) {
        
    }

    private static void printTypeAndValue(String name, Object value) {
        System.out.println(name + ": " + value + "("+ value.getClass().getName() + ")");
    }

    public static void main(String[] args) {
        // Chapter1.e111();
        // Chapter1.e112();
        // Chapter1.e116();
        Chapter1.e1_1_13(10, 5);
    }
}