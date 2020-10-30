package com.pavelzzzzz.task_control.rest.dto;

import java.sql.Timestamp;

public class JSONExportDto {

    private Integer id;
    private String title;
    private String body;
    private Timestamp createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public static JSONExportDtoBuilder builder() {
        return new JSONExportDtoBuilder();
    }

    public static final class JSONExportDtoBuilder {
        private JSONExportDto jSONExportDto;

        private JSONExportDtoBuilder() {
            jSONExportDto = new JSONExportDto();
        }

        public JSONExportDtoBuilder id(Integer id) {
            jSONExportDto.setId(id);
            return this;
        }

        public JSONExportDtoBuilder title(String title) {
            jSONExportDto.setTitle(title);
            return this;
        }

        public JSONExportDtoBuilder body(String body) {
            jSONExportDto.setBody(body);
            return this;
        }

        public JSONExportDtoBuilder createdAt(Timestamp createdAt) {
            jSONExportDto.setCreatedAt(createdAt);
            return this;
        }

        public JSONExportDto build() {
            return jSONExportDto;
        }
    }
}
