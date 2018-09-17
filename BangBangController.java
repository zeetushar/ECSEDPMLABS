package ca.mcgill.ecse211.wallfollowing;

import lejos.hardware.motor.*;

public class BangBangController implements UltrasonicController {

  private final int bandCenter;
  private final int bandWidth;
  private final int motorLow;
  private final int motorHigh;
  private int distance;
  private int ClosestDistanceRotate;
 
	
	
  public BangBangController(int bandCenter, int bandwidth, int motorLow, int motorHigh,int ClosestDistanceRotate) {
    // Default Constructor
    this.bandCenter = bandCenter;
    this.bandWidth = bandwidth;
    this.motorLow = motorLow;
    this.motorHigh = motorHigh;
    this.ClosestDistanceRotate=  ClosestDistanceRotate;
    WallFollowingLab.leftMotor.setSpeed(motorHigh); // Start robot moving forward
    WallFollowingLab.rightMotor.setSpeed(motorHigh);
    WallFollowingLab.leftMotor.forward();
    WallFollowingLab.rightMotor.forward();
    
  }


  @Override
  public void processUSData(int distance) {
    this.distance = distance;
    
//
//	if (distance > MAXDISTANCETHRESHOLD && filterControl < FILTER_OUT) {
		// bad value, do not set the distance var, however do increment the filter value
	//	filterControl ++;
	//} else if (distance > MAXDISTANCETHRESHOLD){ //if we've read max after this long
		// true measurement, so set it. 
	
	//} else {
		// went below our threshold, therefore reset everything.
	//	filterControl = 0;
		//this.distance = distance;
	//}
	//if our robot is closer to the wall than the lowest point of the allowed band (bandCenter-bandwidth)
	
		//if we are closer than our closest allowed distance, we will rotate in spot (spin right backwards)
		if(distance < bandCenter - bandWidth){
			if(distance <= ClosestDistanceRotate){
				WallFollowingLab.leftMotor.setSpeed(motorHigh);				//set the speed to the lower speed (both motors)
				WallFollowingLab.rightMotor.setSpeed(motorLow);				
				WallFollowingLab.leftMotor.forward();						// Spin left motor forward
				WallFollowingLab.rightMotor.backward();		
			// spin right motor BACKWARDS. This will rotate in space
			}
			else if (distance < 5){
				WallFollowingLab.leftMotor.setSpeed(motorHigh);
				WallFollowingLab.rightMotor.setSpeed(motorLow);
				WallFollowingLab.leftMotor.backward();
				WallFollowingLab.rightMotor.backward();
				
			}
			else{
			WallFollowingLab.leftMotor.setSpeed(motorHigh);				//set the speed to the lower speed (both motors)
			WallFollowingLab.rightMotor.setSpeed(motorLow);				
			WallFollowingLab.leftMotor.forward();						// Spin left motor forward
			WallFollowingLab.rightMotor.forward();	
			// spin right motor BACKWARDS. This will rotate in space
		}}
		
	//if our robot is farther than the highest point of the allowed band (bandCenter + bandwidth)
		else if (distance > bandCenter + bandWidth){
		WallFollowingLab.leftMotor.setSpeed(motorLow);				// set left low
		WallFollowingLab.rightMotor.setSpeed(motorHigh);				// set right high (this will move robot closer to wall)
		//spin both motors forward
		WallFollowingLab.leftMotor.forward();
		WallFollowingLab.rightMotor.forward();
	//if our robot is inside the band, we just want to move forward. 
	}
   
		 else  {
		//set both motor speeds to high, 
		WallFollowingLab.leftMotor.setSpeed(motorHigh);
		WallFollowingLab.rightMotor.setSpeed(motorHigh);
		//spin them (move robot forward).
		WallFollowingLab.leftMotor.forward();
		WallFollowingLab.rightMotor.forward();
	}}
	
	
    // Declare local variables
  
  
  @Override
  public int readUSDistance() {
    return this.distance;
  }
}
