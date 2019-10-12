/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cplex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

/**
 *
 * @author soneya
 */
public class Cplex {

    
    
    int numOf_subsets;
    int sizeOf_parent_set;
    Vector<Integer> parent_set= new Vector();
    Vector<Vector<Integer>> subset_family=new Vector() ;
    private int[] weight;
    private int[] f;
    double d[];
    int cost;
    
    
    
    
    private void input(int n,Scanner input)
   {
       String in;
       for (int i=-1;i<n;i++)
       {
            in = input.nextLine();
       
            StringTokenizer strToken = new StringTokenizer(in);
            int count = strToken.countTokens();
            Vector<Integer> sub_set= new Vector();
            for(int x = 0;x < count;x++)
            {
                  int k = Integer.parseInt((String)strToken.nextElement());
                  sub_set.add(k);
                  
            }
            subset_family.add(sub_set);
       }
   }
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        final int sta_Time =(int) System.currentTimeMillis();
        
        LinearObjectiveFunction f = new LinearObjectiveFunction(new
double[] { 3, 4, 9, 9,9,2, 1 ,9 ,6, 8,1 }, 0);
        Collection<LinearConstraint> constraints = new
ArrayList<LinearConstraint>();
        /*
12
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
* 
* 
* 
3       8 0 1 3 5 6 9 10 11 12 14
4       9 0 1 2 4 5 6 7 8 11 15
9       5 4 5 8 9 10 13 
9       5 1 7 8 9 10 13 18 16
9       2 4 9 17 19 
2       7 0 1 6 8 9 10 11
1       8 2 3 5 6 8 9 10 11 14
9       2 4 7
6       9 0 1 2 3 4 5 6 7 9 17 16
8       6 3 5 7 8 9 10
1       8 0 3 4 6 7 9 10 11 13





         */
        
        
        
