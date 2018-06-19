package ru.ifmo.addressbook;

import javax.persistence.*;

@Table(name = "phone")
@Entity
public class Phone extends Identifiable {
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Version
    private Integer version;

    public Phone() {
    }

    public Phone(Integer id, String phone) {
        super(id);
        this.phone = phone;
    }

    public Phone(Integer id, String phone, Contact contact) {
        super(id);
        this.phone = phone;
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
