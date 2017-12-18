

package com.yundao.customer.h5.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * Function: 
 * Reason:	  
 * Date:     2017年8月4日 上午11:24:29 
 * @author   wucq
 * @version   
 */
public class DateFormatUtils {
   public static  String format(Date date,String partten){
	   if(date ==null || StringUtils.isBlank(partten)){
		   return "";
	   }
	   SimpleDateFormat format=new SimpleDateFormat(partten);
	   return format.format(date);
	   
   }
}

