import java.text.NumberFormat;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

public class Client {

    /**
	 * Display the client info nicely.
	 * 
	 * @param info
	 */
	public static void displayProfile(ClientInfo info) {
		System.out.println("|=================================================================================================================|");
		System.out.println("|                                     |                                     |                                     |");
		System.out.println(
				"| Name: " + String.format("%1$-29s", info.getName())
                        + 
				" | Gender: " + String.format("%1$-27s", (info.getGender()==ClientInfo.MALE?"Male":"Female")) +
				" | Age: " + String.format("%1$-30s", info.getAge())+" |");
		System.out.println(
				"| License Number: " + String.format("%1$-19s", info.getLicenseNumber()) + 
				" | No Claims: " + String.format("%1$-24s", info.getNoClaims()+" years") +
				" | Penalty Points: " + String.format("%1$-19s", info.getPoints())+" |");
		System.out.println("|                                     |                                     |                                     |");
		System.out.println("|=================================================================================================================|");
	}

	/**
	 * Display a quotation nicely - note that the assumption is that the quotation will follow
	 * immediately after the profile (so the top of the quotation box is missing).
	 * 
	 * @param quotation
	 */
	public static void displayQuotation(Quotation quotation) {
		System.out.println(
				"| Company: " + String.format("%1$-26s", quotation.getCompany()) + 
				" | Reference: " + String.format("%1$-24s", quotation.getReference()) +
				" | Price: " + String.format("%1$-28s", NumberFormat.getCurrencyInstance().format(quotation.getPrice()))+" |");
		System.out.println("|=================================================================================================================|");
    }

    public static ClientInfo[] clients = new ClientInfo[6];
    public static ClientApplication[] clientApplications = new ClientApplication[6];  

    public static ClientInfo setClient(String name,char gender,int age,int noClaims,int points,String licenseNumber) {

        ClientInfo client = new ClientInfo();

        client.setName(name);
        client.setGender(gender);
        client.setAge(age);
        client.setNoClaims(noClaims);
        client.setPoints(points);
		client.setLicenseNumber(licenseNumber);

        return client;

	}

	public static void setClients() {

		clients[0]  = setClient("Niki Collier", ClientInfo.FEMALE, 43, 0, 5, "PQR254/1");
		clients[1]  = setClient("Old Geeza", ClientInfo.MALE, 65, 0, 2, "ABC123/4");
		clients[2]  = setClient("Hannah Montana", ClientInfo.FEMALE, 16, 10, 0, "HMA304/9");
		clients[3]  = setClient("Rem Collier", ClientInfo.MALE, 44, 5, 3, "COL123/3");
		clients[4]  = setClient("Jim Quinn", ClientInfo.MALE, 55, 4, 7, "QUN987/4");
        clients[5]  = setClient("Donald Duck", ClientInfo.MALE, 35, 5, 2, "XYZ567/9");	
	}

	public static ClientApplication setClientApplication(ClientInfo info,int id) {

		ClientApplication clientApplication = new ClientApplication();

		clientApplication.setClient(info);
		clientApplication.setId(id);
		clientApplication.setList();

		return clientApplication;
	}

	public static void setClientApplications() {
		setClients();
		clientApplications[0] = setClientApplication(clients[0],1); 
		clientApplications[1] = setClientApplication(clients[1],2); 
		clientApplications[2] = setClientApplication(clients[2],3); 
		clientApplications[3] = setClientApplication(clients[3],4); 
		clientApplications[4] = setClientApplication(clients[4],5); 
		clientApplications[5] = setClientApplication(clients[5],6); 
	}

    public static void main(String[] args) {

		setClientApplications();
		for(ClientApplication clientApp: clientApplications) {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<ClientApplication> request = new HttpEntity<>(clientApp);
			ClientApplication result = restTemplate.postForObject("http://localhost:8080/applications", request, ClientApplication.class);
		// 	displayProfile(result.getClient());

			for(Quotation quote: result.getQuotes())
				clientApp.addQoute(quote);

		}

		
		for(ClientApplication clientApp: clientApplications) {

			displayProfile(clientApp.getClient());

			for(Quotation quote: clientApp.getQuotes())
				displayQuotation(quote);


		}

			
       } 

      
       
    
}
