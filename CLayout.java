/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pert;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import org.apache.commons.math.MathException;
import pert.CLayout.MyPanel;


/**
 *
 * @author Ayyappa
 */
public final class CLayout {
    public JFrame frame ;
     public JPanel b,result,data;
     public JTextArea datata;
     public JTextField datatf;
     
 public   JButton b1,b2;
 public String prob;
public  String[] fromact={},toact={},adesc={},oduration={},eduration={},pduration={},distinctact={};
public int[][] preact,table,folact;
 
public int noact,maxcol,distactcount,dn,sa,drn,dsa,col,row,maxr,cp,vn;
public int[] maxrow,acts,noprecs,drawnnode,selectedact,deselectedact,drawingnode,cpath,validnode;
public float[] mean,dev,ET,LT;
public float meanp,devp,dur,zprob;
public     CardLayout cl;
   
    public  CLayout(String[] s1,String[] s2,String[] s3,String[] s4,String[] s5,String[] s6,int c){
         frame = new JFrame("OUTPUT");
        fromact=new String[c+1];
        toact=new String[c+1];
        adesc=new String[c+1];
        oduration=new String[c+1];
        eduration=new String[c+1];
        pduration=new String[c+1];
        distinctact=new String[c*3];
        selectedact=new int[noact];
        mean=new float[noact];
        dev=new float[noact];
        ET=new float[noact*2];
        LT=new float[noact*2];
     deselectedact=new int[noact];
     drawnnode=new int[noact];
     drawingnode=new int[noact*2];
       dn=0;sa=0;dsa=0;drn=0;col=0;row=0;
        distactcount=0; 
        fromact=s1; 
         toact=s2;
         adesc=s3;
         oduration=s4;
         eduration=s5;
         pduration=s6;
         noact=c+1;
         table=new int[noact][noact];
       maxcol=noact*2;
      maxrow= new int[noact*2];
        
         System.out.println("toact[0]="+toact[0]+"[1]="+toact[1]+"[3]"+toact[2]);
         frame.setLayout(new BorderLayout());
            chartalgo();
            dataalgo();
          buttoncol();
          chartscreen();
      
          
             buttons();
     
    
    frame.setDefaultCloseOperation(frame.getDefaultCloseOperation());
    frame.setSize(1300, 700);
    frame.setLocation(10, 10);
    frame.setVisible(true);
 }
    public void buttoncol(){
         b1 = new JButton("Chart");
         b2 = new JButton("Data");
         b = new JPanel();
       
     frame.add(b,BorderLayout.WEST);
       
          final   GridBagLayout blayout = new GridBagLayout();
          final   GridBagConstraints   bcon= new GridBagConstraints();
        b.setLayout(blayout); 
        bcon.fill=GridBagConstraints.VERTICAL;
        bcon.insets =new Insets(5,2,5,5);
        bcon.gridx=0;
        bcon.gridy=0;
        blayout.setConstraints(b1, bcon);
        b.add(b1);
        bcon.gridy++;
        blayout.setConstraints(b2, bcon);
        b.add(b2);
        b.setBackground(Color.LIGHT_GRAY);
       
       
    }
    public void chartscreen(){
     result = new JPanel();
     MyPanel charts = new MyPanel();
    
    data = new JPanel();
   dur=0;
    datata=new JTextArea();
    JLabel datajl=new JLabel("Estimated Project Duration:");
     datatf=new JTextField(10);
//    JButton databb = new JButton("Before");
//    JButton dataab = new JButton("After");
    JButton datagb = new JButton("Set");
    data.add(datajl);
    data.add(datatf);
   
    data.add(datagb);
    
//    databb.addActionListener(
//             new ActionListener(){
//             @Override
//             public void actionPerformed(ActionEvent event){
//                 String s3=datatf.getText();
//   dur=Float.parseFloat(s3);
//            Probcal ob1=new Probcal(meanp,devp,dur);
//                 try {
//                     zprob =ob1.before();
//                 } catch (MathException ex) {
//                     Logger.getLogger(CLayout.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//            adds(" Probability="+zprob);
//            
//             }});
//     dataab.addActionListener(
//             new ActionListener(){
//             @Override
//             public void actionPerformed(ActionEvent event){
//                  String s3=datatf.getText();
//   dur=Float.parseFloat(s3);
//            Probcal ob1=new Probcal(meanp,devp,dur);
//                 try {
//                     zprob=ob1.after();
//                 } catch (MathException ex) {
//                     Logger.getLogger(CLayout.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//            adds(" Probability="+zprob);
//             }});
      datagb.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent event){
                  String s3=datatf.getText();
                 
   dur=Float.parseFloat(s3);
   double z=Math.abs(dur-meanp)/Math.sqrt(devp);
    String s4=(""+z);
            adds("Z for given duration is"+s4);
            Probcal ob1=new Probcal(meanp,devp,dur);
                 try {
                     zprob=ob1.given();
                 } catch (MathException ex) {
                     Logger.getLogger(CLayout.class.getName()).log(Level.SEVERE, null, ex);
                 }
             adds(" Probability for completion for given duration="+zprob);
             }});
    
    datata.setFont(new Font("Serif",Font.PLAIN,20));
    datata.setSize(result.getWidth(), result.getHeight());
    data.add(datata);
     frame.add(new JScrollPane(result),BorderLayout.CENTER);
     cl = new CardLayout();
     result.setLayout(cl);
     result.setBackground(Color.yellow);
     result.add(charts,"1");
     result.add(data,"2");
     cl.show(result,"2");
     charts.setBackground(Color.white);
      adddata();
       
    }
    
    public void buttons(){
     b1.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent event){
             fun1();
             }});
        
        b2.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent event){
             fun2();
             }});
    }

   

    
    public  class MyPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);  
             maxr=0;
             int fromi=0,fromj=0,toi=0,toj=0;
            for(int i=0;i<maxcol;i++){
            if(maxr<maxrow[i]){
            maxr=maxrow[i];
            
            }}
            int roww,colh;
            boolean from=true;
            colh=result.getHeight()/maxr;
            roww=result.getWidth()/maxcol+1;
 
            for(int i=0;i<maxcol;i++)
            {
    //System.out.println("maxrow["+i+"]="+maxrow[i]);
            for(int j=0;j<maxrow[i];j++)
            {
                
    addactivity(g,i*roww+20,j*colh+20,table[i][j]);
   
    
}         
            }//System.out.println("x"+i*colw+"y"+j*rowh[i]);
    
         
        
