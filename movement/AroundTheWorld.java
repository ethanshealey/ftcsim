
/***********************************************************************
*                                                                      *
* OnbotJava Editor is still : beta! Please inform us of any bugs       |
* on our discord channel! https://discord.gg/e7nVjMM                   *
* Only BLOCKS code is submitted when in Arena                          *
*                                                                      *
***********************************************************************/


public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    ColorSensor color1;
    DistanceSensor distance1;
    BNO055IMU imu;

@Override
    public void runOpMode() {
      init();
      
      turnByAngle(50);
      goUntilDist(50);
      turnByAngle(-50);
      goForTime(500);
      turnLeft();
      goUntilDist(50);
      turnByAngle(180);
      goUntilDist(35);
      turnByAngle(-50);
      goUntilDist(35);
      turnByAngle(-75);
      goForTime(1550);
    }
    
    public void init() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        var myBno055imuParameters = new BNO055IMU.Parameters();
        imu.initialize(myBno055imuParameters);
        waitForStart();
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    
    private void go() {
      motorLeft.setPower(.5);
      motorRight.setPower(.5);
    }
    
    private void stop() {
      motorLeft.setPower(0);
      motorRight.setPower(0);
    }
    
    private void goForTime(int ms) {
      go();
      sleep(ms);
      stop();
    }
    
    private void goUntilDist(int dist) {
        go();
        while(getDist() > dist)
            sleep(20);
        stop();
    }
    
    private int getDist() {
        return distance1.getDistance(DistanceUnit.CM);
    }
    
    private void turnLeft() {
        turnByAngle(-90.0);
    }
    
    private void turnRight() {
        turnByAngle(90.0);
    }
    
    private void turnByAngle(double angle) {
        double currentAngle = imu.getAngularOrientation();
        double targetAngle = currentAngle + angle;
        
        if(targetAngle < 0.0)
        targetAngle = 360.0 - Math.abs(currentAngle + angle);
        else if(targetAngle > 360.0)
        targetAngle = targetAngle - 360.0;
        
        boolean shouldTurnLeft = shortestRotation(currentAngle, targetAngle) < 0;
        
        motorLeft.setPower(shouldTurnLeft ? -.5 : .5);
        motorRight.setPower(shouldTurnLeft ? .5 : -.5);
        
        while(!((currentAngle > targetAngle - .1) && (currentAngle < targetAngle + .1))) {
            currentAngle = imu.getAngularOrientation();
            sleep(2);
        }
        
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
    
    private double shortestRotation(double startAngle, double targetAngle) {
        double delta = (targetAngle - startAngle) % 360;
        
        // Shift range to [-180, 180]
        if (delta > 180) {
            delta -= 360;
        } 
        else if (delta < -180) {
            delta += 360;
        }
        
        return delta;
    }
    
}
