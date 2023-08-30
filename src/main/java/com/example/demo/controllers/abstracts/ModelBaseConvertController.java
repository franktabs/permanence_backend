package com.example.demo.controllers.abstracts;

import com.example.demo.dto.interfaces.ModelDto;
import com.example.demo.entities.interfaces.Model;
import com.example.demo.services.abstracts.ModelService;

public abstract class ModelBaseConvertController<J extends Model, D extends ModelDto, T extends ModelService<J>> extends ModelBaseController<J, D, T> implements ModelConvertController {
}