//add arrows
            for(int i=0;i<noact;i++){
                fromi=0;fromj=0;toi=0;toj=0;
                for(int k=0;k<maxcol;k++)
            {
            for(int j=0;j<maxrow[k];j++)
            {
                if(dactmatch(fromact[i])==table[k][j]){
                fromi=k;
               fromj=j;
                }else if(dactmatch(toact[i])==table[k][j]){
               toi=k;
                toj=j;
              }
            }
           }
arrow(g,fromi*roww+75,toi*roww+20,fromj*colh+50,toj*colh+50,mean[i]);
        
        }
        }

        public void addstring(Graphics g,String s) {
           
            g.drawString(s, 20, 20);
            
        }
         private final int ARR_SIZE = 4;
        public void arrow(Graphics g,int x1,int x2,int y1,int y2,float mean){
       // g.drawLine(x1,x2,y1,y2);
           g.setColor(Color.black);
int[] xp={x2-10,x2-10,x2};
int[] yp={y2+10,y2-10,y2};
int strx=((x1+x2)/2)-50,stry=((y1+y2)/2)-20;
           Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(5));
                g2.draw(new Line2D.Float(x1,y1,x2-5,y2));
                g2.fillPolygon(xp,yp, 3);
               String s="mean"+mean;
               g.setColor(Color.BLACK);
               g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
                g.drawString(s,strx,stry);
        }

        public void addactivity(Graphics g,int x,int y,int actnumber) {
            g.setColor(Color.blue);
            g.fillRoundRect(x, y,55, 55, 10, 10);
            g.setColor(Color.white);
            g.fillRoundRect(x+3, y+3,48, 48, 11, 11);
            g.setColor(Color.ORANGE);
             g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
          g.drawString(distinctact[actnumber], x+10, y+22);
          g.drawString("TE="+ET[actnumber], x+10, y+72);
          g.drawString("TL="+LT[actnumber], x+10, y+92);
            //System.out.println("x"+x+"y"+y);
            
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 200);
        }
    }
   public void fun1(){
       
       
   //charts.setBackground(Color.BLACK);
   
   
   cl.show(result, "1");
   }
    public void fun2(){
         data.setBackground(Color.green);
   cl.show(result, "2");
   }
    
   
