package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Functions;
import model.PNN;

import static java.lang.Double.parseDouble;

public class ChartController {

    @FXML
    private ScatterChart<Double,Double> scatterChart;

    @FXML
    private TextField x;

    @FXML
    private TextField y;

    @FXML
    private Button add;

    @FXML
    private Button all;
    
    private XYChart.Series series1 = new XYChart.Series();

    private XYChart.Series series2 = new XYChart.Series();

    private XYChart.Series series3 = new XYChart.Series();

    private PNN network;

    private double max = 150.0;

    private void addToChart(double x,double y,int value)
    {
        switch (value)
        {
            case 1:
                series1.getData().add(new XYChart.Data(x, y));
                break;
            case 2:
                series2.getData().add(new XYChart.Data(x, y));
                break;
            case 3:
                series3.getData().add(new XYChart.Data(x, y));
                break;
        }
    }

    @FXML
    private void addAll()
    {
        for(int i=0;i<max;i++)
        {
            for(int j=0;j<max;j++)
            {
                double[] input = {i/max,j/max};
                int value = network.classify(input);

                addToChart(i,j,value);
            }
        }
    }

    @FXML
    private void addPoint()
    {
            double x1=Double.parseDouble(x.getText());
            double y1=Double.parseDouble(y.getText());

            double[] input = {x1/max,y1/max};
            int value = network.classify(input);

            addToChart(x1,y1,value);

    }



    @FXML
    public void initialize() {
        int numberOfParameters =2;
        double[][][] example = new double[3][][];
        example[0]=new double[3][];
        for(int i=0;i<3;i++)
        {
            example[0][i] = new double[numberOfParameters];
        }
        example[1]=new double[3][];
        for(int i=0;i<3;i++)
        {
            example[1][i] = new double[numberOfParameters];
        }
        example[2]=new double[4][];
        for(int i=0;i<4;i++)
        {
            example[2][i] = new double[numberOfParameters];
        }

        example[0][0][0]=1.0;
        example[0][0][1]=3.0;
        example[0][1][0]=5.5;
        example[0][1][1]=5.0;
        example[0][2][0]=3.0;
        example[0][2][1]=3.2;

        example[1][0][0]=50.0;
        example[1][0][1]=46.0;
        example[1][1][0]=58.8;
        example[1][1][1]=4.9;
        example[1][2][0]=45.7;
        example[1][2][1]=70;

        example[2][0][0]=100.0;
        example[2][0][1]=70.0;
        example[2][1][0]=105.7;
        example[2][1][1]=65.8;
        example[2][2][0]=100.9;
        example[2][2][1]=90.0;
        example[2][3][0]=98.5;
        example[2][3][1]=120.3;
        Functions functions = new Functions();
        double[][][] array = new double[3][][];
        double[][] norm1 = functions.normalize(example[0],max);
        double[][] norm2 = functions.normalize(example[1],max);
        double[][] norm3 = functions.normalize(example[2],max);
        array[0]=norm1;
        array[1]=norm2;
        array[2]=norm3;

        series1.getData().add(new XYChart.Data(example[0][0][0], example[0][0][1]));
        series1.getData().add(new XYChart.Data(example[0][1][0], example[0][1][1]));
        series1.getData().add(new XYChart.Data(example[0][2][0], example[0][2][1]));


        series2.getData().add(new XYChart.Data(example[1][0][0], example[1][0][1]));
        series2.getData().add(new XYChart.Data(example[1][1][0], example[1][1][1]));
        series2.getData().add(new XYChart.Data(example[1][2][0], example[1][2][1]));

        series3.getData().add(new XYChart.Data(example[2][0][0], example[2][0][1]));
        series3.getData().add(new XYChart.Data(example[2][1][0], example[2][1][1]));
        series3.getData().add(new XYChart.Data(example[2][2][0], example[2][2][1]));
        series3.getData().add(new XYChart.Data(example[2][3][0], example[2][3][1]));
        scatterChart.getData().addAll(series1,series2,series3);

        network = new PNN(2,array);
        //double[] test = {20./150.,50./150.};
        //System.out.println("This point belongs to class number: "+network.classify(test));
    }


}
