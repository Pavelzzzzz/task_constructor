package com.pavelzzzzz.task_control.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private String type;
    @Lob
    private byte[] data;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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

    public static FileBuilder builder() {
        return new FileBuilder();
    }

    public static final class FileBuilder {
        private File file;

        private FileBuilder() {
            file = new File();
        }

        public FileBuilder id(Integer id) {
            file.setId(id);
            return this;
        }

        public FileBuilder name(String name) {
            file.setName(name);
            return this;
        }

        public FileBuilder type(String type) {
            file.setType(type);
            return this;
        }

        public FileBuilder data(byte[] data) {
            file.setData(data);
            return this;
        }

        public FileBuilder folderId(Integer folderId) {
            file.setFolderId(folderId);
            return this;
        }

        public FileBuilder createdAt(Timestamp createdAt) {
            file.setCreatedAt(createdAt);
            return this;
        }

        public File build() {
            return file;
        }
    }
}
