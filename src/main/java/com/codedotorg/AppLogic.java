package com.codedotorg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;


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
        byte[] graphDef;
        try {
            graphDef = Files.readAllBytes(Paths.get("src/main/resources/model.pb"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        try (Graph graph = new Graph()) {
            graph.importGraphDef(graphDef);
            try (Session session = new Session(graph)) {
                Tensor<String> inputTensor = Tensor.create(predictedClass.getBytes("UTF-8"), String.class);
                Tensor<?> outputTensor = session.runner()
                                                .feed("input_node", inputTensor)
                                                .fetch("output_node")
                                                .run()
                                                .get(0);
                String userPin = new String(outputTensor.bytesValue(), "UTF-8");
                return userPin;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
        
        return "";
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
        
        return "1234";
    }

}
