package io.swisschain.contracts.smart_contracts;

import java.util.List;
import java.util.Objects;

/** Represents a data metamodel. See Sirius SDK smart contract metamodel. */
public class DataMetamodel {
  private String name;
  private DataType dataType;
  private int size;
  private int scale;
  private boolean isArray;
  private String nativeType;
  private List<DataMetamodel> components;
  private boolean isRequired;

  public DataMetamodel() {}

  public DataMetamodel(
      String name,
      DataType dataType,
      int size,
      int scale,
      boolean isArray,
      String nativeType,
      List<DataMetamodel> components,
      boolean isRequired) {
    this.name = name;
    this.dataType = dataType;
    this.size = size;
    this.scale = scale;
    this.isArray = isArray;
    this.nativeType = nativeType;
    this.components = components;
    this.isRequired = isRequired;
  }

  /** Gets the parameter name. */
  public String getName() {
    return name;
  }

  /** Sets the parameter name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Gets the parameter type. */
  public DataType getDataType() {
    return dataType;
  }

  /** Sets the parameter type. */
  public void setDataType(DataType dataType) {
    this.dataType = dataType;
  }

  /** Gets the parameter size. */
  public int getSize() {
    return size;
  }

  /** Sets the type size. */
  public void setSize(int size) {
    this.size = size;
  }

  /** Gets the type scale. */
  public int getScale() {
    return scale;
  }

  /** Sets the type scale. */
  public void setScale(int scale) {
    this.scale = scale;
  }

  /** Returns true if parameter is an array type. */
  public boolean isArray() {
    return isArray;
  }

  /** Sets true for array types. */
  public void setArray(boolean array) {
    isArray = array;
  }

  /** Gets the native type name that not matched with generic types. */
  public String getNativeType() {
    return nativeType;
  }

  /** Sets the native type name for not generic types. */
  public void setNativeType(String nativeType) {
    this.nativeType = nativeType;
  }

  /** Gets the nested parameters data metamodel. Works for complex types. */
  public List<DataMetamodel> getComponents() {
    return components;
  }

  /** Sets the nested parameters data metamodel. */
  public void setComponents(List<DataMetamodel> components) {
    this.components = components;
  }

  /** Indicated that the type is required. */
  public boolean isRequired() {
    return isRequired;
  }

  /** Indicated that the type is required. */
  public void setRequired(boolean required) {
    isRequired = required;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataMetamodel that = (DataMetamodel) o;
    return size == that.size
        && scale == that.scale
        && isArray == that.isArray
        && name.equals(that.name)
        && dataType == that.dataType
        && nativeType.equals(that.nativeType)
        && components.equals(that.components)
        && isRequired == that.isRequired;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dataType, size, scale, isArray, nativeType, components, isRequired);
  }
}
