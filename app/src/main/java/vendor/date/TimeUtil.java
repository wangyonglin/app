package vendor.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static String difference(String date) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar releasedTime = Calendar.getInstance();
        releasedTime.setTime(df.parse(date));
        Calendar currentTime = Calendar.getInstance();
       currentTime.setTime(new Date());

        if (currentTime.get(Calendar.YEAR) > releasedTime.get(Calendar.YEAR)) { ;
            return new String(formatter.format(df.parse(date)));
        }
        if (currentTime.get(Calendar.MONTH)  > releasedTime.get(Calendar.MONTH)) {
            return new String(formatter.format(df.parse(date)));
        }
        if (currentTime.get(Calendar.DAY_OF_MONTH)  > releasedTime.get(Calendar.DAY_OF_MONTH)) {
            return (currentTime.get(Calendar.DAY_OF_MONTH) - releasedTime.get(Calendar.DAY_OF_MONTH) + "天前");
        }
        if (currentTime.get(Calendar.HOUR)  > releasedTime.get(Calendar.HOUR)) {
            return (currentTime.get(Calendar.HOUR)  - releasedTime.get(Calendar.HOUR) + "小时前");
        }
        if (currentTime.get(Calendar.MINUTE)  > releasedTime.get(Calendar.MINUTE)) {
            return (currentTime.get(Calendar.MINUTE)  - releasedTime.get(Calendar.MINUTE) + "分前");
        }
        return (new String("刚刚"));
    }

    public int differenceYear(Calendar releasedTime,Calendar currentTime){

        if(currentTime.get(Calendar.YEAR) > releasedTime.get(Calendar.YEAR)){

            return  (currentTime.get(Calendar.YEAR)-releasedTime.get(Calendar.YEAR))>1 ? currentTime.get(Calendar.YEAR) - releasedTime.get(Calendar.YEAR):0;
        }
        return 0;
    }
}
