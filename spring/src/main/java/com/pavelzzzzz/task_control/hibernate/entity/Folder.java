package com.pavelzzzzz.task_control.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Folder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String title;
    private Integer parentId;
    @NotNull
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer folderId) {
        this.id = folderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parent) {
        this.parentId = parent;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static FolderBuilder builder() {
        return new FolderBuilder();
    }

    public static final class FolderBuilder {
        private Folder folder;

        private FolderBuilder() {
            folder = new Folder();
        }

        public FolderBuilder id (Integer id) {
            folder.setId(id);
            return this;
        }

        public FolderBuilder title(String title) {
            folder.setTitle(title);
            return this;
        }

        public FolderBuilder parentId(Integer parent) {
            folder.setParentId(parent);
            return this;
        }

        public FolderBuilder createdAt(Timestamp createdAt) {
            folder.setCreatedAt(createdAt);
            return this;
        }

        public FolderBuilder updatedAt(Timestamp updatedAt) {
            folder.setUpdatedAt(updatedAt);
            return this;
        }

        public Folder build() {
            return folder;
        }
    }
}
