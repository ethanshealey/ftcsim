public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    BNO055IMU imu;

    @Override
    public void runOpMode() {
      motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
      motorRight = hardwareMap.get(DcMotor.class, "motorRight");
      imu = hardwareMap.get(BNO055IMU.class, "imu");
      motorLeft.setDirection(DcMotor.Direction.REVERSE);
      
      motorLeft.setPower(.5);
      motorRight.setPower(.5);
      
      sleep(2500);
      
      motorLeft.setPower(0);
      motorRight.setPower(0);
    }
    
}