        //.............................................................................................................
    constraints.add(new LinearConstraint(new double[] {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
Relationship.GEQ,  1));//19
        constraints.add(new LinearConstraint(new double[] {0, 0,0,1,0,0,0, 0,0,0,0},
Relationship.GEQ,  1));//18
        constraints.add(new LinearConstraint(new double[] {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
Relationship.GEQ,  1));//17
        constraints.add(new LinearConstraint(new double[] {0, 0,0,1,0,0,0, 0,1,0,0},
Relationship.GEQ,  1));//16
        
        constraints.add(new LinearConstraint(new double[] {0, 1,0,0,0,0,0, 0,0,0,0},
Relationship.GEQ,  1));//15
        constraints.add(new LinearConstraint(new double[] {1, 0,0,0,0,0,1, 0,0,0,0},
Relationship.GEQ,  1));//14
        constraints.add(new LinearConstraint(new double[] {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
Relationship.GEQ,  1));//13
       
       constraints.add(new LinearConstraint(new double[] {1, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0},
Relationship.GEQ,  1));//12
        constraints.add(new LinearConstraint(new double[] {1, 1, 0, 0, 0,1,0,0,1,0,1},
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 1, 1, 0, 1, 0,1,0,0,1,0,0},
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] {0, 1, 0, 0, 1,0,1,1,1,0,0},
Relationship.GEQ,  1));
        
        constraints.add(new LinearConstraint(new double[] {1,0,0,0,0,0,1,0,1,1,1},
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0,1,1,0,1,0,0,1,1,0,1},
  
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0},
                
                
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 1,1,0 ,0, 0 ,1, 1 ,0 ,1 ,1 ,1},
                
Relationship.GEQ,  1));
        
        
        
        constraints.add(new LinearConstraint(new double[] {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
Relationship.GEQ,  1));
        
        constraints.add(new LinearConstraint(new double[] { 1, 1 ,1 ,1 ,0, 1, 1, 0, 0, 1, 1 },
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] {1 ,1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] {1 ,0 ,1, 1, 0, 1, 1, 0, 0, 1, 1},
Relationship.GEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 1, 1, 0,0,0, 1, 1, 0, 0, 0, 1},
Relationship.GEQ,  1));
        
        
//...................................................................................................................

        constraints.add(new LinearConstraint(new double[] { 1, 0, 0,0,0,0,0,0,0,0,0},
Relationship.LEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 1, 0,0,0,0,0,0,0,0,0},
Relationship.LEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 1,0,0,0,0,0,0,0,0},
Relationship.LEQ,  1));
        
        
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0, 1, 0,0,0,0,0,0,0},
Relationship.LEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0, 0, 1,0,0,0,0,0,0},
Relationship.LEQ,  1));
         constraints.add(new LinearConstraint(new double[] { 0, 0, 0, 0, 0,1,0,0,0,0,0},
Relationship.LEQ,  1));
         
         constraints.add(new LinearConstraint(new double[] { 0, 0, 0,0,0,0,1,0,0,0,0},
Relationship.LEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0,0,0,0,0,1,0,0,0},
Relationship.LEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0,0,0,0,0,0,1,0,0},
Relationship.LEQ,  1));
        
        
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0, 0, 0,0,0,0,0,1,0},
Relationship.LEQ,  1));
        constraints.add(new LinearConstraint(new double[] { 0, 0, 0, 0, 0,0,0,0,0,0,1},
Relationship.LEQ,  1));
        
        
        
  
        
       
 
      
        
       //................................................................................................
        
        
        
        
        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(new MaxIter(100), f, new LinearConstraintSet(constraints),GoalType.MINIMIZE, new
        NonNegativeConstraint(true));
    
    
    
        System.out.println(solution.getValue());
        
        
        
        
        
        Scanner input = new Scanner(new File("input20.txt"));
        int data_size = input.nextInt();
     
        
        Cplex instance= new Cplex(data_size,input);
        instance.d=solution.getPoint();
        /*System.out.println(instance.d[0]);
        System.out.println(instance.d[1]);
        System.out.println(instance.d[2]);
        System.out.println(instance.d[3]);
        System.out.println(instance.d[4]);*/

        instance.calculate_frequency();
        instance.print_id();
        
        
        
        final int end_Time =(int) System.currentTimeMillis();
        int duration=end_Time-sta_Time;
        
        
        
        //...........................................................................................................
        
        
        System.out.println("exectution time : "+duration+" millisecond");  
        File dir=new File(".");
        File dir1=new File(".");
        String location= dir.getCanonicalPath()+File.separator+ "set_LP_runtime_1005105.txt";
        String location1= dir1.getCanonicalPath()+File.separator+ "set_LP_cost_1005105.txt";
        FileWriter f2=new FileWriter(location,true);
        FileWriter f1=new FileWriter(location1,true);
        BufferedWriter b= new BufferedWriter(f2);
        BufferedWriter b1= new BufferedWriter(f1);
        b.write(Integer.toString(instance.sizeOf_parent_set));
        b.write("  ");
        b1.write(Integer.toString(instance.sizeOf_parent_set));
        b1.write("  ");
        b.write(Integer.toString(duration));
        b1.write(Double.toString(instance.cost));
        b.newLine();
        b1.newLine();
        b.close();
        b1.close();
        
        
        
    }

    private Cplex(int data_size, Scanner input) {
        numOf_subsets=data_size-1;
        input(data_size,input);
        
        weight= new int [ numOf_subsets];
        
        print_parent_set ();
        calculate_weight();
         
    }
    
    
    
    private void print_parent_set ()
    {
        subset_family.remove(0);
        int k= subset_family.elementAt(0).size();   
        for(int x = 0; x < k ; x++)
        {
           parent_set.add(subset_family.elementAt(0).elementAt(x));
        }
        subset_family.remove(0);
        sizeOf_parent_set=parent_set.size();
       
        System.out.println("number of subsets:  "+ subset_family.size()+" ");
        System.out.println("size of parent set:  "+ sizeOf_parent_set);
        System.out.print("element of parent set:  "); 
        f= new int [ parent_set.size()];
        for(int x = 0; x < parent_set.size(); x++)
        {
           System.out.print(parent_set.elementAt(x)+" "); 
           f[x]=0;
        }
        
        System.out.println();
        
       
    }
    
    private void calculate_weight() 
    {
        for(int x = 0; x < subset_family.size(); x++)
        {
            Vector<Integer> p= subset_family.elementAt(x) ;
            int i=p.elementAt(0);
            subset_family.elementAt(x).remove(0);
            System.out.println("weight"+"["+x+"]: "+ i+" "); 
            weight[x]=i;
        }
        
        
        
        System.out.println("..............."+"subsets are"+"...................................");
        for(int x = 0;x <subset_family.size();x++)
        {
            for(int cx = 0;cx <subset_family.elementAt(x).size();cx++)
            {
                System.out.print(subset_family.elementAt(x).elementAt(cx)+" "); 
            }

            System.out.println();
        }
        System.out.println("................................................");
        
    }
    int max_f=-100000000;
    private void calculate_frequency() {
        
        
        
        for(int x = 0;x <subset_family.size();x++)
        {
            for(int cx = 0;cx <subset_family.elementAt(x).size();cx++)
            {
                int a=subset_family.elementAt(x).elementAt(cx);
                //System.out.print(subset_family.elementAt(x).elementAt(cx)+" "); 
                for(int k=0;k<parent_set.size();k++)
                {
                    if(parent_set.elementAt(k)==a)
                    {
                        f[k]=f[k]+1;
                    }
                }
            }

            //System.out.println();
        }
        
        for(int x = 0;x <f.length;x++)
        {
            //System.out.println(f[x]);
            if(f[x]>max_f) max_f=f[x];
        }
        
         //System.out.println(max_f);
    }

    private void print_id() {
        cost=0;
        
        for(int x = 0;x <d.length;x++)
        {
            //System.out.println(d[x]);
            double  d1=(d[x]*max_f);
            //System.out.println(d1);
            if(d1>=1)
            {
              System.out.println("subset id:" + x);
              cost=cost+weight[x];
            }
        }
      System.out.println("cost = "+cost);  
        
    }
}
