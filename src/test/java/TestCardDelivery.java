import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCardDelivery {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    String generateDate (int daysToAdd, String pattern){
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldTestV1() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Тест-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("+79787811804");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(3, "dd.MM.yyyy")), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV2() {
        $("[data-test-id='city'] .input__control").setValue("Минск");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Тест-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("+79787811804");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Доставка в выбранный город недоступна")).shouldBe(visible);
        $("[data-test-id='city'] .input__sub")
                .shouldHave(Condition.text("Доставка в выбранный город недоступна"))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV3() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(1, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Тест-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("+79787811804");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='date'] .input__sub")
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен"))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV4() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Теsт-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("+79787811804");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub")
                .shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV5() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Тест-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("++7978781180");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV6() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("");
        $("[data-test-id='phone'] [name='phone']").setValue("+79787811804");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV7() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Тест-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    @Test
    public void shouldTestV8() {
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(Keys.BACK_SPACE);;
        $("[data-test-id='date'] .input__control").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] [name='name']").setValue("Тест-Один Дмитрий");
        $("[data-test-id='phone'] [name='phone']").setValue("+79787811804");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"))
                .shouldBe(Condition.visible);
    }

}

