// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.Scanner;

/**
 * This class prompts the user for a set of coordinates, and then 
 * converts them from polar to cartesian or vice-versa.
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Paul Holden
 * @version July 2000, edited Sept 2018
 */
public class PointCP6T
{
  //Class methods *****************************************************

  /**
   * This method is responsible for the creation of the PointCP
   * object.  This can be done in two ways; the first, by using the
   * command line and running the program using <code> java 
   * PointCPTest &lt;coordtype (c/p)&gt; &lt;X/RHO&gt; &lt;Y/THETA&gt;
   * </code> and the second by getting the program to prompt the user.
   * If the user does not enter a valid sequence at the command line,
   * the program will prompte him or her.
   *
   * 
   */
  public static void main(String[] args)
  {
    PointCP6 point;
    PointCP6 point2;
    double angle = 0.0;
    boolean flag = false;
    String c = "";
    System.out.println("Cartesian-Polar Coordinates Conversion Program");

    // Check if the user input coordinates from the command line
    // If he did, create the PointCP object from these arguments.
    // If he did not, prompt the user for them.
    
    while(!(flag)) {
    	flag = true;
    	System.out.print("Enter (C)artesian or (P)olar: ");
    	Scanner input2 = new Scanner(System.in);
    	c = input2.nextLine();   
    	
    	if (!(c.equals("c") || c.equals("C")) && !(c.equals("p") || c.equals("P")))
    	{
    		System.out.println("Please enter either C for Cartesian or P for Polar.");
    		flag = false;
    	}
    	
    }
    
    if (c.equals("c") || c.equals("C")) {
    	 try
    	    {
    	      point = new PointCP3(Double.valueOf(args[0]).doubleValue(), Double.valueOf(args[1]).doubleValue());
    	      point2 = new PointCP3(0,0);
    	    }
    	    catch(Exception e)
    	    {
    	      // If we arrive here, it is because either there were no
    	      // command line arguments, or they were invalid
    	      if(args.length != 0)
    	        System.out.println("Invalid arguments on command line");

    	      try
    	      {
    	        point = getInputCartesian();
    	        point2 = getPoint();
    	        angle = getRotation();
    	        ;
    	      }
    	      catch(IOException ex) 
    	      {
    	        System.out.println("Error getting input. Ending program.");
    	        return;
    	      }
    	    }
    	    //Asking for the first coordinate
    	    System.out.println("\nYou entered:\n" + point);
    	    System.out.print("Converted to Polar:\n" + point.convertStorageToPolar());
    	    
    	    //Asking for the second coordinate to calculate distance
    	    System.out.println("\nGetting Distance between two points: \n");
    	    System.out.println("The distance between the two given coordinate is " + point.getDistance(point2));
    	    
    	    //Rotating the first point based on the user input angle in degree
    	    System.out.println("\nAfter rotating coordiantes by " + angle +" degrees:\n" + point.rotatePoint(angle));
    	    
    	
    }
    
    else if (c.equals("p") || c.equals("P")) {
    	
    	try
        {
          point = new PointCP2(Double.valueOf(args[0]).doubleValue(), Double.valueOf(args[1]).doubleValue());
          point2 = new PointCP3(0,0);
        }
        catch(Exception e)
        {
          // If we arrive here, it is because either there were no
          // command line arguments, or they were invalid
          if(args.length != 0)
            System.out.println("Invalid arguments on command line");

          try
          {
            point = getInputPolar();
            point2 = getPoint();
            angle = getRotation();
            ;
          }
          catch(IOException ex) 
          {
            System.out.println("Error getting input. Ending program.");
            return;
          }
        }
        //Asking for the first coordinate
        System.out.println("\nYou entered:\n" + point.toString());
        System.out.print("Converted to Cartesian:\n" + point.convertStorageToCartesian());
        
        //Asking for the second coordinate to calculate distance
        System.out.println("\nGetting Distance between two points: \n");
        System.out.println("The distance between the two given coordinate is " + point.getDistance(point2));
        
        //Rotating the first point based on the user input angle in degree
        point = point.rotatePoint(angle);
        System.out.println("\nAfter rotating coordiantes by " + angle +" degrees:\n" + point);
        
    	
    }
    
    
    
    
    
    
  }

