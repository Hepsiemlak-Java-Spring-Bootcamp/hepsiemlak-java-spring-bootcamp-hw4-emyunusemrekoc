# Veritabanları
## Redis Nedir? Hangi Durumlarda Kullanılır?

Redis, geliştiriciler tarafından en çok kullanılan ve bilinen NoSQL veritabanlarından birisidir. Redis, açık kaynaktır ve kaynak kodlarına GitHub üzerinden erişilebilmektedir. C dili ile yazıldığı için yüksek performanslı sonuçlar vermektedir. Linux ve türevi işletim sistemleri tarafından desteklenmekte fakat Windows tarafı için resmi bir destek olmasa da community tarafından desteklenmektedir.

Redis günümüz sistemlerinde en çok kullanılan anahtar-değer veritabanıdır ve genellikle caching, session yönetimi, pub/sub, message broker amacıyla kullanılmaktadır.

### Redis’in Avantajları

#### Yüksek Performans
Redis, verileri disklerde (HDD veya SSD) tutan veri tabanlarının akside bellek (RAM) üzerinde tutar bu sayede disklere erişim ihtiyacını ortadan kaldırarak gecikmeleri, I/O bağlantılarını önler ve daha az CPU kullanan basit algoritmalar ile verilere erişir.
#### In-Memory Veri Yapıları
Redis verileri bellek üzerinde key,value çifti olarak tutmaktadır, burada herbir anahtara denk gelen değerler farklı veri yapılarında tutulabilmektedir. Bu veri yapıları; String, List, Hash, Set, Sorted Set, Bitmaps, HyperLogLogs, Geospatial Indexes

Redis kullanılarak neredeyse her türlü veri bellekte saklanabilir.
#### Replication
Redis, master-slave mimarisini kullanır, master genel olarak yazma işlemlerini yapar ve slave dediğimiz yapılar da master’in birer kopyasıdır, master güncellendikçe ona bağlı bütün slave’ler de güncellenir. Burada master’da oluşacak herhangi bir çökmede, hatada direkt bir slave master olarak seçilir ve sistem çalışmaya devam eder.
#### Persistance (Veri Kalıcılığı)
Redis’te verilerin RAM üzerinde saklandığından bahsettik, olası bir elektrik kesintisi, sunucu kapanması gibi durumlarda veriler silinecektir. Redis bize iki yöntem sunmaktadır verinin kalıcılığını sağlamak için. Bunlar; point-in-time Snapshots ve Append Only File (AOF).

Snapshots yönteminde belirli zaman aralıkları ile RAM üzerindeki verinin kaydı, kopyası diske kayıt edilir bu sayede olası bir elektrik kesintisi gibi durumlarda disk üzerinden verilere tekrar geri dönülebilir.

Append Only File yönteminde ise her değişikliği dosyanın sonuna yazarak oluşan veri değişikliklerinin kaydını tutar.
#### Çoklu Dil Desteği

Redis birçok dil tarafından desteklenmektedir, bunlar; Java, Python, PHP, C, C ++, C #, JavaScript, Node.js, Ruby, R, Go gibi dillerdir ve bunların yanı sıra daha fazla da dil bulunmaktadır.
### Redis’in Bazı Kullanım Senaryoları
#### Caching (Önbellek) Mekanizması
Sıkça kullanılan verilerimizi sürekli veritabanına ya da diğer kaynaklara gidip çağırmak yerine ön belleğe almak performans açısından olumlu bir katkı sağlayacaktır. Dağıtık mimaride çalışabilen Redis, dağıtık cache(distributed caching) yönetimi için birçok uygulamada kullanılmaktadır.
#### Session Yönetimi
Uygulamalarımızı kullanırken kullanıcılara ya da diğer yapılara ait verilerimizi sayfalar arasında taşımak için session’dan sıkça yararlanmaktayız fakat uygulamamız büyüdükçe bu verilerin tutulması artan bellek alanına neden olmaktadır. Redis; sosyal medya, e-ticaret uygulamaları, oyun gibi alanlarda session bilgilerinin tutulmasında da rol almaktadır.
#### Pub/Sub
Redis, pub/sub işlevini destekleyen komutlara da sahiptir ve Redis’in broadcast yayını yapmasına olanak sağlar. Bu, mesajı tek bir istemcinin bir kanala bağlı diğer birçok istemciye yayınlamasına olanak tanır.
#### Queues (Kuyruklar)
Redis, gerçekleşmesi zaman alacak işleri bir kuyruk yapısına alınmasını ve daha sonradan işlenmesini destekler.

