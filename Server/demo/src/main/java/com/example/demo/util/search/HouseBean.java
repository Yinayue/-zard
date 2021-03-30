package com.example.demo.util.search;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HouseBean {

    int id;
    int seller_id;
    Timestamp launch_date;
    String address;
    double size;
    BigDecimal price;
    int has_been_sold;
    int current_holder_id;
    int second_hand;
    String rooms_type;
    int floor;
    int age;
    int decoration;
    int house_type;
    int property;
    int face;
    String remarks;
    int delete_flag;


    public HouseBean(String address){
        this.address = address;
    }


    public HouseBean(int id, int seller_id, Timestamp launch_date, String address,
                     double size, BigDecimal price, int has_been_sold, int current_holder_id,
                     int second_hand, String rooms_type, int floor, int age, int decoration,
                     int house_type, int property, int face, String remarks, int delete_flag) {
        this.id = id;
        this.seller_id = seller_id;
        this.launch_date = launch_date;
        this.address = address;
        this.size = size;
        this.price = price;
        this.has_been_sold = has_been_sold;
        this.current_holder_id = current_holder_id;
        this.second_hand = second_hand;
        this.rooms_type = rooms_type;
        this.floor = floor;
        this.age = age;
        this.decoration = decoration;
        this.house_type = house_type;
        this.property = property;
        this.face = face;
        this.remarks = remarks;
        this.delete_flag = delete_flag;
    }

    public int getId() {
        return id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public Timestamp getLaunch_date() {
        return launch_date;
    }

    public String getAddress() {
        return address;
    }

    public double getSize() {
        return size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getHas_been_sold() {
        return has_been_sold;
    }

    public int getCurrent_holder_id() {
        return current_holder_id;
    }

    public int getSecond_hand() {
        return second_hand;
    }

    public String getRooms_type() {
        return rooms_type;
    }

    public int getFloor() {
        return floor;
    }

    public int getAge() {
        return age;
    }

    public int getDecoration() {
        return decoration;
    }

    public int getHouse_type() {
        return house_type;
    }

    public int getProperty() {
        return property;
    }

    public int getFace() {
        return face;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getDelete_flag() {
        return delete_flag;
    }

    @Override
    public String toString() {
        return "HouseBean{" +
                "id=" + id +
                ", seller_id=" + seller_id +
                ", launch_date=" + launch_date +
                ", address='" + address + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", has_been_sold=" + has_been_sold +
                ", current_holder_id=" + current_holder_id +
                ", second_hand=" + second_hand +
                ", rooms_type='" + rooms_type + '\'' +
                ", floor=" + floor +
                ", age=" + age +
                ", decoration=" + decoration +
                ", house_type=" + house_type +
                ", property=" + property +
                ", face=" + face +
                ", remarks='" + remarks + '\'' +
                ", delete_flag=" + delete_flag +
                '}';
    }
}
