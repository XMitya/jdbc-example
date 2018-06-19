package ru.ifmo.addressbook;

import javax.persistence.*;

@Table(name = "address")
@Entity
public class Address extends Identifiable {
    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Address() {
    }

    public Address(Integer id, String address) {
        super(id);
        this.address = address;
    }

    public Address(Integer id, String address, Contact contact) {
        super(id);
        this.address = address;
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