  /**
   * This method obtains input from the user and verifies that
   * it is valid.  When the input is valid, it returns a PointCP
   * object.
   *
   * @return A PointCP constructed using information obtained 
   *         from the user.
   * @throws IOException If there is an error getting input from
   *         the user.
   */
  private static PointCP6 getInputCartesian() throws IOException
  {
    byte[] buffer = new byte[1024];  //Buffer to hold byte input
    boolean isOK = false;  // Flag set if input correct
    String theInput = "";  // Input information
    
    //Information to be passed to the constructor
    double a = 0.0;
    double b = 0.0;

    // Allow the user to enter the two different arguments
    for (int i = 0; i < 2; i++)
    {
      while (!(isOK))
      {
        isOK = true;  //flag set to true assuming input will be valid
          
        // Prompt the user
        if (i == 0) // First argument
        {
          System.out.print("Enter the value of first coordinate: ");
        }
        if (i == 1) // Second argument 
        {
          System.out.print("Enter the value of second coordinate: ");
        }


        // Get the user's input      
       
        // Initialize the buffer before we read the input
        for(int k=0; k<1024; k++)
        	buffer[k] = '\u0020';        
             
        System.in.read(buffer);
        theInput = new String(buffer).trim();
        
        // Verify the user's input
          if (i == 0) // First argument -- type of coordinates
          {
        	  try {
        	
        		  Float.parseFloat(theInput);
        		  a = Double.valueOf(theInput).doubleValue();
            
        	  }
        	  
        	  catch (NumberFormatException e ) {
              //Invalid input, reset flag so user is prompted again
        		  System.out.println("Invalid input, please try again.");
        		  isOK = false;
            }
          }
          else if (i == 1)  // Second argument
          {
            try {
            	
            	Float.parseFloat(theInput);
            	b = Double.valueOf(theInput).doubleValue();
            }
            
            catch (NumberFormatException e) {
            	System.out.println("Invalid input, please try again.");
            	isOK = false;
            }
          }
        
      }

      //Reset flag so while loop will prompt for other arguments
      isOK = false;
    }
    //Return a new PointCP object 
    return (new PointCP3(a, b));
  }
  
  private static PointCP6 getInputPolar() throws IOException
  {
    byte[] buffer = new byte[1024];  //Buffer to hold byte input
    boolean isOK = false;  // Flag set if input correct
    String theInput = "";  // Input information
    
    //Information to be passed to the constructor
    double a = 0.0;
    double b = 0.0;

    // Allow the user to enter the two different arguments
    for (int i = 0; i < 2; i++)
    {
      while (!(isOK))
      {
        isOK = true;  //flag set to true assuming input will be valid
          
        // Prompt the user
        if (i == 0) // First argument
        {
          System.out.print("Enter the value of first coordinate: ");
        }
        if (i == 1) // Second argument 
        {
          System.out.print("Enter the value of second coordinate: ");
        }


        // Get the user's input      
       
        // Initialize the buffer before we read the input
        for(int k=0; k<1024; k++)
        	buffer[k] = '\u0020';        
             
        System.in.read(buffer);
        theInput = new String(buffer).trim();
        
        // Verify the user's input
          if (i == 0) // First argument -- type of coordinates
          {
        	  try {
        	
        		  Float.parseFloat(theInput);
        		  a = Double.valueOf(theInput).doubleValue();
            
        	  }
        	  
        	  catch (NumberFormatException e ) {
              //Invalid input, reset flag so user is prompted again
        		  System.out.println("Invalid input, please try again.");
        		  isOK = false;
            }
          }
          else if (i == 1)  // Second argument
          {
            try {
            	
            	Float.parseFloat(theInput);
            	b = Double.valueOf(theInput).doubleValue();
            }
            
            catch (NumberFormatException e) {
            	System.out.println("Invalid input, please try again.");
            	isOK = false;
            }
          }
        
      	}

      //Reset flag so while loop will prompt for other arguments
      isOK = false;
      
    	}
    return new PointCP2(a,b);
    }
  
