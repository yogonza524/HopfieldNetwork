/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.hopfield;

/**
 *
 * @author Gonza
 */
public class StepFunction implements IFunction{

    /**
     * Return an array pattern for a single array
     * @param net value to evaluate
     * @return an array with 1 if net >= 0 or (exclusive) -1 if net < 0
     */
    @Override
    public double[] calculate(double[] net) {
        double[] output = new double[net.length];
        for (int i = 0; i < net.length; i++) {
            output[i] = net[i] >= 0? 1.0 : -1.0;
        }
        return output;
    }
    
}
