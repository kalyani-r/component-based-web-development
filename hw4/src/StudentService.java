//This java bean represents the business logic to calculate standard deviation and mean

package swe645_3;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.context.RequestContext;

import swe645_3.Student;


@ManagedBean(name = "studentInfo")
@SessionScoped
public class StudentService implements Serializable{
	
	private Student stud;
  	private List<Student> studentList;

	
	//@PersistenceContext(unitName="krachako")
	//private EntityManager em;

private static EntityManagerFactory emf;

	
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
	private ArrayList<String> checkbox;
	private String radio;
	private String raffle;
	private String menu;
	private String comments;
	private float mean;
	private double sd;
		
	public float getMean() {
		return mean;
	}

	public double getSd() {
		return sd;
	}

	public Student getStud() {
		return stud;
	}

	public List<Student> getStudentList() {
		return studentList;
	}
	
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
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

	public ArrayList<String> getCheckbox() {
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
			

            Client client = ClientBuilder.newClient();
	        WebTarget target = client.target("http://ec2-52-40-147-181.us-west-2.compute.amazonaws.com/SWE645_3/webresources/zips/");
	        WebTarget resourceWT;
	        resourceWT = target.path(this.zip);
	        
	       
	        Invocation.Builder iBuilder;
	        iBuilder = resourceWT.request(MediaType.TEXT_PLAIN);
	        Response response = iBuilder.get();
	        
	        String s = response.readEntity(String.class);
	        setCity(s.split("-")[0]);
	        setState(s.split("-")[1]);
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

	public void setsem(Date sem) {
		this.sem = sem;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCheckbox(ArrayList<String> checkbox) {
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

	private static final long serialVersionUID = 1L;
	
	
	public String surveyForm(){
		return "survey";
	}
	public String surveyList(){
		return "ListSurvey";
	}
	
	private float mean_cal(String raffle){
		String data1[] = raffle.split(",");
    	float mean = 0;
		for(int i=0; i< data1.length; i++)
    		mean =mean+ Integer.parseInt(data1[i]);
    	 mean = mean/ data1.length;
		return mean;
	}
	private double sd_cal(String raffle, float mean){
		String[] data1 = raffle.split(",");
    	double sum=0;
    	double sd = 0;
		for(int i=0; i< data1.length; i++){
    		sum = Math.pow((Integer.parseInt(data1[i]) - mean), 2); 
    	}
    	sd =  Math.sqrt(sum/(data1.length));
    	return sd;
	}
	
	public String saveStudent()
	{
		stud = new Student(fname, lname, address, zip, city, state, telephone, email, sem, date, checkbox.toString(), radio, raffle, menu, comments);
		mean = mean_cal(raffle);
		sd = sd_cal(raffle, mean);
		//EntityManager em = EMCreator.getEntityManager();
		
		emf = Persistence.createEntityManagerFactory("krachako");
		EntityManager em;
		em = emf.createEntityManager();
		
			//studentList.add(stud);
			em.getTransaction().begin();
			em.persist(stud);
			em.getTransaction().commit();
			em.close();
			
			this.setStudentList(querystudentList());

//		this.setStudentList(querystudentList());
		//might give prob with mean and sd calc

		
		FacesContext context = FacesContext.getCurrentInstance();
		if (!date.before(sem)) {
		date= null;
		FacesMessage errorMessage=
		new FacesMessage("Semester start date must be after date of survey");
		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(null, errorMessage);
		return(null);
		}

//		WinningResult res = new WinningResult(mean, sd);
		 if(mean >= 90)
			 return "WinnerAcknowledgement";
		 else
			 return "SimpleAcknowledgement";
	}
	
	private static final String ddstring = "Very Likeky,Likely,Unlikely";
	private static final String[] dropdown = ddstring.split(",");
	
	public List<String> completeDrop(String dd){
		List<String> match = new ArrayList<String>();
		for(String possibleDrop: dropdown){
			if(possibleDrop.toUpperCase().startsWith(dd.toUpperCase())){
				match.add(possibleDrop);
			}
			}
		return(match);
	}
		
	 private List<Student> querystudentList() {
			emf = Persistence.createEntityManagerFactory("krachako");
			EntityManager em;
			em = emf.createEntityManager();
		  TypedQuery<Student> q = em.createQuery("SELECT y from Student y", Student.class);
		  return q.getResultList();
		 }
	
	 public void reset() {
	        RequestContext.getCurrentInstance().reset("form");
	    }
}

// KALYANI RACHAKONDA