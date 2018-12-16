package com.ironshutter.web.controllers.rest.subscription;

import java.io.Serializable;

import com.ironshutter.web.domain.subscription.Subscription;

public class AppAuth implements Serializable{

	private static final long serialVersionUID = 573001037090787755L;

	public static enum Result {
		AUTHORIZED("AUTHORIZED", "사용 가능합니다."),
		ERROR("ERROR", "오류가 발생했습니다."),
		ACTIVATE_NEW_SUBS("ACTIVATE_NEW_SUBS", "새 구독이 활성화되었습니다."),
		EXPIRED("EXPIRED", "기존 구독이 만료되었습니다.");
		
		private String resultCode;
		private String resultMessage;
		
		Result(String resultCode, String resultMessage){
			this.setResultCode(resultCode);
			this.setResultMessage(resultMessage);
		}

		public String getResultCode() {
			return resultCode;
		}

		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultMessage() {
			return resultMessage;
		}

		public void setResultMessage(String resultMessage) {
			this.resultMessage = resultMessage;
		}
	}
	
	private Result resultCode;
	private Subscription activated;
	private Subscription recentExpired;
	
	public AppAuth (Result resultCode) {
		this.resultCode = resultCode;
	}

	public Result getResultCode() {
		return resultCode;
	}

	public void setResultCode(Result resultCode) {
		this.resultCode = resultCode;
	}

	public Subscription getActivated() {
		return activated;
	}

	public void setActivated(Subscription activated) {
		this.activated = activated;
	}

	public Subscription getRecentExpired() {
		return recentExpired;
	}

	public void setRecentExpired(Subscription recentExpired) {
		this.recentExpired = recentExpired;
	}
	
}
