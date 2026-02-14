package dto;

public class DeliveryAddress {
    public String address;
    public String city;
    public String state;
    public String zip;

    public DeliveryAddress(String address, String city, String state, String zip) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
