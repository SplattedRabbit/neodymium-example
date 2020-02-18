package posters.pageobjects.pages.browsing;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Step;
import posters.dataobjects.User;

public class HomePage extends AbstractBrowsingPage
{
    @Override
    @Step("ensure this is a home page")
    public HomePage isExpectedPage()
    {
        $("#titleIndex").should(exist);
        return this;
    }

    @Step("validate home page structure")
    public HomePage validateStructure()
    {
        super.validateStructure();

        // Verifies the company Logo and name are visible.
        $("#brand").shouldBe(visible);

        // Verifies the Navigation bar is visible
        $("#categoryMenu .nav").shouldBe(visible);
        // Asserts there's categories in the nav bar.
        $$("#categoryMenu .header-menu-item").shouldHave(sizeGreaterThan(0));

        // Asserts the first headline is there.
        $("#titleIndex").shouldBe(matchText("[A-Z].{3,}"));
        // Asserts the animated poster rotation is there.
        $("#pShopCarousel").shouldBe(visible);

        // Verifies the "Hot products" section is there.
        $("#main .margin_top20 .h2").shouldBe(matchText("[A-Z].{3,}"));
        // Asserts there's a list of items under "Hot Products".
        $("#productList").shouldBe(visible);
        // Asserts there's at least 1 item in the list.
        $$("#productList .thumbnail").shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("validate home page")
    public HomePage validate()
    {
        validateStructure();
        footer.validate();
        return this;
    }

    @Step("validate successful order on home page")
    public HomePage validateSuccessfulOrder()
    {
        successMessage.validateSuccessMessage(Neodymium.localizedText("HomePage.validation.successfulOrder"));
        // Verify that the mini cart is empty again
        miniCart.validateTotalCount(0);
        miniCart.validateSubtotal("$0.00");
        return this;
    }

    /**
     * @param firstName
     *            The name should be shown in the mini User Menu
     */
    @Step("validate successful login on home page")
    public HomePage validateSuccessfulLogin(String firstName)
    {
        // Verify that you are logged in
        successMessage.validateSuccessMessage(Neodymium.localizedText("HomePage.validation.successfulLogin"));
        // Verify that the user menu shows your first name
        userMenu.validateLoggedInName(firstName);
        return this;

    }

    /**
     * @param user
     */
    @Step("validate successful user login on home page")
    public HomePage validateSuccessfulLogin(User user)
    {
        validateSuccessfulLogin(user.getFirstName());
        return this;
    }

    @Step("validate successful account deletion on home page")
    public HomePage validateSuccessfulDeletedAccount()
    {
        successMessage.validateSuccessMessage(Neodymium.localizedText("HomePage.validation.successfulAccountDeletion"));
        return this;
    }

    public HomePage validateAndVisualAssert()
    {
        validateStructureAndVisual();
        footer.validate();
        return this;
    }
}
