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
