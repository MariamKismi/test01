import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class histogramme extends PApplet {

/* @pjs preload="http://isn.codelab.info/site/assets/files/1523/mongolfiere.jpg"; */

PImage img;
int w = 40;
boolean choix=false;

float[] histoR = new float[256];
float[] histoG = new float[256];
float[] histoB = new float[256];

public void setup() 
{
  img = loadImage("http://isn.codelab.info/site/assets/files/1523/mongolfiere.jpg");
  size(600, 600);

}

public void draw() {


  int xstart = PApplet.parseInt(constrain(mouseX - w/2, 0, width));
  int ystart = PApplet.parseInt(constrain(mouseY -w/2, 0, height-200));
  int xend = PApplet.parseInt(constrain(mouseX + w/2, 0, width));
  int yend = PApplet.parseInt(constrain(mouseY + w/2, 0, height-200));

  if (choix)
  {
    fill(0);
    stroke(0);
    rect(0, 0, width, height-200);
    for (int x = 0; x < 256; x++)
    {
      stroke(x, 0, 0);
      line(60, 50+x, 110, 50+x);
      stroke(0, x, 0);
      line(130, 50+x, 180, 50+x);
      stroke(0, 0, x);
      line(200, 50+x, 250, 50+x);

      stroke(x, x, 0);
      line(270, 50+x, 320, 50+x);
      stroke(0, x, x);
      line(340, 50+x, 390, 50+x);
      stroke(x, 0, x);
      line(410, 50+x, 460, 50+x);

      stroke(x, x, x);
      line(480, 50+x, 530, 50+x);
    }

    stroke(255, 0, 0);
    fill(255, 0, 0);
    rect(60, 305, 50, 50);
    stroke(0, 255, 0);
    fill(0, 255, 0);
    rect(130, 305, 50, 50);
    stroke(0, 0, 255);
    fill(0, 0, 255);
    rect(200, 305, 50, 50);

    stroke(255, 255, 0);
    fill(255, 255, 0);
    rect(270, 305, 50, 50);
    stroke(0, 255, 255);
    fill(0, 255, 255);
    rect(340, 305, 50, 50);
    stroke(255, 0, 255);
    fill(255, 0, 255);
    rect(410, 305, 50, 50);

    stroke(255, 255, 255);
    fill(255, 255, 255);
    rect(480, 305, 50, 50);
  }
  else
  {
   image(img, 0, 0); 
  }


    for (int i=0; i<256 ; i++) {histoR[i] = 0 ; histoG[i] = 0; histoB[i] = 0;}
  for (int x = xstart; x < xend; x++) {
    for (int y = ystart; y < yend; y++ ) {
      histoR[constrain(PApplet.parseInt(red(get(x, y))), 0, 255)]++;
      histoG[constrain(PApplet.parseInt(green(get(x, y))), 0, 255)]++;
      histoB[constrain(PApplet.parseInt(blue(get(x, y))), 0, 255)]++;
    }
  }
  noFill();
  stroke(255, 255,255);
  rect(xstart, ystart, xend-xstart, yend-ystart);
  stroke(0);
  fill(255);
  rect(0, height-200, width, 200);
  rect(0, height-200, width, 200);
  float max=1;
  int maxi=0;

  for (int i = 1; i < 256; i++)
  {
    if (histoR[i]>max) {max=histoR[i];maxi=i;}
    if (histoG[i]>max) {max=histoG[i];maxi=i;}
    if (histoB[i]>max) {max=histoB[i];maxi=i;}
  }
  println(max+" "+maxi+" "+xstart+" "+ystart+" "+(xend-xstart)+" "+(yend-ystart));
  for (int i = 1; i < 256; i++)
  {
    histoR[i]=histoR[i]*50/max;
    histoG[i]=histoG[i]*50/max;
    histoB[i]=histoB[i]*50/max;
  }

  int i =40;
  for (int x = 1; x < 256; x++)
  {
    fill(255, 0, 0);
    stroke(255, 0, 0);
    rect(i, height-20-histoR[x], 2, histoR[x]);
    fill(0, 255, 0);
    stroke(0, 255, 0);
    rect(i, height-80-histoG[x], 2, histoG[x]);
    fill(0, 0, 255);
    stroke(0, 0, 255);
    rect(i, height-140-histoB[x], 2, histoB[x]);
    i++;
    i++;
  }
}

public void mouseClicked() {
 choix=!choix; 
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "histogramme" });
  }
}
