package com.pavelzzzzz.task_control.rest.dto;

import javax.validation.constraints.NotBlank;

public class JSONDataForSaveDto {

    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Body is mandatory")
    private String body;
    @NotBlank(message = "FolderId is mandatory")
    private Integer folderId;

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

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public static JSONDataForSaveDtoBuilder builder() {
        return new JSONDataForSaveDtoBuilder();
    }

    public static final class JSONDataForSaveDtoBuilder {
        private JSONDataForSaveDto jSONDataForSaveDto;

        private JSONDataForSaveDtoBuilder() {
            jSONDataForSaveDto = new JSONDataForSaveDto();
        }

        public JSONDataForSaveDtoBuilder title(String title) {
            jSONDataForSaveDto.setTitle(title);
            return this;
        }

        public JSONDataForSaveDtoBuilder body(String body) {
            jSONDataForSaveDto.setBody(body);
            return this;
        }

        public JSONDataForSaveDtoBuilder folderId(Integer folderId) {
            jSONDataForSaveDto.setFolderId(folderId);
            return this;
        }

        public JSONDataForSaveDto build() {
            return jSONDataForSaveDto;
        }
    }
}
