package com.afriland.dsi.permanence.controllers.abstracts;

import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.interfaces.Model;
import com.afriland.dsi.permanence.services.abstracts.ModelService;

public abstract class ModelConvertController<J extends Model, D extends ModelDto, T extends ModelService<J>> extends ModelController<J, D, T> {
}
