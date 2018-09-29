/**
 * This class contains instances of coordinates in either polar or
 * cartesian format.  It also provides the utilities to convert
 * them into the other type. It is not an optimal design, it is used
 * only to illustrate some design issues.
 *
 */
public class PointCP2 implements PointCP6
{
  //Instance variables ************************************************
	/**
	   * Contains the current value of Rho;
	*/

  private double xOrRho;
  
  /**
   * Contains the current value of Theta;
   */
  private double yOrTheta;
	
  
  //Constructors ******************************************************

  /**
   * Constructs a coordinate object, with a type identifier.
   */
  public PointCP2( double xOrRho, double yOrTheta)
  {
    this.xOrRho = xOrRho;
    this.yOrTheta = yOrTheta;
  }
	
  
  //Instance methods **************************************************
 
 
  public double getRho()
  {
    return (xOrRho);
  }
  
  public double getTheta()
  {
      return (yOrTheta);
  }
  
  public double getX()
  {
	  return (Math.cos(Math.toRadians(yOrTheta)) * xOrRho);
  }
  
  public double getY()
  {
	  return (Math.sin(Math.toRadians(yOrTheta)) * xOrRho);
  }
  
  public PointCP3 convertStorageToPolar() {
	  return new PointCP3(xOrRho,yOrTheta);
  }

  public PointCP2 convertStorageToCartesian() {
	  return new PointCP2(getX(),getY());
  }
  
  public PointCP2 rotatePoint(double rotation)
  {
    double radRotation = Math.toRadians(rotation);
        
    return new PointCP2 (xOrRho, yOrTheta+radRotation);
  }
  
  public double getDistance(PointCP6 pointB)
  {
    // Obtain differences in X and Y, sign is not important as these values
    // will be squared later.
    double deltaX = getX() - pointB.getX();
    double deltaY = getY() - pointB.getY();
    
    return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }
   
  public String toString()
  {
    return("[" + getRho() + "," + getTheta() + "]" + "\n");
  }
}

