package com.iktpreobuka.egradebook.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.egradebook.security.Views;

//@JsonRootName(value = "Created Subject")
@JsonPropertyOrder({ "name", "description", "yerofSchooling", "weeklyHoursRequired" })
public class CreatedSubjectDTO {

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Subject name")
	private String name;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Description")
	private String description;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Year of schooling")
	private String yerofSchooling;

	@JsonView(Views.Admin.class)
	@JsonProperty(value = "Weekly hours required")
	private Integer weeklyHoursRequired;

	public CreatedSubjectDTO() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Integer getWeeklyHoursRequired() {
		return weeklyHoursRequired;
	}

	public String getYerofSchooling() {
		return yerofSchooling;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeeklyHoursRequired(Integer weeklyHoursRequired) {
		this.weeklyHoursRequired = weeklyHoursRequired;
	}

	public void setYerofSchooling(String yerofSchooling) {
		this.yerofSchooling = yerofSchooling;
	}

	@Override
	public String toString() {
		return "CreatedSubjectDTO [name=" + name + ", description=" + description + ", yerofSchooling=" + yerofSchooling
				+ ", weeklyHoursRequired=" + weeklyHoursRequired + "]";
	}

}
