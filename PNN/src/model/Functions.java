package model;

/**
 * Created by qwerty on 29-Nov-18.
 */
public class Functions {
    public double[][] normalize(double[][] tab , double max)
    {
        double[][] result = new double[tab.length][];
       for(int i=0;i<tab.length;i++)
       {
           result[i] = new double[tab[i].length];
           for(int j=0;j<tab[i].length;j++)
           {
               result[i][j] = tab[i][j]/max;
           }
       }
       return result;
    }
}
