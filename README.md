<div align="center">
<h1>TURKCELL BOOTCAMP RENT A CAR PROJEMİZ</h1>
<br>
<br>
<img src=https://github.com/emrealkan884/rent-a-car-microservices/blob/master/Untitled_Diagram.drawio_1.png height=550 width=900><br><br>

  
</div>
<h3>Projemiz servisler localde, Veritabaları ve Kafka  docker üzerinde çalıştırdığımız zaman sorunsuz çalışıyor ve isteklerimizi yerine getiriyor.<h3>

### Projemizin Endpoint'lerini Görüntülemek İçin Linkler :
- car-service'in Endpoint'leri için =  localhost:6002/swagger-ui/index.html
- rantal-service'in Endpoint'leri için =  localhost:6001/swagger-ui/index.html
- customer-service'in Endpoint'leri için =  localhost:6003/swagger-ui/index.html



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
