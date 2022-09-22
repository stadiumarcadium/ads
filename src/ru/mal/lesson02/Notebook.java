package ru.mal.lesson02;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Notebook {
    private double price;
    private int ram;
    private Brand brand;
    public enum Brand{
        Lenuvo, Asos, MacNote, Eser, Xamiou
    }

    @Override
    public String toString() {
        return "Notebook{" +
               "price=" + price +
               ", ram=" + ram +
               ", brand=" + brand +
               '}';
    }
}
