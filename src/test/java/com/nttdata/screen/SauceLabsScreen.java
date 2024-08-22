package com.nttdata.screen;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

public class SauceLabsScreen extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(SauceLabsScreen.class.getName());
    private static final int TIMEOUT = 20;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays all products of catalog']//android.view.ViewGroup")
    private List<WebElement> productos;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Backpack\"]")
    private WebElement prod1;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Bolt T-Shirt\"]")
    private WebElement prod2;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Bike Light\"]")
    private WebElement prod3;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement btnAumentar;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement btnAgregar;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    private WebElement btnCarrito;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays list of selected products\"]/android.view.ViewGroup")
    private List<WebElement> visualizarProds;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemsTV\"]")
    private WebElement totalProds;

    private WebDriverWait wait;

    public SauceLabsScreen() {
        wait = new WebDriverWait(getDriver(), TIMEOUT);
    }

    private void waitVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitProdsVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void prodsQuantity() {
        waitVisibility(productos.get(0));
        LOGGER.info("Cantidad en stock: " + productos.size());
    }

    public int validateLoading() {
        waitProdsVisibility(productos);
        return productos.size();
    }

    public void selectProds(String nomProd, int cant) {
        WebElement producto = getProdsByName(nomProd);
        waitVisibility(producto);
        producto.click();
        increaseProductQuantity(cant);
    }

    private WebElement getProdsByName(String nomProd) {
        switch (nomProd) {
            case "Sauce Labs Backpack":
                return prod1;
            case "Sauce Labs Bolt - T-Shirt":
                return prod2;
            case "Sauce Labs Bike Light":
                return prod3;
            default:
                throw new IllegalArgumentException("No hay producto: " + nomProd);
        }
    }

    private void increaseProductQuantity(int cant) {
        waitVisibility(btnAumentar);
        for (int i = 1; i < cant; i++) {
            btnAumentar.click();
        }
    }

    public void addToCart() {
        waitVisibility(btnAgregar);
        btnAgregar.click();
    }

    public void goToCart() {
        waitVisibility(btnCarrito);
        btnCarrito.click();
    }

    public int confirmAmount() {
        waitProdsVisibility(visualizarProds);
        return visualizarProds.size();
    }

    public void totalProd() {
        waitVisibility(totalProds);
        LOGGER.info("Total de productos: " + totalProds.getText());
    }
}