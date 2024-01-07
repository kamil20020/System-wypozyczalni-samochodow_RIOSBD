package pwr.lab.lab7_projekt;

public class CarClientCreate {
    private String rentalDate;
    private String returnDate;
    private Client createClient;
    private Integer carId;

    public CarClientCreate(String rentalDate, String returnDate, Client createClient, Integer carId) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.createClient = createClient;
        this.carId = carId;
    }


    @Override
    public String toString() {
        return "CarClientCreate{" +
                "rentalDate='" + rentalDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", createClient=" + createClient +
                ", carId=" + carId +
                '}';
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Client getCreateClient() {
        return createClient;
    }

    public void setCreateClient(Client createClient) {
        this.createClient = createClient;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
