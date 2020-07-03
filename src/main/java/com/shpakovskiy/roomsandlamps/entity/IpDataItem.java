package com.shpakovskiy.roomsandlamps.entity;

import javax.persistence.*;

@Entity
@Table(name = "ip_data")
public class IpDataItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ip_from")
    private long ipFrom;

    @Column(name = "ip_to")
    private long ipTo;

    @Column(name = "country_id")
    private int countryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(long from) {
        this.ipFrom = from;
    }

    public long getIpTo() {
        return ipTo;
    }

    public void setIpTo(long to) {
        this.ipTo = to;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "IpDataItem{" +
                "id=" + id +
                ", from=" + ipFrom +
                ", to=" + ipTo +
                ", countryId=" + countryId +
                '}';
    }
}
