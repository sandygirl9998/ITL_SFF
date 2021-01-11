package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_Admin")
@PrimaryKeyJoinColumn(name = "adminid", referencedColumnName = "user_id")
public class Admin extends User {

}
