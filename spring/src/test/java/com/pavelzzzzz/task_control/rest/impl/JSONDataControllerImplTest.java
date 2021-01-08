package com.pavelzzzzz.task_control.rest.impl;

import com.pavelzzzzz.task_control.exception.PocException;
import com.pavelzzzzz.task_control.exception.PocNotFoundException;
import com.pavelzzzzz.task_control.rest.api.FolderController;
import com.pavelzzzzz.task_control.rest.api.JSONDataController;
import com.pavelzzzzz.task_control.rest.dto.FolderDto;
import com.pavelzzzzz.task_control.rest.dto.JSONDataDto;
import com.pavelzzzzz.task_control.rest.dto.JSONDataForSaveDto;
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
class JSONDataControllerImplTest {

    @Autowired
    private JSONDataController jsonDataController;
    @Autowired
    private FolderController folderController;

    private FolderDto rootFolder;
    private JSONDataDto jsonDataDto;

    @BeforeEach
    void setUp() throws PocException {
        rootFolder = folderController.create(
                FolderControllerImplTest.buildFolderForSaveDto("root", null));
        jsonDataDto = jsonDataController.create(
                buildJSONDataForSaveDto("JSON title", "{some : JSON body}", rootFolder.getId()));
    }

    @Test
    void get() throws PocNotFoundException {
        JSONDataDto jsonData = jsonDataController.get(jsonDataDto.getId());
        Assertions.assertEquals(jsonDataDto, jsonData);
    }

    @Test
    void getExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                jsonDataController.get(100));
    }

    @Test
    void list() {
        List<JSONDataDto> jsonDataDtos = jsonDataController.list(0, 10, "ASC", "title");
        Assert.notNull(jsonDataDtos, "Must not be null");
        Assert.notEmpty(jsonDataDtos, "Must not be empty");
        Assertions.assertEquals(1, jsonDataDtos.size());
    }

    @Test
    void create() throws PocException {
        JSONDataForSaveDto JSONDataForSaveDto = buildJSONDataForSaveDto("title", "{some : JSON body2}", rootFolder.getId());
        JSONDataDto jsonDataDto = jsonDataController.create(JSONDataForSaveDto);
        Assert.notNull(jsonDataDto, "Must not be null");
        Assert.notNull(jsonDataDto.getId(), "Must not be null");
        Assert.notNull(jsonDataDto.getCreatedAt(), "Must not be null");
        Assertions.assertEquals(JSONDataForSaveDto.getTitle(), jsonDataDto.getTitle());
        Assertions.assertEquals(JSONDataForSaveDto.getBody(), jsonDataDto.getBody());
        Assertions.assertEquals(JSONDataForSaveDto.getFolderId(), jsonDataDto.getFolderId());
    }

    @Test
    void createWrongFolderIdExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                jsonDataController.create(buildJSONDataForSaveDto("title", "{some : JSON body2}", 100)));
    }

    @Test
    void delete() throws PocNotFoundException {
        jsonDataController.delete(jsonDataDto.getId());
        try {
            jsonDataController.get(jsonDataDto.getId());
            Assertions.fail();
        } catch (PocNotFoundException e) {
            Assertions.assertEquals("JSONDataDto with id = " + jsonDataDto.getId() + " not found.", e.getMessage());
        }
    }

    @Test()
    void deleteWrongIdExpectedPocNotFoundException() {
        Assertions.assertThrows(PocNotFoundException.class, () ->
                jsonDataController.delete(100));
    }

    public JSONDataForSaveDto buildJSONDataForSaveDto(String title, String body, Integer folderId){
        return JSONDataForSaveDto.builder()
                .title(title)
                .body(body)
                .folderId(folderId)
                .build();
    }
}