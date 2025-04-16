public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    ColorSensor color1;
    DistanceSensor distance1;
    BNO055IMU imu;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        waitForStart();
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        
        start();

    }
    
    private void start() {
        
        // First turn
        goUntilDist(50);
        turnLeft();
        
        // Get the duck
        goForTime(1250);
        turnLeft();
        goUntilDist(50);
        turnRight();
        turnRight();
        goUntilDist(40);
        
        // Check if gate is open
        turnLeft();
        goForTime(1200);
        turnLeft();
        
        if(getDist() < 100) {
            // Gate is closed, check color
            String color = getColor();

            if(color == "Red") {
                turnLeft();
                goForTime(1200);
                turnRight();
                goForTime(1200);
                turnRight();
                goForTime(1200);
                turnLeft();
                goUntilDist(40);
            }
            else {
                turnRight();
                goForTime(1200);
                turnLeft();
                goForTime(1200);
                turnLeft();
                goForTime(1200);
                turnRight();
                goUntilDist(40);
            }
        }
        else {
            goUntilDist(40);
        }
    }
    
    private void go() {
        motorLeft.setPower(.5);
        motorRight.setPower(.5);
    }
    
    private void stop() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
    
    private void goUntilDist(int dist) {
        go();
        while(getDist() > dist)
            sleep(20);
        stop();
    }
    
    private void goForTime(int ms) {
        go();
        sleep(ms);
        stop();
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
    
    private int getDist() {
        return distance1.getDistance(DistanceUnit.CM);
    }
    
    private String getColor() {
        if(color1.red() > 200) return "Red";
        else return "Blue";
    }
    
}
