package breakfastcontrolserver.web.exceptionhandler;

import java.time.LocalDateTime;

public class Problem {

	private Integer status;
	private LocalDateTime timeStamp;
	private String message;
	
	public Problem() {
		
	}	
	
	public Problem(Integer status, String message) {
		this.status = status;
		this.timeStamp = LocalDateTime.now();
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
