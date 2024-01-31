//Avi Weiner
import java.util.Scanner;
public class BaseConverter {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean invalidEntry = false;
        boolean invalidBase = false;
        String num;
        int base1;
        int base2;

        System.out.print("Enter the number to convert to a new base: ");
        num = input.nextLine();
        System.out.print("Please enter the current base of your number: ");
        base1 = input.nextInt();
        System.out.print("Please enter what you want the new base to be: ");
        base2 = input.nextInt();
        input.nextLine();

        if (base1 == base2){
            System.out.println("You put in the same number for the old and new base so your number stays the same");
        } else {
            int len = num.length() - 1;
            char[] numsInSpotsChars = new char[len + 1];
            for (int i = len; i >= 0; i--) {
                numsInSpotsChars[i] = num.charAt(i);
            }

            int numHolder;
            int[] numsInSpots = new int[len + 1];
            for (int i = 0; i <= len; i++) {
                numHolder = numsInSpotsChars[i];
                if (numHolder >= 48 && numHolder <= 57){
                    numsInSpots[i] = numHolder - 48;
                } else if (numHolder >= 65 && numHolder <= 90) {
                    numsInSpots[i] = numHolder - 55;
                } else {
                    invalidEntry = true;
                }
                if ((numsInSpots[i] >= base1) || !(base2 <= 36 && base2 >= 2)){
                    invalidBase = true;
                }
            }

            if (!invalidEntry && !invalidBase){
                int multiplier = 1;
                int[] newNum = new int[len + 1];
                for (int i = len; i >= 0; i--) {
                    newNum[i] = numsInSpots[i] * multiplier;
                    multiplier *= base1;
                }

                int partialAnswer = 0;
                for (int number: newNum){
                    partialAnswer += number;
                }

                if (base1 != 10){
                    for (int i = 0; i < numsInSpots.length; i++) {
                        if (newNum[i] >= 1000000000){
                            System.out.print(numsInSpots[i] + "\t\t\t");
                        } else if (newNum[i] >= 1000){
                            System.out.print(numsInSpots[i] + "\t\t");
                        } else {
                            System.out.print(numsInSpots[i] + "\t");
                        }
                    }
                    System.out.print("Total:");
                    System.out.println();

                    for (int number: newNum){
                        System.out.print(number + "\t");
                    }
                    System.out.print(partialAnswer);
                    System.out.println();
                    System.out.println("Your number is " + partialAnswer + " in base-10");
                }

                if (base2 != 10){
                    int numWithExponent = 1;
                    int numSpots = 0;
                    while ((partialAnswer / numWithExponent) >= 1){
                        numWithExponent *= base2;
                        numSpots++;
                    }
                    numWithExponent /= base2;
                    int numToDealWith = partialAnswer;

                    char[] newNumsInSpotsChars = new char[numSpots];
                    int[] newNumsInSpots = new int[numSpots];
                    for (int i = 0; i < newNumsInSpotsChars.length; i++) {
                        numHolder = numToDealWith / numWithExponent;
                        newNumsInSpots[i] = (numHolder * numWithExponent);
                        if (numHolder >= 0 && numHolder <= 9){
                            newNumsInSpotsChars[i] = (char) (numHolder + 48);
                        } else {
                            newNumsInSpotsChars[i] = (char) (numHolder + 55);
                        }
                        numToDealWith -= (numHolder * numWithExponent);
                        numWithExponent /= base2;
                    }

                    for (int i = 0; i < newNumsInSpotsChars.length; i++) {
                        if (newNumsInSpots[i] >= 1000000000){
                            System.out.print(newNumsInSpotsChars[i] + "\t\t\t");
                        } else if (newNumsInSpots[i] >= 1000){
                            System.out.print(newNumsInSpotsChars[i] + "\t\t");
                        } else {
                            System.out.print(newNumsInSpotsChars[i] + "\t");
                        }
                    }
                    System.out.print("Total:");
                    System.out.println();

                    int endNum = 0;
                    for (int spot : newNumsInSpots){
                        endNum += spot;
                        System.out.print(spot + "\t");
                    }
                    System.out.print(endNum);
                    System.out.println();
                    if (endNum == 0){
                        System.out.println("Your number is 0 in base-" + base2);
                        System.out.println("I don't know why you'd want such a pointless conversion");
                    } else {
                        StringBuilder newNum2 = new StringBuilder();
                        for (char spot : newNumsInSpotsChars){
                            newNum2.append(spot);
                        }
                        System.out.println("Your number is " + newNum2 + " in base-" + base2);
                    }

                    if (endNum != partialAnswer){
                        System.out.println("An error has occurred. Your number may be too big for the program to handle");
                    }
                }
            } else if (invalidBase) {
                System.out.println("Invalid base entered");
            } else {
                System.out.println("Invalid number entered. Only numbers and uppercase letters are allowed");
            }
        }
    }
}