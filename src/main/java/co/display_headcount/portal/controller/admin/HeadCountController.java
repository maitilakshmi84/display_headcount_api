package co.display_headcount.portal.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.display_headcount.portal.model.admin.HeadCount;
import co.display_headcount.portal.model.common.CommonResponseModel;
import co.display_headcount.portal.service.admin.HeadCountService;
import co.display_headcount.portal.util.AppCommonUtil;

@CrossOrigin(origins = "*")
@RestController
public class HeadCountController {

	private static final Log logger = LogFactory.getLog(HeadCountController.class);

	@Autowired
	private CommonResponseModel commonResponseObj;
	@Autowired
	private HeadCountService headCountService;
	@Autowired
	private Environment env;
	
	/*
	 * This method fetch List as per nothing specified
	 */
	@RequestMapping(value = "/displayHC/getAllHeadCountData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CommonResponseModel getAllHeadCountData() {
		List<HeadCount> hcList = new ArrayList<HeadCount>();
		try {
			
			hcList = headCountService.getAllHeadCountData();

				logger.info("hcList controller >> " + AppCommonUtil.convertJson(hcList));
				commonResponseObj.setResponseStatus(1);
				commonResponseObj.setResponseMessage("Successfully fetched data");
				commonResponseObj.setResponseList(hcList);
				return commonResponseObj;
			
		} catch (Exception e) {
			logger.error("Exception in hcList >> " + e.getMessage());
			commonResponseObj.setResponseStatus(0);
			commonResponseObj.setResponseMessage("Sorry !!! " + e.getMessage());
			commonResponseObj.setResponseList(hcList);

			return commonResponseObj;
		}
	}
	@RequestMapping(value = "/displayHC/saveHeadCountDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public CommonResponseModel saveVehicleDetails(@RequestBody HeadCount headCountObj) {
		
		try {
			if (null != headCountObj) {
				logger.info("headCountObj controller >> " + AppCommonUtil.convertJson(headCountObj));
				headCountObj = headCountService.saveHeadCountObj(headCountObj);

				logger.info("After save headCountObj controller >> " + AppCommonUtil.convertJson(headCountObj));
				commonResponseObj.setResponseStatus(1);
				commonResponseObj.setResponseMessage("Head Count has been saved Successfully.");
				commonResponseObj.setResponseObj(headCountObj);
				return commonResponseObj;
				
			} else {
				commonResponseObj.setResponseStatus(0);
				commonResponseObj.setResponseMessage("Sorry !!! Please fill up the required information.");
				commonResponseObj.setResponseObj(headCountObj);

				return commonResponseObj;
			}
		} catch (Exception e) {
			logger.error("Exception in headCountObj >> " + e.getMessage());
			commonResponseObj.setResponseStatus(0);
			commonResponseObj.setResponseMessage("Sorry !!! Error " + e.getMessage());
			commonResponseObj.setResponseObj(headCountObj);

			return commonResponseObj;
		}
	}
	/*
	 * Call AI server to get camera feed / External API
	 */
	@RequestMapping(value = "/displayHC/fetchCameraFeed", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public CommonResponseModel fetchCamerFeed(@RequestBody HeadCount headCountObj) throws Exception{
		
		CommonResponseModel commonResponObj = new CommonResponseModel();
		String pythonFUri = env.getProperty("python_uri");
		ResponseEntity<CommonResponseModel> responseEntity = null;
		try {
			
			// -----Call an External API - Python_API------//
			if(null != headCountObj ) {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.set("Content-Type", "application/json");

				commonResponObj.setResponseStatus(1);
				commonResponObj.setResponseMessage("Head Count Obj has been sent.");
				commonResponObj.setResponseObj(headCountObj);
				logger.info("After sent headCountObj controller >> " + AppCommonUtil.convertJson(commonResponObj));

				HttpEntity<CommonResponseModel> entity = new HttpEntity<CommonResponseModel>(commonResponObj,
						headers);

				// ------------------------//
				List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
				// Add the Jackson Message converter
				MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

				// Note: here we are making this converter to process any kind of response,
				// not only application/*json, which is the default behaviour
				converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
				messageConverters.add(converter);
				restTemplate.setMessageConverters(messageConverters);
				// ---------------------------//

				responseEntity = restTemplate.exchange(pythonFUri, HttpMethod.POST, entity,
						CommonResponseModel.class);
				logger.info("responseEntity >> " + AppCommonUtil.convertJson(responseEntity));

				// -----End of External API - Python_API-----//
				return responseEntity.getBody();
			} else {
				commonResponObj.setResponseStatus(0);
				commonResponObj.setResponseMessage("Head Count obj null.");
				commonResponObj.setResponseObj(headCountObj);
				return commonResponObj;
			}
		} catch (Exception e) {
			logger.error("Exception in python uri >> " + e.getMessage());
			commonResponObj.setResponseStatus(0);
			commonResponObj.setResponseMessage("Sorry !!! Error " + e.getMessage());
			commonResponObj.setResponseObj(headCountObj);

			return commonResponObj;
		}
	}
}
