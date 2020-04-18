package activityManagement.activityModular.entity;

import java.util.Date;

public class Activity {
	private Integer id;
	private String actName;
	private String actPicture;
	private String sponsor;
	private String coOrganizer;
	private Date signUpStartDate;
	private Date signUpEndDate;
	private Date actStartDate;
	private Date actEndDate;
	private String actContent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActPicture() {
		return actPicture;
	}

	public void setActPicture(String actPicture) {
		this.actPicture = actPicture;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getCoOrganizer() {
		return coOrganizer;
	}

	public void setCoOrganizer(String coOrganizer) {
		this.coOrganizer = coOrganizer;
	}

	public Date getSignUpStartDate() {
		return signUpStartDate;
	}

	public void setSignUpStartDate(Date signUpStartDate) {
		this.signUpStartDate = signUpStartDate;
	}

	public Date getSignUpEndDate() {
		return signUpEndDate;
	}

	public void setSignUpEndDate(Date signUpEndDate) {
		this.signUpEndDate = signUpEndDate;
	}

	public Date getActStartDate() {
		return actStartDate;
	}

	public void setActStartDate(Date actStartDate) {
		this.actStartDate = actStartDate;
	}

	public Date getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(Date actEndDate) {
		this.actEndDate = actEndDate;
	}

	public String getActContent() {
		return actContent;
	}

	public void setActContent(String actContent) {
		this.actContent = actContent;
	}

}
