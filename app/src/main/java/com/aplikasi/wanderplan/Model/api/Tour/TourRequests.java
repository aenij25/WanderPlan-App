package com.aplikasi.wanderplan.Model.api.Tour;

import java.util.Date;

public class TourRequests {
    private Long id;
    private Long tour_identifier;
    private Long user_identifier;
    private String email;
    private Integer phone;
    private Date start_date;
    private Date end_date;
    private Integer member;
    private String method;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTour_identifier() {
        return tour_identifier;
    }

    public void setTour_identifier(Long tour_identifier) {
        this.tour_identifier = tour_identifier;
    }

    public Long getUser_identifier() {
        return user_identifier;
    }

    public void setUser_identifier(Long user_identifier) {
        this.user_identifier = user_identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public TourRequests(Long id, Long tour_identifier, Long user_identifier, String email, Integer phone, Date start_date, Date end_date, Integer member, String method) {
        this.id = id;
        this.tour_identifier = tour_identifier;
        this.user_identifier = user_identifier;
        this.email = email;
        this.phone = phone;
        this.start_date = start_date;
        this.end_date = end_date;
        this.member = member;
        this.method = method;
    }
}
