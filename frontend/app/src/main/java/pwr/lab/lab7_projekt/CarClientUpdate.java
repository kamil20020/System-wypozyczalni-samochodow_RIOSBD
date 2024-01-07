package pwr.lab.lab7_projekt;

public class CarClientUpdate {
    private String rentalDate;
    private String returnDate;
    private Integer carId;
    private Integer clientId;

    public CarClientUpdate(String rentalDate, String returnDate, Integer carId, Integer clientId) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.carId = carId;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "CarClientUpdate{" +
                "rentalDate='" + rentalDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", carId=" + carId +
                ", clientId=" + clientId +
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

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
