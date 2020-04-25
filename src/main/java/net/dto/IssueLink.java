package net.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Issue link.
 *
 * @version 0.1
 */
public class IssueLink extends Resource {
  @Getter @Setter private IssueLinkType type;
  @Getter @Setter private Issue inwardIssue;
  @Getter @Setter private Issue outwardIssue;
}