## Couchbase Nedir?

Couchbase Server, yerel bir küme içinde güçlü bir şekilde tutarlı olan bellek öncelikli, dağıtılmış, esnek bir JSON belge veritabanıdır. Couchbase Server ayrıca kümeler arasında nihai tutarlılıkla çapraz veri merkezi çoğaltmayı da destekler.

Couchbase Lite, çevrimdışı çalışan ve çevrimiçi olduğunda Couchbase Sync Ağ Geçidi ile senkronize olan yerleşik bir mobil veritabanıdır. Senkron Ağ Geçidi, Couchbase Sunucusunun yanı sıra birden çok Couchbase Lite örneği ile senkronize olur.

Couchbase Server, şirket içinde, bulutta, Kubernetes'te veya hibrit konfigürasyonlarda devreye alınabilir. Hem açık kaynak hem de kurumsal sürümlerde gelir.

Couchbase Server sorgu dili N1QL, analitik uzantılarıyla JSON belge veritabanları için tasarlanmış bir SQL üst kümesidir. Couchbase ayrıca anahtar-değer veri erişimini ve tam metin aramayı da destekler.

### Couchbase Sunucu Mimarisi
Couchbase Server, veri hizmeti, dizin hizmeti, sorgu hizmeti, güvenlik, çoğaltma, arama, olay oluşturma, analitik ve yönetim olmak üzere birden çok rol gerçekleştirir. Bu hizmetlerin her biri bir veya daha fazla düğümde çalıştırılabilir.

Couchbase Server, üç temel ilkeye göre tasarlanmıştır: bellek ve ağ merkezli mimari, iş yükü yalıtımı ve her şeye eşzamansız yaklaşım.

Yazılar belleğe kaydedilir, ardından diske kaydedilir ve okuma veya yazma işlemleri engellenmeden eşzamansız olarak dizine eklenir. En çok kullanılan veriler ve dizinler, hızlı okumalar için şeffaf bir şekilde bellekte tutulur. Bu yoğun bellek kullanımı, Couchbase'in RAM gereksinimlerini artırsa da gecikme ve iş hacmi için iyidir.

Couchbase Server, hizmetlerinin her birini daha verimli hale getirmek için bağımsız olarak ölçeklendirebilir. Sorgu hizmeti daha fazla CPU kaynağından yararlanabilir, dizin hizmeti SSD'leri kullanabilir ve veri hizmeti daha fazla RAM kullanabilir. Couchbase bu çok boyutlu ölçeklendirmeyi (MDS) çağırır ve Couchbase Sunucusunun ayırt edici özelliklerinden biridir.

Eşzamansız işlemler, Couchbase Sunucusunun yazma, okuma veya sorguları engellemekten kaçınmasına yardımcı olur. Geliştirici, gerektiğinde gecikmeye karşı dayanıklılığı ve tutarlılığı dengeleyebilir.

Couchbase JSON veri modeli hem temel hem de karmaşık veri türlerini destekler: sayılar, dizeler, iç içe geçmiş nesneler ve diziler. Normalleştirilmiş veya normal olmayan belgeler oluşturabilirsiniz. Couchbase Server, şemaları gerektirmez ve hatta desteklemez. Buna karşılık, MongoDB şemalara ihtiyaç duymaz, ancak geliştirici seçerse bunları destekleyebilir ve uygulayabilir.

Daha sonra daha ayrıntılı olarak tartışacağım üzere, Couchbase Server belgelerine dört mekanizma üzerinden erişebilirsiniz: anahtar-değer, SQL tabanlı sorgular, tam metin arama ve JavaScript olay oluşturma. JSON belgelerinizin alt belgeleri veya dizileri varsa, tüm belgeyi aktarmanıza ve ayrıştırmanıza gerek kalmadan bunlara doğrudan yol ifadelerini kullanarak erişebilirsiniz. Olay modeli, veri değişiklikleri ( OnUpdate) veya zamanlayıcıları tetikleyebilir . Ek olarak, Couchbase Mobile ile senkronizasyon yoluyla Couchbase Server belgelerine erişebilirsiniz.

Couchbase Sunucusu, kovalar, vBucketler, düğümler ve kümeler halinde düzenlenmiştir. Paketler JSON belgelerini tutar. vBuckets, temelde düğümler arasında otomatik olarak dağıtılan parçalardır. Düğümler, Couchbase Sunucusunun tekli örneklerini barındıran fiziksel veya sanal makinelerdir. Kümeler, düğüm gruplarıdır. Bir kümedeki düğümler arasında senkronize çoğaltma gerçekleşir.

