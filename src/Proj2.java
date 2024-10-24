/**
 * @ BST.AvlTree
 * @ This program makes various lists from the FFN datasets and tracks the speed BST and AVL trees search and insert elements within
 * @ author: Destiny
 * @ date: Oct 23, 2024
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    // makes deep copy of dataArray
    public static void copyFFNList(ArrayList<FastFoodNutritionInfo> newList){
        for (FastFoodNutritionInfo item : FastFoodNutritionInfo.allFFN  ) {
            // Creating a new instance of each element
            newList.add(new FastFoodNutritionInfo(item));
        }
    }
    // methods take an Array and insert into a given tree
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
    // methods take search tree for given array
    public static void treeSearching(AvlTree tree, ArrayList<FastFoodNutritionInfo> list){
        for (FastFoodNutritionInfo item : list ) {
            // Creating a new instance of each element
            tree.contains(new FastFoodNutritionInfo(item));
        }
    }
    public static void treeSearching(BST tree,ArrayList<FastFoodNutritionInfo> list){
        for (FastFoodNutritionInfo item : list ) {
            // Creating a new instance of each element
            tree.search(new FastFoodNutritionInfo(item));
        }
    }
        /*mini parser because my command line is rebelling against me
            Just converts text to code
         */
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
    // Fills a list with the correct amount of entries given NumLine
    public static void listFiller(int numLines, Scanner inputFileNameScanner, ArrayList<FastFoodNutritionInfo> list){
        int linesRead = 0;
        while (inputFileNameScanner.hasNextLine() && linesRead < numLines) {
            String line = inputFileNameScanner.nextLine();
            FastFoodNutritionInfo info = miniParser(line); // Assuming this method parses a CSV line
            list.add(info); // Add to unsorted list
            linesRead++;
        }
    }
