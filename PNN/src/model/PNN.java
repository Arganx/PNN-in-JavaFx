package model;

/**
 * Created by qwerty on 29-Nov-18.
 */
public class PNN {
    private int numberOfInputs;
    private double[][][] pointsInEachClass;// 1- numberOfClasses, 2-number of objects in each class
    private Neuron[][] neurons;


    public PNN(int numberOfInputs, double[][][] pointsInEachClass) {

            this.numberOfInputs = numberOfInputs;
            this.pointsInEachClass = pointsInEachClass;
            neurons = new Neuron[pointsInEachClass.length][];
            for(int i=0;i<pointsInEachClass.length;i++)
            {
                neurons[i]=new Neuron[pointsInEachClass[i].length];
                for(int j=0;j<pointsInEachClass[i].length;j++)
                {
                    neurons[i][j] = new Neuron(numberOfInputs,pointsInEachClass[i][j],0.1);
                }
            }



    }

    public int classify(double[] inputs)
    {
        if(inputs.length!=numberOfInputs)
        {
            return -1;
        }
        double[] probability=new double[pointsInEachClass.length];
        for(int i=0;i<pointsInEachClass.length;i++)
        {
            for(int j=0;j<pointsInEachClass[i].length;j++)
            {
                probability[i]+=neurons[i][j].calculateSimilarityRate(inputs);
            }
        }

        return max(probability);
    }

    private int max(double[] tab){
        double max =  tab[0];
        int result =0;
        for(int i=1;i<pointsInEachClass.length;i++)
        {
            if(max<tab[i])
            {
                max=tab[i];
                result=i;
            }
        }
        return result+1;
    }


}
