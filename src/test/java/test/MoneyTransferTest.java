package test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransfer;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    private int amountValid = 500;
    private int amountInvalid = 100000;


    private DashboardPage shouldEnterDashboardPage() {
        open("http://localhost:7777");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldEnterValidLogin() {
        open("http://localhost:7777");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromCard2toCard1() {
        DashboardPage dashboardPage = shouldEnterDashboardPage();
        dashboardPage.DashboardPageVisible();
        int expected1 = dashboardPage.getBalanceCard1() + amountValid;
        int expected2 = dashboardPage.getBalanceCard2() - amountValid;
        DashboardPage.topUpCard1();
        MoneyTransfer.MoneyTransferVisible();
        MoneyTransfer.setTransferAmmount(amountValid);
        MoneyTransfer.setNumberCardfrom(DataHelper.getCardNumber2());
        MoneyTransfer.doTransfer();
        assertEquals(expected1, DashboardPage.getBalanceCard1());
        assertEquals(expected2, DashboardPage.getBalanceCard2());
    }

    @Test
    void shouldTransferMoneyFromCard1toCard2() {
        DashboardPage dashboardPage = shouldEnterDashboardPage();
        dashboardPage.DashboardPageVisible();
        int expected1 = dashboardPage.getBalanceCard2() + amountValid;
        int expected2 = dashboardPage.getBalanceCard1() - amountValid;
        DashboardPage.topUpCard2();
        MoneyTransfer.MoneyTransferVisible();
        MoneyTransfer.setTransferAmmount(amountValid);
        MoneyTransfer.setNumberCardfrom(DataHelper.getCardNumber1());
        MoneyTransfer.doTransfer();
        assertEquals(expected1, DashboardPage.getBalanceCard2());
        assertEquals(expected2, DashboardPage.getBalanceCard1());
    }

    @Test
    void shouldTransferInvalidAmountFromCard2toCard1() {
        DashboardPage dashboardPage = shouldEnterDashboardPage();
        dashboardPage.DashboardPageVisible();
        DashboardPage.topUpCard1();
        MoneyTransfer.MoneyTransferVisible();
        MoneyTransfer.setTransferAmmount(amountInvalid);
        MoneyTransfer.setNumberCardfrom(DataHelper.getCardNumber2());
        MoneyTransfer.doTransfer();
        MoneyTransfer.errorTransfer();
    }

}