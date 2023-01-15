/**
 * The Agent policy is represented as a 2-d array of probabilities.
 * policy[state][action] is the probability of taking action at state.
 */
public class Policy {
    private static final double EPSILON = 0.0000001; //convergence threshold
    private double[][] policy;
    private int numStates;
    private int numActions;

    public Policy(int numStates, int numActions){
        this.numStates = numStates;
        this.numActions = numActions;
        this.policy = new double[numStates][numActions];
    }

    public void set(int state, int action, double prob){
        policy[state][action] = prob;
    }

    public double get(int state, int action){
        return policy[state][action];
    }

    public boolean equals(Object other){
        boolean result = false;
        if(other instanceof Policy){
            result = true;
            Policy otherP = (Policy) other;
            for(int i=0; i<numStates; i++){
                for(int j=0; j<numActions; j++){
                    if(Math.abs(policy[i][j] - otherP.policy[i][j]) > EPSILON){
                        result = false;
                        break;
                    }
                }
            }   
        }
        return result;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Policy:\n");
        result.append("--------------------------------------\n");
        result.append("State\taction\tprobability\n");
        result.append("--------------------------------------\n");
        for(int i=0; i<numStates; i++){
            for(int j=0; j<numActions; j++){
               result.append(i + "\t" + j + "\t" + policy[i][j] + "\n"); 
            }
            result.append("--------------------------------------\n");
        }   
        return result.toString();    
    }
}
