/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pert;
import java.util.*;
import java.lang.*;
import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.NormalDistribution;
import org.apache.commons.math.distribution.NormalDistributionImpl;
/**
 *
 * @author Ayyappa
 */
class Probcal {
    public double mean,dev,dur;
    private static NormalDistribution d;
    public Probcal(float m,float v,float d){
    mean=(double)m;
    dev=(double)Math.sqrt(v);
    dur=(double)d;
    
    
  }
    
    
    
    
    public float given() throws MathException{
     double ans= prob(dur);  
    return (float)ans;
    }
    public double prob(double x) throws MathException{
  
   x=Math.abs(dur-mean)/dev;
   d = new NormalDistributionImpl(0, 1);
       double prob=(d.cumulativeProbability(x));
      // if(dur<mean){return 1-prob;}
       if(dur-mean>0)
        return prob;
       else return 1-prob;
   
}
}