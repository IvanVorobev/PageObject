package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransfer {
    private SelenideElement transferPage = $(withText("Пополнение карты"));
    private SelenideElement transferAmmount = $("[data-test-id=amount] input");
    private SelenideElement numberCardfrom = $("[data-test-id=from] input");
    private SelenideElement numberCardto = $("[data-test-id=to] input");
    private SelenideElement transferButton = $(byText("Пополнить"));
    private SelenideElement cancelButton = $(byText("Отмена"));
    private SelenideElement errorMessage = $(withText("Ошибка"));

    public void moneyTransferVisible() {
        transferPage.shouldBe(Condition.visible);
    }

    public void setTransferAmmount(int sum) {
        transferAmmount.setValue(String.valueOf(sum));
    }

    public void setNumberCardfrom(String numberCard) {
        numberCardfrom.setValue(numberCard);
    }

    public void doTransfer() {
        transferButton.click();
    }

    public void errorTransfer() {
        errorMessage.shouldBe(Condition.visible);
    }

    public DashboardPage CancelTransfer() {
        cancelButton.click();
        return new DashboardPage();
    }
}