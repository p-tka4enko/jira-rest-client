package net.domain;

import lombok.Data;

@Data
public abstract class Resource {
  protected String self;
  protected String id;

  @Override
  public String toString() {
    return id;
  }
}
