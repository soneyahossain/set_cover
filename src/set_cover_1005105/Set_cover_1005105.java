/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package set_cover_1005105;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 *
 * @author soneya
 */
public class Set_cover_1005105 {
    int numOf_subsets;
    int sizeOf_parent_set;
    Vector<Integer> parent_set= new Vector();
    Vector<Vector<Integer>> subset_family=new Vector() ;
    
    private int[][] dp;
    private int[] mask;
    private int[] weight;
    int combination;
    
   

    private Set_cover_1005105(int data_size, Scanner input) {
       
        numOf_subsets=data_size-1;
        input(data_size,input);
        mask = new int [ numOf_subsets];
        weight= new int [ numOf_subsets];
        
        print_parent_set ();
        calculate_weight();
        calculate_mask();
         
        double j= Math.pow(2.00,sizeOf_parent_set);
        int p=numOf_subsets+1;
       
        dp=new int [(int)j][p];
        combination=(int)j;
        for (int i=0;i<(int)j;i++)
        {
            for (int k=0;k<p;k++)
            {
                dp[i][k]=-1;
            }
            
       }
    }

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
    
//.................................................................................................................  
   
   
   int set_cover(int coveredMask,int nowConsiderIndex)  
   {
       
       int valChoose;
       int valNotChoose;
       
       
  
       if ((nowConsiderIndex == numOf_subsets) && (coveredMask!=((1<<sizeOf_parent_set)-1)))
       {
            return 1000000000;
       }

       else if ((nowConsiderIndex == numOf_subsets) && (coveredMask==((1<<sizeOf_parent_set)-1)))   
       {
                return 0;
       }


      else if(dp[coveredMask][ nowConsiderIndex]!=-1) 

      {
           return dp[ coveredMask][ nowConsiderIndex];
      }
      else 
      {
               
               valChoose= set_cover(coveredMask | mask[nowConsiderIndex],nowConsiderIndex+1) + weight[nowConsiderIndex];
               valNotChoose= set_cover(coveredMask,nowConsiderIndex+1 );
               dp[ coveredMask][ nowConsiderIndex ] = max ( valChoose,valNotChoose);


      }
       


          
          /*System.out.println(".............................");
          System.out.println("value choose="+valChoose);
          System.out.println("value not choose="+valNotChoose);*/
          return dp[coveredMask][ nowConsiderIndex];
         
         
         
          
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
        for(int x = 0; x < parent_set.size(); x++)
        {
           System.out.print(parent_set.elementAt(x)+" "); 
        }
        
        System.out.println();
        
       
    }
    
   
    public static void main(String[] args) throws FileNotFoundException, IOException {
    
        
        final int sta_Time =(int) System.currentTimeMillis();
        Scanner input = new Scanner(new File("input10.txt"));
        int data_size = input.nextInt();
     
        
        Set_cover_1005105 instance= new Set_cover_1005105(data_size,input);
        System.out.println(".............................");
        //instance.print_dp();
        System.out.println(".............................");
        int cost = instance.set_cover(0,0);
        System.out.println("cost:   "+cost);
        System.out.println(".............................");
        //instance.print_dp();
        System.out.println(".............................");
        instance.print(0,0);
        
        
        
        final int end_Time =(int) System.currentTimeMillis();
        int duration=end_Time-sta_Time;
        
        
        //..........................
        
        
        System.out.println("exectution time : "+duration+" millisecond");  
        File dir=new File(".");
        File dir1=new File(".");
        String location= dir.getCanonicalPath()+File.separator+ "set_cover_1005105.txt";
        String location1= dir1.getCanonicalPath()+File.separator+ "set_cover_cost_1005105.txt";
        FileWriter f=new FileWriter(location,true);
        FileWriter f1=new FileWriter(location1,true);
        BufferedWriter b= new BufferedWriter(f);
        BufferedWriter b1= new BufferedWriter(f1);
        b.write(Integer.toString(instance.sizeOf_parent_set));
        b.write("  ");
        b1.write(Integer.toString(instance.sizeOf_parent_set));
        b1.write("  ");
        b.write(Integer.toString(duration));
        b1.write(Double.toString(cost));
        b.newLine();
        b1.newLine();
        b.close();
        b1.close();
        
        
    }

    private int max(int valChoose, int valNotChoose) {
        if(valChoose<valNotChoose) {
            return valChoose;
        }
        else {
            return valNotChoose;
        }
    }

    private void calculate_mask() 
    {
      
      for(int x = 0; x <subset_family.size() ; x++)
      { 
          int m=0;
          for(int y =0; y < subset_family.elementAt(x).size(); y++)
          {
              int val=subset_family.elementAt(x).elementAt(y);
              int position=-1;
              for(int z = 0; z < parent_set.size(); z++)
              {
                  int value=parent_set.elementAt(z);
                  if(value==val)
                  {
                     position=z;
                     
                     
                  }
              }
              
              if(position!=-1) {
                  position=1<<position;
                  m=m|position;
              }
              
          }
          
          System.out.println("mask of subset "+"["+x+"]="+ m+" ");  
          mask[x]=m;
      }
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
   void print_dp()
   {
        for (int i=0;i<combination;i++)
        {
            System.out.println(); 
            for (int k=0;k<numOf_subsets+1;k++)
            {
                System.out.print(dp[i][k]+"    ");
            }
            System.out.println(); 
       }
   }

    private void print(int MASK,int nowconsider_index) {
        
        if(nowconsider_index==numOf_subsets)
        {
            return;
        }
        else if(MASK==((1<<sizeOf_parent_set)-1))
        {
            return;
        }
        double value=dp[MASK][nowconsider_index];
        
        
        int value_not_choose;
        //value_choose = dp[ MASK | mask[nowconsider_index]][nowconsider_index+1];
        value_not_choose = dp[ MASK ][nowconsider_index+1];
        //double cost=weight[nowconsider_index];
        
        if(value== value_not_choose)
        {
            print(MASK,nowconsider_index+1); 
        }
        else
        {
            System.out.println("subset_id:"+nowconsider_index);
            print((MASK | mask[nowconsider_index]),nowconsider_index+1); 
        }
        
    }
}
