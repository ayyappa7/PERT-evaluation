/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pert;
//use array of textfields

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;
/**
 *
 * @author Ayyappa
 */
public class home {
   
    JMenuBar menubar;
   JMenuItem newpro;
   JMenuItem openpro;
   JButton addact,set,done;
   //JButton[] delete;
   JTextField numact;
  JTextField[]fromact;
   JTextField[]toact;
   JTextField[] description;
   JTextField[] oduration;
   JTextField[] eduration;
   JTextField[] pduration;
   
   JFrame frame;
  JPanel ltpanel,rtpanel;
    JLabel slab;
    GridBagLayout layout ;
       GridBagConstraints  con;
static int actcount;
 
  
   
    home(){
       
   initialise();
  
      
      comps();  
        
     
                
          addact.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent event){
                
        
                drawinput(actcount);
                actcount++;
             }});
  
   set.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent event){
                 rtpanel.removeAll();
              // delete=new JButton[30];
       fromact=new JTextField[30];
  toact=new JTextField[30];
   description=new JTextField[30];
   eduration=new JTextField[30];
   oduration=new JTextField[30];
   pduration=new JTextField[30];
 
       
               
       con.gridx=0;
        con.gridy=0;
        
       
       con.fill=GridBagConstraints.HORIZONTAL;
         rtpanel.add(new JLabel("SL No"));
        con.gridx++;
        rtpanel.add(new JLabel("   From Activity  "));
        con.gridx++;
        rtpanel.add(new JLabel("   To Activity  "));
        con.gridx++;
        rtpanel.add(new JLabel("   description   "));
        con.gridx++;
        rtpanel.add(new JLabel("   Optimal Duration      "));
        con.gridx++;
        rtpanel.add(new JLabel("  Expected Duration      "));
        con.gridx++;
        rtpanel.add(new JLabel("   Passimistic Duration      "));
                con.gridx++;
        //rtpanel.add(new JLabel("     Delete      "));
                  String k;
            int l,m,val;
            k=numact.getText();
                 l= Integer.parseInt(k);  
               
                 actcount=0;
                  con.gridx=0;
        con.gridy=1;
        
       
     
                 for( m=0;m<l;m++)
                 {
                 val=m;
         drawinput(actcount);
         actcount++;
                 }
                
                System.out.println("actcount"+actcount);
                rtpanel.revalidate();
                rtpanel.repaint();
       // frame.revalidate();
//frame.repaint();
                
             }});
   done.addActionListener(
             new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent event){
                 String[] fromname=new String[100];
                  String[] toname=new String[100];
                  String[] desc=new String[100];
                  String[] odur=new String[100];
                  String[] edur=new String[100];
                  String[] pdur=new String[100];
                int i;
               
                 System.out.println("aactcount"+actcount);
                for(i=0;i<actcount;i++){
                 fromname[i]=fromact[i].getText();
                 toname[i]=toact[i].getText();
                 desc[i]= description[i].getText();
               odur[i]= oduration[i].getText();
               edur[i]= eduration[i].getText();
               pdur[i]= pduration[i].getText();
               System.out.println("done:"+fromname[i]+ toname[i]);
                 }
                 CLayout cl1= new CLayout(fromname,toname,desc,odur,edur,pdur,actcount-1);
             }});
          
  ltpanel.setBackground(Color.white);
  rtpanel.setBackground(Color.LIGHT_GRAY);

  frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setLocation(10,100)  ;
   frame.setSize(1300,600);
    frame.setVisible(true);
    
    
    }
public void comps(){

        
        ltpanel.add(new JLabel("   Number Of Activity "));
        
        ltpanel.add(numact);
         
         set=new JButton("set");
         set.setSize(20,20);
         
        ltpanel.add(set);
        
         addact=new JButton("ADD Extra Activity");
        addact.setSize(20,20);
        ltpanel.add(addact);
        
          done=new JButton("DONE");
        done.setSize(20,20);
        ltpanel.add(done);
        
           con.gridx=0;
        con.gridy=0;
        
       
       con.fill=GridBagConstraints.HORIZONTAL;
         rtpanel.add(new JLabel("SL No"));
        con.gridx++;
       rtpanel.add(new JLabel("   From Activity  "));
        con.gridx++;
        rtpanel.add(new JLabel("   To Activity  "));
        con.gridx++;
        rtpanel.add(new JLabel("   description   "));
        con.gridx++;
        rtpanel.add(new JLabel("   Optimal Duration      "));
        con.gridx++;
        rtpanel.add(new JLabel("  Expected Duration      "));
        con.gridx++;
        rtpanel.add(new JLabel("   Passimistic Duration      "));
                con.gridx++;
       // rtpanel.add(new JLabel("     Delete      "));
     drawinput(actcount);
     actcount++;
      }
   public void initialise(){
  actcount=0;
  // delete=new JButton[20];
       addact=new JButton("Add Activity");
       // menubar=new JMenuBar();//menubar
        numact = new JTextField("3",10);
    //    delete=new JButton[30];
       fromact=new JTextField[30];
  toact=new JTextField[30];
   description=new JTextField[30];
   oduration=new JTextField[30];
   eduration=new JTextField[30];
   pduration=new JTextField[30];
  frame=new JFrame();
  frame.setLayout(new BorderLayout());
  ltpanel=new JPanel(new GridLayout(5,1));
  ltpanel.setSize(300, 100);
        frame.add(ltpanel,BorderLayout.WEST);
        rtpanel=new JPanel();
         layout =new GridBagLayout();
            con= new GridBagConstraints();
      rtpanel.setLayout(layout); 
        
        rtpanel.setSize(1000,600);
        JScrollPane jsp=new JScrollPane(rtpanel);
        frame.add(jsp,BorderLayout.CENTER);
   
   }
 
    public void drawinput(int k){
    con.fill=GridBagConstraints.HORIZONTAL;
             con.gridx=0;
        con.gridy=k+2;
        int sno =k+1;
        int ch =64+sno;
        char c1=(char)ch;
        char c2=(char)(ch+1);
       
      
        String s=""+c1;
        String s2=""+c2;
         slab=new JLabel(" "+sno+" ");
        layout.setConstraints(slab, con);
        rtpanel.add(slab);
         con.gridx++;
       fromact[k]=new JTextField("0",15);
       fromact[k].setText(s);
        layout.setConstraints(fromact[k], con);
        rtpanel.add(fromact[k]);
         con.gridx++;
         toact[k]=new JTextField(s2,15);
         layout.setConstraints(toact[k], con);
          rtpanel.add(toact[k]);
         con.gridx++;
        description[k]= new JTextField("0",15);
          layout.setConstraints(description[k], con);
       rtpanel.add(description[k]);
         con.gridx++;
         oduration[k]= new  JTextField(""+k+".3",10);
          layout.setConstraints(oduration[k], con);
       rtpanel.add(oduration[k]);
        con.gridx++;
        eduration[k]= new  JTextField(""+k+".5",10);
          layout.setConstraints(eduration[k], con);
       rtpanel.add(eduration[k]);
        con.gridx++;
        pduration[k]= new  JTextField(""+k+".7",10);
          layout.setConstraints(pduration[k], con);
       rtpanel.add(pduration[k]);
        con.gridx++;
        
     //   delete[k]=new JButton("Delete");
       //  layout.setConstraints(delete[k], con);
     //  rtpanel.add(delete[k]);
        con.gridx++;
      
        
     rtpanel.revalidate();
           
        
    }
    
    } 
  