## MongoDB Nedir?
MongoDB, tüm dünyada bilişimciler tarafından bilinen oldukça popüler bir hale gelmiş NoSQL türünde bir uygulamadır. Tamamen modern tabanlı olarak inşa edilmiş olan MongoDB, bulut sistemli veri tabanları için kullanımı gerçekleştirilmiş olan ve oldukça etkili denebilecek bir sistem parçasıdır. Gelecekte, kullanımı kolay ve fazlası ile fonksiyonel bir web sitesine sahip olmak isteyecek kişiler, MongoDB’nin getirdiği kolaylık ve avantajlarından ötürü bu veri sistemini kullanmak isteyeceklerdir.

MongoDB, noSQL ile hiç bir ilişkisi bulunmayan, tamamen açık kaynaklı olan bir veri tabanı yönetim sistemi aracıdır. Bu sistem, veri tabanında ki önem arz eden tüm verileri bir diskte depolar ve yönetir. Verileri saklamak adına kullanıma sunulan bu sistemde, JSON’a benzeyen BSON kaynağı kullanılmaktadır.

NoSQL veri tabanı, MySQL ile karşılaştırıldığında kullanılan verilerin çoğunlukta olduğu sistemler adına çok daha ideal bir kullanım ortaya koyabilmektedir. Bünyesinde çok daha fazla avantaj barındırmaktadır. Buna neden olan olgu ise veri tabanının oluşturulmak istenen tablolara ve satırlara herhangi bir problem oluşturmadan imkân vermemesidir. Bunun yerine nesne odaklı, koleksiyon ve belgelere dayalı olarak çalışmaktadır. MongoDB, bir web sitesi için çok önemli bir yere sahiptir. Eğer sisteme kullanılacak olan büyük verilerin işlenmesini talep ediyorsanız, çok yönlü bir seçenek ile istediğiniz şekilde ölçeklendirebilirsiniz.

### MongoDB'nin Avantajları
MongoDB oldukça faydalı bir yönetim aracıdır. MongoDB kullananlar, sistemin sağlamış olduğu avantajları araştırarak ve görerek sisteme giriş yapmaya başlarlar. Bu sayede ise istediklerine çok kısa bir süre içerisinde ulaşmış olurlar. Bu yönetim sisteminin sağlamış olduğu çok önemli 4 fayda bulunmaktadır. Bular sırası ile şöyledir;

Diğer muadillerine göre çok daha kolay bir kurulum özelliği sağlamaktadır.
Hiç bir şekilde şemaya gerek duymayan bir veri tabanı sistemine sahip olacaksınız.
Kullanım açısından çok faydalı ve yüksek performans aracıdır.
Genel olarak bilişim sektöründe maliyetler düşünüldüğünde, diğer araçlara göre oldukça uygun bir maliyeti olan veri tabanı yönetim sisteminin bütünüdür.

### MongoDB'nin Özellikleri
- Kullanımı tamamen ücretsiz bir veri tabanı türüdür.
- Döküman odaklı çalışan bu sistem, muadilleri gibi standart bir veri formatına bağımlı olmadan çalıştığından sizler için kolaylık sağlar.
- Çok daha fazla sorgulama esnekliğine sahip olduğu için bu sistemde, bir alana göre belli aralıklarla veya regular expression kullanılmak adına arama yapabilme fırsatı fazlasıyla sunulmaktadır. Böylece yapılmış olan aramalarda istenilen field’ların döndürülebilmesi olanağı daha rahat bir şekilde sağlanabilmektedir.
- Load balancing, birçok MongoDb instance arasında yatay bir ölçekleme işlevi yapabildiği için herhangi bir donanımsal arıza tespit edildiğinde aktif olarak çalışan instance üzerinden çalışmaya devam eder.
- Replikasyon imkânıyla birlikte birden fazla MongoDB instance oluşturmaya imkân sunarak, kullanıcısına yüksek kalitede kullanabilirlik fırsatı sunar.
- Indexleme, database sistemlerinde performans açısından çok önemli sayılabilecek noktalardan biri olarak kabul edilir. Bu özelliği sayesinde kullanıcılar hızlı bir şekilde sorgu sonuçlarına varabilmektedir.
- Orijinal olan tüm verilerin birden fazla kopyasını oluşturabilir. Bu sayede de veri kaybının önüne geçerek kullanılabilirliğini katlamaktadır.
- Verilerin toplu bir şekilde işlenmesine olanak sağlar.
- Güncel programlama dillerinin neredeyse bir çoğu için kolaylık olan driver destek imkânını sunar.

