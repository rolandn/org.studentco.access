package org.studentco.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class Utils {
    
        
    // initialise the secure random instance
    private static final java.security.SecureRandom seeder = new java.security.SecureRandom();
    
    public static final String NULL = "${NULL}";
    
            
    
    
       
    /**
     * @return un id form? de 32 caract?tes al?atoires pris dans l'alphabet et les chiffres
     */
    public static String generateId() {
        return getGuid();
    }
    
    /**
     * @return une cha?ne de 32 caract?tes al?atoires pris dans l'alphabet et les chiffres
     */
    public static String generateString() {
        return getGuid();
    }
    
    /**
     * @param lenght un nombre <=32. Si > 32, il est consid?r? comme =32
     * @return une cha?ne de lenght caract?tes al?atoires pris dans l'alphabet et les chiffres.
     */
    public static String generateString(int lenght) {
    	return limited(getGuid(), lenght); 
    }
    
    /**
     * @return une cha?ne de 32 caract?res al?atoires pris dans l'alphabet et les chiffres
     */
    public static String getGuid() {
        
    	StringBuffer tmpBuffer = new StringBuffer(16);
		String hashcode = hexFormat(System.identityHashCode(new Object()), 8);
		String hashcode2 = hexFormat(System.identityHashCode(new Object()), 8);
		//
		tmpBuffer.append(hashcode);
		tmpBuffer.append(hashcode2);
    //
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & 0xFFFFFFFF;
		int node = seeder.nextInt();
		//
		StringBuffer guid = new StringBuffer(32);
		guid.append(hexFormat(timeLow, 8));
		guid.append(tmpBuffer.toString());
		guid.append(hexFormat(node, 8));
		return guid.toString();
    }
    
    /**
     * V?rifie si une cha?ne est nulle
     * @param s la cha?ne
     * @return true si la cha?ne est nulle ou vide (""). false sinon.
     */
    public static boolean isNullIgnoreEmpty(String s) {
        return (s == null || s.equals("")) ? true : false;
    }
    

    public static final String generateString12() {
    	String id = getGuid();
    	String code = id.substring(0, 6)+id.substring(26,32);
    	return code;
    }
    
       
    private static String hexFormat(int i, int j) {
        String s = Integer.toHexString(i);
        return padHex(s, j) + s;
    }
    
    private static String padHex(String s, int i) {
        StringBuffer tmpBuffer = new StringBuffer();
        if (s.length() < i) {
            for (int j = 0; j < i - s.length(); j++) {
                tmpBuffer.append('0');
            }
        }
        return tmpBuffer.toString();
    }
    
    /**
     * this function return a double passed as first argument at the decimal
     * place passed as second argument
     * @param d
     * @param places
     * @return The rounded Double
     */
    public static final Double roundDouble(double d, int places) {
        return new Double(Math.round(d * Math.pow(10, (double) places)) / Math.pow(10, (double) places));
    }
    
    /**
     * G?n?re al?atoirement true ou false
     * @return
     */
    public static Boolean randomBoolean() {
        int i = new Random().nextInt(2);
        boolean b = (i == 0) ? false : true;
        return b;
    }
    
    /**
     * G?n?re un nombre al?atoire compris entre low et high y compris
     * @param low
     * @param high
     * @return r
     */
    public static int randomInt(int low, int high) {
        int r = (int)(Math.random() * (high+1-low)) + low;
        return r ;
    }
    
    /**
     * @return un bool?en al?atoire
     */
    public static Boolean generateBoolean() {
        int i = new Random().nextInt(2);
        boolean b = (i == 0) ? false : true;
        return b;
    }
    
   
    /**
     * Limite une cha?ne ? un nombre de caract?res donn?s
     * @param s la cha?ne dont on veut limiter la longueur
     * @param i la longueur impos?e
     * @return	si s.lenght() > i, s est tronqu?
     * 			si s.lenght() < i, s est renvoy? tel quel
     * 			si s est vide (=null ou "") "" est renvoy?.
     */
    public static String limited(String s, int i) {
        if (!Utils.isNullIgnoreEmpty(s)) {
            int index = Math.min(i, s.length());
            return s.substring(0, index);
        }
        return "";
    }
    
    
}
