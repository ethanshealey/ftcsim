/*
 * Paste this method into any level to easily initialize all modules!
 */
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
