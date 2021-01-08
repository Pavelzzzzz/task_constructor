package com.pavelzzzzz.task_control.rest.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class FolderDto {

    private Integer id;
    private String title;
    private Integer parentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public static FolderDtoBuilder builder() {
        return new FolderDtoBuilder();
    }


    public static final class FolderDtoBuilder {
        private FolderDto folderDto;

        private FolderDtoBuilder() {
            folderDto = new FolderDto();
        }

        public FolderDtoBuilder id(Integer id) {
            folderDto.setId(id);
            return this;
        }

        public FolderDtoBuilder title(String title) {
            folderDto.setTitle(title);
            return this;
        }

        public FolderDtoBuilder parentId(Integer parentId) {
            folderDto.setParentId(parentId);
            return this;
        }

        public FolderDtoBuilder createdAt(Timestamp createdAt) {
            folderDto.setCreatedAt(createdAt);
            return this;
        }

        public FolderDtoBuilder updatedAt(Timestamp updatedAt) {
            folderDto.setUpdatedAt(updatedAt);
            return this;
        }

        public FolderDto build() {
            return folderDto;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderDto folderDto = (FolderDto) o;
        return Objects.equals(getId(), folderDto.getId()) &&
                Objects.equals(getTitle(), folderDto.getTitle()) &&
                Objects.equals(getParentId(), folderDto.getParentId()) &&
                Objects.equals(getCreatedAt(), folderDto.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), folderDto.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getParentId(), getCreatedAt(), getUpdatedAt());
    }
}
