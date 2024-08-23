package com.nttdata.screen;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceLabsScreen extends PageObject {

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

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // Timeout en segundos
    }

    public void prodsQuantity() {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays all products of catalog']//android.view.ViewGroup")));
        int quantity = productos.size();
        System.out.println("Cantidad en stock (int): " + quantity);
    }


    public int validateLoading() {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays all products of catalog']//android.view.ViewGroup")));
        return productos.size();
    }

    public void selectProds(String nomProd, int cant) {
        WebDriverWait wait = getWait();
        WebElement producto = getProdsByName(nomProd);
        wait.until(ExpectedConditions.visibilityOf(producto));
        producto.click();

        wait.until(ExpectedConditions.visibilityOf(btnAumentar));
        for (int i = 1; i < cant; i++) {
            btnAumentar.click();
        }
    }

    private WebElement getProdsByName(String nomProd) {
        switch (nomProd) {
            case "Sauce Labs Backpack":
                return prod1;
            case "Sauce Labs Bolt T-Shirt":
                return prod2;
            case "Sauce Labs Bike Light":
                return prod3;
            default:
                throw new IllegalArgumentException("No hay producto: " + nomProd);
        }
    }

    public void addToCart() {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.visibilityOf(btnAgregar));
        btnAgregar.click();
    }

    public void goToCart() {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.visibilityOf(btnCarrito));
        btnCarrito.click();
    }

    public int confirmAmount() {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.visibilityOfAllElements(visualizarProds));
        return visualizarProds.size();
    }

    public void totalProd() {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.visibilityOf(totalProds));
        System.out.println("Total de productos: " + totalProds.getText());
    }
}
