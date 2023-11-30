<div align="center">
<h1>TURKCELL BOOTCAMP RENT A CAR PROJEMİZ</h1>
<br>
<br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Untitled_Diagram.drawio_1.png height=550 width=900><br><br>

  
</div>
<h3>Projemiz tamamen docker uzerinde calisabilir halde. Discovery Service'imiz Eureka Server'i kullaniyor ve Api Gateway projede dahil.<h3>

<div align="center">

Eureka = localhost:8761 <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(51).png height=550 width=900><br><br>

 Car Service =  localhost:6002/swagger-ui/index.html <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(52).png height=550 width=900><br><br>

 Rental Service =  localhost:6001/swagger-ui/index.html <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(63).png height=550 width=900><br><br>

 Customer Service =  localhost:6003/swagger-ui/index.html <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(53).png height=550 width=900><br><br>

 Docker <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(56).png height=550 width=900><br><br>

 Keycloak = localhost=8080 <br><br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/images/Screenshot%20(64).png height=550 width=900><br><br>
</div>

## Servislerinin Gerçekleştirdiği İşlemler

### car-service:
- Araç ekleme, güncelleme, silme ve listeleme işlemlerini yapıyor.
- Araçlar için Cloudinary kullanarak resim ekleyebiliyoruz.

### costumer-service:
- Müşterilerin kaydını yapıyor.
- Müşteriler bakiye ekleyebiliyor ve çekebiliyor.

### rental-service:
- Kiralama işlemi yapıyor. 
- Kiralama işlemi yapılırken kiralanacak aracın durumuna, aracın günlük fiyatına ve kiralayacak müşterinin bakiyesine bakılıyor. Kiralanacak aracın durumu 'false' veya günlük kiralama ücreti müşterinin bakiyesinden büyükse 'Araç kiralamaya uygun değildir' dönüyor. Diğer türlü araç kiralanıyor.
- Aracın durumu 'true' dan 'false' a dönüyor.
- Aracın kiralama ücreti müşterinin bakiyenden düşüyor ve müşterinin güncel bakiyesi PostgreSQL'deki customer-service tablosunda güncelleniyor
- Kiralama ile ilgili mesaj notification-service üzerinden görüntüleniyor. 

