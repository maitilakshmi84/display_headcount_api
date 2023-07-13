package co.display_headcount.portal.service.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import co.display_headcount.portal.dao.admin.HeadCountRepository;
import co.display_headcount.portal.model.admin.HeadCount;
import co.display_headcount.portal.util.AppCommonUtil;

@Component
public class HeadCountService {

	private static final Log logger = LogFactory.getLog(HeadCountService.class);

	@Autowired
	private HeadCountRepository headCountRepository;

	/*
	 * This method fetch List as per nothing specified
	 */
	public List<HeadCount> getAllHeadCountData() throws Exception{
		
		List<HeadCount> headCountList = new ArrayList<HeadCount>();
		headCountList = headCountRepository.getAllHeadCountData();
		
		logger.info("headCountList after get >> " + AppCommonUtil.convertJson(headCountList));
		return headCountList;
	}
	/*
	 * This method saves an object of HeadCount
	 * 
	 */
	public HeadCount saveHeadCountObj(HeadCount headCountObj) throws Exception {
		logger.info("headCountObj from front end >> " + AppCommonUtil.convertJson(headCountObj));
		
		// ----for update existing value---//
		if (null != headCountObj.getHeadCountId() && !StringUtils.isEmpty(headCountObj.getHeadCountId())
				&& headCountObj.getHeadCountId()>0) {

			headCountObj.setModifiedOn(new Date());
			
			logger.info("vehicleDetailsObj before update >> " + AppCommonUtil.convertJson(headCountObj));
			headCountObj = headCountRepository.save(headCountObj);

			return headCountObj;
		} // ---end of update existing value---//
		else {// ---for new value save-----//
			headCountObj.setCreatedOn(new Date());
			logger.info("headCountObj before save >> " + AppCommonUtil.convertJson(headCountObj));
			headCountObj = headCountRepository.save(headCountObj);
			return headCountObj;
		}
	}
	/*
	 * This method saves an object of HeadCount
	 * for testing purpose
	 */
	public HeadCount saveHeadCountSchedularObj(HeadCount headCountObj ) throws Exception{
		// ----for update existing value---//
		if (null != headCountObj.getHeadCountId() && !StringUtils.isEmpty(headCountObj.getHeadCountId())
				&& headCountObj.getHeadCountId()>0) {

			headCountObj.setModifiedOn(new Date());
			
			logger.info("vehicleDetailsObj before update >> " + AppCommonUtil.convertJson(headCountObj));
			headCountObj = headCountRepository.save(headCountObj);

			return headCountObj;
		} // ---end of update existing value---//
		else {// ---for new value save-----//
			headCountObj.setCreatedOn(new Date());
			logger.info("headCountObj before save >> " + AppCommonUtil.convertJson(headCountObj));
			headCountObj = headCountRepository.save(headCountObj);
			return headCountObj;
		}
	}
}
