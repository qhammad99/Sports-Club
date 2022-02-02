package pk.edu.uaar.group_sports_club.sports_club;

public class tournament {

        private String name;
        private String last_date;

    public tournament(String name, String last_date) {
        this.name = name;
        this.last_date = last_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }
}
