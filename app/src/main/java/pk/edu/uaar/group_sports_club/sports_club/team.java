package pk.edu.uaar.group_sports_club.sports_club;

public class team {

        private String name;
        private String location;
        private String id;

        public team(String name, String location, String id) {
            this.name = name;
            this.location = location;
            this.id=id;
        }

        public String getName() {
            return name;
        }
        public String getLocation() {
            return location;
        }

    public String getId() {
        return id;
    }

    public void setName(String name) {
            this.name = name;
        }
        public void setLocation(String location) {
            this.location = location;
        }

    public void setId(String id) {
        this.id = id;
    }
}