public void chartalgo(){
        
      
      
   distact();
caltable();


}
public void distact(){
int l=0,i,j;
    boolean exists;
   

    for( i=0;i<noact;i++){
   exists=false;
        for( j=0;j<l;j++)
   {
   if(fromact[i].equals(distinctact[j])){
  exists=true;
   }
   }
   if(exists==false){
  distinctact[l]=fromact[i];
  l++;
   }
    }
    
    for( i=0;i<noact;i++){
   exists=false;
        for( j=0;j<l;j++)
   {
   if(toact[i].equals(distinctact[j])){
  exists=true;
   }
   }
   if(exists==false){
  distinctact[l]=toact[i];
  l++;
   }
    }
    
    distactcount=l;
    System.out.println("l:"+l);
    for( i=0;i<distactcount;i++){
    System.out.println("distinctact"+i+"="+distinctact[i]);
    }
}

 int dactmatch(String s){
    for(int i=0;i<distactcount;i++){
    if(distinctact[i].equalsIgnoreCase(s)){
    return i;
}
    }    return distinctact.length;
}
    public void caltable(){
      table = new int [noact*2][noact];
      for(int i=0;i<noact*2;i++){
      for(int j=0;j<noact;j++){
      table[i][j]=999;
      }
      }
    drawnnode=new int [noact*3];
   dn=0;
        System.out.println("distinctactcount"+distactcount);
       calinnode();
       while(sa<noact){
        
       selectedact=new int[noact*2];sa=0;dsa=0;
     deselectedact=new int[noact*2];
     entsds();
     drawingnode=new int[noact*2];
     drn=0;
      entdrn(); 
       sub();
       enttable();
       entdn();
       
      }
       disptable();
    
    
}
    public void calinnode(){
     row=0;col=0;
        for(int i=0;i<distactcount;i++){
            boolean exists=false;
         for(int j=0;j<noact;j++){
          //   System.out.println("distinctact["+i+"]"+distinctact[i]+"toact["+i+"]="+toact[j]);
         if(distinctact[i].equalsIgnoreCase(toact[j])){
        exists=true;
        break;
         }
         }
         if(exists==false){
         table[0][row]=dactmatch(distinctact[i]);
                 row++;
                 drawnnode[dn]=table[0][row-1];
                 dn++;
                 
         }
        }
        maxrow[0]=row;
        col++;
        
        System.out.println("innode="+table[0][0]);
     //   System.out.println("drawnnode="+table[0][0]+"maxrow[0]="+maxrow[0]);
    }
     
    public void entdn(){
 
            for(int j=0;j<row;j++){
           int newcol=col-1;
           
       drawnnode[dn]=table[newcol][j];
        System.out.println("dn:"+dn);
            dn++;   
            
             //  System.out.println("new drawnnode="+drawnnode[dn-1]);
             //  System.out.println("new dn="+dn);
            }
               for(int j=0;j<dn;j++){
           //    System.out.println(" drawnnode====================="+drawnnode[j]);
               
               }
    
  
    }
    public void entsds() {
       
      for(int i=0;i<noact;i++){
       if(indn(dactmatch(fromact[i]))){
       selectedact[sa]=i;
       sa++;
    //  System.out.println("selectedact"+i+"sa"+sa);
       }else{
       deselectedact[dsa]=i;
       dsa++;
      //System.out.println("deselectedact"+i); 
       }
       }
    }
    
     public void entdrn() {
      for(int i=0;i<noact;i++){
  if(indsa(i)==false && indrn(i)==false){
      drawingnode[drn]=dactmatch(toact[i]);
  drn++;
 // System.out.println("drawingnde:toact of selected act:"+dactmatch(toact[i]));
  }}
     }
public boolean indn(int l){
 for(int i=0;i<dn;i++){
       if(l==drawnnode[i])
       {
           System.out.println("drawnnode"+drawnnode[i]);
           return true;
       
       }
       
       }
 return false;
}
public void sub()
{
   System.out.println("started sub");
for(int j=0;j<noact;j++){
if(indsa(j)){
remv(dactmatch(toact[j]));

   
}
}
for(int i=0;i<dn;i++){
remv(drawnnode[i]);
}

for(int i=0;i<drn;i++){
System.out.println("drawingnode="+drawingnode[i]);

}
}
public boolean indsa(int k ){
    for(int i=0;i<dsa;i++){
    if(k==deselectedact[i]){
    return true;
    }
}
return false;

}
public boolean indrn(int k ){
    for(int i=0;i<drn;i++){
    if(dactmatch(toact[k])==drawingnode[i]){
    return true;
    }
}
return false;

}
public void remv(int k){

    for(int i=0;i<drn;i++){
        if(drawingnode[i]==k){
        drawingnode[i]='\0';
        }
    }
    

}
public void enttable(){
row=0;
    for(int j=0;j<drn;j++){
if(drawingnode[j]!='\0'){
table[col][row]=drawingnode[j];
row++;
System.out.println("table["+col+"]["+row+"-1]="+table[col][row-1]);
}
}maxrow[col]=row;
    System.out.println("maxrow["+col+"]"+maxrow[col]);
    col++;
    maxcol=col;
}
public void disptable(){
for(int i=0;i<col;i++){
    for(int j=0;j<table[i].length;j++){
        System.out.println("table["+i+"]["+j+"]="+table[i][j]);
}}
}



 public void dataalgo(){
        ET = new float[distactcount];
        LT = new float[distactcount];
       mean=new float[noact];
       dev=new float[noact];
        calms();
      
       for(int i=0;i<maxcol;i++){
       for(int j=0;j<maxrow[i];j++){
      if(i==0){ET[table[i][j]]=0;
     }
      else{
      ET[table[i][j]]=maxet(table[i][j]);
     
      }
       }
       }
       for(int i=maxcol-1;i>=0;i--){
       for(int j=0;j<maxrow[i];j++){
           if(i==maxcol-1){
           LT[table[i][j]]=ET[table[i][j]];
           System.out.println("max copied");
           }else
       LT[table[i][j]]=ltcal(table[i][j]);
                }
       }
       
       criticalpath();
      calmdp();
       
 }
