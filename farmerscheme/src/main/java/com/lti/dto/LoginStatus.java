package com.lti.dto;

public class LoginStatus extends Status {

	//in case we want to return all the details of Customer on login
    //then declare Customer object here-> private Customer customer; 
    private int userId;
    private String username;
    private String role;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
	
}
