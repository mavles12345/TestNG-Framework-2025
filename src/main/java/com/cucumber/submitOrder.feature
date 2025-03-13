Feature: submit the order in E commerce application 

#Background: 
	#User is on the browser

Scenario Outline: Submit the product order in Ecom 
	Given User is on the E commerce website application 
	When User is logged with "<username>" and "<Password>" 
	When User is adding the "<product>" into the cart 
	And User checkout the "<product>" and submit the order 
	Then "THANKYOU FOR THE ORDER." message display in the confirmation page 
	
	Examples: 
		|username|Password|product|
		|jackmavles@gmail.com|Selvam@18|ZARA COAT 3|