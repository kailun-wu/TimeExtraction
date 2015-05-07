package time_Extraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Formatter {
    private List<SimpleDateFormat> formatters = new ArrayList<SimpleDateFormat>();
    private Calendar referenceTime;
    
    public Formatter(String fileName, Calendar referenceTime){
        List<String> formatterStr = IO.readDateFormat(fileName);
        for(String format: formatterStr){
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            formatters.add(formatter);
        }
        this.referenceTime = referenceTime;
    }
    
    public Calendar format(TimeBundle timeBundle){
        Calendar resultDate = Calendar.getInstance();
        for(SimpleDateFormat formatter: formatters){
            resultDate = parse(formatter, timeBundle.getRawValue());
            if(resultDate != null){
                timeBundle.setDateFormat(formatter);
                setDefaultDate(resultDate);
                relativeHandler(formatter, resultDate);
                return resultDate;
            }
        }
        System.out.println("- Can't parse this absolute time: " + timeBundle.getRawValue());
        return null;
    }
    
    // used to handle relative-like format, e.g friday 13:00
    private void relativeHandler(SimpleDateFormat formatter,
            Calendar resultDate) {
        if(formatter.toPattern().equals("E H:m")){
            Calendar tmp = (Calendar)referenceTime.clone();
            int dayDelta = resultDate.get(Calendar.DAY_OF_WEEK) - tmp.get(Calendar.DAY_OF_WEEK);
            tmp.add(Calendar.DATE, dayDelta);
            resultDate.set(Calendar.YEAR, tmp.get(Calendar.YEAR));
            resultDate.set(Calendar.MONTH, tmp.get(Calendar.MONTH));
            resultDate.set(Calendar.DATE, tmp.get(Calendar.DATE));
        }
        
    }

    // Set year, month, day as referenceTime if they are 1970-1-1
    private void setDefaultDate(Calendar cal) {
        if(cal.get(Calendar.YEAR) == 1970){
            cal.set(Calendar.YEAR, referenceTime.get(Calendar.YEAR));
            //The first month of the year in the Gregorian and Julian calendars is JANUARY which is 0
            if(cal.get(Calendar.MONTH) == 0 && cal.get(Calendar.DATE) == 1){
                cal.set(Calendar.MONTH, referenceTime.get(Calendar.MONTH));
                cal.set(Calendar.DATE, referenceTime.get(Calendar.DATE));
            }
        }
    }

    private Calendar parse(SimpleDateFormat sdf, String rawStr){
        Calendar myCal = Calendar.getInstance();
        try{
            myCal.setTime(sdf.parse(rawStr));
        } catch(ParseException e){
            myCal = null;
        }
        return myCal;
    }
}
