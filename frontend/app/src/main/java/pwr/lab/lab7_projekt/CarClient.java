package pwr.lab.lab7_projekt;

public class CarClient {
    
    private Integer id;
    private String rentalDate;
    private String returnDate;
    private double totalCost;
    private Car car;
    private Client client;


    @Override
    public String toString() {
        return "CarClient{" +
                "id=" + id +
                ", rentalDate='" + rentalDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", totalCost=" + totalCost +
                ", car=" + car +
                ", client=" + client +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
