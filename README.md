[<img src='http://4.bp.blogspot.com/--wUe5Oz23Ow/U8w2OvRr1fI/AAAAAAAAG6c/w0o_FxM1z7Y/s1600/gitHub-download-button.png' />](https://github.com/yogonza524/HopfieldNetwork/releases/download/1.0/Hopfield-1.0.jar) <br />
You can dounload the app with GUI here <br />
[<img src='http://images.freecreatives.com/wp-content/uploads/2015/05/Download-button-1712111121111111.png' />](https://github.com/yogonza524/HopfieldNetwork/releases/download/1.0.1/HopfieldFX-1.0.jar) <br />
<img src='http://thales.cica.es/rd/Recursos/rd98/TecInfo/07/5-1.jpg' />
<img src='https://upload.wikimedia.org/math/a/b/4/ab46c4ec598e884f2fdaf34f88ca73c8.png' />
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
         
         h.train(patterns); //train and load the Weight matrix
         
         double[] result = h.test(p3); //Test a pattern
         
         System.out.println("\nConnections of Network: " + h.connections() + "\n"); //show Neural connections
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
 1.0	    1.0     1.0     -1.0     1.0	   -1.0	     -1.0	    1.0     -1.0
-------------------------
The auxiliar vector is empty
```
## Explanation
When you call to test(double[] pattern) this method return a pattern present in patterns list (after training). If the result pattern of test is not equals NaN (for each value) then, the auxiliar vector will be empty, otherwise the auxiliar vector will be loaded with partial patterns until found the pattern or found a loop.