  /*
   * This method contains the codes to get input from the user for the rotation of coordinates. When the input is valid it returns a double of the 
   * angle to rotate in degrees
   * 
   * @return double converted from String 
   * @throws IOException the catch invalid input
   */
  private static double getRotation() throws IOException {
	  
	  byte[] buffer = new byte[512]; //initiating buffer to hold argument
	  boolean flag = false;
	  double rotation = 0.0;
	  String temp = "";
	  while (!(flag)) {
		  flag = true;
		  System.out.println("Enter the degrees in which the coordinate will be rotated about the origin: ");
		  
		  for (int j = 0; j<512; j++) {
			  buffer[j] = '\u0020';
		  }
			  
			  System.in.read(buffer);
			  temp = new String(buffer).trim();
			  
			  //verifying user input
			  try {
				  
				  Float.parseFloat(temp); //catching to see if user input is a float
				  rotation = Double.valueOf(temp).doubleValue();
			  }
			  
			  catch (NumberFormatException e) {
				  
				  System.out.println("Invalid Input.");
				  flag = false;
			  }	  
		  
	  }
	  
	  return rotation;
	  
  }
   
  
 /*
  * This method prompts the user for the second point to test the getDistane() method in PointCPD2. It will return a 
  * valid PointCPD2 as a new coordinate
  * 
  * @return A new constructed PointCPD2 stored into the instance var point1 of the main method
  * @throws IOException to catch any invalid inputs
  */
 private static PointCP3 getPoint() throws IOException {
	  
	  byte[] buffer = new byte[1024]; //initiating buffer to hold argument
	  boolean isOK = false;  // Flag set if input correct
	  String pointInput = "";  // Input information
	    
	    //Information to be passed to the constructor
	  double x = 0.0;
	  double y = 0.0;

	    // Allow the user to enter the two different arguments
	  for (int i = 0; i < 2; i++)
	  {
	    while (!(isOK))
	    {
	       isOK = true;  //flag set to true assuming input will be valid
	          
	        // Prompt the user
	       if (i == 0) // First argument
	       {
	          System.out.print("Enter the value of x coordinate in Cartesian for the second point: ");
	       }
	       if (i == 1) // Second argument 
	       {
	         System.out.print("Enter the value of y coordinate in Cartesian for the second point: ");
	       }


	        // Get the user's input      
	       
	        // Initialize the buffer before we read the input
	      for(int k=0; k<1024; k++)
	    	  buffer[k] = '\u0020';        
	             
	      System.in.read(buffer);
	      pointInput = new String(buffer).trim();
	        
	        // Verify the user's input
	       if (i == 0) // First argument
	        {
	        	try {
	        
	     		 Float.parseFloat(pointInput);
	     		 x = Double.valueOf(pointInput).doubleValue();
	            
	        	  }
	        	  
	        	catch (NumberFormatException e ) {
	              //Invalid input, reset flag so user is prompted again
	        		System.out.println("Invalid input, please try again.");
	        		isOK = false;
	            }
	          }
	          else if (i == 1)  // Second argument
	          {
	            try {
	            	
	            	Float.parseFloat(pointInput);
	            	y = Double.valueOf(pointInput).doubleValue();
	            }
	            
	            catch (NumberFormatException e) {
	            	System.out.println("Invalid input, please try again.");
	            	isOK = false;
	            }
	          }
	        
	      }

	      //Reset flag so while loop will prompt for other arguments
	      isOK = false;
	    }
	    //Return a new PointCP object 
	    return (new PointCP3(x, y));
	  
  }
}
  
