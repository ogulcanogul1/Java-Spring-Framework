package com.eazybytes.eazyschool.controllers;

import com.eazybytes.eazyschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @RequestMapping("holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal, Model model) {

        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
        );

        Holiday.Type[] types = Holiday.Type.values();

        for (Holiday.Type type : types)
        {
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }

    @RequestMapping("holidays/{display}")
    public String displayHolidaysWithPathVariable(@PathVariable String display, Model model)
    {
        if(display.equals("all") && display != null)
        {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        }
        else if(display.equals("festival") && display != null)
            model.addAttribute("festival", true);
        else if(display.equals("federal") && display != null)
            model.addAttribute("federal", true);

        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
        );

        Holiday.Type[] types = Holiday.Type.values();

        for (Holiday.Type type : types)
        {
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";

    }
}

// th:href="@{/holidays(festival=true, federal=true)}" // bu şekilde URL'ye festival ve federal query parametrelerini ekleyerek yönlendirme yapar.

/*
Query String
// http://localhost:8080/products?order=popular
// http://localhost:8080/products?order=popular&filter=new

Path Variable
// http://localhost:8080/products/popular
// http://localhost:8080/products/popular/new
 */

/*
* @RequestParams

1. name:
Açıklama: name parametresi, HTTP isteğindeki parametre adını belirtir. Yani, formdan veya query string'den gelen belirli bir parametreyi tanımlar.

Örnek: Eğer URL'de bir parametre olarak name gönderiyorsanız, o zaman @RequestParam("name") ile bu parametreyi alırsınız.
------------------------------------------------------------------------------------------------
2. required:
Açıklama: required parametresi, parametrenin zorunlu olup olmadığını belirtir. Varsayılan olarak true'dur, yani bu parametre gönderilmek zorundadır. Eğer parametre eksikse, Spring bir hata döndürecektir. Eğer isteğinizde parametre opsiyonelse, required = false kullanabilirsiniz.
------------------------------------------------------------------------------------------------
3. value:
Açıklama: value parametresi, name parametresiyle aynıdır. İki parametre arasında temel fark, value genellikle daha yaygın olarak kullanılır, çünkü @RequestParam kullanıldığında parametreyi belirtmek için value parametresi daha yaygındır.
------------------------------------------------------------------------------------------------
4. defaultValue:
Açıklama: defaultValue parametresi, gönderilen parametre yoksa veya null ise, bu parametre için bir varsayılan değer sağlar. Bu, parametrenin opsiyonel olmasını sağlar ve bir değerin eksik olması durumunda sistemin bir şeyle çalışmasını garanti eder.
------------------------------------------------------------------------------------------------

*/


