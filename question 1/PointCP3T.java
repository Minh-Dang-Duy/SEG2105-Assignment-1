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
 * @version July 2000
 */
public class PointCP3T
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
   * @param args[0] The value of X 
   * @param args[1] The value of Y
   */
  public static void main(String[] args)
  {
	  PointCP3 point;
	  PointCP3 point2;
    
    System.out.println(" Polar Coordinates Program CSI 2105 ");
    
    System.out.print("The type of Coordinates you "
            + "are inputting :Cartesian \n");

    // Check if the user input coordinates from the command line
    // If he did, create the PointCP2 object from these arguments.
    // If he did not, prompt the user for them.
    try
    {
      point = new PointCP3( Double.valueOf(args[0]).doubleValue(),Double.valueOf(args[1]).doubleValue());
      
    }
    catch(Exception e)
    {
      // If we arrive here, it is because either there were no
      // command line arguments, or they were invalid
      if(args.length != 0)
        System.out.println("Invalid arguments on command line");

      try
      {
        point = getInput();
      }
      catch(IOException ex)
      {
        System.out.println("Error getting input. Ending program.");
        return;
      }
    }
    System.out.println("\nYou entered:\n"+"store as Cartesian \n" + point.toString());
    System.out.println("After asking to store as Polar :\n" + point.convertStorageToPolar());
    System.out.println("Now enter another point to compute distance \n");
    
    try
    {
      point2 = new PointCP3( Double.valueOf(args[0]).doubleValue(),Double.valueOf(args[1]).doubleValue());
      
    }
    catch(Exception e)
    {
      // If we arrive here, it is because either there were no
      // command line arguments, or they were invalid
      if(args.length != 0)
        System.out.println("Invalid arguments on command line");

      try
      {
        point2 = getInput();
      }
      catch(IOException ex)
      {
        System.out.println("Error getting input. Ending program.");
        return;
      }
    }
    
    System.out.println("\nThe Distance between (Cartesian Point) "
    		+ point.convertStorageToCartesian() +"and the (Carestian Point)"
    		+ point2.convertStorageToCartesian() +"is "
    		+ point.getDistance(point2)
    		);
    
    System.out.println("\nNow enter a degree for rotating Carestian point " + point.toString()+ "to compute the rotate point in Carestian");
    	boolean fl = false;
    	byte[] buffer = new byte[1024]; 
    	String t="";
    	double a=0.0;
    	while (! fl) {
    		for(int k=0; k<1024; k++)
            	buffer[k] = '\u0020';        
                 
            try {
				System.in.read(buffer);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            t = new String(buffer).trim();
            try
            {
                  a = Double.valueOf(t).doubleValue();
                  fl=true;
                
            }
    		catch(Exception e)
            {
            	System.out.println("Incorrect input Please enter again ");
            	fl = false;  //Reset flag as so not to end while loop
            }
            
            
    	}
    System.out.println("The rotated point is: " + point.rotatePoint(a));
    
    
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
  private static PointCP3 getInput() throws IOException
  {
    byte[] buffer = new byte[1024];  //Buffer to hold byte input
    boolean isOK = false;  // Flag set if input correct
    String theInput = "";  // Input information
    
    //Information to be passed to the constructor
    double a = 0.0;
    double b = 0.0;

    // Allow the user to enter the three different arguments
    for (int i = 0; i < 2; i++)
    {
      while (!(isOK))
      {
        isOK = true;  //flag set to true assuming input will be valid
          
        // Prompt the user
        
        System.out.print("Enter the value of " 
                + (   (i == 0 ? "Rho " : "Theta "))
                + "using a decimal point(.): ");

        // Get the user's input      
       
        // Initialize the buffer before we read the input
        for(int k=0; k<1024; k++)
        	buffer[k] = '\u0020';        
             
        System.in.read(buffer);
        theInput = new String(buffer).trim();
        
        // Verify the user's input
        try
        {
            //Convert the input to double values
            if (i == 0) {
              a = Double.valueOf(theInput).doubleValue();
            }else {
              b = Double.valueOf(theInput).doubleValue();
            }
            
        }
        catch(Exception e)
        {
        	System.out.println("Incorrect input");
        	isOK = false;  //Reset flag as so not to end while loop
        }
      }

      //Reset flag so while loop will prompt for other arguments
      isOK = false;
    }
    //Return a new PointCP object
    return (new PointCP3(a, b));
  }
}

