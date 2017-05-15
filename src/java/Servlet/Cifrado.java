package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;

public class Cifrado {
    
    private String word;
    
    public Cifrado(String word)
        {
        this.word=word;
        }
    public String getWord() {
        String pass="";
        for(int i=0;i<word.length();i++)
            pass+=cifrar(word.charAt(i));
        pass+=solt();    
        return pass;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    public static void main(String[] args) {
        String str="micontrasena";
        Cifrado c=new Cifrado(str);
        System.out.println(c.getWord());    
    }
    
    private  String solt()
        {
        String solt=""; 
        char[] arr=word.toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) 
            if(primo(i))
                solt+=cifrar(arr[i]);
        return solt;
        }
    
    private  boolean primo(int n)
        {
        boolean es=false;
        int c=0;
        for(int i=2;i<=Math.sqrt(n);i++)
            if(n%i==0)
                {
                c++;
                break;
                }
        if(c==0)
            es=true;
        return es;
        }
    
    private char cifrar( char c)
        {
        int x=-1*((int)c-93)*((int)c-93)/39+126;    
        return (char)x;
        }
}
