package com.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Component
@Aspect
//@Order(2) // AOP sıralamasını belirler. 1. sırada olan önce çalışır.
public class LoggerAspect {

    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());
    @Around("execution(* com.example.services.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable
    {
//        logger.info();
        Instant start = Instant.now();
        joinPoint.proceed();
        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start,finish).toMillis();
        logger.info("Method executed in: " + timeElapsed + " milliseconds");
        logger.info(joinPoint.getSignature().toString() + " method execution end");
    }


}
/*execution(modifier? return-type declaring-type? method-name(param) throws?) => execution([erişim belirteci] [dönüş türü] [paket ve sınıf adı].[metot adı]([parametreler]))

Genellikle aşağıdaki tip kullanılır:
execution(<dönüş tipi> <paket ve sınıf adı>.<metot adı>(<parametreler>))

Örnek İncelemesi :
@Around("execution(* com.example.services.*.*(..))")

* =>	Herhangi bir dönüş tipi (void, int, String vs.)

com.example.services.* =>	com.example.services paketindeki tüm sınıflar

* =>	Bu sınıflardaki tüm metodlar

(..) => Herhangi sayıda ve türde parametre (0 ya da daha fazla)

--------------------------------------------------------------------------------

 Sık kullanılan kalıplar ve ne işe yaradıkları:

İfade	Anlamı
* =>	Her şey anlamında joker karakter (dönüş tipi, metot adı vb. için)
.. =>	Herhangi sayıda parametre (sıfır, bir, ya da daha fazla)
() =>	Parametre yok
(String) =>	Tek parametre ve türü String olmalı
(*, int) =>	İki parametre: biri herhangi tür, diğeri int olmalı

--------------------------------------------------------------------------------
execution(public void com.example.services.UserService.getUserById(int))
UserService sınıfında getUserById(int) metoduna tam hedefli bir AOP uygulanır.

execution(* com.example.controllers.*.*(..))
controllers paketindeki tüm sınıflardaki tüm metodlara AOP uygula (global kullanım).


@Around("execution(* *..MyService.*(..))")
 Herhangi bir paketteki ismi MyService ile biten sınıfın bütün metodlarına uygulanır.

*/

// "execution(* com.example.services.*.*(..))" * erişim belirleyici . larda paketlerin içinde gezmek için com.example.services => paket adı bundan . dendiğinde sınıflara ulaşılır * olarak belirterek herhangi bir sınıf olur. Bir nokta daha eklenerek metotlara ulaşılır oradan * yine girilerek herhangi bir metot anlamı katılır. (..) bu ifade de herhangi bir parametre olabileceğini anlatır.

/*
execution(...) → Belirli metodları hedefler.

@within(...) → Belirli sınıfları hedefler.

@annotation(...) → Belirli anotasyonlara sahip metodları hedefler.

@target(...) → Belirli anotasyona sahip sınıfları hedefler.

@args(...) → Belirli parametre türüne sahip metodları hedefler.

this ve target → Nesne türünü kontrol eder.

bean → Spring Bean'lerine odaklanır.
 */

// ---------------------------------------------------------------------------

//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//@interface LogAspect {
//
//}

//@LogAspect
//public String playMusic(boolean started,String song)
//{
//
//}

//@Around("@annotation(com.example.interfaces.LogAspect)")
//public void logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable
//{
//
//}

