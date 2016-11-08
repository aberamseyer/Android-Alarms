package ilstu.edu.project2alarms;

/**
 * Created by kyle on 11/8/2016.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kyle
 *
 */
public class repeatAlarm extends alarmBase {
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
    private Date date= new Date();
    private int countdown;
    private String initalTime= dateFormat.format(date);

    /**
     * check location every 10 seconds until returns not -1 then count down from returned numebr
     */
    public int checkLocation()
    {
        date= new Date();
        int result=-1;
        int[] tenLess=new int[3];
        String timeLeft=initalTime.substring(0,initalTime.indexOf(' '));

        String tenLeft="";

        for(int i=0;i<tenLess.length;i++)
        {
            int temp=timeLeft.indexOf(':');
            if(temp>-1)
                tenLess[i]=Integer.parseInt(timeLeft.substring(0, temp));
            else
                tenLess[i]=Integer.parseInt(timeLeft);
            timeLeft=timeLeft.substring(temp+1);
        }
        tenLess[1]+=countdown;

//		adds minutes to countdown from
//		can be adjusted for if user can set minutes and seconds for countdown
        if(tenLess[1]>59)
        {

            tenLess[0]+=tenLess[1]%60;
            tenLess[1]+=tenLess[1]/60;
        }
        System.out.println(tenLess[0]+" "+tenLess[1]+" "+tenLess[2]);
        tenLess[2]-=10;
        System.out.println(tenLess[0]+" "+tenLess[1]+" "+tenLess[2]);
        for(int i=tenLess.length-1;i>0;i--)
        {
//				This is suppsed to check and correct for if seonds go below 0
//				for example if seconds are 06 then 10 less is 56 not -4
//				if(tenLess[2]<0)
//				{
//					tenLess[2]+=60;
//					tenLess[1]-=1;
//				}
//				if(tenLess[1]<0)
//				{
//					tenLess[1]+=60;
//					tenLess[0]-=1;
//				}
            tenLeft=tenLess[i]+tenLeft;
        }

        tenLeft=tenLeft.substring(0,2)+":"+tenLeft.substring(2);
        tenLeft=tenLess[0]+":"+tenLeft+initalTime.substring(initalTime.indexOf(' '));
        try {

            result=	date.compareTo((Date)(dateFormat.parse(tenLeft)));
//		result=	date.after((Date)(dateFormat.parse(tenLeft)));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return result;
    }

    /**
     * @return the countdown
     */
    private int getCountdown() {
        return countdown;
    }

    /**
     * @param countdown the countdown to set
     */
    private void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    /**
     * @return the initalTime
     */
    private String getInitalTime() {
        return initalTime;
    }

    /**
     * @param  the initalTime to set
     */
    private void setInitalTime() {

        date= new Date();
        this.initalTime = dateFormat.format(date);
    }


}
