package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    // SpringBootWebSecurityConfiguration sınıfı incele
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).permitAll()); // bütün requestlere izin ver.


//        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).denyAll()); // bütün requestleri reddeder.
//        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
//        return (SecurityFilterChain)http.build();


        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/about/**").permitAll() // alt route'lar dahil
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/assets/**").permitAll() // assets klasöründeki dosyalar için
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()  // Diğer tüm URL'lere kimlik doğrulama gerekiyor
        ); // sadece belirtilen requestlere authentication olmadan erişim izni veriyoruz. diğerlerine authentication gerekiyor.
        // assets/** ile assets klasöründeki bütün dosyalara izin veriyoruz. css ve js dosyaları için yoksa onlarıda korur


        http.csrf(csrf -> csrf.disable()); // CSRF korumasını devre dışı bırakıyoruz. (Spring Security 5.0'dan itibaren varsayılan olarak etkinleştirilmiştir)

        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard",false)
                .failureUrl("/login?error=true")
                ); // login sayfasında formu oluştur ve bu form / login action olarak ayarlanır form post olarak da ayarlanır post edildiğinde login işlemi spring security kendiliğinden gerçekleştirecektir. Burada ayarlandığı gibi başarılı bir şekilde gerçekleşirse dashboarda gönderecektir. alwayUse parametresi false yapılırsa kullanıcı erişmek istediği fakat authenticate gerektiği için erişemediği sayfaya giriş yaptığında yönlendirilir. true olursa her zaman dashboarda yönlendirir. yani login sayfasına girmeden önce hangi sayfaya gitmek istiyorsa oraya yönlendirir. (defaultSuccessUrl("/dashboard",false) gibi yazarsan, kullanıcı önce başka bir sayfaya gitmek istemişse, oraya yönlendirilir.)

        http.logout(logout -> logout
                .logoutUrl("/logout")                     // Kullanıcının logout olacağı endpoint (default: /logout)
                .logoutSuccessUrl("/login?logout=true")         // Başarılı çıkıştan sonra yönlendirilecek URL
                .invalidateHttpSession(true)               // Oturum verilerini temizler
               .clearAuthentication(true)                 // Authentication bilgisini temizler
                .deleteCookies("JSESSIONID")                // İstersen cookie de silersin
        );
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }
    //Eğer true vermezsen (yani .defaultSuccessUrl("/home") gibi yazarsan), defaultSuccessUrl("/dashboard",false)
//                kullanıcı önce başka bir sayfaya gitmek istemişse, oraya yönlendirilir.


    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}

/*
CSRF (Cross-Site Request Forgery) Nedir?
CSRF (Cross-Site Request Forgery), Türkçesiyle Siteler Arası İstek Sahteciliği, bir web güvenlik açığıdır.
Kullanıcının tarayıcısındaki oturum bilgilerini kullanarak, kullanıcının haberi olmadan kötü niyetli bir isteğin gönderilmesidir.

Biraz açarsak:

Sen bir siteye giriş yaptın (örneğin bankanın sitesine) ve oturumun açık.

Bir kötü niyetli siteye de giriş yaparsan (veya bir zararlı linke tıklarsan), o kötü niyetli site, tarayıcında açık olan oturum bilgilerini kullanarak senin adına bankaya istek gönderebilir.

Mesela sen farkında olmadan para transferi yapabilir, şifre değiştirebilir.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Bu saldırı genellikle şu şekilde işler:

Tarayıcın çerezlerde (cookie) oturum bilgisini tutar.

Başka bir site, senin tarayıcını kullanarak bu oturumu kullanır.

Çünkü tarayıcı, istek atarken çerezleri otomatik olarak gönderir.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CSRF'den korunmak için:

Spring Security ve diğer modern frameworkler otomatik olarak koruma sağlar.
Bunun için CSRF token denen bir yöntem kullanılır:

Sunucu, her formla birlikte gizli bir token (anahtar) üretir ve sayfaya ekler.

Form gönderildiğinde bu token da gönderilir.

Sunucu bu tokenı doğrular.

Eğer token yoksa veya yanlışsa isteği reddeder.

Böylece kötü niyetli bir site, geçerli bir token bilmediği için saldırı yapamaz.
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Spring'de CSRF:
Spring Security varsayılan olarak CSRF korumasını açık tutar.

Eğer bir API geliştiriyorsan veya sadece REST çalışıyorsan, CSRF'yi kapatman gerekebilir, çünkü API'lerde genellikle oturum kullanılmaz.

*/
