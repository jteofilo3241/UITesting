package playwrightTraditional;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.microsoft.playwright. options. AriaRole;

import java.nio.file.Paths;

public class playwrightTraditionalTests {

    @Test
    public void testBookstore(){
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720));


            Page page = context.newPage();
            page.navigate("https://depaul.bncollege.com/");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("earbuds");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).press("Enter");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand")).click();
            page.locator(".facet__list.js-facet-list.js-facet-top-values > li:nth-child(3) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
            page.locator("#facet-Color > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort by")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
            page.locator("#facet-price > .facet__values > .facet__list > li:nth-child(2) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).click();
            //page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
//
            page.getByText("sku").nth(1).click();
            page.getByText("668972707").nth(1).click();
            page.getByText("$164.98").click();
            page.getByText("Adaptive noise cancelling").click();
            String productName = page.locator("h1.name").first().innerText();
            String price = page.getByText("$164.98").first().innerText();
            String cart = page.getByText("1 Items").first().innerText();
            assertThat(productName).contains("JBL Quantum True Wireless");
            assertThat(price).contains("$164.98");
            assertThat(cart).contains("1 items");

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items")).click();
            //page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your Shopping Cart(1 Item)")).click();
            String cartH = page.getByText("Your Shopping Cart").first().innerText();
            assertThat(cartH).contains("Your Shopping Cart");

            page.getByText("qty:").click();
            String quantity = page.getByRole(AriaRole.TEXTBOX,
                            new Page.GetByRoleOptions().setName("Quantity, edit and press")).first().inputValue();
            assertThat(quantity).contains("1");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Quantity, edit and press")).click();





            page.getByText("FAST In-Store PickupDePaul").click();
            page.getByText("Subtotal").click();
            page.getByText("$").nth(1).click();
            page.getByText("Handling To support the").click();
            page.getByText("$3.00", new Page.GetByTextOptions().setExact(true)).click();
            page.getByText("Taxes").click();
            page.getByText("TBD").click();
            page.getByText("Estimated Total", new Page.GetByTextOptions().setExact(true)).click();
            page.getByText("$167.98").click();

            String subtot = page.getByText("164.98").first().innerText();
            assertThat(subtot).contains("164.98");
            String handling0 = page.getByText("3.00").first().innerText();
            assertThat(handling0).contains("3.00");
            String taxes = page.getByText("TBD").first().innerText();
            assertThat(taxes).contains("TBD");

            String total = page.getByText("167.98").first().innerText();
            assertThat(total).contains("167.98");

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).press("CapsLock");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).fill("TEST");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).press("CapsLock");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Promo Code")).click();
            page.getByText("The coupon code entered is").click();

            String promoMessage = page.getByText("The coupon code entered is").first().innerText();
            assertThat(promoMessage).contains("The coupon code entered is");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Proceed To Checkout")).first().click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Create Account")).click();
            String createAccountLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Create Account")).first().innerText();
            assertThat(createAccountLabel).contains("Create Account");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest")).click();

            //
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact Information")).click();
            String contactInfoHeading = page
                    .getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact Information"))
                    .first()
                    .innerText();
            assertThat(contactInfoHeading).contains("Contact Information");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).fill("mike");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).press("Tab");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).fill("smith");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).fill("testemail1234@gmail.com");
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).click();
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).fill("1234567890");
            page.getByText("Order Subtotal").nth(1).click();
            page.getByText("$164.98").nth(2).click();
            page.getByText("Handling To support the").nth(1).click();
            page.getByText("$3.00").nth(3).click();
            page.getByText("Tax").nth(1).click();
            page.getByText("TBD").nth(2).click();
            page.getByText("Total").nth(3).click();
            page.getByText("$167.98").nth(1).click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("PICKUP")).click();
            page.getByText("$164.98").nth(3).click();
            String subtotal = page.getByText("164.98").first().innerText();
            assertThat(subtotal).contains("164.98");
            String handling = page.getByText("3.00").first().innerText();
            assertThat(handling).contains("3.00");
            String tax = page.getByText("TBD").first().innerText();
            assertThat(tax).contains("TBD");
            String tot = page.getByText("167.98").first().innerText();
            assertThat(tot).contains("167.98");
            //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).nth(1).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            //
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact Information")).click();
            page.getByText("Full Name").first().click();
            page.getByText("mike").click();
            page.getByText("smith").click();
            page.getByText("Email Address", new Page.GetByTextOptions().setExact(true)).click();
            page.getByText("testemail1234@gmail.com").click();
            page.getByText("Phone Number").first().click();
            page.getByText("11234567890").click();
            String firstName = page.getByLabel("First Name (required)").inputValue();
            String lastName = page.getByLabel("Last Name (required)").inputValue();
            String email = page.getByLabel("Email address (required)").inputValue();
            String phone = page.getByLabel("Phone Number (required)").inputValue();

            assertThat(firstName).contains("mike");
            assertThat(lastName).contains("smith");
            assertThat(email).contains("testemail1234@gmail.com");
            assertThat(phone).contains("1234567890");

            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Pickup Location")).click();
            page.locator("#bnedPickupPersonForm").getByText("DePaul University Loop Campus").click();
            String pickup = page.getByText("DePaul University Loop Campus").first().innerText();
            assertThat(pickup).contains("DePaul University Loop Campus");

            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Pickup Person")).click();
            page.getByText("I'll pick them up").click();
            String personPickup =  page.getByText("I'll pick them up").first().innerText();
            assertThat(personPickup).contains("I'll pick them up");

            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Order Summary")).click();
            page.getByText("Order Subtotal").nth(1).click();
            page.getByText("$164.98").nth(2).click();
            page.getByText("Handling To support the").nth(1).click();
            page.getByText("$3.00").nth(3).click();
            page.getByText("Total").nth(3).click();
            page.getByText("$167.98").nth(1).click();
            String subTotal =  page.getByText("164.98").first().innerText();
            assertThat(subTotal).contains("164.98");
            String handling2 = page.getByText("3.00").first().innerText();
            assertThat(handling2).contains("3.00");
            String taxes2 = page.getByText("TBD").first().innerText();
            assertThat(taxes2).contains("TBD");
            String total2 = page.getByText("167.98").first().innerText();
            assertThat(total2).contains("167.98");
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("PICKUP").setExact(true)).click();
            //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).nth(1).click();
            //page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Order Summary")).click();
            page.getByText("Order Subtotal").nth(1).click();
            page.getByText("$164.98").nth(2).click();
            page.getByText("Handling To support the").nth(1).click();
            page.getByText("$3.00").nth(3).click();
            page.getByText("Tax").nth(1).click();
            page.getByText("$17.22").nth(1).click();
            page.getByText("Total").nth(3).click();
            page.getByText("$185.20").nth(1).click();
            String subTotal3 =  page.getByText("164.98").first().innerText();
            assertThat(subTotal3).contains("164.98");
            String handling3 = page.getByText("3.00").first().innerText();
            assertThat(handling3).contains("3.00");
            String taxes3 = page.getByText("$17.22").first().innerText();
            assertThat(taxes3).contains("$17.22");
            String total3 = page.getByText("$185.20").first().innerText();
            assertThat(total3).contains("$185.20");

            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("PICKUP")).click();
            page.getByText("$164.98").nth(3).click();
            String pickupPrice = page.getByText("$164.98").first().innerText();
            //
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum")).click();
            page.getByText("Product has been removed from").click();
            page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your cart is empty")).click();
            String emptyCart = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your cart is empty")).innerText();
            assertThat(emptyCart).contains("Your cart is empty");

        }
    }

}
