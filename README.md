Presentation and class code review on Oct 11.

Your midterm program will be  a collaboration of lab partners. You will present your program to your classmates on the presentation date and participate in the class code review by accepting all critiques without feedback. For example, a code review committee member says, "Good animal names, but your code was unreadable." and you and your partner say, "Thank you for your feedback."

The following programming concepts will be demonstrated:

-- Clear, Concise, and Correct Code: Program must be written in the class programming style guide.

-- File I/O: Create a file, write to a file, open a file, read from a file, close a file, delete a file, append to a file, etc.

-- Exception Handling: Standard file IO exceptions, create an exception to handle adding an animal to a habitat that does not have enough room for the new animal.

-- Functions and Arrays: Use functions and arrays to organize the data read from the input zoo document

-- Input - File and String Handling: Parse string data from input files. 

-- Output - Formatted Output: Output a report listing the zoo animals with their attributes and their habitats 

Animals are arriving from zoos around the world and you must give them names and bithdates and a nice new home (organize then into habitats). To accomplish this task, you will write a Java program that:

1) Finds the age, sex, species, color, and weight of each animal from and an input text file (arrivingAnimals.txt)

2) Calculate a birthday based on input data

3) Create a name for each animal using names collected from a recent community fund raiser (animalNames.txt)

4) Create a unique ID for each animal to be used in your zoo information system

5) Process the animals into appropriate habitats,

6) Output a report (zooPopulation.txt) containing all zoo animals and their information.

Use at least four functions with the following names. These functions must be explained to the code review committee. The four functions will be named:

genBirthDay(), genUanimalID(), genAnimalName(), genZooHabitats()

Calculate a birthday from the information you receive from the originating zoo. (use a function named: genBirthDay() ) What will you do if the birth season is unknown?
Calculate a uniqueID (use a function named: genUanimalID() ) to uniquely identify each animal in your zoo.
Create an animal name based on input from a community fundraiser (animalNames.txt) using a function named: genAnimalName()
Assign each new animal a habitat using a static 4 x 4 array with a function named: genZooHabitats(). Each species must have its own habitat.
Name your project CppZoo.

Use fileIO to parse and input the file named arrivingAnimals.txt. Use fileIO to create a report named zooPopulation.txt.  Use 2D arrays, 1D arrays, functions and string parsing methods() to organize your zoo.

Information will arrive in the following format with an unknown number of lines.

4 year old female hyena, born in spring, tan color, 70 pounds, from Friguia Park, Tunisia

12 year old male hyena, born in fall, brown color, 150 pounds, from Friguia Park, Tunisia

4 year old male hyena, born in spring, black color, 120 pounds, from Friguia Park, Tunisia

8 year old female hyena, unknown birth season, black and tan striped color, 105 pounds, from Friguia Park, Tunisia

6 year old female lion, born in spring, tan color, 300 pounds, from Zanzibar, Tanzania

You will use functions to calculate the following data elements for each animal:

Unique animal ID, animal name, birth date, color, weight, origin

You must place species in their own habitat and output that in a report similar to:

Hyena Habitat:

Hy01, Kamari, 4 years old, tan, 70 pounds, Friguia Park

Names to get you started:

Hyena Names:

Shenzi, Banzai, Ed, Zig, Bud, Lou, Kamari, Wema, Nne, Madoa, Prince Nevarah

Lion Names:

Scar, Mufasa, Simba, Kiara, King, Drooper, Kimba, Nala, Leo, Samson, Elsa, Cecil

Bear Names:

Yogi, Smokey, Paddington, Lippy, Bungle, Baloo, Rupert, Winnie the Pooh, Snuggles, Bert
