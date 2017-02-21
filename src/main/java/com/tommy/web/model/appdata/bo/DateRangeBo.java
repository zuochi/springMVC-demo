package com.tommy.web.model.appdata.bo;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tommy.web.utils.DateUtils;

/**
 * @update Tommy Lau on 2016/11/24
 */
public class DateRangeBo {

	private static final Logger logger = LoggerFactory.getLogger(DateRangeBo.class);

    private Date startDate;
    private Date endDate;

    public DateRangeBo(String startDate,String endDate){
        try {
        	this.startDate = DateUtils.strToDate(startDate, "yyyy-MM-dd");
        	this.endDate = DateUtils.strToDate(endDate, "yyyy-MM-dd");
        }catch(Exception e){
        	 Date now = Calendar.getInstance().getTime();
             this.startDate = now;
             this.endDate = now;
             
            logger.warn("日期格式不对：(startDate:"+startDate+",endDate:"+endDate+"),默认返回当前日期！");
            logger.debug(e.getMessage());
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStartDateString(){
        return DateUtils.dateToStr(startDate, "yyyy-mm-dd");
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEndDateString(){
    	return DateUtils.dateToStr(endDate, "yyyy-mm-dd");
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
