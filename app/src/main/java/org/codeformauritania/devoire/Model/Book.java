package org.codeformauritania.devoire.Model;

public class Book {

    private String title;
    private String price;
    private  int image;

    public Book() {
    }

    public Book(String title, String price, int image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
