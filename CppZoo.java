// MidTerm Project
// CppZoo
// kt

import java.util.Arrays;
import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.List;

public class CppZoo {
    static String gbirthDate = "";
    static  String ganimalAge = "";
    static String gGender = "";


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
        //System.out.println("season =" + birthDate);
        //System.out.printf("Substring 'old' is at index %d\n", index);
        return gbirthDate;
    }

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

    static void createFileAndWrite(){
        //Create a file
        try {
            File myFile = new File("C:/javaScratch/tempAnimalFile.txt");
            if (myFile.createNewFile()){
                System.out.println("File created is: " + myFile.getName());
            }
            else {
                System.out.println("File already exists");
            }
        }
        catch (IOException e){
            System.out.println("Exception caught");
        }
        //Write to the new file
        try {
            FileWriter myWriter = new FileWriter("C:/javaScratch/tempAnimalFile.txt");
            //myWriter.write("This is line one written to my new file I just created!\n");

            // 3) Advanced: Create a loop to write 100 lines of text to your new file
            for (int i=0; i<101; i++){
                myWriter.write("\nThis is line: " + i);
            }

            myWriter.close();
            System.out.println("File write completed");
        }
        catch (IOException e){
            System.out.println("File IO Exception caught!");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        System.out.println("\nWelcome to my Midterm Project");

        // Open a text file and read the contents into a linear array.
        String path = "C:/javaScratch/arrivingAnimals.txt";
        String line = "";
        String[] contents;
        String age = "";
        String season = "";
        String animalInfo = "";
        String animalColor = "";
        String animalWeight = "";
        String animalFrom = "";
        String animalPark = "";

        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            //check for end of file
            while ((line = bf.readLine()) != null) {
                //System.out.println(line);
                contents = line.split(",");
                age = contents[0];
                season = contents[1];
                animalColor = contents[2].trim();
                animalWeight = contents[3];
                animalPark = contents[4];
                animalFrom = contents[5];

                stripAge(age);
                stripGender(age);
                genBirthDay(age,season);

                //System.out.println("age = " + ganimalAge);
                //System.out.println("birthday = " +  gbirthDate);

                animalInfo = ganimalAge + "; birth date " + gbirthDate + "; " + animalColor + "; " + gGender + "; " + animalWeight + "; " + animalPark + "; " + animalFrom;
                System.out.println(animalInfo);

            }
            bf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
