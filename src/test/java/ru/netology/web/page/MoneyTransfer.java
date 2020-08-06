package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransfer {
    private static SelenideElement transferPage = $(withText("Пополнение карты"));
    private static SelenideElement transferAmmount = $("[data-test-id=amount] input");
    private static SelenideElement numberCardfrom = $("[data-test-id=from] input");
    private static SelenideElement numberCardto = $("[data-test-id=to] input");
    private static SelenideElement transferButton = $(byText("Пополнить"));
    private static SelenideElement cancelButton = $(byText("Отмена"));
    private static SelenideElement errorMessage = $(withText("Ошибка"));

    public static void MoneyTransferVisible() {
        transferPage.shouldBe(Condition.visible);
    }

    public static void setTransferAmmount(int sum) {
        transferAmmount.setValue(String.valueOf(sum));
    }

    public static void setNumberCardfrom(String numberCard) {
        numberCardfrom.setValue(numberCard);
    }

    public static void doTransfer() {
        transferButton.click();
    }

    public static void errorTransfer() {
        errorMessage.shouldBe(Condition.visible);
    }

    public static DashboardPage CancelTransfer() {
        cancelButton.click();
        return new DashboardPage();
    }
}