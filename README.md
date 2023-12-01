<div align="center">
<h1>TURKCELL BOOTCAMP RENT A CAR PROJECT</h1>
<br>
<br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Untitled_Diagram.drawio_1.png><br><br>

  
</div>
<h3>Our project is fully workable on docker. Our Discovery Service uses Eureka Server and Api Gateway is included in the project.<h3>

<div align="center">

 Eureka = localhost:8761 <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(51).png ><br><br>

 Car Service =  localhost:6002/swagger-ui/index.html <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(52).png ><br><br>

 Rental Service =  localhost:6001/swagger-ui/index.html <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(63).png ><br><br>

 Customer Service =  localhost:6003/swagger-ui/index.html <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(53).png ><br><br>

 Docker <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(56).png ><br><br>

 Keycloak = localhost:8080 <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(64).png ><br><br>

</div>

### To access other images you can go to the images folder in the project.  <br><br>


## Operations Performed by Services

### car-service:
- All CRUD operation
- We can add images using Cloudinary.

### costumer-service:
- All CRUD operation
- Customers can add and withdraw balances.

### rental-service:
- All CRUD operation
- During the rental process, the status of the vehicle to be rented, the daily price of the vehicle and the balance of the customer to be rented are checked. If the status of the vehicle to be rented is 'false' or the daily rental fee is greater than the customer's balance, 'The vehicle is not suitable for renting' is returned. Otherwise, the vehicle is rented.
- The status of the vehicle changes from 'true' to 'false'.
- The rental price is calculated by multiplying the number of days rented by the daily price and deducted from the customer's balance. The customer's current balance is updated in the customer-service table in PostgreSQL.
- The message about the rental is displayed via notification-service.  

