package domain;

public class Customer {
        private String name;
        private String Id;
        private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer(String name, String id, String email) {
        this.name = name;
        Id = id;
        this.email = email;
    }
}