public float maxet(int i){

float max=0;    
    for(int j=0;j<noact;j++){
if(dactmatch(toact[j])==i){
if(max<ET[dactmatch(fromact[j])]+mean[j]){
max=ET[dactmatch(fromact[j])]+mean[j];
System.out.println("selected fromact"+fromact[j]+"mean["+j+"]="+mean[j]);
    }
}
}
    System.out.println("max="+max);
    return max;
}

public float ltcal(int i)
{
    float min=999;    
    for(int j=0;j<noact;j++){
if(dactmatch(fromact[j])==i){
if(min>LT[dactmatch(toact[j])]-mean[j]){
min=LT[dactmatch(toact[j])]-mean[j];
System.out.println("selected toact"+toact[j]+"mean["+j+"]="+mean[j]);
    }
}
}
    System.out.println("min="+min);
    if(min<0){min=0;}
    return min;
    
}
public void calms(){
    float od,ed,pd;
   
for(int j=0;j<noact;j++){
 od=Float.parseFloat(oduration[j]);
 ed=Float.parseFloat(eduration[j]);
 pd=Float.parseFloat(pduration[j]);
     mean[j]=(float)(od+4*ed+pd)/6;
       dev[j]= (pd-od)*(pd-od)/36;    }
}
public void calmdp(){
    
for(int i=0;i<noact;i++){
for(int j=0;j<cp;j++){
if(cpath[j]==dactmatch(fromact[i])){
meanp=meanp+mean[i];
devp=devp+dev[i];
}
}
}
}
public void criticalpath(){
cpath=new int[maxcol];
cp=0;
int node=0;
    calvalidarc();
    cpath[cp]=table[0][0];
    System.out.print("Cpath["+cp+"]="+distinctact[cpath[cp]]+"   ");
    cp++;
    for(int i=1;i<maxcol-1;i++){
    for(int j=0;j<maxrow[i];j++){
    if(invn(table[i][j])){
    node=table[i][j];
    }
    }
    
    cpath[cp]=node;
    System.out.print("Cpath["+cp+"]="+distinctact[cpath[cp]]+"   ");
    cp++;
    }
    cpath[cp]=table[maxcol-1][0];
    System.out.print("Cpath["+cp+"]="+distinctact[cpath[cp]]+"   ");
}
public void calvalidarc(){
validnode=new int[distactcount];
vn=0;
for(int i=0;i<noact;i++){
if((ET[dactmatch(fromact[i])]+mean[i]) == ET[dactmatch(toact[i])]){
validnode[vn]=dactmatch(fromact[i]);
vn++;
}
}
}
public boolean invn(int i){
    
    for(int j=0;j<vn;j++){
    if(validnode[j]==i){
    return true;
    }
    }
    return false;

}

public void adddata(){

    datata.append("Sno\tactivities\tMean\tVariance\n");
    for(int i=0;i<noact;i++){
    datata.append(""+i+"\t"+fromact[i]+"-"+toact[i]+"\t"+mean[i]+"\t"+dev[i]+"\n");
    }
    datata.append("Critical Path=");
    datata.append(" "+distinctact[cpath[0]]+" ");
            for(int i=1;i<=cp;i++){
            datata.append("-"+distinctact[cpath[i]]+" ");
            }
            datata.append("\n");
            datata.append("mean of whole Projet = "+meanp+"\n");
            datata.append("variance of whole Projet = "+Math.sqrt(devp)+"\n");
           
    
}
public void adds(String s){
datata.append("\n"+s);
}
}