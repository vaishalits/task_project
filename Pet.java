package task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Pet {
	public static void main(String[] args) {
		
		// API testing with RestAssured
        // Get pet by ID
        RequestSpecification request = RestAssured.given();
        Response response1 = request.get("https://petstore.swagger.io/v2/pet/1");
        String responseBody1 = response1.getBody().asString();
        System.out.println("Response 1: " + responseBody1);

        // Find pets by status
        Response response2 = request.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
        String responseBody2 = response2.getBody().asString();
        System.out.println("Response 2: " + responseBody2);

        // Add a new pet to the store
        String requestBody = "{\"id\": 0,\"category\": {\"id\": 0,\"name\": \"string\"},\"name\": \"doggie\"," +
                "\"photoUrls\": [\"string\"],\"tags\": [{\"id\": 0,\"name\": \"string\"}],\"status\": \"available\"}";
        Response response3 = request.body(requestBody).post("https://petstore.swagger.io/v2/pet");
        String responseBody3 = response3.getBody().asString();
        System.out.println("Response 3: " + responseBody3);

     
	}



}
