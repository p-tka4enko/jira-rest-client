package net.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Base abstract resource class.
 *
 * @version 0.1
 */
public abstract class Resource {
  @Getter @Setter protected String self;
  @Getter @Setter protected String id;

  @Override
  public String toString() {
    return id;
  }
}
