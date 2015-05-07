package time_Extraction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class RelativeTimeConverter {
    Calendar referenceTime;
    List<String> relativeRegexs;
    int[] regexHash;
    
    public RelativeTimeConverter(Calendar referenceTime, 
            List<String> relativeRegexs){
        this.referenceTime = referenceTime;
        this.relativeRegexs = relativeRegexs;
        this.regexHash = new int[relativeRegexs.size()];
        for (int i = 0; i < relativeRegexs.size(); i++) {
            regexHash[i] = hash(relativeRegexs.get(i));
        }
    }
    
    public Calendar convert(String relativeTime, String regex){
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(referenceTime.getTimeInMillis());

        int inputRegexHash = hash(regex);
        switch (inputRegexHash) {
            case regexHash[0]:
            break;
            case regexHash[1]:
            break;
            case regexHash[2]:
            break;
            case regexHash[3]:
            break;
            case regexHash[4]:
            break;
            case regexHash[5]:
            break;
            case regexHash[6]:
            break;
            case regexHash[7]:
            break;
            case regexHash[8]:
            break;
            case regexHash[9]:
            break;
            case regexHash[10]:
            break;
            case regexHash[11]:
            break;
            case regexHash[12]:
            break;
            default:
            break;
        }

        if(relativeTime.contains("today")){
            return result;
        }
        if(relativeTime.contains("yesterday")){
            result.add(Calendar.DATE, -1);
            return result;
        }
        if(relativeTime.contains("tomorrow")){
            result.add(Calendar.DATE, 1);
            return result;
        }
        if(relativeTime.contains("next month") || relativeTime.contains("a month later")){
            result.add(Calendar.MONTH, 1);
            return result;
        }
        if(relativeTime.contains("last month") || relativeTime.contains("a month ago")){
            result.add(Calendar.MONTH, -1);
            return result;
        }
        if(relativeTime.contains("next year")){
            result.add(Calendar.YEAR, 1);
            return result;
        }
        if(relativeTime.contains("last year") || relativeTime.contains("past year")
                || relativeTime.contains("year ago") || relativeTime.contains("year earlier")){
            result.add(Calendar.YEAR, -1);
            return result;
        }
        if(relativeTime.contains("years ago") || relativeTime.contains("years earlier")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.YEAR, year*-1);
            return result;
        }
        if(relativeTime.contains("years later")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.YEAR, year);
            return result;
        }
        if(relativeTime.contains("months ago") || relativeTime.contains("months earlier")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.MONTH, year*-1);
            return result;
        }
        if(relativeTime.contains("months later")){
            int year = Integer.parseInt(relativeTime.replaceAll("[^0-9]", ""));
            result.add(Calendar.MONTH, year);
            return result;
        }
        if(relativeTime.contains("this morning")){
            result.set(Calendar.HOUR, 8);
            result.set(Calendar.MINUTE, 0);
            result.set(Calendar.AM_PM, Calendar.AM);
            return result;
        }

      
        System.out.println("- Relative time not converted: " + relativeTime);
       
        return null;
    }

    private int hash(String regex) {
        int hash = 7;
        for (int i = 0; i < regex.length(); i++) {
            hash = hash * 31 + regex.charAt(i);
        }
        return hash;
    }
}