### MongoDB Nasıl Kullanılır?
MongoDB kullanıcı için gerçekten de çok faydalı bir araç olma özelliği taşır. Farklı amaçlarla kullanabilmek için MongoDB, birçok komut içirme özelliğine bünyesinde barındırır. MongoDB’de en çok kullanılan komutlar ise şu şekilde çalıştırılır;

- Mongo: MongoDB veri tabanı uygulamasını harekete geçirmek için gerekli olan komuttur. Komut ekranı açıldıktan sonra yapılacak işlem ”mongo” komutu yazılarak, MongoDB’nin basit bir şekilde çalıştırılması olacaktır.
- Show databases: Sistemin içerisinde bulunan veri tabanı listelerini görmek için show databases komutu kullanılmaktadır. Bu komutu girdikten sonra sistem içerisindeki veri tabanları ve boyutlarının birikmiş olduğu liste kolay bir şekilde karşımıza çıkmaktadır.
- Use: Yeni bir veri tabanı oluşturmak adına kullanılan bu komutta, ”use YeniVeriTabanı” şeklinde komut yazılırsa ve sistem içerisinde YeniVeriTabanı adıyla bir veri tabanı yok ise bu veri tabanı üzerinde oluşturulmaktadır. Bu işlemi tamamladıktan sonra ”switbhed to db YeniVeriTabanı ” olarak bir mesaj ekranı ekranda belirir. Eğer ki ”use” komutunu, elde bulunan bir veri tabanı ismiyle kullanılırsa, bu veri tabanına direkt geçiş sağlanabilir.
- dropDatabase(): Mevcut olan komutu silmek için kullanılan bu komut, ”use VeriTabanınınAdı” ile veri tabanı seçilirse daha sonra ”db dropDatabase()” komutu oluşur ve mevcut olan veri tabanı sistemden silinmeye başlar.
- createCollection(”yenitablo): Bu komut kullanılır ise veri tabanı içerisinde yeni bir tablo oluşturulur. İlk önce ”use VeriTabanınınAdı” komutu çalıştırılması gerekir. Daha sonra ”db createCollection(tabloadı)” komutu çalıştırılmak üzere yeni bir tablo oluşturulmaktadır.
- Show collections: Veri tabanı içerisindeki tabloları listelemek adına kullanılan komuttur.
- tabloismi.drop(): Veri tabanı içerisinde oluşturulmuş olan herhangi bir tabloyu silmek için bu komutu kullanablirsiniz.
- tabloadı.insert(): Bu komut sayesinde veri tabanı içerisindeki tabloya veri ekleme yapılabilinir.

## PostgreSQL Nedir? Ne İşe Yarar?
PostgreSQL, bir veritabanı yönetim sistemidir. Güçlü özelliklere ve avantajlara sahiptir. Aynı zamanda açık kaynaklıdır ve ücretsizdir. Bu sistemin ortaya çıkışı 1986 yılına denk geliyor. Berkley Kaliforniya Üniversitesi’nde geliştirilen POSTGRES projesinin parçasıdır. Bu projenin parçası olarak ortaya çıkan PostgreSQL sisteminin ana platformu gelişimi 30 yıla yakın bir zamandır sürmektedir. En başta DARPA (Defense Advanced Research Project Agency) sponsorluğunda gelişimini sürdüren bu projeye sonrasında NSF (National Science Foundation), ARO (Army Research Office) ve ESL olmak üzere çeşitli kuruluşların sponsorluğunu almıştır.

PostreSQL sistemi, günümüzde kullanıcılara sunmuş olduğu kaliteli veri mimarisi, başarılı özellik seti, doğruluğu, esnekliği ve açık kaynak olması ile yazılım sektöründe en fazla kabul gören veri yönetim sistemlerinden birisidir. Pek çok işletim sistemi (Windows, Linux, MacOS) tarafından da desteklenmektedir.

