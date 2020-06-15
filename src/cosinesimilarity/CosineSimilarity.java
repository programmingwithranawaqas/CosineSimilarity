/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosinesimilarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Hashtable;
import java.util.LinkedList;
/**
 *
 * @author LENOVO
 */
public class CosineSimilarity {

     public class values
     {
          int val1;
          int val2;
          values(int v1, int v2)
          {
           this.val1=v1;
           this.val2=v2;
          }
          public void Update_VAl(int v1, int v2)
          {
           this.val1=v1;
           this.val2=v2;
          }
     }//end of class values
//     public double Cosine_Similarity_Score(String Text1, String Text2)
//     {
//
//          double sim_score=0.0000000;
//          //1. Identify distinct words from both documents
//          String [] word_seq_text1 = Text1.split(" ");
//          String [] word_seq_text2 = Text2.split(" ");
//          Hashtable<String, values> word_freq_vector = new Hashtable<String, CosineSimilarity.values>();
//          LinkedList<String> Distinct_words_text_1_2 = new LinkedList<String>();
//           
//          //prepare word frequency vector by using Text1
//          for(int i=0;i<word_seq_text1.length;i++)
//          {
//           String tmp_wd = word_seq_text1[i].trim();
//           if(tmp_wd.length()>0)
//           {
//            if(word_freq_vector.containsKey(tmp_wd))
//            {
//             values vals1 = word_freq_vector.get(tmp_wd);
//             int freq1 = vals1.val1+1;
//             int freq2 = vals1.val2;
//             vals1.Update_VAl(freq1, freq2);
//             word_freq_vector.put(tmp_wd, vals1);
//            }
//            else
//            {
//             values vals1 = new values(1, 0);
//             word_freq_vector.put(tmp_wd, vals1);
//             Distinct_words_text_1_2.add(tmp_wd);
//            }
//           }
//          }
//           
//          //prepare word frequency vector by using Text2
//          for(int i=0;i<word_seq_text2.length;i++)
//          {
//           String tmp_wd = word_seq_text2[i].trim();
//           if(tmp_wd.length()>0)
//           {
//            if(word_freq_vector.containsKey(tmp_wd))
//            {
//             values vals1 = word_freq_vector.get(tmp_wd);
//             int freq1 = vals1.val1;
//             int freq2 = vals1.val2+1;
//             vals1.Update_VAl(freq1, freq2);
//             word_freq_vector.put(tmp_wd, vals1);
//            }
//            else
//            {
//             values vals1 = new values(0, 1);
//             word_freq_vector.put(tmp_wd, vals1);
//             Distinct_words_text_1_2.add(tmp_wd);
//            }
//           }
//          }
//           
//          //calculate the cosine similarity score.
//          double VectAB = 0.0000000;
//          double VectA_Sq = 0.0000000;
//          double VectB_Sq = 0.0000000;
//           
//          for(int i=0;i<Distinct_words_text_1_2.size();i++)
//          {
//           values vals12 = word_freq_vector.get(Distinct_words_text_1_2.get(i));
//           
//           double freq1 = (double)vals12.val1;
//           double freq2 = (double)vals12.val2;
//           //System.out.println(Distinct_words_text_1_2.get(i)+"#"+freq1+"#"+freq2);
//            
//           VectAB=VectAB+(freq1*freq2);
//            
//           VectA_Sq = VectA_Sq + freq1*freq1;
//           VectB_Sq = VectB_Sq + freq2*freq2;
//          }
//          //System.out.println("VectAB "+VectAB+" VectA_Sq "+VectA_Sq+" VectB_Sq "+VectB_Sq);
//          sim_score = ((VectAB)/(Math.sqrt(VectA_Sq)*Math.sqrt(VectB_Sq)));
//           
//          return(sim_score);
//
//     }
//    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        // TODO code application logic here
       // System.out.println(calculateJaccardSimilarity("rana waqas", "rana waqar"));
       readNews();
    }
    public static void readNews() throws IOException, ClassNotFoundException, SQLException
    {
        String path = "F:\\research\\compute results";
        File folder = new File(path);
         File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
              File file = listOfFiles[i];
                    if (file.isFile() && file.getName().endsWith(".txt")) {
                        FileReader fr = new FileReader(path+"\\"+file.getName());
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        String completenews="";
                        while((line=br.readLine())!=null)
                        {
                            completenews+=line;
                        }
                        System.out.println();
                        sendText(completenews);
                      /* do somthing with content */
                      }
            }
    }
    public static void sendText(String completenews) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/news","root", "");
        String text="",source="",type="",location="",date="";
        int i=0;
        while(completenews.charAt(i)!='$')
        {
            location+=completenews.charAt(i);
            i++;
        }
        i++;
        while(completenews.charAt(i)!='$')
        {
            date+=completenews.charAt(i);
            i++;
        }
        i++;
        while(completenews.charAt(i)!='$')
        {
            type+=completenews.charAt(i);
            i++;
        }
        i++;
        while(completenews.charAt(i)!='$')
        {
            source+=completenews.charAt(i);
            i++;
        }
        i++;
        while(completenews.charAt(i)!='$')
        {
            text+=completenews.charAt(i);
            i++;
        }
        i++;
        System.out.println("Location : "+location);
        System.out.println("Date     : "+date);
        System.out.println("Type     : "+type);
        System.out.println("Source   : "+source);
        System.out.println("Text     : "+text);
        System.out.println("");
        
        int id;
        id = returnSimilarId(location,type,text);
        if(id==-1)
        {
                    PreparedStatement ps1 = conn.prepareStatement("INSERT INTO `fulltext` " + "VALUES (?,?,?,?,?,?,?)");
                    ps1.setString(1, "");
                    ps1.setString(2, source);
                    ps1.setString(3, location);
                    ps1.setString(4, type);
                    ps1.setString(5, text);
                    ps1.setString(6, "");
                    ps1.setString(7, "D");
                    ps1.executeUpdate();
        }
        else
        {
                    PreparedStatement ps1 = conn.prepareStatement("INSERT INTO `fulltext` " + "VALUES (?,?,?,?,?,?,?)");
                    ps1.setString(1, "");
                    ps1.setString(2, source);
                    ps1.setString(3, location);
                    ps1.setString(4, type);
                    ps1.setString(5, text);
                    ps1.setString(6, id+"");
                    ps1.setString(7, "F");
                    ps1.executeUpdate();
        }
    }
     private static Double calculateJaccardSimilarity(CharSequence left, CharSequence right) {
        Set<String> intersectionSet = new HashSet<String>();
        Set<String> unionSet = new HashSet<String>();
        boolean unionFilled = false;
        int leftLength = left.length();
        int rightLength = right.length();
        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }

        for (int leftIndex = 0; leftIndex < leftLength; leftIndex++) {
            unionSet.add(String.valueOf(left.charAt(leftIndex)));
            for (int rightIndex = 0; rightIndex < rightLength; rightIndex++) {
                if (!unionFilled) {
                    unionSet.add(String.valueOf(right.charAt(rightIndex)));
                }
                if (left.charAt(leftIndex) == right.charAt(rightIndex)) {
                    intersectionSet.add(String.valueOf(left.charAt(leftIndex)));
                }
            }
            unionFilled = true;
        }
        return Double.valueOf(intersectionSet.size()) / Double.valueOf(unionSet.size());
    }

    private static int returnSimilarId(String location, String type, String text) throws ClassNotFoundException, SQLException {
        int id=-1;
        
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/news","root", "");
            System.out.print("Database is connected !");
            
            PreparedStatement ps = conn.prepareStatement("select * from `fulltext` where location=? and type=? and flag=?");
            
            ps.setString(1, location);
            ps.setString(2, type);
            ps.setString(3, "D");
            
            ResultSet rs = ps.executeQuery();
            boolean flag=false;
            List<String> listofobjects = new ArrayList<String>();
            List<Double> similarities = new ArrayList<Double>();
            
            while(rs.next())
            {
                flag = true;
                String dbid,dbtext;
                dbid = rs.getString("id");
                dbtext = rs.getString("text");
                CosineSimilarity cs1 = new CosineSimilarity();
               Double sim = cs1.calculateJaccardSimilarity(text, text);
                       //cs1.Cosine_Similarity_Score(text, dbtext);
                System.out.println("Text :"+text);
                System.out.println("dbText :"+dbtext);
                System.out.println("Similarity :"+sim);
               if(sim>0.55)
               {
                   listofobjects.add(dbid);
                   similarities.add(sim);
               }
            }
            
            // some objects fetched from DB
            if(flag==true)
            {
                Double greaterSimilarity=0.0;
                int greaterIndex = -1;
                for(int i=0; i<similarities.size();i++)
                {
                    if(greaterSimilarity<similarities.get(i))
                    {
                        greaterSimilarity = similarities.get(i);
                        greaterIndex = i;
                    }
                }
                //System.out.println("Similarity : "+greaterSimilarity);
                if(greaterIndex!=-1)
                   id = Integer.parseInt(listofobjects.get(greaterIndex));
            }
            rs.close();
            ps.close();
            conn.close();
        return id;
    }
}