package main;

import com.example.config.ProjectConfig;
import com.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example8 {
    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        VehicleService vehicleService = context.getBean(VehicleService.class);


    vehicleService.makeSound(false);
    vehicleService.powerCalculation(10,0);


    }
}
/*
Aspect Oriented Programming (AOP), yani Yönelimli Programlama, nesne yönelimli programlamanın yetersiz kaldığı bazı çapraz kesen (cross-cutting) konuları daha etkili ve modüler şekilde ele almayı amaçlayan bir programlama paradigmasıdır.

AOP Nedir?
AOP, yazılımda tekrarlayan işlemleri (örneğin loglama, güvenlik, hata yönetimi, cache, yetkilendirme) ayrı birer modül olarak tanımlamamıza olanak tanır. Bu sayede ana iş mantığı kodu bu detaylardan arındırılır.

Aspect =>Bir çapraz kesit ilgisini temsil eder. (örneğin loglama işlemi).

Join Point =>	Aspect’in uygulanabileceği noktalardır (örneğin bir metodun başlangıcı).

Advice => Aspect’in ne zaman ve nasıl çalışacağını belirler (before, after, around).

Pointcut => Hangi join point’lere müdahale edileceğini belirtir.

Weaving =>Aspect’lerin uygulamaya entegre edildiği süreçtir. (Derleme zamanı, yükleme zamanı ya da çalışma zamanı olabilir.)

---------------------------------------------------------------------------------------

@Before => Metot çağrılmadan önce | Metot çalışmadan önce yapılacak işleri tanımlar.

@AfterReturning => Metot başarıyla döndüğünde | Metot hata vermeden tamamlandığında çalışır.

@AfterThrowing => Metot exception (hata) fırlattığında | Metot hata verdiğinde çalışır.

@After =>	Metot her durumda tamamlandığında | Hata olsa da olmasa da çalışır (finally gibi).

@Around =>	Metot öncesi ve sonrası kontrol	Hem öncesinde hem sonrasında işlem yapılabilir | . Metodu kendin çağırman gerekir (proceed() ile).
-----------------------------------------------------------------------------------------
execution(* com.ornek.service.*.*(..))

* (ilk yıldız)

Bu ilk * ifadesi -> dönüş tipi anlamına gelir.

Yani void, int, String, List<String>… ne olursa olsun!

Örnek: void metotAdı(), String getAd(), int hesapla(), hepsi kapsanır.

-----------------------------------------
2. com.ornek.service.*

Bu bölüm → paket ve sınıf adı içindir.

com.ornek.service → hedeflenen paket.

* → bu paketteki herhangi bir sınıf (HelloService, UserService, vb.)

-----------------------------------------
3. * (metot ismi)

Bu * → herhangi bir metot adı.

selamla(), hesapla(), getData(), deleteUser() gibi tüm metotlar dahil.

----------------------------------------
4. (..) (parametre listesi)

Bu (..) → her türden ve her sayıda parametre anlamına gelir.

() → parametre yoksa

(String) → tek parametre

(String, int) → iki parametre

(..) → bunların hepsi
 */



