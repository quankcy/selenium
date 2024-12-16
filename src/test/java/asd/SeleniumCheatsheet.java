package asd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SeleniumCheatsheet {

    private void cheatsheet() {
        //// Prepare drivers
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();

        //// Utworzenie obiektu driver - otwiera przegladarke
        ChromeDriver driver = new ChromeDriver();

        //// Wlasciwosci drivera
        /// get(String) = driver.navigate().to(URL) = driver.navigate().to(String)
        /*
        "wpisuje" url strony i przechodzi na nia
         */
        driver.get("https://www.wp.pl");
        driver.navigate().to("https://www.wp.pl");
        try {
            driver.navigate().to(new URL("https://www.wp.pl"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        //// Selektor
        By cssSelector = By.cssSelector("");
        By xpathSelector = By.xpath("");

        //// Znajduje webElement - czyli tag html
        WebElement webElement = driver.findElement(cssSelector);

        //// Znajduje liste webElementow
        List<WebElement> webElementsList = driver.findElements(cssSelector);

        //// Iterowanie po liscie
        // petla
        WebElement variableForWebElement;
        for(WebElement iterationWebElement: webElementsList) {
            if( webElement.isDisplayed() ){
                variableForWebElement = iterationWebElement;
                break;
            }
        }
        // stream
        webElementsList.stream()
                .filter( iterationWebElement -> {
                    return iterationWebElement.isDisplayed();
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No web element found")); // wyjscie z optionala - moze byc wykonane na kilka spososbow

        //// Wlasciwosci webElementow

        /// isDisplayed()
        /*
         sprawdza wlasciwosc css, czy jest wyswietlony - nie znaczy to, ze jest widoczny na ekranie, bo
         moze byc poza obszarem wyswietlanym i trzeba zescrollowac do niego
         */
        webElement.isDisplayed();

        /// getAttribute(String)
        /*
        zostalo zamienione na getDomAttribute() - pozwala pobrac atrybut z taga html, np.:
        <input type="password">...</input> ->
         */
        webElement.getDomAttribute("type"); // dla przykladu powyzej zwroci "password"

        /// click()
        /*
        to tak jakbys kliknal myszka, moze kliknac we wszystko, ale tu bardzo wazna rzecz - temat
        jest bardziej skomplikowany. Pamietaj, ze klikasz w DOM, a nie na stronie internetowej, tzn.
        ze czasami moze Ci sie nie udac kliknac w ten element i dostaniesz
        ElementNotInteractableException lub ElementClickInterceptedException bo bedziesz probowac klikac
        w jakas wewnetrzna warstwe strony. Zobacz link, fajnie to obrazuje, ale wycofali juz z firefoxa
        https://firefox-source-docs.mozilla.org/devtools-user/3d_view/index.html
         */
        webElement.click();

        /// clear()
        /*
        Usuwa zawartosc w polu do wpisywania tekstu
         */
        webElement.clear();

        /// sendKeys(String)
        /*
        wpisuje caly tekst do pola tekstowego
         */
        webElement.sendKeys("test");

        ///  getText()
        /*
        pobiera tekst wyswietlany przez tag html: <div>tekst</div> zwroci "tekst"
         */
        webElement.getText();

//        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");

    }

}
