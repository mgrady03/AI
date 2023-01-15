import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class representing a Markov Decision Process
 */
public class MDP {
    private int numStates;
    private int numActions;

    private double[][][] mdp; // mdp[i][j][action] is the transition probability
                              // of moving from state i to state j given action
    private double[][] reward; // reward[state][action] is the immediate reward
                               // when agent takes action at state

    public MDP(int numStates, int numActions){
        this.numStates = numStates;
        this.numActions = numActions;
        mdp = new double[numStates][numStates][numActions];
        reward = new double[numStates][numActions];
    }

    public MDP() {
    }

    public void setReward(int state, int action, double reward){
        this.reward[state][action] = reward;
    }

    public void setProb(int fromState, int toState, int action, double prob) {
        mdp[fromState][toState][action] = prob;
    }

    public int getNumStates(){
        return numStates;
    }

    public int getNumActions(){
        return numActions;
    }

    public double getReward(int state, int i) {
        return reward[state][i];
    }

    public double getProb(int state, int j, int i) {
        return mdp[state][j][i];
    }

     /**
     * Load the MDP from a file. The file is structured as follows
     * <num of states> <num of actions>
     * The following line is repeated <num of states> * <num of actions> times
     * <state> <action> <reward>
     * ....
     * The following line is repeated for each transition probability
     * <from state> <to state> <action> <probability>
     * ....
     * 
     * @param fileName
     */
    public void load(String fileName) {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }

        numStates = fileScanner.nextInt();
        numActions = fileScanner.nextInt();
        mdp = new double[numStates][numStates][numActions];
        reward = new double[numStates][numActions];
        fileScanner.nextLine();
        // read rewards
        for (int i = 0; i < numStates; i++) {
            for (int j = 0; j < numActions; j++) {
                int state = fileScanner.nextInt();
                int action = fileScanner.nextInt();
                double reward = fileScanner.nextDouble();
                this.reward[state][action] = reward;
            }
        }
        // read state transitions
        while (fileScanner.hasNext()) {
            int fromState = fileScanner.nextInt();
            int toState = fileScanner.nextInt();
            int action = fileScanner.nextInt();
            double prob = fileScanner.nextDouble();
            if (fileScanner.hasNext()) {
                fileScanner.nextLine();
            }
            this.mdp[fromState][toState][action] = prob;
        }
    }

    
}
