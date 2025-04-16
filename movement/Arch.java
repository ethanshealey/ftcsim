
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
        
        turnByAngel(45);
        sleep(1000);
        goForTime(1000);
        turnByAngel(-45);
        goForTime(700);
        turnByAngel(-45);
        goForTime(700);
    }
    
    public void init() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        //Create a new Parameters object
        var myBno055imuParameters = new BNO055IMU.Parameters();
        //Pass the parameters object in the initialize function so the IMU is now linked to this object
        imu.initialize(myBno055imuParameters);
        // Put initialization blocks here
        waitForStart();
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    
    private void turnByAngel(double angel) {
        double currentAngel = imu.getAngularOrientation();
        double targetAngel = currentAngel + angel;
        
        if(targetAngel < 0.0)
            targetAngel = 360.0 - Math.abs(currentAngel + angel);
        else if(targetAngel > 360.0)
            targetAngel = targetAngel - 360.0;
            
        boolean shouldTurnLeft = shortestRotation(currentAngel, targetAngel) < 0;
        
        motorLeft.setPower(shouldTurnLeft ? -.5 : .5);
        motorRight.setPower(shouldTurnLeft ? .5 : -.5);
        
        while(!((currentAngel > targetAngel - .1) && (currentAngel < targetAngel + .1))) {
            currentAngel = imu.getAngularOrientation();
            sleep(2);
        }
        
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
    
    private double shortestRotation(double startAngle, double targetAngle) {
        double delta = targetAngle - startAngle;

        double step1 = delta + 180.0;
        int step2 = step1 % 360;
        return step2 - 180;
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
}


