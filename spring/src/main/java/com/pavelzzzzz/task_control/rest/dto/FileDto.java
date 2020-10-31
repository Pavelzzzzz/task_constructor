package com.pavelzzzzz.task_control.rest.dto;

import java.sql.Timestamp;

public class FileDto {

    private Integer id;
    private String name;
    private String type;
    private long size;
    private Integer folderId;
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
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

    public static FileDtoBuilder builder() {
        return new FileDtoBuilder();
    }

    public static final class FileDtoBuilder {
        private FileDto fileDto;

        private FileDtoBuilder() {
            fileDto = new FileDto();
        }

        public FileDtoBuilder id(Integer id) {
            fileDto.setId(id);
            return this;
        }

        public FileDtoBuilder name(String name) {
            fileDto.setName(name);
            return this;
        }

        public FileDtoBuilder type(String type) {
            fileDto.setType(type);
            return this;
        }

        public FileDtoBuilder size(long size) {
            fileDto.setSize(size);
            return this;
        }

        public FileDtoBuilder folderId(Integer folderId) {
            fileDto.setFolderId(folderId);
            return this;
        }

        public FileDtoBuilder createdAt(Timestamp createdAt) {
            fileDto.setCreatedAt(createdAt);
            return this;
        }

        public FileDto build() {
            return fileDto;
        }
    }
}
