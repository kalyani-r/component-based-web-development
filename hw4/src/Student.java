//This is the java class with all the getter methods

package swe645_3;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
//@Table(name = "STUDENT")
public class Student implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String fname;
	private String lname;
	private String address;
	private String zip;
	private String city;
	private String state;
	private String telephone;
	private String email;
	private Date sem;
	private Date date;
	private String checkbox;
	private String radio;
	private String raffle;
	private String menu;
	private String comments;
	
	public Student()
	{
		
	}

	public Student(String fname, String lname, String address, String zip, String city, String state, String telephone, String email, Date sem, Date date, String checkbox, String radio, String raffle, String menu, String comments ){
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.email = email;
		this.sem = sem;
		this.date = date;
		this.checkbox = checkbox;
		this.radio = radio;
		this.raffle = raffle;
		this.menu = menu;
		this.comments = comments;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getAddress() {
		return address;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getEmail() {
		return email;
	}

	public Date getsem() {
		return sem;
	}

	public Date getDate() {
		return date;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public String getRadio() {
		return radio;
	}

	public String getRaffle() {
		return raffle;
	}

	public String getMenu() {
		return menu;
	}

	public String getComments() {
		return comments;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSem(Date sem) {
		this.sem = sem;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}

// KALYANI RACHAKONDA