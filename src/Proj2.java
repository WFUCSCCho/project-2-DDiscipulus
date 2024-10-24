import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void copyFFNList(ArrayList<FastFoodNutritionInfo> newList){
        for (FastFoodNutritionInfo item : FastFoodNutritionInfo.allFFN  ) {
            // Creating a new instance of each element
            newList.add(new FastFoodNutritionInfo(item));
        }
    }
    public static void insertListToTree(BST tree, ArrayList<FastFoodNutritionInfo> list){
        for (FastFoodNutritionInfo item : FastFoodNutritionInfo.allFFN  ) {
            // Creating a new instance of each element
            tree.insert(new FastFoodNutritionInfo(item));
        }
    }
    public static void insertListToTree(AvlTree tree, ArrayList<FastFoodNutritionInfo> list){
        for (FastFoodNutritionInfo item : FastFoodNutritionInfo.allFFN  ) {
            // Creating a new instance of each element
            tree.insert(new FastFoodNutritionInfo(item));
        }
    }
    public static void treeSearching(AvlTree tree){
        for (FastFoodNutritionInfo item : FastFoodNutritionInfo.allFFN  ) {
            // Creating a new instance of each element
            tree.contains(new FastFoodNutritionInfo(item));
        }
    }
    public static void treeSearching(BST tree){
        for (FastFoodNutritionInfo item : FastFoodNutritionInfo.allFFN  ) {
            // Creating a new instance of each element
            tree.search(new FastFoodNutritionInfo(item));
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();
//-
	// started
        // read the dataset and put it in an ArrayList
        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv");
 // initialize our lists
    ArrayList<FastFoodNutritionInfo> unsortedList = null;
    ArrayList<FastFoodNutritionInfo> sortedList = null;

// fill our lists
    copyFFNList(unsortedList);
    copyFFNList(sortedList);

    // sort and scramble
        Collections.sort(sortedList);
        Collections.shuffle(unsortedList);


        // make our trees
        BST<FastFoodNutritionInfo> sortedBST = new BST<>();
        BST<FastFoodNutritionInfo> unSortedBST = new BST<>();

        AvlTree<FastFoodNutritionInfo> sortedAvl = new AvlTree<>();
        AvlTree<FastFoodNutritionInfo> unsortedAvl = new AvlTree<>();

        // inserts
        insertListToTree(unSortedBST,unsortedList);
        insertListToTree(sortedBST,sortedList);

        insertListToTree(unsortedAvl,unsortedList);
        insertListToTree(sortedAvl,sortedList);



    }
}
