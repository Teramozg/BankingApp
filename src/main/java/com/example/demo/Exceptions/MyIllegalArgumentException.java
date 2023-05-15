package com.example.demo.Exceptions;

public class MyIllegalArgumentException extends IllegalArgumentException {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Не существует введённый ИД." +this.id;
    }

    @Override
    public String toString() {

        return "не существует введенный ид " + this.id;
    }
}
