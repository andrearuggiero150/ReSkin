package com.example.reskin.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class POP {
    private int id;
    private String testo;
    private LocalDateTime data;
    private int customerId;
    private String email;

    public POP() {
    }

    public POP(int id, String testo, LocalDateTime data, int customerId) {
        this.id = id;
        this.testo = testo;
        this.data = data;
        this.customerId = customerId;
    }

        public POP(int id, String testo, LocalDateTime data, int customerId, String email) {
            this.id = id;
            this.testo = testo;
            this.data = data;
            this.customerId = customerId;
            this.email = email;
        }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
            return email;
        }

    public void setEmail(String email) {
            this.email = email;
        }
}
