package co.display_headcount.portal.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import co.display_headcount.portal.model.admin.HeadCount;
import co.display_headcount.portal.service.admin.HeadCountService;

@Component
public class PythonInvokeScheduler {
	
	private static final Log logger = LogFactory.getLog(PythonInvokeScheduler.class);
	
	@Autowired
	private HeadCountService headCountService;

	/*@Scheduled(cron = "0 * 9 * * ?")
	   public void cronJobSch() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      logger.info("Java cron job expression:: " + strDate);
	   }*/
	
	/*
	 * A scheduler method to call AI server for every 10 seconds
	 * to know the current Head count through multiple camera
	 */
	//@Scheduled(fixedDelay = 10000, initialDelay = 3000)
	public  void fixedDelaySch() {
		
		try {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//---using java 7--//
			  Date now = new Date();
			  String strDate = sdf.format(now);
			  
			  LocalDateTime localDt = LocalDateTime.now();//---using java 8---//
			  int second = localDt.getSecond();
			  logger.info("Fixed Delay scheduler:: " + strDate+"\t"+second );
			  
			  //---Call a method---//
			  List<HeadCount> hcList = getScheduleHeadCount();
			  logger.info("hcList Schedular >> " + AppCommonUtil.convertJson(hcList));
			  HeadCount headCountObj = saveScheduleHeadCount(hcList.get(0),second);
			  logger.info("after update Schedular >> " + AppCommonUtil.convertJson(headCountObj));
			  
		} catch (Exception e) {
			logger.error("Exception in scheduler >> " + e.getMessage());			
		}
	}
	
	/*
	 * Call a get method every after 15 seconds through a Scheduler
	 * Method
	 */
	public  List<HeadCount> getScheduleHeadCount() throws Exception{
		
		List<HeadCount> hcList = new ArrayList<HeadCount>();		
		hcList = headCountService.getAllHeadCountData();
		return hcList;
	}
	/*
	 * Call a save method every after 15 seconds through a Scheduler
	 * Method
	 */
	public  HeadCount saveScheduleHeadCount(HeadCount param,int second) throws Exception{
		
		HeadCount headCountObj = new HeadCount();
		  int mxAp = param.getMaxAllowedPerson();
		  headCountObj.setCreatedBy("sritama");
			  headCountObj.setCreatedOn(null);
			  headCountObj.setHeadCountId(10);
			  headCountObj.setMaxAllowedPerson(mxAp);
			  headCountObj.setModifiedBy(null);
			  headCountObj.setNewAllowedPerson(null);
			  headCountObj.setPersonCount(second);
		 headCountObj = headCountService.saveHeadCountSchedularObj(headCountObj);
		 
		 return headCountObj;
	}
}
