package pwr.lab.lab7_projekt;


public class Car {

    private Integer id;
    private String manufacturer;
    private String model;
    private String type;
    private String color;
    private String licenceNumber;
    private double cost15min;
    private String photo;

    public Car(){}
    public Car(String manufacturer, String model, String type, String color, String licenceNumber, double cost15min, String photo) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.color = color;
        this.licenceNumber = licenceNumber;
        this.cost15min = cost15min;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", licenceNumber='" + licenceNumber + '\'' +
                ", cost15min=" + cost15min +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public double getCost15min() {
        return cost15min;
    }

    public void setCost15min(double cost15min) {
        this.cost15min = cost15min;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