Açık kaynaklı olma avantajıyla yayına devam eden bu sistem, 1996 senesinden bugüne dek hem bağımsız hem de yalnız gönüllü insanların çabaları ile geliştirilmiştir. PostgreSQL, özgür yazılım felsefesinin ürünlerinden birisidir.
### PostgreSQL Ne İşe Yarar?
PostgreSQL sistemi, hem yöneticilerin hem de proje yöneticilerinin büyüklü küçüklü fark etmeden geliştirdikleri yazılımlar için kullandıkları bir sistemdir. PostgreSQL’in verileri yönetmesine ve hatasız bir şekilde -hata payı olmadan- depolamasına yarar. Diğer pek çok türevinin aksine PostgreSQL sistemi, açık kaynaklı olmasından dolayı tamamıyla uyarlanabilir bir yapıdadır. Örnek verelim; geliştiricilerin ya da yöneticilerin kendi veri tiplerini belirlemek istediklerinde özel fonksiyonlar oluşturarak veri tabanını derleme işini tekrar etmelerine gerek kalmaz. Buna gerek kalmadan kullanabilmeleri mümkündür.

PostgreSQL sistemi SQL standartında bir sistem olduğu için hedefi yüksek performans sunmaktır. SQL’in öne çıkan pek çok özelliğini destekler. Buna rağmen birtakım syntax fonksiyonlarında bazı farklılıklar görmek mümkündür. 2017 senesinde yayınlanmış olan PostrgreSQL’in 10. sürümü ile beraber SQL’in 179 öne çıkan özelliğinden 160 tanesi sunulmuştur.
### Temel Özellikleri
- Geliştiricilerin ya da proje yöneticilerinin uygulama oluşturmalarına yardımcıdır.
- Veri bütünlüğünü korur. Böylece hata payının olmadığı bir ortam oluşturur.
- Pek çok platform ile uyumludur.
- Sofistike kilitleme mekanizması sunar.
- Eşzamanlılık kontrol desteği sunar.
- SQL standartları ile uyumludur.
### Avantajları
- PostgreSQL sistemi, dinamizmini koruyan web siteleri ile web uygulamalarını LAMP yığını seçeneği ile çalıştırabilir.
- PostgreSQL’in yazmayı durdurmayan günlüğü, onun hata yapma olasılığını yok eder.
- Bu sistemin açık kaynak kodlu olması özgür bir yazılım olmasını sağlar. Bu da sistemi iş gerekliliklerine göre kullanmak, değiştirmek ve uygulamak gibi özgürlükler anlamına gelir.
- PostgreSQL, coğrafi nesneleri de destekler. Bu sayede coğrafi bilgi içeren sistemler ve konum tabanlı olan servisler için kullanılabilir.
- Öğrenmesi ve kullanılması için çok detaylı eğitimlere gerek yoktur.
- Bakım yönetimi de hem kurumsal hem de gömülü kullanımlar için düşüktür.
### Dezavantajları
PostgreSQL herhangi bir kuruluş bünyesinde değildir. Bundan dolayı kendini duyurmakta diğer DBMS sistemleri ile benzerlikleri olmasına rağmen güçlük çekmiştir.
PostgreQL uyumluluk konusuna odaklandığı için hız iyileştirmesi adına yapılan tüm değişiklikler MySQL’e nazaran daha çok çalışma gerektirir.
Açık kaynaklı pek çok uygulama MySQL’i desteklerken PostgreSQL’li desteklemiyor olabilir.
Performans ölçümünde PostgreSQL, MySQL’e göre daha yavaştır.
Özet olarak, PostgreSQL kurumsal sınıfta açık kaynaklı olan bir veritabanı yönetim sistemidir. Michael Stonebraker adlı bir bilgisayar bilimci profesör ile profesörün ekibi tarafından yaratılmıştır. Bu sistem, bütün ara katman yazılımları ile ana dilleri kullanmakta olan çeşitli platformlar ile uyumlu çalışır. Hem açık kaynaklı hem de ücretsiz bir yazılımdır, hiçbir ödeme yapmadan bu hizmetten yararlanılabilir. Açık kaynaklı ve ücretsiz olması onu özgür bir yazılım yapar çünkü önceliği maddiyat değildir. PostgreSQL, dinamizmini koruyan web siteleri ve web uygulamalarını LAMP yığına çalıştırabilir. Tüm bunlarla beraber PostgreSQL, JSON verilerini destekler diyebiliriz. Bir kuruluşa özgü değildir. Uygulama alanları geniştir. PostgreSQL, finans endüstrisi, üretim, devlet CBS verileri, web teknolojisi, NoSQL ile bilimsel veri toplama ve çalışmaları alanlarında sık olarak kullanılmakta olan bir yazılımdır.