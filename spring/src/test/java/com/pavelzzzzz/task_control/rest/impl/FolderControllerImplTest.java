package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.FolderController;
import com.pavelzzzzz.task_control.rest.dto.FolderDto;
import com.pavelzzzzz.task_control.rest.dto.FolderForSaveDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
class FolderControllerImplTest {

    @Autowired
    private FolderController folderController;

    private FolderDto root;
    private FolderDto child;

    @BeforeEach
    void setUp() throws PocException {
        root = folderController.create(
                buildFolderForSaveDto("root", null));
        child = folderController.create(buildFolderForSaveDto("child", root.getId()));
    }

    @Test
    void list() {
        List<FolderDto> folders = folderController.list(0, 10, "ASC", "title");
        Assert.notNull(folders, "Must not be null");
        Assert.notEmpty(folders, "Must not be empty");
        Assertions.assertEquals(2, folders.size());
    }

    @Test
    void listByParentId() throws PocNotFoundException {
        List<FolderDto> childs = folderController.listByParentId(
                root.getId(), 0, 10, "ASC", "title");
        Assert.notNull(childs, "Must not be null");
        Assert.notEmpty(childs, "Must not be empty");
        Assertions.assertEquals(1, childs.size());
        Assertions.assertEquals(child, childs.iterator().next());
    }

    @Test()
    void listByParentIdExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                folderController.listByParentId(100, 0, 10, "ASC", "title"));
    }

    @Test
    void get() throws PocNotFoundException {
        FolderDto folderDto = folderController.get(child.getId());
        Assertions.assertEquals(child, folderDto);
    }

    @Test
    void getExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                folderController.get(100));
    }

    @Test
    void create() throws PocException {
        FolderForSaveDto folderForSaveDto = buildFolderForSaveDto("folder", root.getId());
        FolderDto folderDto = folderController.create(folderForSaveDto);
        Assert.notNull(folderDto, "Must not be null");
        Assert.notNull(folderDto.getId(), "Must not be null");
        Assert.notNull(folderDto.getCreatedAt(), "Must not be null");
        Assert.isNull(folderDto.getUpdatedAt(), "Must be null");
        Assertions.assertEquals(folderForSaveDto.getTitle(), folderDto.getTitle());
        Assertions.assertEquals(folderForSaveDto.getParentId(), folderDto.getParentId());
    }

    @Test
    void createWrongParentIdExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                folderController.create(buildFolderForSaveDto("folder", 100)));
    }

    @Test
    void update() throws PocException {
        FolderForSaveDto folderForSaveDto = buildFolderForSaveDto("folder", null);
        FolderDto folderDto = folderController.update(child.getId(), folderForSaveDto);
        Assert.notNull(folderDto, "Must not be null");
        Assertions.assertEquals(child.getId(), folderDto.getId());
        Assert.notNull(folderDto.getCreatedAt(), "Must not be null");
        Assert.notNull(folderDto.getUpdatedAt(), "Must not be null");
        Assertions.assertEquals(folderForSaveDto.getTitle(), folderDto.getTitle());
        Assertions.assertEquals(child.getParentId(), folderDto.getParentId());
    }

    @Test()
    void updateWrongIdExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                folderController.update(100, buildFolderForSaveDto("folder", null)));
    }

    @Test
    void delete() throws PocNotFoundException {
        folderController.delete(child.getId());
        try {
            folderController.get(child.getId());
            Assertions.fail();
        } catch (PocNotFoundException e) {
            Assertions.assertEquals("FolderDto with id = " + child.getId() + " not found.", e.getMessage());
        }
    }

    @Test()
    void deleteWrongIdExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                folderController.delete(100));
    }

    public static FolderForSaveDto buildFolderForSaveDto(String title, Integer parentId){
        return FolderForSaveDto.builder()
                .title(title)
                .parentId(parentId)
                .build();
    }
}