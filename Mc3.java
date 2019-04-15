package mc3;
import java.lang.String;
import java.io.*;
import java.util.*;

class MDT
{
    int index;
    String def;
    MDT(int i,String a)
    {
        this.index=i;
        this.def=a;
    }
      
}
class MNT
{
    int index,mdtind;
    String name;
    Vector<String>ala;
    MNT(int i,String a,int ind,Vector<String> b)
    {
        this.index=i;
        this.name=a;
        this.mdtind=ind;
        this.ala=b;
    }
}

public class Mc3
{
        static int po=0;
    
static int mdlc=0,n=0,sp=-1;
 static String[] s=new String[100];

 public static int searchmnt(Vector<MNT> a,String b)
 {
     int pos=-1;
     for(int i=0;i<a.size();i++)
     {
         MNT x=a.get(i);
         if(x.name.equals(b))
         {
             pos=i;
             break;
         }
         
     }
     return pos;
         
 }
 public static String readmodule(Vector<MDT> al,int po)throws IOException
 {
 StringBuffer ss = new StringBuffer();
 int l;
      BufferedReader r=new BufferedReader(new FileReader("D:\\trail.txt"));
     String d=" ";
       if(sp==-1)
       {
           d=r.readLine();
           if(po>=1)
            {
           for(int m=0;m<po;m++)
               d=r.readLine();
             }
       }
     
       else
       {
     
         int i;
        
          s[sp+1]=String.valueOf((Integer.parseInt(s[sp+1])+1));
         l=Integer.parseInt(s[sp+1]);
      
         MDT x=al.get(l);
         String def=x.def;
         int k=1;
           for(int k9 = 0; k9< def.length()-1; k9++)
                    {
                             if( def.charAt(k9) == '#') 
                             {
                               ss.append(s[sp+k+1]);
                                
                             }
                          else
                      ss.append(def.charAt(k9));
                         
                         
                    }
            d=ss.toString();
           
          
           if(def.contains("MEND"))
           {
               if(mdlc!=0)
               {  d=r.readLine();
                  
               }
                else
                   n=sp-Integer.parseInt(s[sp])-2;
               sp=Integer.parseInt(s[sp]);
              
            }
           
        }
       return d;
 }
       
 

   public static void main(String args[])throws IOException
   {
       try
       {
       //  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         Vector<MDT> mdt=new Vector<MDT>();
         Vector<MNT> mnt=new Vector<MNT>();
         Vector<String> isc=new Vector<String>();
         String d;
         boolean flag=false;
         while((d=readmodule(mdt,po))!=null)
         {   po++;
       
           d=d.trim();
           if(d.isEmpty()) continue;
           int pos;
            
                 String temp[]=d.split("\\s+");
                 pos=searchmnt(mnt,temp[0]);
                   
                 if(pos==-1)
                 {
           
                     if(d.contains("MACRO"))
                         flag=true;
                     else 
                         flag=false;
             
                     if(flag)
                     {  
                         d=readmodule(mdt,po); 
                         po++;
                         
                         String t[]=d.split("\\s+");
                         String t1[]=t[1].split("\\,");
                         Vector<String> arg=new Vector<String>();
                         for(int j=0;j<t1.length;j++)
                         {
                             String p=t1[j];
                             if(t1[j].contains("="))
                                 p=t1[j].substring(0,t1[j].indexOf("="));
                             arg.addElement(p);
                             
                         }
                         mnt.addElement(new MNT(mnt.size()+1,t[0],mdt.size()+1,arg));
                         mdt.addElement(new MDT(mdt.size()+1,d));
                         mdlc=mdlc+1;
                          d=readmodule(mdt,po);
                          po++;
                           while(mdlc!=0)
                           {
                               
                               
                              if(d.equalsIgnoreCase("macro")) 
                               { 
                                   mdt.addElement(new MDT(mdt.size()+1,d));
                                       mdlc=mdlc+1;
                                         d=readmodule(mdt,po);
                                         continue;
                                }
                               else if((d.equalsIgnoreCase("mend")))
                               {
                                    mdt.addElement(new MDT(mdt.size()+1,d));
                                       mdlc=mdlc-1;
                                         d=readmodule(mdt,po); 
                                        
                               }
                              else
                               {
                                    if(d.contains("&")) 
                                    {
                                        int i = d.indexOf('&');
                                        MNT h = mnt.get(mnt.size() - 1);
                                         int j;
                                       for(j = 0; j < h.ala.size(); j++) 
                                       {
                                        if(d.substring(i).equals(h.ala.get(j)))
                                        break;
                                         }
                                       mdt.addElement(new MDT(mdt.size() + 1, d.substring(0,i)+"#"+(j+1)));
                                    }
                                    else
                                        mdt.addElement(new MDT(mdt.size() + 1, d));
                                         d = readmodule(mdt,po);
                                         po++;  
                                }
                               }
                             if(mdlc==0)
                            continue;
                           }
                     
                else
                     {
                   System.out.println(d);
                    continue;
                        }
                     }
                                   
                       
                else
                 {
                    s[sp+n+2]=String.valueOf(sp);
                    sp=sp+n+2;
                    MNT j=mnt.get(pos);
                    s[sp+1]=String.valueOf(j.mdtind);
                    String alap[]=temp[1].split("\\,");
                    n=alap.length;
                 
                    int fg=0;
                    for(int g=2;g<=n+1;g++)
                    { s[sp+g]=alap[fg];
                    //    System.out.println(sp+g+""+alap[fg]);
                     fg++;
                    }
                   
                    continue;          
                  }
             }
         }
       catch(Exception ex)
       {
               System.out.println("chagdvvdann");
       }
    }
}
   

                     
                 
             