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
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        // Put initialization blocks here
        waitForStart();
        // Put run blocks here
        
        // Start
        goUntilDist(50);
        turnLeft();
        
        // go until you cant and turn left, then turn again
        // This will either lead you into the middle or bottom right
        goUntilDist(40);
        turnLeft();
        goUntilDist(40);
        turnLeft();
        
        // If facing wall, this mean you are in the middle
        // area and need to turn right 180deg
        if(getDist() < 50) {
            // need to go right!!
            turnRight();
            turnRight();
            goUntilDist(40);
            turnLeft();
            goUntilDist(40);
            turnLeft();
            goUntilDist(40);
            
        }
        // Else can just go straight
        else
            goUntilDist(40);

        // If still going, need to backtrack a bit (worst case)
        backUp();
        turnLeft();
        goUntilDist(125);
        turnRight();
        goUntilDist(40);
        turnRight();
        goUntilDist(40);
        turnLeft();
        goUntilDist(40);
    }
    
    private void go() {
        motorLeft.setPower(.5);
        motorRight.setPower(.5);
    }
    
    private void stop() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
    
    private void backUp() {
        motorLeft.setPower(-.5);
        motorRight.setPower(-.5);
        sleep(100);
        stop();
    }
    
    private void goUntilDist(int dist) {
        go();
        while(getDist() > dist) {
            sleep(5);
        }
        stop();
    }
    
    private int getDist() {
        return distance1.getDistance(DistanceUnit.CM);
    }
    
    private void turnLeft() {
        motorLeft.setPower(-.5);
        motorRight.setPower(.5);
        sleep(950);
        stop();
    }
    
    private void turnRight() {
        motorLeft.setPower(.5);
        motorRight.setPower(-.5);
        sleep(950);
        stop();
    }
    
}
