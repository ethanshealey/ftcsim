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
      imu = hardwareMap.get(BNO055IMU.class, "imu");
      motorLeft.setDirection(DcMotor.Direction.REVERSE);
      
      motorLeft.setPower(.5);
      motorRight.setPower(.1);
      
      sleep(5000);
      
      motorLeft.setPower(0);
      motorRight.setPower(0);
    }
    
}
