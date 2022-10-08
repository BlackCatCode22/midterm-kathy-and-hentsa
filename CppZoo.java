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
        int upperBound = 999;
        String newNum = "";

        int int_random = randNum.nextInt(1,upperBound);
        newNum = Integer.toString(int_random);

        //Assign new ID to animal
        gNewAnimalID = gAnimalType.substring(0,2).toUpperCase() + newNum;
        return  gNewAnimalID;
    }

    public static String generateName(HashMap<String, Boolean> used, String[][] names, String species){

        int speciesId;
        Random rand = new Random();
        switch (species){
            case "Hy":
                speciesId = 0;
                break;
            case "Li":
                speciesId = 1;
                break;
            case "Be":
                speciesId = 2;
                break;
            case "Ti":
                speciesId = 3;
                break;
            default:
                speciesId = 0;
        }
        String name = "";
        do {
            name = names[speciesId][rand.nextInt(names[speciesId].length)];
        } while (used.containsKey(name));
        used.put(name, true);
        return name;
    }

    static void genZooHabitats(String inputInfo){
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

    ////////////////////////////////////////////////////////////////
    //Custom Methods to parse information
    static String stripAge(String age){
        int index = age.indexOf("old");
        ganimalAge = age.substring(0, index+3);
        return ganimalAge;
    }

    // Extract gender from first contents of file
    static String stripGender(String age){
         if (age.contains("female")){
             gGender = "female";
         }
         else {
             gGender = "male";
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

    public static void main(String[] args){
        System.out.println("\nWelcome to my Midterm Project");
        HashMap<String, Boolean> usedNames = new HashMap<String, Boolean>();
        HashMap<String, String[]> allAnimalData = new HashMap<String, String[]>();
        String filePath = "C:/javaScratch/animalNames.txt";

        Random rand = new Random();
        String[][] zooIds = new String[4][4];

        // Get Names
        String animalNamesPath =  ("C:/javaScratch/animalNames.txt");
        String[] animalNames = new String[0];
        try {
            String names = "";
            File namesFile = new File(animalNamesPath);
            Scanner scannerFile = new Scanner(namesFile);
            while (scannerFile.hasNextLine()){
                String data = scannerFile.nextLine();
                if (data.contains(",")){
                    names = names.concat(data + ";");
                }
            }
            scannerFile.close();
            animalNames = names.split(";");
        } catch (FileNotFoundException e){
            System.out.println("who");
            e.printStackTrace();
        }

        String[][] allNames = {animalNames[0].split(", "),animalNames[1].split(", "),animalNames[2].split(", "),animalNames[3].split(", ")};


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

                //Call method to assign animal name
                String species = "";
                species = contents[0].split(" ")[4].substring(0, 1).toUpperCase() + contents[0].split(" ")[4].substring(1, 2);

                gAnimalName = generateName(usedNames, allNames, species);

                //Get current date for arrival date
                DateFormat dateFormat = new SimpleDateFormat("MMMM dd YYYY");
                Date date = new Date();

                animalInfo = gNewAnimalID + ", " + gAnimalName + ", " + ganimalAge + "; birth date " + gbirthDate + "; " + animalColor + "; " + gGender + "; " + animalWeight + "; " + animalPark + "; " + animalFrom + ", arrived " + dateFormat.format(date) ;

                genZooHabitats(animalInfo);

                //System.out.println(animalInfo);

            }
            bf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
