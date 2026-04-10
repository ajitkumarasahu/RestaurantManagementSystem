package com.rms.model;

public class Restaurant {

    private int id;
    private String name;
    private String address;
    private String phone;
    private int ownerId;
    private String createdAt;

    public Restaurant() {}

    /**
     * Constructs a new Restaurant with the specified ID, name, address, phone number, owner ID, and creation timestamp.
     *
     * @param id the ID of the restaurant
     * @param name the name of the restaurant
     * @param address the address of the restaurant
     * @param phone the phone number of the restaurant
     * @param ownerId the ID of the user who owns the restaurant
     * @param createdAt the timestamp when the restaurant was created
     */
    public Restaurant(int id, String name, String address, String phone, int ownerId, String createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ownerId = ownerId;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}