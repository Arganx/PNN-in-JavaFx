package model;

/**
 * Created by qwerty on 29-Nov-18.
 */
public class Neuron {
    private int numberOfInputs;
    private double[] values;
    private Double sigma;

    public Neuron(int numberOfInputs, double[] values, Double sigma) {
        this.numberOfInputs = numberOfInputs;
        this.values = values;
        this.sigma = sigma;
        if(sigma==null)
        {
            this.sigma=0.1;
        }
    }

    public double calculateSimilarityRate(double[] input)
    {
        if(input.length!=numberOfInputs)
        {
            return -1;
        }
        double result=0;
        for(int i=0;i<numberOfInputs;i++)
        {
            result+=Math.pow(input[i]-values[i],2);
        }
        result=result*-1;
        result=result/(2*sigma*sigma);
        result = Math.exp(result);
        return result;
    }

}
