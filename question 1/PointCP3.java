
/**
 * This class contains instances of coordinates in either polar or
 * cartesian format.  It also provides the utilities to convert
 * them into the other type. It is not an optimal design, it is used
 * only to illustrate some design issues.
 *
 */
public class PointCP3 implements PointCP6
{
  //Instance variables ************************************************
	/**
	   * Contains the current value of X;
	*/

  private double xOrRho;
  
  /**
   * Contains the current value of Y;
   */
  private double yOrTheta;
	
  
  //Constructors ******************************************************

  /**
   * Constructs a coordinate object, with a type identifier.
   */
  public PointCP3( double xOrRho, double yOrTheta)
  {
    this.xOrRho = xOrRho;
    this.yOrTheta = yOrTheta;
  }
	
  
  //Instance methods **************************************************
 
 
  public double getX()
  {
    return (xOrRho);
  }
  
  public double getY()
  {
      return (yOrTheta);
  }
  
  public double getRho()
  {
      return (Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2)));
  }
  
  public double getTheta()
  {
      return Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
  }
  
  public PointCP3 convertStorageToPolar() {
	  return new PointCP3(getRho(),getTheta());
  }

  public PointCP2 convertStorageToCartesian() {
	  return new PointCP2(xOrRho,yOrTheta);
  }
  
  public PointCP3 rotatePoint(double rotation)
  {
    double radRotation = Math.toRadians(rotation);
    double X = getX();
    double Y = getY();
        
    return new PointCP3 ((Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y), (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
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
    return("(" + getX() + "," + getY() + ")" );
  }
}

