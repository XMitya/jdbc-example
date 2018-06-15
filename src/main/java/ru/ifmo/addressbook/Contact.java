package ru.ifmo.addressbook;

import java.util.ArrayList;
import java.util.List;

public class Contact extends Identifiable {
    private String name;
    private String address;
    private List<Phone> phones;
    private List<Address> addresses;

    public Contact() {
    }

    public Contact(Integer id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;

        phones = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
