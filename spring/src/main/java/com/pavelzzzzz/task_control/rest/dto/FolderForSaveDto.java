package com.pavelzzzzz.task_control.rest.dto;

import javax.validation.constraints.NotBlank;

public class FolderForSaveDto {

    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "ParentId is mandatory")
    private Integer parentId;

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

    public static FolderForSaveDtoBuilder builder() {
        return new FolderForSaveDtoBuilder();
    }


    public static final class FolderForSaveDtoBuilder {
        private FolderForSaveDto folderDto;

        private FolderForSaveDtoBuilder() {
            folderDto = new FolderForSaveDto();
        }

        public FolderForSaveDtoBuilder title(String title) {
            folderDto.setTitle(title);
            return this;
        }

        public FolderForSaveDtoBuilder parentId(Integer parentId) {
            folderDto.setParentId(parentId);
            return this;
        }

        public FolderForSaveDto build() {
            return folderDto;
        }
    }
}
