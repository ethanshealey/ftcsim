/*
 * Paste this method into any level to easily initialize all modules!
 */
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
