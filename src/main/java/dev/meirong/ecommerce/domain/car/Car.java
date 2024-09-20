package dev.meirong.ecommerce.domain.car;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String brand;
  private String model;
  private String color;
  private String registrationNumber;
  private int modelYear;
  private int price;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner")
  private Owner owner;

  public Car() {
    super();
  }

  public Car(
      String brand,
      String model,
      String color,
      String registrationNumber,
      int modelYear,
      int price) {
    super();
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.registrationNumber = registrationNumber;
    this.modelYear = modelYear;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public int getModelYear() {
    return modelYear;
  }

  public void setModelYear(int modelYear) {
    this.modelYear = modelYear;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((brand == null) ? 0 : brand.hashCode());
    result = prime * result + ((model == null) ? 0 : model.hashCode());
    result = prime * result + ((color == null) ? 0 : color.hashCode());
    result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
    result = prime * result + modelYear;
    result = prime * result + price;
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Car other = (Car) obj;
    if (id == null) {
      if (other.id != null) return false;
    } else if (!id.equals(other.id)) return false;
    if (brand == null) {
      if (other.brand != null) return false;
    } else if (!brand.equals(other.brand)) return false;
    if (model == null) {
      if (other.model != null) return false;
    } else if (!model.equals(other.model)) return false;
    if (color == null) {
      if (other.color != null) return false;
    } else if (!color.equals(other.color)) return false;
    if (registrationNumber == null) {
      if (other.registrationNumber != null) return false;
    } else if (!registrationNumber.equals(other.registrationNumber)) return false;
    if (modelYear != other.modelYear) return false;
    if (price != other.price) return false;
    if (owner == null) {
      if (other.owner != null) return false;
    } else if (!owner.equals(other.owner)) return false;
    return true;
  }
}
