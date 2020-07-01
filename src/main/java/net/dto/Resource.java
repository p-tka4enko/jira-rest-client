package net.dto;

import lombok.Data;

/**
 * Base abstract resource class.
 *
 * @version 0.1
 */
@Data
public abstract class Resource {
  protected String self;
  protected String id;

  @Override
  public String toString() {
    return id;
  }
}
