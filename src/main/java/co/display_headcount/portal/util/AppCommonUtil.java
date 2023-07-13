package co.display_headcount.portal.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppCommonUtil {
	
	private static final Log logger = LogFactory.getLog(AppCommonUtil.class);
	   
    /*
     * Convert any List or Object to JSON format to see JSON in Structured way
     * 
     *  @Author Abhishek Samanta
     */
	public static String convertJson(Object obj) {
	
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";

		try {
			jsonInString = mapper.writeValueAsString(obj);
			
		} catch (JsonProcessingException e) {
			logger.error("Exception in convertJson >> "+e.getMessage());
		}
		return jsonInString;
	}
	/*
	 * Method to remove all spaces and special character from Address 
	 * string
	 */
	public static String removeCharString(String strToRemv) {
		
		if(null != strToRemv && !StringUtils.isEmpty(strToRemv)) {
			strToRemv = strToRemv.trim().replaceAll("[^a-zA-Z0-9]", ""); 
		}
		logger.info("After remove >> "+strToRemv);  
		return strToRemv;
	}	
}
