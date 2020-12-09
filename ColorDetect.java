/*
 * Color Detection Example for Ultimate Goal FTC Season 2020/2021
 * By Bart, Amy, Howard, Rajesh, Ehren, and Rishabh.
 * Version 1
 * Modified 12/03/2020
 */
public class ColorDetect
{
        enum Color 
        {
          NOCOLOR, //0
          ERROR,   //1
          BLUE,    //2
          RED,     //3
          YELLOW   //4
        }
    
        public static int redCnt = 0;
        public static int blueCnt = 0;
        public static int yellowCnt = 0;
        
        public static Color DetectColor(int hueIn)
        {
           final int blueMin = 220; //Blue Starts at 220deg and goes to 240deg.
           final int blueMax = 240;
           final int redMin = 350; //Red Starts at 350deg and wraps around to 10deg.
           final int redMax = 10;
           final int yellowMin = 45; //Yellow Starts at 45deg and goes to 65deg
           final int yellowMax = 65;
           
           Color detectedColor = Color.ERROR;
           
           redCnt--;
           yellowCnt--; 
           blueCnt--; 
           
           //ensure blueCnt,redCnt,yellowCnt are always greater than 0

           
           if(hueIn >= blueMin && hueIn <= blueMax)
           {
               blueCnt+=2; //Increment twice because we already decremented 
           }   
           
           if(hueIn >= redMin || hueIn <= redMax) //Red is a special value when you consider it with the hue values since it wraps around. So we used "OR" to deterimine instead of &&
           {
               redCnt+=2; //Increment twice because we already decremented 
           }     
           
           if(hueIn >= yellowMin && hueIn <= yellowMax)
           {
               yellowCnt+=2; //Increment twice because we already decremented 
           }
           
           //ensure blueCnt,redCnt,yellowCnt are always less than 5
           //                IN 65535  OUT IS 5
           //                    IN 6  OUT IS 5
           //                    IN 4  OUT IS 4
           //                    IN 3  OUT IS 3
           //                    IN 2  OUT IS 2
           //                    IN 1  OUT IS 1
           //                    IN 0  OUT IS 0
           blueCnt = Math.min(blueCnt,5);    
           redCnt = Math.min(redCnt,5); 
           yellowCnt = Math.min(yellowCnt,5); 
           blueCnt = Math.max(blueCnt,0);
           redCnt = Math.max(redCnt,0); 
           yellowCnt = Math.max(yellowCnt,0);
           
           //blueCnt = 0 redCnt = 0 yellow = 4
           if (blueCnt >= 3 && redCnt <= 2 && yellowCnt <= 2)
           {
               //System.out.print("Blue is your color");
               detectedColor = Color.BLUE;
           }
           else if (redCnt >= 3 && yellowCnt <= 2 && blueCnt <= 2)
           {
               //System.out.print("Red is your color");
               detectedColor = Color.RED;
           }
           else if (yellowCnt >= 3 && blueCnt <= 2 && redCnt <= 2)
           {  
               //System.out.print("Yellow is your color");
               detectedColor = Color.YELLOW;
           }
           else 
           {
               //System.out.print("No color detected");
               detectedColor = Color.NOCOLOR;
           }
           
           return(detectedColor);

     }
     
     
     
     
     
     
     /*
      * This is merely a test method to feed data around our mins and maxs.  
      * 
      * This method should count up blue 6 times, countdown using !blue 6 times, count up red 6 times, 
      * countdown using !red 6 times, count up yellow 6 times,and finally countdown using !yellow 6 times.
      * 
      *   final int blueMin = 220; //Blue Starts at 220deg and goes to 240deg.
      *   final int blueMax = 240;
     */
     public static void RunColorDectionBoundary() 
     { //Boundry value analysis 
         int colorSensorHueIn[] = {220,240,220,240,220,240, //Count up blue using blueMin and blueMax
                                   219,241,219,241,219,241, //Count down using blueMin -1 and blueMax +1
                                   350,10,350,10,350,10, //Count up red using redMin and redMax
                                   349,11,349,11,349,11, //Count down using redMin -1 and redMax +1
                                   45,65,45,65,45,65, //Count up yellow using yellowMin and yellowMax
                                   44,66,44,66,44,66}; //Count down using yellowMin -1 and yellowMax +1
         for (int i=0; i<colorSensorHueIn.length; i++) //Loop through all elements of the above array. 
         {
             System.out.print("Your Color is:" + DetectColor(colorSensorHueIn[i]) + " ");
             System.out.print("Blue Count is:" + blueCnt + " ");
             System.out.print("Red Count is:" + redCnt + " ");
             System.out.println("Yellow Count is:" + yellowCnt + " ");
         }
     }  
     
     /*
      * Another good test method would be to count up blue 6 times, countup red 2 times, countup blue 3 times, count up red 3 times
      * then ensure that the color is "NOCOLOR" first two outputs "BLUE" in the middle and "RED" for the last output.
      */
     public static void RunColorDectionHyst() 
     { //hysteresis test - protect against false pos and neg. 
         int colorSensorHueIn[] = {220,240,220,240,220,240, //Count up blue using blueMin and blueMax six times. 
                                   350,10, //Count down blue with two reds
                                   220,240,220, //Count up blue using blueMin and blueMax three times.
                                   350,10,350 //Count up red three times.
                                   };
         for (int i=0; i<colorSensorHueIn.length; i++) //Loop through all elements of the above array.
         {
             System.out.print("Your Color is:" + DetectColor(colorSensorHueIn[i]) + " ");
             System.out.print("Blue Count is:" + blueCnt + " ");
             System.out.println("Red Count is:" + redCnt + " ");
             //We dont use yellow in this test. So we are going to ignore it for now. This will make the output more readable. 
             //System.out.println("Yellow Count is:" + yellowCnt + " ");
         }
     }
}
