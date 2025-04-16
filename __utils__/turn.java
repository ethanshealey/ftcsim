/*
 * turn.java
 * 
 * Copy and paste these methods as needed to make turning in FTC SIM super simple. This package can turn 
 * your robot left or right within .2 degrees, or by any degree desired!
 *
 */

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
