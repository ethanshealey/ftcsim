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
  
  while(!((currentAngel > targetAngel - .1) && (currentAngel < targetAngel + .1))) {
      motorLeft.setPower(shouldTurnLeft ? -.5 : .5);
      motorRight.setPower(shouldTurnLeft ? .5 : -.5);
      currentAngel = imu.getAngularOrientation();
      
      sleep(5);
  }
  
  motorLeft.setPower(0);
  motorRight.setPower(0);
}

private int shortestRotation(int startAngle, int targetAngle) {
  int delta = targetAngle - startAngle;
  return ((delta + 180) % 360) - 180;
}
