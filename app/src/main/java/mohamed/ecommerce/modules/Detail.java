package mohamed.ecommerce.modules;

/**
 * Created by mohamed mesalm on 14/05/18.
 */

public class Detail {

    private String title;
    private String image;
    private String type;
    private String price;
    private String overPrice;
    private String isSale;
    private String isFeatured;
    private String isNew;


    public Detail(String title, String image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public Detail() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOverPrice() {
        return overPrice;
    }

    public void setOverPrice(String overPrice) {
        this.overPrice = overPrice;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getIsNew() {
        return isNew;
    }
}
