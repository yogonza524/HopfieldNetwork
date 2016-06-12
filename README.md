# Hopfield Network
Java application for discret Hopfield Networks. Implementation for Artificial Intelligence

# Requirements
- Java Virtual Machine v 7.0 (JRE 7)

# License
MIT

# Unit test
```java
     @Test
     public void HopfieldTest(){
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
         System.out.println("Weight Matrix");
         Matrix.showMatrix(h.getWeights());
         System.out.println("\nPattern result of test");
         Matrix.showVector(result);
        
        h.showAuxVector();
     }
```
After run the test we have the output like this
```
Running HopfieldTest

Connections of Network: 72

Good recuperation capacity of samples: 1

Perfect recuperation capacity of samples: 1

Energy: -32.0

Weight Matrix
 0.0    	0.0    	2.0    -2.0      2.0       -2.0       0.0    	0.0    	0.0
 0.0    	0.0    	0.0    	0.0    	 0.0    	0.0    	 -2.0    	2.0	   -2.0
 2.0    	0.0    	0.0    -2.0   	 2.0   	   -2.0       0.0   	0.0    	0.0
-2.0  	    0.0	   -2.0	    0.0	    -2.0	    2.0	      0.0	    0.0	    0.0
 2.0	    0.0	    2.0	   -2.0	     0.0	   -2.0       0.0	    0.0    	0.0
-2.0	    0.0	   -2.0	    2.0	    -2.0        0.0	      0.0	    0.0	    0.0
 0.0	   -2.0	    0.0	    0.0      0.0	    0.0	      0.0	   -2.0	    2.0
 0.0	    2.0	    0.0	    0.0	     0.0	    0.0	     -2.0	    0.0	   -2.0
 0.0	   -2.0	    0.0	    0.0	     0.0	    0.0	      2.0	   -2.0	    0.0

Pattern result of test
1.0	1.0	1.0	-1.0	1.0	-1.0	-1.0	1.0	-1.0
-------------------------
The auxiliar vector is empty
```
