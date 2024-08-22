package com.nttdata.step;

import com.nttdata.screen.SauceLabsScreen;
import org.junit.Assert;

public class SauceLabsStep {
    private SauceLabsScreen sauceLabsScreen;

    public void confirmLoading() {
        Assert.assertTrue("No hay productos en la galería", sauceLabsScreen.validateLoading() > 0);
        sauceLabsScreen.prodsQuantity();
    }

    public void addProds(String prod, int cant) {
        sauceLabsScreen.selectProds(prod, cant);
        sauceLabsScreen.addToCart();
    }

    public void validateProds() {
        sauceLabsScreen.goToCart();
        Assert.assertTrue("No hay productos en el carrito", sauceLabsScreen.confirmAmount() > 0);
        sauceLabsScreen.totalProd();
    }
}