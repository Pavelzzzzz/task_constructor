package com.pavelzzzzz.task_control.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class JSONExport {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String title;
    private String body;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public static JSONExportBuilder builder() {
        return new JSONExportBuilder();
    }

    public static final class JSONExportBuilder {
        private JSONExport jSONExport;

        private JSONExportBuilder() {
            jSONExport = new JSONExport();
        }

        public JSONExportBuilder id(Integer id) {
            jSONExport.setId(id);
            return this;
        }

        public JSONExportBuilder title(String title) {
            jSONExport.setTitle(title);
            return this;
        }

        public JSONExportBuilder body(String body) {
            jSONExport.setBody(body);
            return this;
        }

        public JSONExportBuilder createdAt(Timestamp createdAt) {
            jSONExport.setCreatedAt(createdAt);
            return this;
        }

        public JSONExport build() {
            return jSONExport;
        }
    }
}
