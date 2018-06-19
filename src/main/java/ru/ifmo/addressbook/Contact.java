package ru.ifmo.addressbook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "contact")
@Entity
public class Contact extends Identifiable {
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "contact")
    private List<Phone> phones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "contact")
    private List<Address> addresses;

    @Version
    private Integer version;

    public Contact() {
    }

    public Contact(Integer id, String name) {
        super(id);
        this.name = name;

        phones = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
