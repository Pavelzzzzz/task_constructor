package com.pavelzzzzz.task_control.rest.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class JSONDataDto {

    private Integer id;
    private String title;
    private String body;
    private Integer folderId;
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

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public static JSONDataDtoBuilder builder() {
        return new JSONDataDtoBuilder();
    }

    public static final class JSONDataDtoBuilder {
        private JSONDataDto jSONDataDto;

        private JSONDataDtoBuilder() {
            jSONDataDto = new JSONDataDto();
        }

        public JSONDataDtoBuilder id(Integer id) {
            jSONDataDto.setId(id);
            return this;
        }

        public JSONDataDtoBuilder title(String title) {
            jSONDataDto.setTitle(title);
            return this;
        }

        public JSONDataDtoBuilder body(String body) {
            jSONDataDto.setBody(body);
            return this;
        }

        public JSONDataDtoBuilder folderId(Integer folderId) {
            jSONDataDto.setFolderId(folderId);
            return this;
        }

        public JSONDataDtoBuilder createdAt(Timestamp createdAt) {
            jSONDataDto.setCreatedAt(createdAt);
            return this;
        }

        public JSONDataDto build() {
            return jSONDataDto;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONDataDto that = (JSONDataDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getBody(), that.getBody()) &&
                Objects.equals(getFolderId(), that.getFolderId()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getBody(), getFolderId(), getCreatedAt());
    }
}
