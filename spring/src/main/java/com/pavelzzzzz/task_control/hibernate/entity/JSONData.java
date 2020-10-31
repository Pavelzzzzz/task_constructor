package com.pavelzzzzz.task_control.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class JSONData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String title;
    private String body;
    @NotNull
    private Integer folderId;
    @NotNull
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

    public static JSONDataBuilder builder() {
        return new JSONDataBuilder();
    }

    public static final class JSONDataBuilder {
        private JSONData jSONData;

        private JSONDataBuilder() {
            jSONData = new JSONData();
        }

        public JSONDataBuilder id(Integer id) {
            jSONData.setId(id);
            return this;
        }

        public JSONDataBuilder title(String title) {
            jSONData.setTitle(title);
            return this;
        }

        public JSONDataBuilder body(String body) {
            jSONData.setBody(body);
            return this;
        }

        public JSONDataBuilder folderId(Integer folderId) {
            jSONData.setFolderId(folderId);
            return this;
        }

        public JSONDataBuilder createdAt(Timestamp createdAt) {
            jSONData.setCreatedAt(createdAt);
            return this;
        }

        public JSONData build() {
            return jSONData;
        }
    }
}
