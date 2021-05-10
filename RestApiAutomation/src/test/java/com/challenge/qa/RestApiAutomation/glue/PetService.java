package com.challenge.qa.RestApiAutomation.glue;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.qa.RestApiAutomation.data.SharedData;
import com.challenge.qa.RestApiAutomation.model.entity.Category;
import com.challenge.qa.RestApiAutomation.model.entity.Pet;
import com.challenge.qa.RestApiAutomation.model.entity.PetStatus;
import com.challenge.qa.RestApiAutomation.model.entity.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.When;

public class PetService {
	
	final Logger LOG = LoggerFactory.getLogger(PetService.class);
	
	private final CloseableHttpClient httpClient;
	
	private SharedData sharedData;
	
	private final ObjectMapper mapper;
	
	private long petId;
	
	public PetService(SharedData sharedData) {
		this.sharedData = sharedData;
		this.mapper = new ObjectMapper();
		httpClient = HttpClients.createDefault();
	}
	
	@When("user get {string} pets")
	public void user_get_pets(String string) throws ClientProtocolException, IOException {
	    HttpGet request = new HttpGet(sharedData.getTargetUrl() + "pet/findByStatus?status=" + string);
	    
	    try (CloseableHttpResponse response = httpClient.execute(request)) {
	    	
	    	// Get HttpResponse Status
	    	int responseStatusCoderesponse = response.getStatusLine().getStatusCode();
	    	
	    	// Assert expected result code
	    	assertEquals("The response status is " + responseStatusCoderesponse, 200, responseStatusCoderesponse);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	// Assert expected result by mapping to custom entity
            	Arrays.asList(mapper.readValue(EntityUtils.toString(entity), Pet[].class));
            }

        }
	}
	
	@When("user create new pet")
	public void user_create_new_pet() {
		HttpPost request = new HttpPost(sharedData.getTargetUrl() + "pet");
		
		// Create new pet
		Pet pet = new Pet();
		pet.setName("doogie");
		
		Category category = new Category();
		category.setId(1001);
		category.setName("Animal");
		pet.setCategory(category);
		
		List<String> photoUrls = new ArrayList<>();
		photoUrls.add("img/test/dog.jpeg");
		photoUrls.add("img/test/dog1.jpeg");
		pet.setPhotoUrls(photoUrls);
		
		List<Tag> tags = new ArrayList<>();
		Tag tag = new Tag();
		tag.setId(0);
		tag.setName("/newPetAdded");
		tags.add(tag);
		pet.setTags(tags);
		
		pet.setStatus(PetStatus.AVAILABLE);
		
		try {
			String jsonBody = mapper.writeValueAsString(pet);
			StringEntity body = new StringEntity(jsonBody);
			request.setEntity(body);
			
			request.addHeader("content-type", "application/json");
			
			HttpResponse response = httpClient.execute(request);
			
			// Get HttpResponse Status
	    	int responseStatusCoderesponse = response.getStatusLine().getStatusCode();
	    	
	    	// Assert expected result code
	    	assertEquals("The new Pet is added", 200, responseStatusCoderesponse);
	    	
	    	HttpEntity entity = response.getEntity();
            if (entity != null) {
            	// Assert expected result
            	Pet newPet = mapper.readValue(EntityUtils.toString(entity), Pet.class);
            	sharedData.setPet(newPet);
            }

		} catch (IOException ioe) {
			LOG.debug("A exception has ocurred: " + ioe);
		}
		
	}
	
	@When("user update the created pet")
	public void user_update_the_created_pet() {
		Pet createdPet = sharedData.getPet();		
		createdPet.setStatus(PetStatus.SOLD);
		
		HttpPut request = new HttpPut(sharedData.getTargetUrl() + "pet");
		
		try {
			String jsonBody = mapper.writeValueAsString(createdPet);
			StringEntity body = new StringEntity(jsonBody);
			request.setEntity(body);
			
			request.addHeader("content-type", "application/json");
			
			HttpResponse response = httpClient.execute(request);
			
			// Get HttpResponse Status
	    	int responseStatusCoderesponse = response.getStatusLine().getStatusCode();
	    	
	    	// Assert expected result code
	    	assertEquals("The pet is updated", 200, responseStatusCoderesponse);
	    	
	    	HttpEntity entity = response.getEntity();
            if (entity != null) {
            	// Assert expected result
            	Pet newPet = mapper.readValue(EntityUtils.toString(entity), Pet.class);
            	sharedData.setPet(newPet);
            }

		} catch (IOException ioe) {
			LOG.debug("A exception has ocurred: " + ioe);
		}
	}
	
	@When("user delete the created pet")
	public void user_delete_the_created_pet() {
		Pet createdPet = sharedData.getPet();
		HttpDelete request = new HttpDelete(sharedData.getTargetUrl() + "pet/" + createdPet.getId());
		
		try {
			HttpResponse response = httpClient.execute(request);
			
			// Get HttpResponse Status
	    	int responseStatusCoderesponse = response.getStatusLine().getStatusCode();
	    	
	    	// Assert expected result code
	    	assertEquals("The pet is deleted", 200, responseStatusCoderesponse);

		} catch (IOException ioe) {
			LOG.debug("A exception has ocurred: " + ioe);
		}
	}
}
