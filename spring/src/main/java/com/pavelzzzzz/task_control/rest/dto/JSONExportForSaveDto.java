package com.pavelzzzzz.task_control.rest.dto;

import java.sql.Timestamp;

public class JSONExportForSaveDto {

    private String title;
    private String body;

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

    public static JSONExportDtoBuilder builder() {
        return new JSONExportDtoBuilder();
    }

    public static final class JSONExportDtoBuilder {
        private JSONExportForSaveDto jSONExportDto;

        private JSONExportDtoBuilder() {
            jSONExportDto = new JSONExportForSaveDto();
        }

        public JSONExportDtoBuilder title(String title) {
            jSONExportDto.setTitle(title);
            return this;
        }

        public JSONExportDtoBuilder body(String body) {
            jSONExportDto.setBody(body);
            return this;
        }

        public JSONExportForSaveDto build() {
            return jSONExportDto;
        }
    }
}
