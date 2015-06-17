package com.ydbaobao.model;

public class SessionCustomer {

	private String sessionId;
	private String sessionName;
	private String sessionGrade;
	
	public SessionCustomer(String sessionId, String sessionName, String sessionGrade) {
		this.sessionId = sessionId;
		this.sessionName = sessionName;
		this.sessionGrade = sessionGrade;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public String getSessionGrade() {
		return sessionGrade;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public void setSessionGrade(String sessionGrade) {
		this.sessionGrade = sessionGrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionGrade == null) ? 0 : sessionGrade.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((sessionName == null) ? 0 : sessionName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionCustomer other = (SessionCustomer) obj;
		if (sessionGrade == null) {
			if (other.sessionGrade != null)
				return false;
		} else if (!sessionGrade.equals(other.sessionGrade))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (sessionName == null) {
			if (other.sessionName != null)
				return false;
		} else if (!sessionName.equals(other.sessionName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SessionCustomer [sessionId=" + sessionId + ", sessionName=" + sessionName + ", sessionGrade="
				+ sessionGrade + "]";
	}
}