package co.display_headcount.portal.controller.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@CrossOrigin(origins = "*")
@RestController
public class HelloController {

	private static final Log logger = LogFactory.getLog(HelloController.class);
	/*
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public  String getTimeLocation() {

		Calendar cal = Calendar.getInstance();
	    Date date1=cal.getTime();
	    
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    String formattedDate="display_headcount Project runs. Todays' date is : "+dateFormat.format(date1);
		String base =  "<html><body><table>"+
		"<thead>"+
			"<tr style='border-top: 1px solid black;border-bottom: 1px solid black; border-width: thin; background-color: #F1F1F1; font-size: 12pt; font-family: Arial'>"+
				"<th rowspan='2' style='text-align:center'>SL No.</th>"+
				"<th rowspan='2' style='text-align:left'>Consumer Code</th>"+
				"<th rowspan='2' style='text-align:left'>Consumer Name</th>"+
				"<th style='text-align:center;border-width: thin; border-bottom: 1px solid black' colspan='6'>Consumption(SCM)</th>"+
				"<th rowspan='2' style='text-align:center'>DA</th>"+
				"<th rowspan='2' style='text-align:center'>Total Billing Figure<br>(SCM)</th>"+
				"<th rowspan='2' style='text-align:center'>Remarks</th>"+
			"</tr>"+
			"<tr style='border-bottom: 1px solid black;border-width: thin; background-color: #F1F1F1; font-size: 10pt; font-family: Arial'>"+
				"<th style='text-align:center'>Tea Manufacturing</th>"+
				"<th style='text-align:center'>Genset</th>"+
				"<th style='text-align:center'>Liquid Tea Boiling</th>"+
				"<th style='text-align:center'>Canteen</th>"+
				"<th style='text-align:center'>Small Indus</th>"+
				"<th style='text-align:center'>Others</th>"+
			"</tr>"+			
		"</thead>"+
		"<tr style='border-bottom: 1px solid black;border-width: thin; background-color: #F1F1F1; font-size: 25pt; font-family: Arial'><td colspan='13'>HeadCount Project runs. Todays' date is : "+dateFormat.format(date1)+"</td></tr>"+
		"</table></body></html>";
		
		logger.info("Logger worked.");
	    System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
	    
		return base;
	}

}
