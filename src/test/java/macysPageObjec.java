import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

public class macysPageObjec {

    private final String ATTR = "[data-validate=";

    Faker fake = new Faker();

    public void setup() {
        Configuration.browserSize = "1920x1080";
        open("https://www.macys.com/");
    }

    public void search(String productName) {
        SelenideElement search = $("#globalSearchInputField");
        search.setValue(productName).pressEnter();
        $("#resultsFoundMessage").shouldHave(text(productName));
    }

    public void addToCart(String productId, String productSize) {
        SelenideElement menuEl = $(By.className("sortableGrid"));
        menuEl.find(By.id(productId)).click();
        $(".details-content").shouldHave(text(("Web ID: ") + (productId)));
        SelenideElement selectSize = $(by("data-el", "sizes"));
        selectSize.shouldHave(text(productSize)).click();
        selectSize.attr("aria-pressed").contains("true");
        $("#bag-add-" + productId).click();
        $("#atbIntCheckout").click();
    }

    public void checkout() {
        $("#guest-checkout").click();
        $(ATTR + "firstName]").setValue(fake.name().firstName());
        $(ATTR + "lastName]").setValue(fake.name().lastName());
        $(ATTR + "address1]").setValue(fake.address().fullAddress());
        $(ATTR + "address2]").setValue(fake.address().fullAddress());
        $(ATTR + "zipCode]").setValue(fake.address().zipCode());
        $(ATTR + "state]").selectOption("Alaska");
        $(ATTR + "city]").setValue(fake.address().city());
        $(ATTR + "phone]").setValue(fake.phoneNumber().phoneNumber());
        $("#rc-shipping-submit").click();

        $("#useEnteredAddress").should(Condition.visible).click();

        $(ATTR +"cardType]").selectOption("Visa");
        $(ATTR + "cardNumber]").setValue(fake.business().creditCardNumber());
        $(ATTR + "expMonth]").selectOption("07");
        $(ATTR + "expYear]").selectOption("2024");
        $(ATTR + "email]").setValue(fake.internet().safeEmailAddress());
        $("#rc-ccdetails-save").click();
    }
}
