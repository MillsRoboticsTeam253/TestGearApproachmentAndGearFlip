package org.usfirst.frc253.Code2016.commands;


import org.usfirst.frc253.Code2016.Robot;

//import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.DrawMode;
//import com.ni.vision.NIVision.Image;
//import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Accel extends Command {
	Accelerometer accel;
	double accelerationX;
	double accelerationY;
	double accelerationZ;
	Ultrasonic ultra1;
	Ultrasonic ultra2;
	Ultrasonic ultra3;
    int session;
//    Image frame;
//    AxisCamera camera;
    public Accel() {
    //	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	ultra1 = new Ultrasonic(5,6);
    	ultra2 = new Ultrasonic(0,1);
//    	ultra3 = new Ultrasonic(3,4);
    	ultra1.setAutomaticMode(true);
    	ultra2.setAutomaticMode(true);
    //	ultra3.setAutomaticMode(true);
    	
    	//camera = new AxisCamera("10.1.91.100");
    	requires(Robot.drivetraintank);
    	// turns on automatic mode
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
      	
    	double range1 = ultra1.getRangeInches();
    	double range2 = ultra2.getRangeInches();
    	boolean isAligned = false;
    	SmartDashboard.putBoolean("Is the gear aligned?", isAligned);
    	SmartDashboard.putNumber("Ultrasonic 1", range1);
 		SmartDashboard.putNumber("Ultrasonic 2", range2);
    	
    	if(Math.abs(range1 - range2) < 3.0){
    		isAligned = true;
    		Robot.drivetraintank.setLeft_Back(0);
        	Robot.drivetraintank.setLeft(0);
        	Robot.drivetraintank.setRight(0);
        	Robot.drivetraintank.setRight_Back(0);
    	} else {
    		isAligned = false;
    		if(range1 > range2){
    			Robot.drivetraintank.setLeft_Back(.5);
        	    Robot.drivetraintank.setLeft(.5);
        	    Robot.drivetraintank.setRight(-.5);
        	    Robot.drivetraintank.setRight_Back(-.5);
//    			} else if (myTimer.get() < 2){
//    				Robot.drivetraintank.setLeft_Back(-.5);
//        	    	Robot.drivetraintank.setLeft(-.5);
//        	    	Robot.drivetraintank.setRight(-1);
//        	    	Robot.drivetraintank.setRight_Back(-1);
    			//}
    			//myTimer.stop();
    		} else if(range1 < range2){
    				Robot.drivetraintank.setLeft_Back(-.5);
        	    	Robot.drivetraintank.setLeft(-.5);
        	    	Robot.drivetraintank.setRight(.5);
        	    	Robot.drivetraintank.setRight_Back(.5);
//    			} else if (myTimer.get() < 2){
//    				Robot.drivetraintank.setLeft_Back(-1);
//        	    	Robot.drivetraintank.setLeft(-1);
//        	    	Robot.drivetraintank.setRight(-.5);
//        	    	Robot.drivetraintank.setRight_Back(-.5);
//    			}
//    			myTimer.stop();
    		}
    	}
    }
    
 		
 		  
 		// Special Camera Vision
// 		camera.getImage(frame);  
// 		 NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
// 		NIVision.imaqDrawShapeOnImage(frame, frame, rect,
//                  DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
//
//          CameraServer.getInstance().setImage(frame);
//
//
//          /** robot code here! **/
//          Timer.delay(0.005);	
//
// 		if (Math.abs(range) <= 5)
// 			SmartDashboard.getBoolean("DB/Button 0", true);
// 		else
// 			SmartDashboard.getBoolean("DB/Button 0", false);
//
// 			
// 		//SmartDashboard.putData(ultra.getSmartDashboardType(), ultra);
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetraintank.setLeft_Back(0);
    	Robot.drivetraintank.setLeft(0);
    	Robot.drivetraintank.setRight(0);
    	Robot.drivetraintank.setRight_Back(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
