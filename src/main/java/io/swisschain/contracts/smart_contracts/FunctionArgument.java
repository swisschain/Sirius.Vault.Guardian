package io.swisschain.contracts.smart_contracts;

import java.util.List;
import java.util.Objects;

/** Represents a function argument. See Sirius SDK smart contract metamodel. */
public class FunctionArgument {
  private DataMetamodel dataModel;
  private List<FunctionArgument> components;
  private String value;

  public FunctionArgument() {}

  public FunctionArgument(
      DataMetamodel dataModel, List<FunctionArgument> components, String value) {
    this.dataModel = dataModel;
    this.components = components;
    this.value = value;
  }

  /** Gets the argument metamodel. */
  public DataMetamodel getDataModel() {
    return dataModel;
  }

  /** Sets the argument metamodel. */
  public void setDataModel(DataMetamodel dataModel) {
    this.dataModel = dataModel;
  }

  /** Gets the nested arguments. Works for complex types. */
  public List<FunctionArgument> getComponents() {
    return components;
  }

  /** Sets the nested arguments. */
  public void setComponents(List<FunctionArgument> components) {
    this.components = components;
  }

  /** Gets the argument value in string representation. */
  public String getValue() {
    return value;
  }

  /** Sets the argument value in string representation. */
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FunctionArgument that = (FunctionArgument) o;
    return dataModel.equals(that.dataModel)
        && components.equals(that.components)
        && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataModel, components, value);
  }
}
