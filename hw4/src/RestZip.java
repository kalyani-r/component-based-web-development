//This java class which has zipcode, city and state values

package swe645_3;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/zips")
public class RestZip {
		
	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	@Path("{zip}")
	public String restFind(@PathParam("zip") String nzip) {
		String state=null, city = null;
			
    for (Map.Entry<String, String> entry : getMap().entrySet()) {
			if (entry.getKey().equals(nzip)) {
				String[] cityState = entry.getValue().split("-");
				city = cityState[0];
				state = cityState[1];
			
		}
	 }
	return city+"-"+state;
	     
	}
    public Map<String, String> getMap() {
 	Map<String, String> zipCS = new HashMap<String, String>();
	zipCS.put("22312", "Alexandria-VA");
	zipCS.put("22030", "Fairfax-VA");
	zipCS.put("22301", "Tysons Corner-MD");
	zipCS.put("20148", "Ashburn-VA");

	return zipCS;
    }
	

}

// KALYANI RACHAKONDA