package com.customer.core;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    //@JsonProperty("name")
    String name;
    //@JsonProperty("email")
    String email;
    //@JsonProperty("age")
    int age;
    //@JsonProperty("mobile")
    int mobile;

    //@JsonProperty("isSubmmitted")


    boolean customerNotified;

    boolean customerFormSubmitted;

    public Customer() {
        super();
    }

    public Customer(String name, String email, int age, int mobile, boolean isSubmmitted, boolean isNotified) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.mobile = mobile;

        this.customerNotified = isNotified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }


    public boolean isCustomerNotified() {
        return customerNotified;
    }

    public void setCustomerNotified(boolean customerNotified) {
        this.customerNotified = customerNotified;
    }

    public boolean isCustomerFormSubmitted() {
        return customerFormSubmitted;
    }

    public void setCustomerFormSubmitted(boolean customerFormSubmitted) {
        this.customerFormSubmitted = customerFormSubmitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age &&
                mobile == customer.mobile &&
                customerNotified == customer.customerNotified &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, age, mobile, customerNotified);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", mobile=" + mobile +
                ", isNotified=" + customerNotified +
                '}';
    }
}
