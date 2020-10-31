package com.pavelzzzzz.task_control.rest.dto;

public class FolderForSaveDto {

    private String title;
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
