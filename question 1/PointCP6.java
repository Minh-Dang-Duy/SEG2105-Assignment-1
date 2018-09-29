
public interface PointCP6 {
	
	abstract PointCP3 convertStorageToPolar();
	abstract PointCP2 convertStorageToCartesian();
	abstract PointCP6 rotatePoint(double rotation);
	abstract double getDistance(PointCP6 pointB);
	/**
	 * getter methods
	 *
	 */
	abstract double getX();
	abstract double getY();
	abstract double getRho();
	abstract double getTheta();
	abstract String toString(); 
	
}
