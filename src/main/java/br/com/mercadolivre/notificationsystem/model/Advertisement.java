package br.com.mercadolivre.notificationsystem.model;

import lombok.Data;

@Data
public class Advertisement {
  private String code;
  private String userId;
  private String title;
  private String description;
  private String type;

  public boolean isValid() {
    return code != null && !code.isBlank()
        && userId != null && !userId.isBlank()
        && title != null && !title.isBlank()
        && description != null && !description.isBlank()
        && type != null && !type.isBlank();
  }

  @Override
  public String toString() {
    return "{" +
        "id='" + code + '\'' +
        ", userId='" + userId + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
