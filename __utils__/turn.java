/*
 * turn.java
 * 
 * Copy and paste these methods as needed to make turning in FTC SIM super simple. This package can turn 
 * your robot left or right within .2 degrees, or by any degree desired!
 *
 */

private void turnLeft() {
    turnByAngel(-90.0);
}

private void turnRight() {
    turnByAngel(90.0);
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
