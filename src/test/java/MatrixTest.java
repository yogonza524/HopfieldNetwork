/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.core.hopfield.Hopfield;
import com.core.hopfield.IFunction;
import com.core.hopfield.Matrix;
import com.core.hopfield.StepFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Gonza
 */
public class MatrixTest {
    
    public MatrixTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     @Ignore
     public void operationsTest() {
        System.out.println("D");
        System.out.println("--------------------");
        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
        Matrix.showMatrix(d);
        System.out.println();

        System.out.println("I");
        System.out.println("--------------------");
        double[][] c = Matrix.identity(5);
        Matrix.showMatrix(c);
        System.out.println();

        System.out.println("A");
        System.out.println("--------------------");
        double[][] a = Matrix.random(5, 5);
        Matrix.showMatrix(a);
        System.out.println();

        System.out.println("A^T");
        System.out.println("--------------------");
        double[][] b = Matrix.transpose(a);
        Matrix.showMatrix(b);
        System.out.println();

        System.out.println("A + A^T");
        System.out.println("--------------------");
        double[][] e = Matrix.add(a, b);
        Matrix.showMatrix(e);
        System.out.println();

        System.out.println("A * A^T");
        System.out.println("--------------------");
        double[][] f = Matrix.multiply(a, b);
        Matrix.showMatrix(f);
        System.out.println();
     }
     
     @Test
     @Ignore
     public void firstTest(){
         double[] p1 = new double[]{1.0, 1.0,-1.0,-1.0};
         double[] p2 = new double[]{-1.0, -1.0,1.0,1.0};
         double[] p3 = new double[]{1.0, -1.0,1.0,1.0};
         
         ArrayList<double[]> patterns = new ArrayList<>();
         patterns.add(p1);
         patterns.add(p2);
         
         Hopfield h = new Hopfield(4, new StepFunction());
         h.train(patterns);
         
         System.out.println("\nConnections of Network: " + h.connections() + "\n");
         System.out.println("Good recuperation capacity of samples: " + Hopfield.goodRecuperation(h.getWeights().length) + "\n");
         System.out.println("Perfect recuperation capacity of samples: " + Hopfield.perfectRacuperation(h.getWeights().length) + "\n");
         System.out.println("Matrix of Weights");
         
         Matrix.showMatrix(h.getWeights());
         double[] result = h.test(p3);
         System.out.println("\nEnergy: " + h.energy(result) + "\n");
         
         Matrix.showVector(result);
        
        h.showAuxVector();
     }
     
     @Test
//     @Ignore
     public void secondTest(){
         double[] p1 = new double[]{1.0, -1.0,1.0,-1.0,1.0,-1.0,1.0,-1.0,1.0};
         double[] p2 = new double[]{1.0, 1.0,1.0,-1.0,1.0,-1.0,-1.0,1.0,-1.0};
         double[] p3 = new double[]{1.0, 1.0,-1.0,-1.0,1.0,-1.0,-1.0,1.0,-1.0};
         
         ArrayList<double[]> patterns = new ArrayList<>();
         patterns.add(p1);
         patterns.add(p2);
         
         Hopfield h = new Hopfield(9, new StepFunction());
         h.train(patterns);
         double[] result = h.test(p3);
         System.out.println("\nConnections of Network: " + h.connections() + "\n");
         System.out.println("Good recuperation capacity of samples: " + Hopfield.goodRecuperation(h.getWeights().length) + "\n");
         System.out.println("Perfect recuperation capacity of samples: " + Hopfield.perfectRacuperation(h.getWeights().length) + "\n");
         System.out.println("Energy: " + h.energy(result));
         Matrix.showMatrix(h.getWeights());
         System.out.println("\nPattern result of test");
         Matrix.showVector(result);
        
        h.showAuxVector();
     }
     
     @Test
     @Ignore
     public void thirth(){
         double[] p1 = new double[]{1.0, -1.0,-1.0,1.0};
         double[] p2 = new double[]{-1.0, 1.0,1.0,-1.0};
         
         double[][] w1 = Matrix.subtract(Matrix.multiply(p1, p1), Matrix.identity(4));
         double[][] w2 = Matrix.subtract(Matrix.multiply(p2, p2), Matrix.identity(4));
         
         double[][] W = Matrix.add(w1, w2);
         
         IFunction step = new StepFunction();
         Matrix.showVector(step.calculate(Matrix.multiply(W, p2)));
         
         System.out.println(W.length);
     }
}
