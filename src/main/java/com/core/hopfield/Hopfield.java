/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.hopfield;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gonza
 */
public class Hopfield {
    
    private double[][] W;
    private final IFunction f;
    private ArrayList<double[]> patterns;
    private ArrayList<double[]> aux;

    public ArrayList<double[]> getAux() {
        return aux;
    }

    public ArrayList<double[]> getPatterns() {
        return patterns;
    }
    
    public Hopfield(int neurons, IFunction function) {
            this.f = function;
            this.W = new double[neurons][neurons];
            initW();
    }
    
    private void initW(){
        for (int i = 0; i < W.length; i++) {
            for (int j = 0; j < W.length; j++) {
                this.W[i][j] = 0.0;
            }
        }
    }
    
    public void train(ArrayList<double[]> patterns){
        this.patterns = patterns;
        Iterator<double[]> i = patterns.iterator();
        while(i.hasNext()){
            if (i.next().length != this.W.length) {
                throw new IndexOutOfBoundsException("All patterns must have " + this.W.length + " dimensions");
            }
        }
        i = patterns.iterator();
        while(i.hasNext()){
            double[] v = i.next();
            double[][] wAux = Matrix.subtract(Matrix.multiply(v, v), Matrix.identity(this.W.length));
            this.W = Matrix.add(W, wAux);
        }
    }
    
    public double[] test(double[] pattern){
        boolean found = false;
        boolean loop = false;
        if (pattern.length != this.W.length) {
            throw new IndexOutOfBoundsException("The pattern doesn't have " + this.W.length + " dimensions");
        }
        double[] result = pattern;
        aux = new ArrayList<>();
        while(!found && !loop){
            result = f.calculate(Matrix.multiply(W, result));
            if (!this.contains(result, patterns)) {
                //The result wasn't in the pattern list
                if (!this.contains(result, aux)) {
                    //There are not loops
                    aux.add(result);
                }
                else{
                    loop = true; //loop found, result exists in aux list
                }
            }
            else{
                found = true; //pattern equals result, found in patterns list
            }
        }
        if (!found) {
            for (int i = 0; i < pattern.length; i++) {
                result[i] = Double.NaN;
            }
        }
        return result;
    }
    
    public double energy(double[] patterns){
        double[] result = this.test(patterns);
        double energy = Double.MIN_VALUE;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == Double.NaN) {
                break;
            }
        }
        for (int i = 0; i < this.W.length; i++) {
            for (int j = 0; j < this.W.length; j++) {
                energy += -0.5 * this.W[i][j] * result[i] * result[j];
            }
        }
        return energy;
    }
    
    private boolean contains(double[] pattern, ArrayList<double[]> patterns){
        boolean output = false;
        for (int i = 0; i < patterns.size(); i++) {
             if (Matrix.equals(pattern, patterns.get(i))) {
                 output = true;
                 break;
             }
         }
        return output;
    }
    
    public void showWeights(){
        Matrix.showMatrix(W);
    }
    
    public double[][] getWeights(){
        return this.W;
    }
    
    public void showAuxVector(){
        System.out.println("-------------------------");
        if (!this.aux.isEmpty()) {
            System.out.println("Auxiliar vector for loop inside the test");
            for (int i = 0; i < aux.size(); i++) {
                System.out.println("Iteration " + i);
                Matrix.showVector(aux.get(i));
            }
        }
        else{
            System.out.println("The auxiliar vector is empty");
        }
    }
    
    public static int goodRecuperation(int neurons){
        return (int)(0.138 * neurons);
    }
    
    public static int perfectRacuperation(int neurons){
        return (int)(neurons / (4 * Math.log(neurons)));
    }
    
    public int connections(){
        return this.W.length * (this.W.length - 1);
    }
}
