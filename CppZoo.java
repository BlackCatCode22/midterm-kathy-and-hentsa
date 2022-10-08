// MidTerm Project
// CppZoo
// kt

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CppZoo {
    private static final String newLine = System.getProperty("line.separator");

    // Global variables
    static String gbirthDate = "";
    static  String ganimalAge = "";
    static String gGender = "";
    static String gAnimalType = "";
    static String gAnimalName = "";
    static String gNewAnimalID = "";

    // Determine animal birthdate by age and season born
     static String genBirthDay(String age, String season){
        // Extracting age from string
        int index = age.indexOf("year");
        String strYear = age.substring(0, index-1);
       int year = Integer.parseInt(strYear);

        // Getting current calendar year and subtracting year to set birth year
        // need to import Calendar library
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int birthYear = currentYear - year;

       // Finding season from string to determine Month
        int seasonLen = season.length();
        String month = season.substring(8,seasonLen);

        // Trimming any spaces in order to find match
        month = month.trim();

        // Using switch statement to assign birthDate
        switch(month){
            case ("spring"):
                gbirthDate = "Mar 21, " + birthYear;
                break;
            case("fall"):
                gbirthDate = "Sept 22, " + birthYear;
                break;
            case("winter"):
                gbirthDate = "Dec 21, " + birthYear;
                break;
            default:
                gbirthDate = "Jan 1, " + birthYear;
        }
        return gbirthDate;
    }

    // Generate unique animal ID
    static String genUAnimalID(String gAnimalType){
        String animalNamePath = "C:/javaScratch/zooPopulation.txt";
        String line = "";

        //generate random number
        Random randNum = new Random();
        int upperBound = 60;
        String newNum = "";

        int int_random = randNum.nextInt(1,upperBound);
        newNum = Integer.toString(int_random);

        //Access Zoo Population to see if ID is used
        accessZooPopulationID(newNum);

        //Assign new ID to animal
        gNewAnimalID = gAnimalType.substring(0,2).toUpperCase() + newNum;
        return  gNewAnimalID;

    }

    //Method to assign name to arriving animal
    static String genAnimalName(String gAnimalType){
        String inFile = "c:/javaScratch/zooPopulation.txt";
        String toMatch = gAnimalType.substring(0,2);

        int lineCount = fileLineCount(inFile);
        String[] linesInAnimalNameFile = new String[lineCount];

        String str[] = new String[lineCount];
        String usedNames[] = new String[0];

        try {
            File myFile = new File(inFile);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()){
                String myData = myReader.nextLine();
                String matchingData = "";
                if (myData != ""){
                    matchingData = myData.substring(0,2).toLowerCase();
                    if (matchingData.equals(toMatch)){
                        if (!myData.contains("Habitat")){
                            str = myData.split(",");
                        }
                    }
                }
            }
            myReader.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        if (str != null) {
             usedNames = str;
        }

        //Access animal names files to pick one not used
        String[] availableNames =  accessAnimalNames(gAnimalType);

        if (usedNames.length != 0){
            // Loop through available names to make sure it hasn't been used
            for (int i=0; i< availableNames.length; i++){
                for (int j=0; j<usedNames.length; j++){
                    if (usedNames[j] != null){
                        if (usedNames[j].contains(availableNames[i])) {
                            j++;
                        }
                        gAnimalName = availableNames[i];
                        i++;
                        break;
                    }
                    break;
                }
                break;
            }
        }
        else {
            for (int i=0; i< availableNames.length; i++){
                gAnimalName = availableNames[i];
                i++;
                break;
            }
        }
        return gAnimalName;
     }


    ////////////////////////////////////////////////////////////////
    //Custom Methods to parse information
    static String stripAge(String age){
        int index = age.indexOf("old");
        ganimalAge = age.substring(0, index+3);
        //System.out.println("age=" + ganimalAge);
        return ganimalAge;
    }

    // Extract gender from first contents of file
    static String stripGender(String age){
         int index = age.indexOf("female");
         if (index == -1){
             gGender = "male";
         }
         else{
             gGender = "female";
         }
        //System.out.println("gender is: " + gGender);
        return  gGender;
    }

    // Strip the animal type from first element of array
    static String stripAnimal(String age){
         if (age.contains("female")){
             int index = age.indexOf("female");
             gAnimalType = age.substring(index+7,age.length());
         }
         else if (age.contains("male")){
             int index = age.indexOf("male");
             gAnimalType = age.substring(index+5, age.length());
         }
        //System.out.println("animal is: " + gAnimalType);
         return  gAnimalType;
    }


    ////////////////////////////////////////////////////////
    //Access zoo population for new ID
    static boolean accessZooPopulationID(String randNum) {
        String animalNamePath = "C:/javaScratch/zooPopulation.txt";
        String animalId = "";
        Boolean okay = false;

        Scanner sc = null;
        try {
            File file = new File(animalNamePath); // java.io.File
            sc = new Scanner(file);     // java.util.Scanner
            String line;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.toLowerCase();
                if (line != ""){
                    animalId = line.substring(2,4);
                    if (isInteger(animalId)){
                        if (animalId.equals(randNum)){
                            okay = true;
                        }
                    }
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return  okay;
    }

    //Accessing zooPopulation file to get a list of used names
    static void accessZooPopulationNames(String animalType) {
        String tempFileName = "c:/javaScratch/tempFile.txt";
        String animalFile = "c:/javaScratch/zooPopluation.txt";
        PrintWriter printWriter = null;

        // Get line count for file
        int lineCount = fileLineCount(tempFileName);

        // Get the lines into a 1D array.
        String[] linesInAnimalNameFile = convertFileToArray(tempFileName);
        int elementNum = 0;

        // Return only the list of names for that animal type
        String returnString ="";
        String matchingString = "";

        try {
            File file = new File(tempFileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String fileLineAsString = scanner.nextLine();
                // Write string data to the array.
                if (fileLineAsString != ""){
                    linesInAnimalNameFile[elementNum] = fileLineAsString;
                    elementNum++;
                }
            }
            // Close the file.
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //String str[] = returnString.split(",");
    }

    static int fileLineCount(String filePath){
        int lineCount = 0;

        // Open a file and read it line by line.
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            lineCount = 1;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                lineCount++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return  lineCount;
    }

    static String[] convertFileToArray(String filePath){
        int lineCount = fileLineCount(filePath);
        String[] linesInAnimalNameFile = new String[lineCount];
        int elementNum = 0;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String fileLineAsString = scanner.nextLine();
                // Write string data to the array.
                if (fileLineAsString != ""){
                    linesInAnimalNameFile[elementNum] = fileLineAsString;
                    elementNum++;
                }
            }
            // Close the file.
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return linesInAnimalNameFile;
    }

    static String[] accessAnimalNames(String animalType){
       String filePath = "C:/javaScratch/animalNames.txt";
       int lineCount = fileLineCount(filePath);

        // Get the lines into a 1D array.
        String[] linesInAnimalNameFile = convertFileToArray(filePath);
        int elementNum = 0;

        // Return only the list of names for that animal type
        String returnString = "";
        for (int i=0; i<linesInAnimalNameFile.length; i++){
            if (linesInAnimalNameFile[i].toLowerCase().startsWith(animalType)){
                i++;
                returnString = linesInAnimalNameFile[i];
                break;
            }
        }
        String str[] = returnString.split(",");
        return str;
    }

    //Method to determine if string is Integer
    static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    static void createFileAndWrite(String inputInfo){
        String fileName = "c:/javaScratch/zooPopulation.txt";
        PrintWriter printWriter = null;
        File file = new File(fileName);
        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(newLine + inputInfo);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    static String[] createTempFile(String animalType){
        String inFile = "c:/javaScratch/zooPopulation.txt";
        String toMatch = animalType.substring(0,2);

        int lineCount = fileLineCount(inFile);
        String[] linesInAnimalNameFile = new String[lineCount];

        String str[] = new String[lineCount];

        try {
            File myFile = new File(inFile);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()){
                String myData = myReader.nextLine();
                String matchingData = "";
                if (myData != ""){
                    matchingData = myData.substring(0,2).toLowerCase();
                    if (matchingData.equals(toMatch)){
                        if (!myData.contains("Habitat")){
                            str = myData.split(",");
                        }
                    }
                }
            }
            myReader.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args){
        System.out.println("\nWelcome to my Midterm Project");

        // Open a text file and read the contents into a linear array.
        String arrivingAnimalPath = "C:/javaScratch/arrivingAnimals.txt";
        String line = "";
        String[] contents;
        String age = "";
        String season = "";
        String animalInfo = "";
        String animalColor = "";
        String animalWeight = "";
        String animalFrom = "";
        String animalPark = "";

        // Read the arriving animal file and parse out the info needed
        try {
            BufferedReader bf = new BufferedReader(new FileReader(arrivingAnimalPath));
            //check for end of file
            while ((line = bf.readLine()) != null) {
                contents = line.split(",");
                age = contents[0];
                season = contents[1];
                animalColor = contents[2].trim();
                animalWeight = contents[3];
                animalPark = contents[4];
                animalFrom = contents[5];

                // Calling methods to get specific info needed
                stripAge(age);
                stripGender(age);
                stripAnimal(age);
                genBirthDay(age,season);

                //Call method to determine new ID
                genUAnimalID(gAnimalType);

                createTempFile(gAnimalType);

                //Call method to assign animal name
                genAnimalName(gAnimalType);

                //Get current date for arrival date
                DateFormat dateFormat = new SimpleDateFormat("MMMM dd YYYY");
                Date date = new Date();

                animalInfo = gNewAnimalID + ", " + gAnimalName + ", " + ganimalAge + "; birth date " + gbirthDate + "; " + animalColor + "; " + gGender + "; " + animalWeight + "; " + animalPark + "; " + animalFrom + ", arrived " + dateFormat.format(date) ;

                createFileAndWrite(animalInfo);

                //System.out.println(animalInfo);

            }
            bf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
