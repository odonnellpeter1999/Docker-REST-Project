package client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import service.core.ClientApplication;
import service.core.ClientInfo;
import service.core.Quotation;

@RestController
public class BrokerService {

    private Map<Integer, ClientApplication> applications = new HashMap<>();
    private int seed = 0;

    private String[] services = { "http://auldfellas:8081/quotations", "http://girlpower:8082/quotations",
            "http://dodgydrivers:8083/quotations" };

    @RequestMapping(value = "/applications", method = RequestMethod.POST)
    public ResponseEntity<ClientApplication> createQuotation(@RequestBody ClientApplication client) {

        applications.put(seed++, client);
        client.setId(seed);

     

        for (String service : services) {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<ClientInfo> request = new HttpEntity<>(client.getClient());
            Quotation quotation = restTemplate.postForObject(service,request, Quotation.class);
            client.addQoute(quotation);

        }
        
		String path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/applications/" + seed;
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setLocation(new URI(path));
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		return new ResponseEntity<>(client, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/applications/{reference}", method = RequestMethod.GET)
	public ClientApplication getResource(@PathVariable("reference") Integer reference) {

		ClientApplication application = applications.get(reference);
		if (application == null)
			throw new NoSuchClientApplicationException();
		return application;
    }

    @RequestMapping(value = "/applications", method = RequestMethod.GET)
	public LinkedList<ClientApplication> getResource() {

        LinkedList<ClientApplication> clientApplicationList = new LinkedList<ClientApplication>(applications.values());
       
		if (applications.isEmpty())
			throw new NoSuchClientApplicationException();
		return clientApplicationList;
    }
    
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class NoSuchClientApplicationException extends RuntimeException {
		static final long serialVersionUID = -6516152229878843037L;
	}
    















    
    
}
