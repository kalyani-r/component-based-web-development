/* This bean is used for the searching the survey records and updating the database*/

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;

import swe645_3.Student;

@ManagedBean(name = "searchInfo")
@SessionScoped
public class SearchService implements Serializable {
	
	private Student stud;
	
	private static final long serialVersionUID = 1L;
	private String fname;
	private String lname;
	private String city;
	private String state;
	
	private List<Student> studentList;
	private static EntityManagerFactory emf;
	
	
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public void saveQuery(){
		
		this.setStudentList(querysearchList());
		
	}
	
	private List<Student> querysearchList() {
//	SessionFactory sf = new Configuration().configure().buildSessionFactory();
//	Session session = sf.openSession();
//	Criteria cr = session.createCriteria(Student.class);
//	List results = cr.list();
//	Criterion name = Restrictions.ilike("First Name","fname");
	
		emf = Persistence.createEntityManagerFactory("krachako");
		CriteriaBuilder cb = emf.getCriteriaBuilder();
		EntityManager em = emf.createEntityManager();
		  
		  CriteriaQuery<Student> cq = cb.createQuery(Student.class); 
		   Root<Student> sr = cq.from(Student.class);
		   
		   List<Predicate> predicates = new ArrayList<Predicate>();

		      if (fname != "") {
		          predicates.add(
		                  cb.equal(sr.get("fname"), fname));
		      }
		      if (lname != "") {
		          predicates.add(
		                  cb.equal(sr.get("lname"), lname));
		      }
		      if (city != "") {
		          predicates.add(
		                  cb.equal(sr.get("city"), city));
		      }
		      if (state != "") {
		          predicates.add(
		                  cb.equal(sr.get("state"), state));
		      }
		      //query itself
		      cq.select(sr)
		              .where(predicates.toArray(new Predicate[]{}));
		      //execute query and do something with result  
		   List<Student> stud = em.createQuery(cq).getResultList();
		   return stud;
		 }
	
	public void deleteEntry(Long id){
		
		emf = Persistence.createEntityManagerFactory("krachako");
		EntityManager em = emf.createEntityManager();
	      em.getTransaction().begin();
	     Student student = (Student) em.find(Student.class, id);
	      em.remove(student);
	      em.getTransaction().commit();
	      em.close();
		  saveQuery();
		
	}
	
	public String searchSurvey(){
		return "Search";
	}
}

//KALYANI RACHAKONDA