// Modified from Proj1, prints and writes to result file an input
    public static void writeToFileAndPrint(String content) throws IOException {
        // create file variable
        File myFile = new File("C:\\Users\\desti\\Documents\\project-2-DDiscipulus\\src\\Results.csv");

        // ensure it exists or create one
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {

            }
        }
        System.out.println(content);

        FileWriter dataTyper = new FileWriter(myFile, true); // file writer
        dataTyper.write(content +"\n"); // write given string and new line

        dataTyper.close(); // close file to preserve data
    }

    public static void main(String[] args) throws IOException {
        // clears previous result files
        new PrintWriter("C:\\Users\\desti\\Documents\\project-2-DDiscipulus\\src\\Results.csv").close();

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
        treeSearching(unSortedBST,unsortedList);
        treeSearching(sortedBST,sortedList);

        treeSearching(unsortedAvl,unsortedList);
        treeSearching(sortedAvl,sortedList);



// timing
        int numLines1 = 50;
        int numLines2 = 100;
        int numLines3 = 301;

        //inserts
        // Time Results for input 1
        writeToFileAndPrint("Trial 1:");

        long start1 = System.nanoTime(); // start time
        insertListToTree(unSortedBST, unsortedList);
        long end1 = System.nanoTime(); // end time

        long start2 = System.nanoTime(); // start time
        treeSearching(unSortedBST,unsortedList);
        long end2 = System.nanoTime(); // end time

        writeToFileAndPrint("BST Unsorted: " + numLines1 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

        start1 = System.nanoTime(); // reset start time
        insertListToTree(sortedBST, sortedList);
        end1 = System.nanoTime(); // end time

        start2 = System.nanoTime(); // start time
        treeSearching(sortedBST,sortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("BST Sorted: " + numLines1 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// Formatting split up AVL and BST tree results
        writeToFileAndPrint(""); // for formatting

        start1 = System.nanoTime(); // reset start time
        insertListToTree(unsortedAvl, unsortedList); // AVL Sorted
        end1 = System.nanoTime(); // end time

        start2 = System.nanoTime(); // start time
        treeSearching(unsortedAvl,unsortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("AVL Unsorted: " + numLines1 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

        start1 = System.nanoTime(); // reset start time
        insertListToTree(sortedAvl, sortedList); // AVL Unsorted
        end1 = System.nanoTime(); // end time

        start2 = System.nanoTime(); // start time
        treeSearching(sortedAvl,sortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("AVL Sorted: " + numLines1 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

        writeToFileAndPrint(""); // for formatting

// Reset
        sortedList.clear();
        unsortedList.clear();
        sortedBST.clear();
        unSortedBST.clear();
        sortedAvl.clear();
        unsortedAvl.clear();

        listFiller(numLines2, inputFileNameScanner, unsortedList);
        listFiller(numLines2, inputFileNameScanner, sortedList);

// Sort and scramble
        Collections.sort(sortedList);
        Collections.shuffle(unsortedList);

        writeToFileAndPrint("Trial: 2");

// BST Unsorted
        start1 = System.nanoTime(); // start time
        insertListToTree(unSortedBST, unsortedList);
        end1 = System.nanoTime(); // end time

        start2 = System.nanoTime(); // start time
        treeSearching(unSortedBST,unsortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("BST Unsorted: " + numLines2 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// BST Sorted
        start1 = System.nanoTime(); // reset start time
        insertListToTree(sortedBST, sortedList);
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(sortedBST,sortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("BST Sorted: " + numLines2 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// Formatting split up AVL and BST tree results
        writeToFileAndPrint(""); // for formatting

// AVL Unsorted
        start1 = System.nanoTime(); // reset start time
        insertListToTree(unsortedAvl, unsortedList);
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(unsortedAvl,unsortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("AVL Unsorted: " + numLines2 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// AVL Sorted
        start1 = System.nanoTime(); // reset start time
        insertListToTree(sortedAvl, sortedList);
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(sortedAvl,sortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("AVL Sorted: " + numLines2 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// Trial 3
        writeToFileAndPrint(""); // formatting
// Reset lists and trees
        sortedList.clear();
        unsortedList.clear();
        sortedBST.clear();
        unSortedBST.clear();
        sortedAvl.clear();
        unsortedAvl.clear();

// Fill the lists for Trial 3
        listFiller(numLines3, inputFileNameScanner, unsortedList);
        listFiller(numLines3, inputFileNameScanner, sortedList);

// Sort and scramble the lists
        Collections.sort(sortedList);
        Collections.shuffle(unsortedList);

        writeToFileAndPrint("Trial: 3");

// BST Unsorted
        start1 = System.nanoTime(); // start time
        insertListToTree(unSortedBST, unsortedList);
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(unSortedBST,unsortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("BST Unsorted: " + numLines3 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// BST Sorted
        start1 = System.nanoTime(); // reset start time
        insertListToTree(sortedBST, sortedList);
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(sortedBST,sortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("BST Sorted: " + numLines3 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// Formatting split up AVL and BST tree results
        writeToFileAndPrint(""); // for formatting

// AVL Unsorted
        start1 = System.nanoTime(); // reset start time
        insertListToTree(unsortedAvl, unsortedList); // AVL Unsorted
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(unsortedAvl,unsortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("AVL Unsorted: " + numLines3 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");

// AVL Sorted
        start1 = System.nanoTime(); // reset start time
        insertListToTree(sortedAvl, sortedList); // AVL Sorted
        end1 = System.nanoTime(); // end time
        start2 = System.nanoTime(); // start time
        treeSearching(sortedAvl,sortedList);
        end2 = System.nanoTime(); // end time

        writeToFileAndPrint("AVL Sorted: " + numLines3 + " lines ");
        writeToFileAndPrint("\t Insert Time: " + (end1 - start1) / 1e9 + " sec");
        writeToFileAndPrint("\t Search Time: " + (end2 - start2) / 1e9 + " sec");


    }
}
