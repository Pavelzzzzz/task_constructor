package com.pavelzzzzz.task_control.service.api;

import com.pavelzzzzz.task_control.exception.PocException;

public interface EntityTransform<Dto, DtoForSave, Entity> {

  Dto fromEntity(Entity entity);

  Entity toEntity(DtoForSave dto, Entity oldEntity) throws PocException;
}
