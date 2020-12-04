




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
          NOCOLOR,
          ERROR,
          BLUE,
          RED,
          YELLOW
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
           
           if(hueIn >= blueMin && hueIn <= blueMax)
           {
               blueCnt+=2; //We already decremented so we need to increase by two. 
           }   
           
           if(hueIn >= redMin || hueIn <= redMax) //Red is a special value when you consider it with the hue values since it wraps around. So we used "OR" to deterimine instead of &&
           {
               redCnt+=2; //We already decremented so we need to increase by two. 
           }     
           
           if(hueIn >= yellowMin && hueIn <= yellowMax)
           {
               yellowCnt+=2; //We already decremented so we need to increase by two. 
           }
           
           //ensure blueCnt,redCnt,yellowCnt are always between 0 and 5. 
           blueCnt = Math.min(blueCnt,5); 
           blueCnt = Math.max(blueCnt,0); 
           redCnt = Math.min(redCnt,5); 
           redCnt = Math.max(redCnt,0); 
           yellowCnt = Math.min(yellowCnt,5); 
           yellowCnt = Math.max(yellowCnt,0);
           
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
           else if (yellowCnt <= 2 && blueCnt <= 2 && redCnt <= 2)
           {
               //System.out.print("No color detected");
               detectedColor = Color.NOCOLOR;
           }
           else
           {
               //System.out.print("Something went wrong");
               detectedColor = Color.ERROR;
           }
           
           return(detectedColor);

     }
     
     
     
     
     
     
     /*
      * This is merely a test method to feed data around our mins and maxs.  
      * 
      * This method should count up blue 6 times, countdown using !blue 6 times, count up red 6 times, 
      * countdown using !red 6 times, count up yellow 6 times,and finally countdown using !yellow 6 times.
      * 
      * Another good test method would be to count up blue 6 times, countup red 2 times, countup blue 3 times, 
      * then ensure that the color never changed from blue
     */
     public static void RunColorDection() 
     {
         int colorSensorHueIn[] = {220,240,220,240,220,240, //Count up blue using blueMin and blueMax
                                   219,241,219,241,219,241, //Count down using blueMin -1 and blueMax +1
                                   350,10,350,10,350,10, //Count up red using redMin and redMax
                                   349,11,349,11,349,11, //Count down using redMin -1 and redMax +1
                                   45,65,45,65,45,65, //Count up yellow using yellowMin and yellowMax
                                   44,66,44,66,44,66}; //Count down using yellowMin -1 and yellowMax +1
         for (int i=0; i<colorSensorHueIn.length; i++)
         {
             System.out.print("Your Color is:" + DetectColor(colorSensorHueIn[i]) + " ");
             System.out.print("Blue Count is:" + blueCnt + " ");
             System.out.print("Red Count is:" + redCnt + " ");
             System.out.println("Yellow Count is:" + yellowCnt + " ");
         }
     }  
}
