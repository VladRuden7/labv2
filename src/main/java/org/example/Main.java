package org.example;

import java.awt.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {
  public static String readFromFile(String path)
  {
    try (FileReader reader = new FileReader(path)){
      String s = "";
      int c;
      while((c=reader.read())!=-1){
        s+=(char)c;
      }
      return s;

    }
    catch(IOException ex){

      System.out.println("Error"+ex.getMessage());
    }
    return null;
  }
  public static String[] splitWords(String text) {
    if(text == null || text.length() == 0 ) return null;
    Pattern p = Pattern.compile("\\W");
    Matcher k = p.matcher(text);
    String s = k.replaceAll(" ");
    s = s.trim().replaceAll("\\s{2,}", " ");
    String l[] = s.split(" ");
    if(l.length == 1 && l[0].equals("")) return null;
    for (int i = 0; i<l.length;i++) {
      if(l[i].length() > 30) l[i] = l[i].substring(0,29);
    }
    return l;
  }
  public static ArrayList<String> result(String[] s)
  {
    ArrayList<String> result = new ArrayList<>();
    String sPattern = "^(?:([A-Za-z])(?!.*\\1))*$";
    for (String s1: s) {
      if(Pattern.matches(sPattern,s1))
      {
        if(result.contains(s1))
          continue;
        result.add(s1);
      }

    }
    return result;
  }

  public static void main(String[] args) {

    Frame a = new Frame();
    a.setSize(500, 500);
    FileDialog b = new FileDialog(a, "Choose file!");
    b.setVisible(true);
    String s = b.getDirectory()+ b.getFile();
    if (s == null || s.length() == 0)
    {
      System.out.println("Bye!");
      System.exit(0);
    }
    else {
      String s1 = readFromFile(s);
      ArrayList<String> result = result(splitWords(s1));
      System.out.print("Acceptable words: ");
      for (String res : result) {
        System.out.print(res + " ");
      }
      System.exit(1);

    }
  }

}