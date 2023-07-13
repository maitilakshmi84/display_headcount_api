package co.display_headcount.portal.model.common;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseModel {

	private long responseStatus;
	private String responseMessage = "";
	private Object responseObj;
	private List responseList;
	
	public long getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(long responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResponseObj() {
		return responseObj;
	}
	public void setResponseObj(Object responseObj) {
		this.responseObj = responseObj;
	}
	public List getResponseList() {
		return responseList;
	}
	public void setResponseList(List responseList) {
		this.responseList = responseList;
	}
	
	
}
