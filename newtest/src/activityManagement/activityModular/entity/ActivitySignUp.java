package activityManagement.activityModular.entity;

import java.util.Date;

public class ActivitySignUp {
	private Integer userId;
	private Integer activityId;
	private Date signUpTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Date getSignUpTime() {

		return signUpTime;
	}

	public void setSignUpTime(Date signUpTime) {
		this.signUpTime = signUpTime;
	}
}
