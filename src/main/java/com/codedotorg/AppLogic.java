package com.codedotorg;

public class AppLogic {

    /** The pin to unlock the app */
    private String pin;

    /** The pin the user has provided */
    private String user;

    /**
     * Constructor for the AppLogic class.
     * Initializes the pin with a random value and user with an empty string.
     */
    public AppLogic() {
        pin = createRandomPin();
        user = "";
    }

    /**
     * Creates a user PIN based on the predicted class.
     * @param predictedClass the predicted class from the machine learning model
     * @return the user PIN as a string
     */
    public String createUserPin(String predictedClass) {
        StringBuilder userPin = new StringBuilder();
        for (char ch : predictedClass.toCharArray()) {
            userPin.append((int) ch % 10);
        }
        return userPin.toString();
    }
    /**
     * Checks if the length of the user's PIN is equal to 4.
     * @return true if the length of the user's PIN is equal to 4, false otherwise.
     */
    public boolean checkPinLength() {
        return user.length() == 4;
    }

    /**
     * Returns the status of the user's input PIN.
     * @param userPin the PIN entered by the user
     * @return a string indicating whether the PIN is correct or not
     */
    public String getPinStatus(String userPin) {
        if (userPin.equals(pin)) {
            return "PIN is correct";
        } else {
            return "PIN is incorrect";
        }
    }
    
    /**
     * Resets the logic of the application by generating
     * a new random PIN and clearing the user field.
     */
    public void resetLogic() {
        pin = createRandomPin();
        user = "";
    }

    /**
     * Generates a random 4-digit PIN number.
     * @return the generated PIN number as a string.
     */
    private String createRandomPin() {
        int randomPin = (int)(Math.random() * 9000) + 1000; // Generates a random 4-digit number
        return String.valueOf(randomPin);
    }
}
