package pk.edu.uaar.group_sports_club.sports_club;

public class member {
    private String id;
    private String name;
    private String city;
    private String status;

    public member(String name, String city, String status, String id) {
        this.id=id;
        this.name = name;
        this.city = city;
        this.status = status;
    }

    public String getCity() { return city; }
    public String getName() {
        return name;
    }
    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }
}
