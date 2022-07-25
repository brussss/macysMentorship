import org.junit.jupiter.api.Test;


public class buyProduct extends macysPageObjec{

    private final String PRODUCT_NAME = "lakers";
    private final String PRODUCT_ID = "13423064";
    private final String PRODUCT_SIZE = "Large";

    macysPageObjec product = new macysPageObjec();

    @Test
    public void buyProduct() {
        product.setup();
        product.search(PRODUCT_NAME);
        product.addToCart(PRODUCT_ID, PRODUCT_SIZE);
        product.checkout();
    }
}
