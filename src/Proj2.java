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
    public static FastFoodNutritionInfo miniParser(String data){
        String[] properData = data.split(",");

        // Extract the relevant data from the array
        String company = properData[0];
        String item = properData[1];
        Double calories = Double.parseDouble(properData[2]);
        Double totalFat = Double.parseDouble(properData[3]);
        Double carbs = Double.parseDouble(properData[4]);
        Double protein = Double.parseDouble(properData[5]);

        // Create and return a new FastFoodNutritionInfo object
        return new FastFoodNutritionInfo(company, item, totalFat, calories, carbs, protein);
    }
    public static void listFiller(int numLines, Scanner inputFileNameScanner, ArrayList<FastFoodNutritionInfo> list){
        int linesRead = 0;
        while (inputFileNameScanner.hasNextLine() && linesRead < numLines) {
            String line = inputFileNameScanner.nextLine();
            FastFoodNutritionInfo info = miniParser(line); // Assuming this method parses a CSV line
            list.add(info); // Add to unsorted list
            linesRead++;
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        String inputFileName = "C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv";
        int numLines = 100;



        // Open the input file
        FileInputStream inputFileNameStream = new FileInputStream(inputFileName);
        Scanner inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();
//-
	// started
        // read the dataset and put it in an ArrayList

        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv");
 // initialize our lists
    ArrayList<FastFoodNutritionInfo> unsortedList =  new ArrayList<>();
    ArrayList<FastFoodNutritionInfo> sortedList =  new ArrayList<>();


// fill our lists
    listFiller(numLines,inputFileNameScanner,unsortedList);
    listFiller(numLines,inputFileNameScanner,sortedList);

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

        // searching
        treeSearching(unSortedBST);
        treeSearching(sortedBST);

        treeSearching(unsortedAvl);
        treeSearching(sortedAvl);
System.out.println("I made it through! :)");


    }
}
