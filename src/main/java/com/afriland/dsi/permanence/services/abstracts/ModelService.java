package com.afriland.dsi.permanence.services.abstracts;

import com.afriland.dsi.permanence.controllers.abstracts.ModelController;
import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.interfaces.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface  ModelService<J extends Model>  {

    public J create(J model);

    public J update(J modelUpdate, Long id);

    public List<J> getAllModel();

    public J getModelById(Long id);

    public boolean delete(Long id);

}
