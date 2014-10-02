import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SnowflakeCatcher extends PApplet {

SnowFlake [] snowOne, snowTwo;
int sizeOfBall;

public void setup()
{
  size(900,500);
  background(0);
  snowOne = new SnowFlake [150];
  for(int i=0; i<snowOne.length; i++){
    snowOne[i] = new SnowFlake(i*6, (int)(Math.random()*4)+1);
  }
}
public void draw()
{
  for(int i=0; i<snowOne.length; i++){
     snowOne[i].erase();
     snowOne[i].lookDown();
     snowOne[i].move();
     snowOne[i].wrap();
     snowOne[i].show();
  }
}

public void mouseDragged()
{
  noStroke();
  if (mousePressed == true && mouseButton == LEFT){
      fill(255);
      ellipse(mouseX, mouseY, 15, 15);
  }
  if (mousePressed == true && mouseButton == RIGHT){
      fill(0);
      ellipse(mouseX, mouseY, 25, 25);
  }
}

public void keyPressed(){
  if (key == 'c'){
    background(0);
  }
}

class SnowFlake
{
  int x, y, speed;
  boolean isMoving;
  SnowFlake(int myX, int mySpeed)
  {
    x= myX;
    y= (int)(Math.random()*500);
    isMoving = true;
    speed = mySpeed; 
  }
  public void show()
  {
    colorMode(HSB);
    int h = (int)(Math.random()*255);
    fill(h, 255/2, 255);
    sizeOfBall = (int)(Math.random()*8)+1;
    noStroke();
    ellipse(x, y, sizeOfBall, sizeOfBall);
  }
  public void lookDown()
  {
    if(y>=0 && y<=500){
      if(get(x, y+6) != color(0)){
        isMoving = false;
      }
      else{
        isMoving = true; 
      }
    }
  }
  public void erase()
  {
    fill(0);
    noStroke();
    ellipse(x, y, 9, 9);
  }
  public void move()
  {
    if (isMoving == true){
      y += speed;
    }
  }
  public void wrap()
  {
    if(y>491){
      y=0;
      speed = (int)(Math.random()*4)+1;
    }
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SnowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
